package com.myfood.common.util;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.common.constant.FeedBackWebConstant;
import com.myfood.common.constant.MyFoodBusinessConstant;
import com.myfood.common.constant.MyFoodConstant;
import com.myfood.common.constant.ClientParamConstant;
import com.myfood.common.constant.UserParameters;
import com.myfood.common.enumeration.DateTimeFormatEnum;
import com.myfood.dto.AdvertisementDTO;
import com.myfood.dto.AdvertisementInfo;
import com.myfood.dto.AdvtBusinessInfo;
import com.myfood.dto.BusinessFileInfo;
import com.myfood.dto.BusinessInfo;
import com.myfood.dto.BusinessSearchVO;
import com.myfood.dto.FeedBackInfo;
import com.myfood.dto.FeedBackReplyInfo;
import com.myfood.dto.FileInfo;
import com.myfood.dto.PaymentInfo;
import com.myfood.dto.RegistrationInfo;
import com.myfood.dto.UserInfo;



public class RequestProcessorUtil {
	
	private static Logger logger = LoggerFactory.getLogger(RequestProcessorUtil.class);

	public static AdvertisementDTO enrichAdvertisementDTO(HashMap<String, Object> requestMap,AdvertisementDTO advertisementDTO ){
		if (null != requestMap) {
			AdvertisementInfo advertisementInfo =new AdvertisementInfo();
			AdvtBusinessInfo  businessInfo = new AdvtBusinessInfo();
			PaymentInfo paymentInfo = new PaymentInfo();
			if(null!=requestMap.get(MyFoodConstant.FILE_DATA)){
				advertisementDTO.setFileInfo((FileInfo) requestMap.get(MyFoodConstant.FILE_DATA));
				logger.debug(advertisementDTO.getFileInfo().getFile_name())	;
			}
			
			if(null!=requestMap.get(MyFoodConstant.AD_NAME)){
				advertisementInfo.setAdvtName((String) requestMap.get(MyFoodConstant.AD_NAME));
			}
			
			if(null!=requestMap.get(MyFoodConstant.USERID)){
				advertisementInfo.setCreatorId(Long.parseLong(requestMap.get(MyFoodConstant.USERID).toString()));
			}
			if(null!=requestMap.get(MyFoodConstant.AD_HEADER)){
				advertisementInfo.setAdHeader((String) requestMap.get(MyFoodConstant.AD_HEADER));
			}
			if(null!=requestMap.get(MyFoodConstant.ADVT_DESC)){
				advertisementInfo.setAdvtDesc((String) requestMap.get(MyFoodConstant.ADVT_DESC));
			}
			if(null!=requestMap.get(MyFoodConstant.ADTYPE)){
				advertisementInfo.setAdtype((String) requestMap.get(MyFoodConstant.ADTYPE));
			}
			if(null!=requestMap.get(MyFoodConstant.CITY)){
				advertisementInfo.setCity((String) requestMap.get(MyFoodConstant.CITY));
			}
			if(null!=requestMap.get(MyFoodConstant.STATE)){
				advertisementInfo.setState((String) requestMap.get(MyFoodConstant.STATE));
			}
			if(null!=requestMap.get(MyFoodConstant.ZIPCODE)){
				advertisementInfo.setZipcode((String) requestMap.get(MyFoodConstant.ZIPCODE));
			}
			if(null!=requestMap.get(MyFoodConstant.COUNTRY)){
				advertisementInfo.setCountry((String) requestMap.get(MyFoodConstant.COUNTRY));
			}
			if(null != requestMap.get(MyFoodConstant.LATITUDE)){
				advertisementInfo.setLatitude(Float.parseFloat(requestMap.get(MyFoodConstant.LATITUDE).toString()));
			}
			if(null != requestMap.get(MyFoodConstant.LONGITUDE)){
				advertisementInfo.setLongitude(Float.parseFloat(requestMap.get(MyFoodConstant.LONGITUDE).toString()));
			}
			if(null!=requestMap.get(MyFoodConstant.WEB_URL)){
				advertisementInfo.setWebUrl((String) requestMap.get(MyFoodConstant.WEB_URL));
			}
			if(null!=requestMap.get(MyFoodConstant.ADDRESS)){
				advertisementInfo.setAddress((String) requestMap.get(MyFoodConstant.ADDRESS));
			}
			if(null!=requestMap.get(MyFoodConstant.PHONE1)){
				advertisementInfo.setPhoneNumber((String) requestMap.get(MyFoodConstant.PHONE1));
			}
			if(null!=requestMap.get(MyFoodConstant.CONTACT_PHONE)){
				advertisementInfo.setContactPhone((String) requestMap.get(MyFoodConstant.CONTACT_PHONE));
			}
			if(null!=requestMap.get(MyFoodConstant.CONTACT_NAME)){
				advertisementInfo.setContactName((String) requestMap.get(MyFoodConstant.CONTACT_NAME));
			}
			if(null!=requestMap.get(MyFoodConstant.CONTACT_EMAIL)){
				advertisementInfo.setContactEmail((String) requestMap.get(MyFoodConstant.CONTACT_EMAIL));
			}
			if(null!=requestMap.get(MyFoodConstant.DAILY_BUDGET)){
				advertisementInfo.setDailyBudget(Float.parseFloat( requestMap.get(MyFoodConstant.DAILY_BUDGET).toString()));
			}
			if(null!=requestMap.get(MyFoodConstant.STARTDATE)){
				advertisementInfo.setStartDate((String) requestMap.get(MyFoodConstant.STARTDATE));
			}
			if(null!=requestMap.get(MyFoodConstant.ENDDATE)){
				advertisementInfo.setEndDate((String) requestMap.get(MyFoodConstant.ENDDATE));
			}
			if(null!=requestMap.get(MyFoodConstant.STARTTIME)){
				advertisementInfo.setStartTime((String) requestMap.get(MyFoodConstant.STARTTIME));
			}
			if(null!=requestMap.get(MyFoodConstant.BUDGET)){
				advertisementInfo.setBudget(Float.parseFloat(requestMap.get(MyFoodConstant.BUDGET).toString()));
			}
			
			if(null!=requestMap.get(MyFoodConstant.BUSINESS_ADDRESS)){
				businessInfo.setB_address((String) requestMap.get(MyFoodConstant.BUSINESS_ADDRESS));
			}
			
			if(null!=requestMap.get(MyFoodConstant.CARD_NUMBER)){
				paymentInfo.setCard_number((String) requestMap.get(MyFoodConstant.CARD_NUMBER));
			}
			if(null!=requestMap.get(MyFoodConstant.NAME_ON_CARD)){
				paymentInfo.setName_on_card((String) requestMap.get(MyFoodConstant.NAME_ON_CARD));
			}
			if(null!=requestMap.get(MyFoodConstant.TRANSACTION_ID)){
				paymentInfo.setTransaction_id((String) requestMap.get(MyFoodConstant.TRANSACTION_ID));
			}
			
			advertisementDTO.setBusinessInfo(businessInfo);
			advertisementDTO.setAdvertisementInfo(advertisementInfo);
			advertisementDTO.setPaymentInfo(paymentInfo);
			
			logger.debug("getAdvt_name == "+advertisementDTO.getAdvertisementInfo().getAdvtName())	;
			
			logger.debug("getB_address=="+advertisementDTO.getBusinessInfo().getB_address())	;
			
			logger.debug("getCard_number=="+advertisementDTO.getPaymentInfo().getCard_number())	;
			
		}
		return  advertisementDTO;
	}

	
	public static void enrichRegistrationInfo(HashMap<String, Object> requestMap, RegistrationInfo registrationVO,Map<String, Object> clientParamMap) {
		if (null != requestMap) {
			if (null != requestMap.get(UserParameters.PASSWORD)) {
				registrationVO.setPassword(requestMap.get(UserParameters.PASSWORD).toString());
			}
			if (null != requestMap.get(UserParameters.REGISTRATION_MODE)) {
				registrationVO.setRegistrationMode(Integer.parseInt(requestMap.get(UserParameters.REGISTRATION_MODE).toString()));
			}
			if (null != requestMap.get(UserParameters.PARTNER_USER_KEY)) {
				registrationVO.setUserKey(requestMap.get(UserParameters.PARTNER_USER_KEY).toString());
			}
			if (null != requestMap.get(UserParameters.PARTNER_APP_ID)) {
				registrationVO.setAppKey(requestMap.get(UserParameters.PARTNER_APP_ID).toString());
			}
			if (null != requestMap.get(UserParameters.ADVTCODE)) {
				registrationVO.setAdvtCode(requestMap.get(UserParameters.ADVTCODE).toString());
			}
			if(null != clientParamMap.get(ClientParamConstant.CLIENT_VERSION)){
				registrationVO.setCurrentClientVersion((String) clientParamMap.get(ClientParamConstant.CLIENT_VERSION));
			}
			if(null != clientParamMap.get(ClientParamConstant.PLATFORM)){
				registrationVO.setCurrentPlatform((String) clientParamMap.get(ClientParamConstant.PLATFORM));
			}
			if(null != clientParamMap.get(ClientParamConstant.LATITUDE)){
				registrationVO.setLatitude(Float.parseFloat(clientParamMap.get(ClientParamConstant.LATITUDE).toString()));
			}
			if(null != clientParamMap.get(ClientParamConstant.LONGITUDE)){
				registrationVO.setLongitude(Float.parseFloat(clientParamMap.get(ClientParamConstant.LONGITUDE).toString()));
			}
			if(null !=  clientParamMap.get(ClientParamConstant.ADDREASS)){
				registrationVO.setLastLocation((String) clientParamMap.get(ClientParamConstant.ADDREASS));
			}
			
			
			enrichUserVO(requestMap, registrationVO,clientParamMap);
		}
	}

