package com.myfood.common.user.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.myfood.common.enumeration.UserStatusEnum;
import com.myfood.dto.RegistrationInfo;
import com.myfood.dto.UserAuth;
import com.myfood.dto.UserInfo;





public class UserUtils {
	static Logger logger = LoggerFactory.getLogger(UserUtils.class);
	
	public static UserAuth transformRegistrationInfoToUserAuth(RegistrationInfo registrationVO, boolean isRegistration) {
		UserAuth userAuth = new UserAuth();
		if (null != registrationVO) {
			userAuth.setId(registrationVO.getId());
			userAuth.setPassword(registrationVO.getPassword());
			userAuth.setRegistrationMode(registrationVO.getRegistrationMode());
			if(StringUtils.isNotBlank(registrationVO.getUserName())){
			userAuth.setUserName(registrationVO.getUserName());
			}else if(StringUtils.isNotBlank(registrationVO.getFirstName()) && StringUtils.isNotBlank(registrationVO.getLastName())){
				userAuth.setUserName(registrationVO.getFirstName()+" "+registrationVO.getLastName());
			}else if(StringUtils.isNotBlank(registrationVO.getFirstName())){
				userAuth.setUserName(registrationVO.getFirstName());
			}else{
				userAuth.setUserName(registrationVO.getLastName());
			}
			userAuth.setCurrentClientVersion(registrationVO.getCurrentClientVersion());
			userAuth.setCurrentPlatform(registrationVO.getCurrentPlatform());
			if (isRegistration) {
				if (registrationVO.getRegistrationMode() > 0) {
					userAuth.setStatus(UserStatusEnum.ACTIVE.getStatus());
				} else {
					userAuth.setStatus(UserStatusEnum.INACTIVE.getStatus());
				}
				userAuth.setLastLoginMode(registrationVO.getRegistrationMode());
				userAuth.setCreatedDate(new Date());
				userAuth.setLoginStatus(0);
				userAuth.setLatitude(registrationVO.getLatitude());
				userAuth.setLongitude(registrationVO.getLongitude());
				userAuth.setLastLocation(registrationVO.getLastLocation());
			}
			userAuth.setLastLoginTime(new Date());
			userAuth.setModifiedDate(new Date());
			//userAuth.setPushKey(registrationVO.getPushKey());
		}
		return userAuth;
	}
	
  public static UserInfo transformRegistrationInfoToUserInfo(RegistrationInfo userVO) {
		UserInfo userInfo = new UserInfo();
		if (null != userVO) {
			try {
				BeanUtils.copyProperties(userInfo, userVO);
			} catch (IllegalAccessException e) {
				logger.error("Error in copying RegistrationInfo " + userVO + " to userInfo", e);
			} catch (InvocationTargetException e) {
				logger.error("Error in copying RegistrationInfo " + userVO + " to userInfo", e);
			}
			userInfo.setModifiedDate(new Date());

		}
		return userInfo;
	}

  public static void updateDBUserVO(UserInfo userInfoObj, UserInfo userInfo) {
		if (null != userInfoObj && null != userInfoObj) {
			if (null != userInfo.getFirstName()) {
				userInfoObj.setFirstName(userInfo.getFirstName());
			}
			if (null != userInfo.getContactName()) {
				userInfoObj.setContactName(userInfo.getContactName());
			}
			if (null != userInfo.getBusinessName()) {
				userInfoObj.setBusinessName(userInfo.getBusinessName());
			}
			if (null != userInfo.getLastName()) {
				userInfoObj.setLastName(userInfo.getLastName());
			}
			if (null != userInfo.getCity()) {
				userInfoObj.setCity(userInfo.getCity());
			}
			if (null != userInfo.getContactAddressLine1()) {
				userInfoObj.setContactAddressLine1(userInfo.getContactAddressLine1());
			}
			if (null != userInfo.getContactAddressLine2()) {
				userInfoObj.setContactAddressLine2(userInfo.getContactAddressLine2());
			}
			if (null != userInfo.getContactNumber()) {
				userInfoObj.setContactNumber(userInfo.getContactNumber());
			}
			if (null != userInfo.getCountry()) {
				userInfoObj.setCountry(userInfo.getCountry());
			}
			if (null != userInfo.getProfilePicFileId()) {
				userInfoObj.setProfilePicFileId(userInfo.getProfilePicFileId());
			}
			if (null != userInfo.getProfilePicFileExt()) {
				userInfoObj.setProfilePicFileExt(userInfo.getProfilePicFileExt());
			}
			if (null != userInfo.getSalutation()) {
				userInfoObj.setSalutation(userInfo.getSalutation());
			}
			if (null != userInfo.getSecondaryEmailAddress()) {
				userInfoObj.setSecondaryEmailAddress(userInfo.getSecondaryEmailAddress());
			}
			if (null != userInfo.getState()) {
				userInfoObj.setState(userInfo.getState());
			}
			if (null != userInfo.getTimeZone()) {
				userInfoObj.setTimeZone(userInfo.getTimeZone());
			}
			if (null != userInfo.getZipcode()) {
				userInfoObj.setZipcode(userInfo.getZipcode());
			}
			if (null != userInfo.getLanguage()) {
				userInfoObj.setLanguage(userInfo.getLanguage());
			}
			if (null != userInfo.getLocation()) {
				userInfoObj.setLocation(userInfo.getLocation());
			}
			if (null != userInfo.getUserDescription()) {
				userInfoObj.setUserDescription(userInfo.getUserDescription());
			}
			if (null != userInfo.getWebSite()) {
				userInfoObj.setWebSite(userInfo.getWebSite());
			}
		}
	}
}
