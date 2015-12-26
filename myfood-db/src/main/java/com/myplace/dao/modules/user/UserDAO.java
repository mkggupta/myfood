package com.myfood.dao.modules.user;

import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.exception.DataUpdateFailedException;
import com.myfood.dto.UserAuth;
import com.myfood.dto.UserInfo;
import com.myfood.dto.UserThirdPartyAuth;






public interface UserDAO {
	
	public void saveUserInfo(UserInfo userInfo) throws DataUpdateFailedException;
	public Long saveUserAuth(UserAuth userAuth) throws DataUpdateFailedException;
	public void saveUserThirdPartyAuth(UserThirdPartyAuth userThirdPartyAuth) throws DataUpdateFailedException;
	public Long getUserExists(String userKey,int thirdPartyId) throws DataAccessFailedException;
	public UserInfo getUserProfile(long userId) throws DataAccessFailedException ;
	public String getUserNameById(long userId) throws DataAccessFailedException ;
	public void updateUser(UserInfo userInfo) throws DataUpdateFailedException ;
	public Long getUserIdByNamePassword(String userName,String password) throws DataAccessFailedException;
	public Long getUserIdByNameForApp(String userName) throws DataAccessFailedException;
	

}
