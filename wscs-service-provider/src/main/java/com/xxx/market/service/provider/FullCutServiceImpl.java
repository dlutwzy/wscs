package com.xxx.market.service.provider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.market.model.FullCut;
import com.xxx.market.model.FullCutProduct;
import com.xxx.market.model.FullCutSet;
import com.xxx.market.model.Product;
import com.xxx.market.service.api.product.ProductException;
import com.xxx.market.service.api.product.ProductFullCutResultDto;
import com.xxx.market.service.api.product.ProductParamDto;
import com.xxx.market.service.api.product.ProductResultDto;
import com.xxx.market.service.api.ump.FullCutParamDto;
import com.xxx.market.service.api.ump.FullCutService;
import com.xxx.market.service.api.ump.PromotionParamDto;
import com.xxx.market.service.api.ump.UmpException;
import com.xxx.market.service.base.AbstractServiceImpl;
import com.xxx.market.service.utils.DateTimeUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
@Service("fullCutService")
public class FullCutServiceImpl extends AbstractServiceImpl implements FullCutService{

	@Override
	@Transactional(rollbackFor = UmpException.class)
	public FullCut save(FullCutParamDto paramDto) throws UmpException {
		if(paramDto == null || paramDto.getSellerId() == null) throw new UmpException("保存出错,请检查参数");
		FullCut fullCut=new FullCut();
		fullCut.setId(paramDto.getId());
		fullCut.setFullCutName(paramDto.getFullCutName());
		fullCut.setStartDate(paramDto.getStartDate());
		fullCut.setEndDate(paramDto.getEndDate());
		fullCut.setSellerId(paramDto.getSellerId());
		fullCut.setProductIds(paramDto.getProductIds());
		if(fullCut.getId()==null){
			fullCut.setActive(true);
			fullCut.save();
			String productids=paramDto.getProductIds();
			String productIds[]=(productids.substring(0,productids.length()-1)).split("-");
			for (int i = 0; i < productIds.length; i++) {
				FullCutProduct p=new FullCutProduct();
				p.setProductId(new Long(productIds[i]));
				p.setFullCutId(fullCut.getId());
				try {
					p.save();	
				} catch (UmpException e) {
					throw new UmpException("请设置满减项");
				}

			}
		}else{
			fullCut.update();
			Db.deleteById(FullCutProduct.table,"full_cut_id",fullCut.getId());
			String productids=paramDto.getProductIds();
			String productIds[]=(productids.substring(0,productids.length()-1)).split("-");
			for (int i = 0; i < productIds.length; i++) {
				FullCutProduct p=new FullCutProduct();
				p.setProductId(new Long(productIds[i]));
				p.setFullCutId(fullCut.getId());
				try {
					p.save();	
				} catch (UmpException e) {
					throw new UmpException("请设置满减项");
				}
			}
		}
		JSONArray jarr = JSONArray.parseArray(paramDto.getSetItem());
		if(jarr == null || jarr.size()<=0) throw new UmpException("请设置满减项");
		
		 for (Iterator<?> iterator = jarr.iterator(); iterator.hasNext();) { 
	          JSONObject job = (JSONObject) iterator.next(); 
	          FullCutSet fullCutset=new FullCutSet();
	          fullCutset.setId(job.getLong("id"));
	          fullCutset.setFullCutId(fullCut.getId());
	          fullCutset.setMeet(job.getBigDecimal("meet"));
	          if(job.getInteger("cash_required")==null){
	        	  fullCutset.setCashRequired(0);
	          }else{
	          fullCutset.setCashRequired(job.getInteger("cash_required"));
	          }
	          if(job.getInteger("postage")==null){
	        	  fullCutset.setPostage(0); 
	          }else{
	          fullCutset.setPostage(job.getInteger("postage"));
	          }
	          fullCutset.setCash(job.getBigDecimal("cash"));
	          if(fullCutset.getId()==null){
	        	  fullCutset.setActive(true);
	        	  fullCutset.save();
	          }else{
	        	  fullCutset.update();  
	          }
		 }
		return fullCut; 
	}

