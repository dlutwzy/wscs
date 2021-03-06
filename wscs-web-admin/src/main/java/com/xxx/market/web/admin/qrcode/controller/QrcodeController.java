package com.xxx.market.web.admin.qrcode.controller;

import com.xxx.market.web.core.annotation.RouteBind;
import com.xxx.market.web.core.controller.BaseController;
import com.xxx.market.web.core.render.Base64ImageRender;
import com.xxx.market.web.core.render.QrcodeRender;

@RouteBind(path="qrcode")
public class QrcodeController extends BaseController{
	
	//生成二维码图片流
	public void genio(){
		final String content = getPara("url");
	    render(new QrcodeRender(content));
	}
	
	public void base64(){
		final String content = getPara("url");
		render(new Base64ImageRender(content));
	}
	
}
