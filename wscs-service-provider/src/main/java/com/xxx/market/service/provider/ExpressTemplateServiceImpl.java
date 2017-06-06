package com.xxx.market.service.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.market.model.ExpressComp;
import com.xxx.market.model.ExpressImg;
import com.xxx.market.model.ExpressTemplate;
import com.xxx.market.service.api.authuser.AuthUserService;
import com.xxx.market.service.api.exception.MarketBaseException;
import com.xxx.market.service.api.order.ExpTemplateService;
import com.xxx.market.service.api.order.ExpTplSaveParamDto;
import com.xxx.market.service.api.order.OrderException;
import com.xxx.market.service.api.order.PalletElementResultDto;
import com.jfinal.kit.StrKit;

@Service("expressTemplateService")
public class ExpressTemplateServiceImpl implements ExpTemplateService{

	@Autowired
	private AuthUserService authUserService;
	
	@Override
	public List<ExpressComp> getUserExpComps(Long sellerId) throws OrderException {
		if(sellerId == null) throw new OrderException("getUserExpComps 接口sellerId 参数必须");
		//系统所有的快递公司
		List<ExpressComp> expressComps =  ExpressComp.dao.find("select * from " + ExpressComp.table);
		//获取用户已添加的快递模板
		List<ExpressTemplate> templates = ExpressTemplate.dao.find
				("select * from " + ExpressTemplate.table + " where seller_id=? and active = 1", sellerId);
		List<ExpressComp> resultComp = new ArrayList<ExpressComp>();
		for(ExpressComp expComp : expressComps){
			boolean has = false;
			for(ExpressTemplate etemp : templates){
				if(expComp.getExpKey().equals(etemp.getExpKey()))
					has = true;
			}
			if(!has) resultComp.add(expComp);
		}
		return resultComp;
	}

	@Override
	public List<ExpressImg> getExpTemplateBackImage(String expKey) throws OrderException {
		if(StrKit.isBlank(expKey)) throw new OrderException("getExpTemplateBackImage 接口参数错误");
		return ExpressImg.dao.find("select * from " + ExpressImg.table + " where exp_key=? ", expKey);
	}

	@Override
	public ExpressTemplate getUserExpTemplateByKey(String key, Long sellerId) throws OrderException {
		if(StrKit.isBlank(key) || sellerId == null) throw new OrderException("getUserExpTemplateByKey 接口参数错误");
		
		ExpressTemplate expTpl = ExpressTemplate.dao.findFirst("select * from " + ExpressTemplate.table + " where "
				+ " seller_id=? and exp_key = ? ", sellerId, key);
		
		if(expTpl == null){
			//用户自定义的模板为空的情况下，查询出公共模板
			expTpl = ExpressTemplate.dao.findFirst("select * from " + ExpressTemplate.table + " where exp_key=? ", key);
		}
		
		if(expTpl == null)
			throw new OrderException("模板不存在");
		
		return expTpl;
	}

	@Override
	public ExpressTemplate saveTemplate(ExpTplSaveParamDto expTemplateDto) throws OrderException {
		if(expTemplateDto == null || expTemplateDto.getSellerId() == null 
				|| StrKit.isBlank(expTemplateDto.getExpkey()) || StrKit.isBlank(expTemplateDto.getExpname())
				|| StrKit.isBlank(expTemplateDto.getExpbgimg()))
			throw new OrderException("saveTemplate 接口参数错误");
		Long sellerId = expTemplateDto.getSellerId();
		String expname = expTemplateDto.getExpname();
		String expkey = expTemplateDto.getExpkey();
		String expbgimg = expTemplateDto.getExpbgimg();
		String tplcontent = expTemplateDto.getTplcontent();
		if(StrKit.isBlank(tplcontent)) throw new OrderException("模板内容为空");
		String designhtml = expTemplateDto.getDesignhtml();
		if(StrKit.isBlank(designhtml)) throw new OrderException("设计内容为空");
		int pagewidth = expTemplateDto.getPagewidth();
		int pageheight = expTemplateDto.getPageheight();
		int offsetx = expTemplateDto.getOffsetx();
		int offsety = expTemplateDto.getOffsety();
		ExpressTemplate etpl = ExpressTemplate.dao.findFirst("select * from " + ExpressTemplate.table + " where exp_key=? and seller_id=?", expkey, sellerId);
		if(etpl==null){
			etpl = new ExpressTemplate();
			etpl.setExpName(expname.trim());
			etpl.setExpKey(expkey);
			etpl.setSellerId(sellerId);
			etpl.setActive(1);
			etpl.setCreated(new Date());
			etpl.setUpdated(new Date());
			etpl.setExpBgimg(expbgimg);
			etpl.setExpTplcontent(tplcontent);
			etpl.setExpDesignhtml(designhtml);
			etpl.setPagewidth(pagewidth);
			etpl.setPageheight(pageheight);
			etpl.setOffsetx(offsetx);
			etpl.setOffsety(offsety);
			etpl.save();
		}else {
			etpl.setActive(1);
			etpl.setExpBgimg(expbgimg);
			etpl.setExpDesignhtml(designhtml);
			etpl.setExpTplcontent(tplcontent);
			etpl.setPagewidth(pagewidth);
			etpl.setPageheight(pageheight);
			etpl.setOffsetx(offsetx);
			etpl.setOffsety(offsety);
			etpl.setUpdated(new Date());
			etpl.update();
		}
		return etpl;
	}
	

