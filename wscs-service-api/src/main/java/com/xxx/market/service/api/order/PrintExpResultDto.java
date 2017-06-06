package com.xxx.market.service.api.order;

import java.util.List;

import com.xxx.market.model.ExpressTemplate;
import com.xxx.market.service.common.AbstractResultDto;

@SuppressWarnings("serial")
public class PrintExpResultDto extends AbstractResultDto{

	List<PrintExpItemResultDto> printData;	//打印数据
	ExpressTemplate expressTemplate;		//快递模板数据
	
	public List<PrintExpItemResultDto> getPrintData() {
		return printData;
	}

	public void setPrintData(List<PrintExpItemResultDto> printData) {
		this.printData = printData;
	}

	public ExpressTemplate getExpressTemplate() {
		return expressTemplate;
	}

	public void setExpressTemplate(ExpressTemplate expressTemplate) {
		this.expressTemplate = expressTemplate;
	}

}
