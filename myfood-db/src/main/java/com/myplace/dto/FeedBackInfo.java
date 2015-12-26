package com.myfood.dto;

public class FeedBackInfo {

	private Long userId;
	private Long feedBackId;
	private String feedBackText;
	private String email;
	private Byte status;
	private String feedBackStatus; 
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getFeedBackId() {
		return feedBackId;
	}
	public void setFeedBackId(Long feedBackId) {
		this.feedBackId = feedBackId;
	}
	public String getFeedBackText() {
		return feedBackText;
	}
	public void setFeedBackText(String feedBackText) {
		this.feedBackText = feedBackText;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getFeedBackStatus() {
		return feedBackStatus;
	}
	public void setFeedBackStatus(String feedBackStatus) {
		this.feedBackStatus = feedBackStatus;
	}
	
}
