package com.myfood.service.user.service.v1_0;

import java.util.Map;

import com.myfood.dto.RegistrationInfo;
import com.myfood.dto.UserInfo;
import com.myfood.service.user.exception.UserServiceFailedException;
import com.myfood.service.user.exception.UserServiceValidationFailedException;


public interface UserService {
	 
	public Long  regLoginUser(RegistrationInfo registrationInfo, Map<String, Object> clientMap)throws UserServiceFailedException, UserServiceValidationFailedException;
	public UserInfo getUserProfile(long userId) throws UserServiceFailedException, UserServiceValidationFailedException;
	public UserInfo updateUser(UserInfo userInfo) throws UserServiceFailedException;
}
