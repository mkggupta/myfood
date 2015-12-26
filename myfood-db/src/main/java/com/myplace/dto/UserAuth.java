package com.myfood.dto;

import java.util.Date;

public class UserAuth {

	private long id;
	private String userName;
	private String password;
	private int status;
	private Date createdDate;
	private Date modifiedDate;
	private Date lastLoginTime;
	private int registrationMode;
	private int lastLoginMode;
	private String currentClientVersion;
	private String currentPlatform;
	private int loginStatus;
	private float longitude;
	private float latitude; 
    private String lastLocation;
    private String pushKey;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getRegistrationMode() {
		return registrationMode;
	}
	public void setRegistrationMode(int registrationMode) {
		this.registrationMode = registrationMode;
	}
	public int getLastLoginMode() {
		return lastLoginMode;
	}
	public void setLastLoginMode(int lastLoginMode) {
		this.lastLoginMode = lastLoginMode;
	}
	public String getCurrentClientVersion() {
		return currentClientVersion;
	}
	public void setCurrentClientVersion(String currentClientVersion) {
		this.currentClientVersion = currentClientVersion;
	}
	public String getCurrentPlatform() {
		return currentPlatform;
	}
	public void setCurrentPlatform(String currentPlatform) {
		this.currentPlatform = currentPlatform;
	}
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public String getLastLocation() {
		return lastLocation;
	}
	public void setLastLocation(String lastLocation) {
		this.lastLocation = lastLocation;
	}
	public String getPushKey() {
		return pushKey;
	}
	public void setPushKey(String pushKey) {
		this.pushKey = pushKey;
	}
	
}
