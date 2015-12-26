package com.myfood.dto;

public class AdvertisementDTO {
	AdvertisementInfo advertisementInfo ;
	FileInfo fileInfo;
	AdvtBusinessInfo businessInfo;
	PaymentInfo paymentInfo ;
	
	public AdvertisementInfo getAdvertisementInfo() {
		return advertisementInfo;
	}
	public void setAdvertisementInfo(AdvertisementInfo advertisementInfo) {
		this.advertisementInfo = advertisementInfo;
	}
	public FileInfo getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	public AdvtBusinessInfo getBusinessInfo() {
		return businessInfo;
	}
	public void setBusinessInfo(AdvtBusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}
	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	
}
