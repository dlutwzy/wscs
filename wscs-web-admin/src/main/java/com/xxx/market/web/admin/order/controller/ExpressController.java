package com.xxx.market.web.admin.order.controller;

import java.util.List;

import com.xxx.market.model.ExpressImg;
import com.xxx.market.model.ExpressTemplate;
import com.xxx.market.service.api.order.ExpTemplateService;
import com.xxx.market.service.api.order.ExpTplSaveParamDto;
import com.xxx.market.service.api.order.OrderException;
import com.xxx.market.web.core.annotation.RouteBind;
import com.xxx.market.web.core.controller.BaseController;
import com.xxx.market.web.core.plugin.spring.Inject.BY_NAME;
import com.jfinal.kit.StrKit;

@RouteBind(path = "express")
public class ExpressController extends BaseController{

	@BY_NAME
	private ExpTemplateService expressTemplateService;
	
	/**
	 * 获取用户支持的快递公司
	 */
	public void comps(){
		setAttr("expresscomps", expressTemplateService.getUserExpComps(getSellerId()));
		render("/order/exp_list.html");
	}
	
	public void getTplBgImagesByCompkey(){
		String expkey = getPara("expkey");
		try {
			List<ExpressImg> expressImgs = expressTemplateService.getExpTemplateBackImage(expkey); 
			rendSuccessJson(expressImgs);	
		} catch (OrderException e) {
			rendFailedJson(e.getMessage());
		}
		
	}
	
	public void getUserTplByKey(){
		try {
			ExpressTemplate expTemplate = expressTemplateService.getUserExpTemplateByKey(getPara("expKey"), getSellerId());
			rendSuccessJson(expTemplate);
		} catch (OrderException e) {
			rendFailedJson(e.getMessage());
		}
	}
	
	public void delTemplate(){
		try {
			expressTemplateService.delTemplate(getPara("expKey"), getSellerId());
			rendSuccessJson();
		} catch (OrderException e) {
			rendFailedJson(e.getMessage());
		}
	}
	
	public void saveTemplate(){
		String expkey = getPara("expkey");
		if("".equals(expkey) || expkey==null){
			rendFailedJson("您还没有选择快递公司!");
			return;
		}
		String expname = getPara("expname");
		String expbgimg = getPara("expbgimg");
		if(StrKit.isBlank(expname) || StrKit.isBlank(expbgimg)){
			rendFailedJson("快递名称跟背景图不能为空");
			return;
		}
		
		Long sellerId = getSellerId();
		String tplcontent = getPara("tplcontent");
		String designhtml = getPara("designhtml");
		int pagewidth = getParaToInt("pagewidth", 0);
		int pageheight = getParaToInt("pageheight", 0);
		int offsetx = getParaToInt("offsetx", 0);
		int offsety = getParaToInt("offsety", 0);
		
		ExpTplSaveParamDto expTemplateSaveParamDto = new ExpTplSaveParamDto(expkey, expname, expbgimg, sellerId);
		expTemplateSaveParamDto.setTplcontent(tplcontent);
		expTemplateSaveParamDto.setDesignhtml(designhtml);
		expTemplateSaveParamDto.setPagewidth(pagewidth);
		expTemplateSaveParamDto.setPageheight(pageheight);
		expTemplateSaveParamDto.setOffsetx(offsetx);
		expTemplateSaveParamDto.setOffsety(offsety);
		
		try {
			rendSuccessJson(expressTemplateService.saveTemplate(expTemplateSaveParamDto));
		} catch (OrderException e) {
			rendFailedJson(e.getMessage());
		}
		
	}
	
	public void setBgImage(){
		String expkey = getPara("expKey");
		List<ExpressImg> expressImgs = expressTemplateService.getExpTemplateBackImage(expkey);
		setAttr("expImgs", expressImgs);
		render("/order/exp_change_bg.html");
	}
	
	public void initPallet(){
		setAttr("elementCategoryMap", expressTemplateService.initPalletElement(getSellerId()));
		render("/order/exp_tpl_setting.html");
	}
		
}
