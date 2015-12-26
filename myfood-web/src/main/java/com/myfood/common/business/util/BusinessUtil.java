package com.myfood.common.business.util;

import com.myfood.dto.BusinessInfo;

public class BusinessUtil {

	
	 public static void updateBusinessInfo(BusinessInfo businessInfoObj, BusinessInfo businessInfo) {
			if (null != businessInfoObj && null != businessInfo) {
				if(null!=businessInfo.getBusinessFileInfo()){
					businessInfoObj.setBusinessFileInfo(businessInfo.getBusinessFileInfo());
				}
				
				if(null!=businessInfo.getBussName()){
					businessInfoObj.setBussName(businessInfo.getBussName());
				}
				if(null!=businessInfo.getBussAddress()){
					businessInfoObj.setBussAddress(businessInfo.getBussAddress());
				}
				if(null!=businessInfo.getBussCity()){
					businessInfoObj.setBussCity(businessInfo.getBussCity());
				}
				if(null!=businessInfo.getBussState()){
					businessInfoObj.setBussState(businessInfo.getBussState());
				}
				if(null!=businessInfo.getBussCountry()){
					businessInfoObj.setBussCountry(businessInfo.getBussCountry());
				}
				if(null!=businessInfo.getBussZip()){
					businessInfoObj.setBussZip(businessInfo.getBussZip());
				}
				if(null!=businessInfo.getBussPhone()){
					businessInfoObj.setBussPhone(businessInfo.getBussPhone());
				}
				if(null!=businessInfo.getBussEmail()){
					businessInfoObj.setBussEmail(businessInfo.getBussEmail());
				}
				if(null != businessInfo.getBussLat()){
					businessInfoObj.setBussLat(businessInfo.getBussLat());
				}
				if(null != businessInfo.getBussLong()){
					businessInfoObj.setBussLong(businessInfo.getBussLong());
				}
				if(null!=businessInfo.getBussWeb()){
					businessInfoObj.setBussWeb(businessInfo.getBussWeb());
				}
				if(null!=businessInfo.getBussDesc()){
					businessInfoObj.setBussDesc(businessInfo.getBussDesc());
				}
				if(null!=businessInfo.getBussService()){
					businessInfoObj.setBussService(businessInfo.getBussService());
				}
				if(null!=businessInfo.getBussSpeciality()){
					businessInfoObj.setBussSpeciality(businessInfo.getBussSpeciality());
				}
				if(null != businessInfo.getCatId()){
					businessInfoObj.setCatId(businessInfo.getCatId());
				}
				if(null != businessInfo.getSubCatId()){
					businessInfoObj.setSubCatId(businessInfo.getSubCatId());
				}
				
			}
	 }

}
