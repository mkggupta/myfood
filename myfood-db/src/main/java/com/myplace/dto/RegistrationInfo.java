package com.myfood.dto;

public class RegistrationInfo extends UserInfo {
	
	private String password;
	private int registrationMode;
	private String userKey;
	private String appKey;
	private String currentClientVersion;
	private String currentPlatform;
	private float longitude;
	private float latitude; 
    private String lastLocation;
    private String advtCode;
    
	public String getAdvtCode() {
		return advtCode;
	}
	public void setAdvtCode(String advtCode) {
		this.advtCode = advtCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRegistrationMode() {
		return registrationMode;
	}
	public void setRegistrationMode(int registrationMode) {
		this.registrationMode = registrationMode;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
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
	

}
