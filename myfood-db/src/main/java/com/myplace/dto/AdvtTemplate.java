package com.myfood.dto;

public class AdvtTemplate {
	
	private String advtType;
	private String advtUrl;
	private Byte status;
	private String templateType;

	public String getAdvtType() {
		return advtType;
	}
	public void setAdvtType(String advtType) {
		this.advtType = advtType;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	
	
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	
	public String getAdvtUrl() {
		return advtUrl;
	}
	public void setAdvtUrl(String advtUrl) {
		this.advtUrl = advtUrl;
	}
	

}
