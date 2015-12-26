package com.myfood.dto;

public class AdvtStats {

	private long advtCode;
	private long clicks;
	private long views;
	private long calls;
	private long sms;
	private float longitude;
	private float latitude; 
	private String advtName;
	
	
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
	public String getAdvtName() {
		return advtName;
	}
	public void setAdvtName(String advtName) {
		this.advtName = advtName;
	}
	
	public long getAdvtCode() {
		return advtCode;
	}
	public void setAdvtCode(long advtCode) {
		this.advtCode = advtCode;
	}
	public long getClicks() {
		return clicks;
	}
	public void setClicks(long clicks) {
		this.clicks = clicks;
	}
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	public long getCalls() {
		return calls;
	}
	public void setCalls(long calls) {
		this.calls = calls;
	}
	public long getSms() {
		return sms;
	}
	public void setSms(long sms) {
		this.sms = sms;
	}
	
	
}