	@Override
	public void delTemplate(String expkey, Long sellerId) throws OrderException {
		if(StrKit.isBlank(expkey) || sellerId == null) throw new OrderException("删除模板参数错误");
		
		ExpressTemplate etpl = ExpressTemplate.dao.findFirst("select * from " + ExpressTemplate.table + " where exp_key=? and seller_id=?", expkey, sellerId);
		if(etpl != null){
			etpl.setActive(0);
			etpl.update();
		}
	}

	@Override
	public Map<String, List<PalletElementResultDto>> initPalletElement(Long sellerId) {
		Map<String, List<PalletElement>> elementCategoryMap = new LinkedHashMap<String, List<PalletElement>>();
		
		List<PalletElement> elements = new ArrayList<PalletElement>();
		PalletElement element = new PalletElement("寄件人姓名", "contact_name");
		elements.add(element);
		element = new PalletElement("寄件人手机", "phone");
		elements.add(element);
		element = new PalletElement("寄件人邮编", "zip_code");
		elements.add(element);
		element = new PalletElement("寄件人所在省", "province");
		elements.add(element);
		element = new PalletElement("寄件人所在市", "city");
		elements.add(element);		
		element = new PalletElement("寄件人所在区/县", "country");
		elements.add(element);
		element = new PalletElement("寄件人详细地址", "addr");
		elements.add(element);
		element = new PalletElement("寄件人公司", "seller_company");
		elements.add(element);
		element = new PalletElement("寄件人备注", "memo");
		elements.add(element);
		elementCategoryMap.put("寄件人信息", elements);

		elements = new ArrayList<PalletElement>();
		element = new PalletElement("收件人姓名", "receiverName");
		elements.add(element);
		element = new PalletElement("收件人手机", "receiverPhone");
		elements.add(element);
		element = new PalletElement("收件人邮编", "zipCode");
		elements.add(element);
		element = new PalletElement("收件人所在省", "receiverProvince");
		elements.add(element);
		element = new PalletElement("收件人所在市", "receiverCity");
		elements.add(element);
		element = new PalletElement("收件人所在区/县", "receiverCountry");
		elements.add(element);
		element = new PalletElement("收件人所在地址", "receiverAddr");
		elements.add(element);
		element = new PalletElement("买家昵称", "buyerNick");
		elements.add(element);
		element = new PalletElement("买家留言", "buyerMemo");
		elements.add(element);
		elementCategoryMap.put("收件人信息", elements);
		
		elements = new ArrayList<PalletElement>();
		element = new PalletElement("订单编号", "orderSn");
		elements.add(element);
		element = new PalletElement("下单时间", "orderCreated");
		elements.add(element);
		/*element = new PalletElement("宝贝名称", "title");
		elements.add(element);
		element = new PalletElement("商家编码", "outerIid");
		elements.add(element);
		element = new PalletElement("宝贝属性", "skuPropertiesName");
		elements.add(element);*/
		element = new PalletElement("运费", "postFee");
		elements.add(element);
		element = new PalletElement("订单总价", "totalPrice");
		elements.add(element);
		/*element = new PalletElement("代收金额", "expressAgencyFee");
		elements.add(element);
		element = new PalletElement("商品总量", "num");
		elements.add(element);*/
		elementCategoryMap.put("订单信息", elements);

		/*elements = new ArrayList<PalletElement>();
		element = new PalletElement("打印时间", "printDate");
		elements.add(element);
		element = new PalletElement("打勾", "duigou");
		elements.add(element);
		elementCategoryMap.put("系统信息", elements);*/
		
		//公众号
		elements = new ArrayList<PalletElement>();
		element = new PalletElement("公众号名称", "nick_name");
		elements.add(element);
		try {
			String qrcodeUrl = authUserService.getUsedAuthUser(sellerId).getQrcodeUrl();
			element = new PalletElement("公众号二维码", "qrcode_url", "img", qrcodeUrl);
			elements.add(element);
		} catch (MarketBaseException e) {
			e.printStackTrace();
		}
		
		element = new PalletElement("公众号账号主体", "principal_name");
		elements.add(element);
		elementCategoryMap.put("公众号信息", elements);
				
		return convert2ElementDtoMap(elementCategoryMap);
	}
	
	private Map<String, List<PalletElementResultDto>> convert2ElementDtoMap(Map<String, List<PalletElement>> elementCategoryMap){
		Map<String, List<PalletElementResultDto>> elementCategoryDtoMap = new HashMap<String, List<PalletElementResultDto>>();
		for(String elementKey : elementCategoryMap.keySet()){
			List<PalletElement> elementsInMap = elementCategoryMap.get(elementKey);
			List<PalletElementResultDto> elementDtos = new ArrayList<PalletElementResultDto>();
			for(PalletElement pe : elementsInMap){
				PalletElementResultDto peDto = new PalletElementResultDto();
				peDto.setImgSrc(pe.getImgSrc());
				peDto.setKey(pe.getKey());
				peDto.setLabel(pe.getLabel());
				peDto.setText(pe.getText());
				peDto.setType(pe.getType());
				elementDtos.add(peDto);
			}
			elementCategoryDtoMap.put(elementKey, elementDtos);
		}
		return elementCategoryDtoMap;
	}
}