	public static void enrichUserVO(HashMap<String, Object> requestMap, UserInfo userVO,Map<String, Object> clientParamMap) {
		if (null != requestMap) {
			if (null != requestMap.get(UserParameters.CITY)) {
				userVO.setCity((String)requestMap.get(UserParameters.CITY));
			}else if (null!=clientParamMap && null != clientParamMap.get(UserParameters.CITY)) {
				userVO.setCity((String)clientParamMap.get(UserParameters.CITY));
			}
			if (null != requestMap.get(UserParameters.CONTACT_ADDRESS_LINE1)) {
				userVO.setContactAddressLine1(requestMap.get(UserParameters.CONTACT_ADDRESS_LINE1).toString());
			}
			if (null != requestMap.get(UserParameters.CONTACT_ADDRESS_LINE2)) {
				userVO.setContactAddressLine2(requestMap.get(UserParameters.CONTACT_ADDRESS_LINE2).toString());
			}
			if (null != requestMap.get(UserParameters.CONTACT_NUMBER)) {
				userVO.setContactNumber(requestMap.get(UserParameters.CONTACT_NUMBER).toString());
			}
			
			if (null != requestMap.get(UserParameters.COUNTRY)) {
				userVO.setCountry((String)requestMap.get(UserParameters.COUNTRY));
			}else if (null!=clientParamMap && null != clientParamMap.get(UserParameters.COUNTRY)) {
				userVO.setCountry((String)clientParamMap.get(UserParameters.COUNTRY));
			}
			//logger.debug("enrichUserVO--"+clientParamMap.get(UserParameters.COUNTRY));
			if (null != requestMap.get(UserParameters.DOB)) {
				logger.debug("enrichUserVO ::DOB :: "+ requestMap.get(UserParameters.DOB));
				String dob = requestMap.get(UserParameters.DOB).toString();
				userVO.setDob(DateTimeUtils.getDateObject(dob, DateTimeFormatEnum.WEB_DATE_FORMAT));
			}
			if (null != requestMap.get(UserParameters.CONTACT_NAME)) {
				userVO.setContactName(requestMap.get(UserParameters.CONTACT_NAME).toString());
			}
			if (null != requestMap.get(UserParameters.FIRST_NAME)) {
				userVO.setFirstName(requestMap.get(UserParameters.FIRST_NAME).toString());
			}
			
			if (null != requestMap.get(UserParameters.LAST_NAME)) {
				userVO.setLastName(requestMap.get(UserParameters.LAST_NAME).toString());
			}
			if (null != requestMap.get(UserParameters.GENDER)) {
				userVO.setGender(Integer.parseInt(requestMap.get(UserParameters.GENDER).toString()));
			}
			if (null != requestMap.get(UserParameters.BUSINESS_NAME)) {
				userVO.setBusinessName(requestMap.get(UserParameters.BUSINESS_NAME).toString());
			}
			if (null != requestMap.get(UserParameters.PROFILE_PIC_FILE_ID)) {
				userVO.setProfilePicFileId(requestMap.get(UserParameters.PROFILE_PIC_FILE_ID).toString());
			}
			if (null != requestMap.get(UserParameters.SATUTATION)) {
				userVO.setSalutation(requestMap.get(UserParameters.SATUTATION).toString());
			}
			if (null != requestMap.get(UserParameters.SECONDARY_EMAIL_ADDRESS)) {
				userVO.setSecondaryEmailAddress(requestMap.get(UserParameters.SECONDARY_EMAIL_ADDRESS).toString());
			}
			if (null != requestMap.get(UserParameters.STATE)) {
				userVO.setState((String)requestMap.get(UserParameters.STATE));
			}else if (null!=clientParamMap && null != clientParamMap.get(UserParameters.STATE)) {
				userVO.setState((String)clientParamMap.get(UserParameters.STATE));
			}
			if (null != requestMap.get(UserParameters.TIME_ZONE)) {
				userVO.setTimeZone(requestMap.get(UserParameters.TIME_ZONE).toString());
			}
			if (null != requestMap.get(UserParameters.ZIPCODE)) {
				userVO.setZipcode(requestMap.get(UserParameters.ZIPCODE).toString());
			}
			if (null != requestMap.get(UserParameters.USER_NAME)) {
				userVO.setUserName((requestMap.get(UserParameters.USER_NAME).toString()).toLowerCase());
			}
			if (null != requestMap.get(UserParameters.USER_ID)) {
				userVO.setId(Long.parseLong(requestMap.get(UserParameters.USER_ID).toString()));
			}
			if (null != requestMap.get(UserParameters.USER_DESC)) {
				userVO.setUserDescription(requestMap.get(UserParameters.USER_DESC).toString());
			}
			if (null != requestMap.get(UserParameters.WEB_SITE)) {
				userVO.setWebSite(requestMap.get(UserParameters.WEB_SITE).toString());
			}
			if (null != requestMap.get(UserParameters.USER_LANGUAGE)) {
				userVO.setLanguage(requestMap.get(UserParameters.USER_LANGUAGE).toString());
			}else if (null!=clientParamMap && null != clientParamMap.get(ClientParamConstant.LANGUAGE)) {
				userVO.setLanguage((String)clientParamMap.get(ClientParamConstant.LANGUAGE));
			}
			if (null != requestMap.get(ClientParamConstant.LOCATION)) {
				userVO.setLocation(requestMap.get(ClientParamConstant.LOCATION).toString());
			}else if(null!=clientParamMap && null != clientParamMap.get(ClientParamConstant.LOCATION)){
				userVO.setLocation((String) clientParamMap.get(ClientParamConstant.LOCATION));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static BusinessInfo enrichBusinessInfo(HashMap<String, Object> requestMap,BusinessInfo businessInfo ){
		if (null != requestMap) {
			
			if(null!=requestMap.get(MyFoodConstant.B_FILE_DATA)){
				businessInfo.setBusinessFileInfo((List<BusinessFileInfo>) requestMap.get(MyFoodConstant.B_FILE_DATA));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.USERID)){
				businessInfo.setUserId(Long.parseLong(requestMap.get(MyFoodBusinessConstant.USERID).toString()));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BID)){
				businessInfo.setBussId(Long.parseLong(requestMap.get(MyFoodBusinessConstant.BID).toString()));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BNAME)){
				businessInfo.setBussName((String) requestMap.get(MyFoodBusinessConstant.BNAME));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BADDRESS)){
				businessInfo.setBussAddress((String) requestMap.get(MyFoodBusinessConstant.BADDRESS));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BCITY)){
				businessInfo.setBussCity((String) requestMap.get(MyFoodBusinessConstant.BCITY));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BSTATE)){
				businessInfo.setBussState((String) requestMap.get(MyFoodBusinessConstant.BSTATE));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BCOUNTRY)){
				businessInfo.setBussCountry((String) requestMap.get(MyFoodBusinessConstant.BCOUNTRY));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BZIP)){
				businessInfo.setBussZip((String) requestMap.get(MyFoodBusinessConstant.BZIP));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BPHONE)){
				businessInfo.setBussPhone((String) requestMap.get(MyFoodBusinessConstant.BPHONE));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BEMAIL)){
				businessInfo.setBussEmail((String) requestMap.get(MyFoodBusinessConstant.BEMAIL));
			}
			if(null != requestMap.get(MyFoodBusinessConstant.BLATITUDE)){
				businessInfo.setBussLat(Float.parseFloat(requestMap.get(MyFoodBusinessConstant.BLATITUDE).toString()));
			}
			if(null != requestMap.get(MyFoodBusinessConstant.BLONGITUDE)){
				businessInfo.setBussLong(Float.parseFloat(requestMap.get(MyFoodBusinessConstant.BLONGITUDE).toString()));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BWEB)){
				businessInfo.setBussWeb((String) requestMap.get(MyFoodBusinessConstant.BWEB));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BDESC)){
				businessInfo.setBussDesc((String) requestMap.get(MyFoodBusinessConstant.BDESC));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BSERVICE)){
				businessInfo.setBussService((String) requestMap.get(MyFoodBusinessConstant.BSERVICE));
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BSPECIALITY)){
				businessInfo.setBussSpeciality((String) requestMap.get(MyFoodBusinessConstant.BSPECIALITY));
			}
			if(null != requestMap.get(MyFoodBusinessConstant.CATID)){
				businessInfo.setCatId(Long.parseLong(requestMap.get(MyFoodBusinessConstant.CATID).toString()));
			}
			if(null != requestMap.get(MyFoodBusinessConstant.SUBCATID)){
				businessInfo.setSubCatId((String)requestMap.get(MyFoodBusinessConstant.SUBCATID).toString());
			}
			if(null != requestMap.get(MyFoodBusinessConstant.BSTARTYEAR)){
				String startDate= (String) requestMap.get(MyFoodBusinessConstant.BSTARTYEAR);
				businessInfo.setBussStartDate(startDate);	
			}else{
				businessInfo.setBussStartDate("");
			}
			logger.debug("businessInfo=="+businessInfo)	;
			
		}
		return  businessInfo;
	}
	
	public static BusinessSearchVO enrichSearchVO(HashMap<String,Object> requestMap) {
		BusinessSearchVO searchVO = new BusinessSearchVO();
		if(null != requestMap.get(MyFoodBusinessConstant.BLATITUDE)){
			searchVO.setLatitude(Float.parseFloat(requestMap.get(MyFoodBusinessConstant.BLATITUDE).toString()));
		}
		if(null != requestMap.get(MyFoodBusinessConstant.BLONGITUDE)){
			searchVO.setLongitude(Float.parseFloat(requestMap.get(MyFoodBusinessConstant.BLONGITUDE).toString()));
		}
		if (null != requestMap.get(MyFoodBusinessConstant.BZIP)) {
			searchVO.setZipCode((String) requestMap.get(MyFoodBusinessConstant.BZIP));
		}
		if (null != requestMap.get(MyFoodBusinessConstant.TEXT)) {
			searchVO.setText((String) requestMap.get(MyFoodBusinessConstant.TEXT));
		}
		if (null != requestMap.get(MyFoodBusinessConstant.TYPE)) {
			searchVO.setType((String) requestMap.get(MyFoodBusinessConstant.TYPE));
		}
		if (null != requestMap.get(MyFoodBusinessConstant.SUBCATID)) {
			searchVO.setSubCatId((String) requestMap.get(MyFoodBusinessConstant.SUBCATID));
		}
		if (null != requestMap.get(MyFoodBusinessConstant.CATID)) {
			searchVO.setCatId(Integer.parseInt(requestMap.get(MyFoodBusinessConstant.CATID).toString()));
		}
		if (null != requestMap.get(MyFoodBusinessConstant.USERID)) {
			searchVO.setUserId(Long.parseLong(requestMap.get(MyFoodBusinessConstant.USERID).toString()));
		}
		if (null != requestMap.get(MyFoodBusinessConstant.DISTANCE)) {
			searchVO.setDistance(Integer.parseInt(requestMap.get(MyFoodBusinessConstant.DISTANCE).toString()));
		}else{
			searchVO.setDistance(MyFoodBusinessConstant.DEFAULT_DISTANCE);
		}
		/*else if(null != userId){
			searchVO.setUserId(userId);
		}*/
		return searchVO;
	}
	
	
	public static FeedBackInfo enrichFeedBackInfoInfo(HashMap<String, Object> requestMap,FeedBackInfo feedBackInfo ){
		if (null != requestMap) {
			
			if(null!=requestMap.get(FeedBackWebConstant.USERID)){
				feedBackInfo.setUserId(Long.parseLong(requestMap.get(FeedBackWebConstant.USERID).toString()));
			}
		
			if(null!=requestMap.get(FeedBackWebConstant.EMAIL)){
				feedBackInfo.setEmail((String) requestMap.get(FeedBackWebConstant.EMAIL));
			}
			if(null!=requestMap.get(FeedBackWebConstant.FEED_BACK_TEXT)){
				feedBackInfo.setFeedBackText((String) requestMap.get(FeedBackWebConstant.FEED_BACK_TEXT));
			}
			
			if(null!=requestMap.get(FeedBackWebConstant.FEED_BACK_ID)){
				feedBackInfo.setFeedBackId(Long.parseLong(requestMap.get(FeedBackWebConstant.FEED_BACK_ID).toString()));
			}	
		}	
		return feedBackInfo;
	}
	
	public static FeedBackReplyInfo enrichFeedBackReplyInfo(HashMap<String, Object> requestMap,FeedBackReplyInfo feedBackReplyInfo ){
		if (null != requestMap) {
			
			if(null!=requestMap.get(FeedBackWebConstant.REPLYING_USER_ID)){
				feedBackReplyInfo.setReplyingUserId(Long.parseLong(requestMap.get(FeedBackWebConstant.REPLYING_USER_ID).toString()));
			}
			if(null!=requestMap.get(FeedBackWebConstant.REPLY_TEXT)){
				feedBackReplyInfo.setReplyText((String) requestMap.get(FeedBackWebConstant.REPLY_TEXT));
			}
			if(null!=requestMap.get(FeedBackWebConstant.FEED_BACK_ID)){
				feedBackReplyInfo.setFeedBackId(Long.parseLong(requestMap.get(FeedBackWebConstant.FEED_BACK_ID).toString()));
			}	
		}	
		return feedBackReplyInfo;
	}
}