	@Override
	public Page<ProductResultDto> getProducts4FullCutPage(ProductParamDto productParamDto) throws ProductException {
		if(productParamDto == null || productParamDto.getSellerId() == null) throw new ProductException("获取未打折商品列表出错");
		List<ProductResultDto> resultDtos = new ArrayList<ProductResultDto>();
		
		//查询出商品有效满减送数据
		Date currDate = new Date();
		final String select = "select * ";
		final String sqlExceptSelect = " from "+Product.table+" where seller_id=? and is_marketable=1 and active=1 and id not in"
				+ " (select product_id from " + FullCutProduct.table + " where full_cut_id "
				+ "in (select id from "+FullCut.table+" where seller_id=? and start_date<=? and end_date >=? ))";
		Page<Product> pages = Product.dao.paginate(productParamDto.getPageNo(), productParamDto.getPageSize(),
				select, sqlExceptSelect,
				productParamDto.getSellerId(), productParamDto.getSellerId(), currDate, currDate);
		for(Product product : pages.getList()){
			ProductResultDto resultDto = new ProductResultDto();
			resultDto.setId(product.getId());
			resultDto.setImg(getImageDomain() + product.getImage());
			resultDto.setName(product.getName());
			resultDto.setPrice(product.getPrice());
			resultDto.setStock(product.getStock());
			resultDtos.add(resultDto);
		}
	
		return new Page<ProductResultDto>(resultDtos, pages.getPageNumber(), pages.getPageSize(), pages.getTotalPage(), pages.getTotalRow());
	}

	@Override
	public Page<FullCut> list(PromotionParamDto promotionParam) throws UmpException {
		if(promotionParam == null || promotionParam.getSellerId() == null)
			throw new UmpException("获取满减送列表数据参数错误");
		Page<FullCut> pages = FullCut.dao.paginate(promotionParam.getPageNo(), promotionParam.getPageSize(),
				"select * ", 
				" from " + FullCut.table + " where seller_id=? ", promotionParam.getSellerId());
		List<FullCut> lists=new ArrayList<FullCut>();
		for (FullCut fullCut : pages.getList()) {
			lists.add(fullCut);
		}
		return new Page<FullCut> (lists, promotionParam.getPageNo(), promotionParam.getPageSize(), pages.getTotalPage(), pages.getTotalRow());
	}

	@Override
	public List<ProductFullCutResultDto> getProductFullCut(Product product) throws UmpException {
		List<ProductFullCutResultDto> resultDtos=new ArrayList<ProductFullCutResultDto>();
		String sql="select f.start_date,f.end_date,s.meet,s.cash,s.cash_required,s.postage from t_full_cut f LEFT JOIN t_full_cut_set s on "
				   +" f.id=s.full_cut_id where f.id in "
				   +"(select p.full_cut_id from t_full_cut_product p where p.product_id=? ) and f.seller_id=? and f.start_date<=? and f.end_date>=?";
		List<Record> rocords=Db.find(sql,product.getId(),product.getSellerId(), new Date(), new Date());
		for (Record record : rocords) {
			ProductFullCutResultDto profcrd=new ProductFullCutResultDto();
			Date startDate=record.getDate("start_date");
			Date endDate=record.getDate("end_date");
			BigDecimal meet=record.getBigDecimal("meet");
			BigDecimal cash=record.getBigDecimal("cash");
			int cashRequired=record.getInt("cash_required");
			int postage=record.getInt("postage");
			profcrd.setMeet(meet);
			profcrd.setCash(cash);
			profcrd.setPostage(postage);
			if(cashRequired==1&&postage==1){
				profcrd.setFullCutInfo("满￥"+meet+"减"+cash+"+包邮");
			}else if(cashRequired==1&&postage!=1){
				profcrd.setFullCutInfo("满￥"+meet+"减"+cash+"");
			}else if(cashRequired!=1&&postage==1){
				profcrd.setFullCutInfo("满￥"+meet+"包邮"); 
			}
			 
			if(startDate.after(new Date())){
				profcrd.setFullCutTime("还差" + DateTimeUtil.compareDay(startDate, new Date()) + "天开始"); 
			}else{
				profcrd.setFullCutTime("剩余" + DateTimeUtil.compareDay(endDate, new Date()) + "天结束");
			}
			 
			resultDtos.add(profcrd);
		}
		return resultDtos;
	}

}
