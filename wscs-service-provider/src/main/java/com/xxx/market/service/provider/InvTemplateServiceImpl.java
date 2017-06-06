package com.xxx.market.service.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.market.model.InvoiceTemplate;
import com.xxx.market.service.api.authuser.AuthUserService;
import com.xxx.market.service.api.exception.MarketBaseException;
import com.xxx.market.service.api.order.InvTemplateService;
import com.xxx.market.service.api.order.InvTplSaveParamDto;
import com.xxx.market.service.api.order.OrderException;
import com.xxx.market.service.api.order.PalletElementResultDto;
import com.jfinal.kit.StrKit;

@Service("invTemplateService")
public class InvTemplateServiceImpl implements InvTemplateService {
	
	@Autowired
	private AuthUserService authUserService;
	
	@Override
	public InvoiceTemplate getUserTpl(Long sellerId) throws OrderException {
		if(sellerId == null) throw new OrderException("获取用户模板接口sellerId必传");
		
		InvoiceTemplate invoiceTemplate = InvoiceTemplate.dao.findFirst("select * from " + InvoiceTemplate.table + " where seller_id=? ", sellerId);
		if(invoiceTemplate == null){
			invoiceTemplate = InvoiceTemplate.dao.findFirst("select * from " + InvoiceTemplate.table + " where seller_id is null");
		}
		return invoiceTemplate;
	}

	@Override
	public InvoiceTemplate save(InvTplSaveParamDto tplParamDto) throws OrderException {
		if(tplParamDto == null || tplParamDto.getSellerId() == null) throw new OrderException("调用保存发货单模板接口参数错误");
		
		if(StrKit.isBlank(tplParamDto.getTplcontent())) throw new OrderException("模板内容不能为空");
		if(StrKit.isBlank(tplParamDto.getDesignhtml())) throw new OrderException("请设计发货单模板");
		if(StrKit.isBlank(tplParamDto.getTablehtml())) throw new OrderException("表格模板不能为空");
		
		InvoiceTemplate invoicetpl = InvoiceTemplate.dao.findFirst("select * from " + InvoiceTemplate.table + " where seller_id=? ", tplParamDto.getSellerId());
		
		if(invoicetpl == null){
			invoicetpl = new InvoiceTemplate();
			invoicetpl.setSellerId(tplParamDto.getSellerId());
			invoicetpl.setCreated(new Date());
			invoicetpl.setActive(1);
		}
		
		invoicetpl.setInvTplcontent(tplParamDto.getTplcontent());
		invoicetpl.setInvDesignhtml(tplParamDto.getDesignhtml());
		invoicetpl.setInvTabelhtml(tplParamDto.getTablehtml());
		invoicetpl.setInvImgDesignhtml(tplParamDto.getImghtml());
		invoicetpl.setPageheight(tplParamDto.getPageheight());
		invoicetpl.setPagewidth(tplParamDto.getPagewidth());
		invoicetpl.setOffsetx(tplParamDto.getOffsetx());
		invoicetpl.setOffsety(tplParamDto.getOffsety());
		invoicetpl.setUpdated(new Date());
		
		if(invoicetpl.getId() == null){
			invoicetpl.save();
		}else{
			invoicetpl.update();
		}
		
		return invoicetpl;
	}
	
	@Override
	public Map<String, List<PalletElementResultDto>> initPalletElement(Long sellerId) throws OrderException {
		if(sellerId == null) throw new OrderException("请传入参数");
		
		Map<String, List<PalletElement>> elementCategoryMap = new HashMap<String, List<PalletElement>>();
		
		//订单
		List<PalletElement> elements = new ArrayList<PalletElement>();
		PalletElement element = new PalletElement("收件人", "receiverName", "收件人:");
		elements.add(element);
		element = new PalletElement("收件人手机", "receiverPhone", "收件人手机:");
		elements.add(element);
		element = new PalletElement("收件人所在省", "receiverProvince", "收件人所在省:");
		elements.add(element);		
		element = new PalletElement("收件人所在市", "receiverCity", "收件人所在市:");
		elements.add(element);
		element = new PalletElement("收件人所在区/县", "receiverCountry", "收件人所在区/县:");
		elements.add(element);
		element = new PalletElement("收件人详细地址", "receiverAddr", "收件人详细地址:");
		elements.add(element);
		element = new PalletElement("下单时间", "orderCreated", "下单时间:");
		elements.add(element);
		element = new PalletElement("买家昵称", "buyerNick", "买家:");
		elements.add(element);
		element = new PalletElement("买家留言", "buyerMemo", "留言:");
		elements.add(element);
		element = new PalletElement("订单编号", "orderSn", "订单编号:");
		elements.add(element);
		elementCategoryMap.put("订单信息", elements);

		//寄件人
		elements = new ArrayList<PalletElement>();
		element = new PalletElement("寄件人姓名", "contact_name", "寄件人:");
		elements.add(element);
		element = new PalletElement("寄件人手机", "phone", "寄件人手机:");
		elements.add(element);
		element = new PalletElement("寄件人邮编", "zip_code", "寄件人邮编:");
		elements.add(element);
		element = new PalletElement("寄件人所在省", "province");
		elements.add(element);
		element = new PalletElement("寄件人所在市", "city");
		elements.add(element);		
		element = new PalletElement("寄件人所在区/县", "country");
		elements.add(element);
		element = new PalletElement("寄件人详细地址", "addr", "寄件人详细地址:");
		elements.add(element);
		element = new PalletElement("寄件人公司", "seller_company", "寄件人公司:");
		elements.add(element);
		element = new PalletElement("寄件人备注", "memo", "寄件人备注:");
		elements.add(element);
		elementCategoryMap.put("寄件人信息", elements);
		
		//公众号
		elements = new ArrayList<PalletElement>();
		element = new PalletElement("公众号名称", "nick_name", "发货单");
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

	@Override
	public Map<String, List<PalletElementResultDto>> initTableColumnElement(Long sellerId) throws OrderException {
		Map<String, List<PalletElement>> elementCategoryMap = new HashMap<String, List<PalletElement>>();
		List<PalletElement> elements = new ArrayList<PalletElement>();
		PalletElement element = new PalletElement("宝贝名称", "title", "宝贝名称");
		elements.add(element);
		element = new PalletElement("宝贝属性", "skuPropertiesName", "宝贝属性");
		elements.add(element);
		element = new PalletElement("属性商家编码", "price", "属性商家编码");
		elements.add(element);
		element = new PalletElement("数量", "num", "数量");
		elements.add(element);
		element = new PalletElement("单价", "price");
		elements.add(element);
		element = new PalletElement("优惠金额", "payment", "优惠金额");
		elements.add(element);
		element = new PalletElement("金额", "price", "金额");
		elements.add(element);
		element = new PalletElement("合计", "total", "合计");
		elements.add(element);
		elementCategoryMap.put("选择显示的列", elements);
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
