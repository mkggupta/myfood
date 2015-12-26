package com.myfood.dto;

public class FeedBackReplyInfo {
	private Long replyId;
	private Long feedBackId;
	private String replyText;
	private Long replyingUserId;
	
	public Long getReplyId() {
		return replyId;
	}
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}
	public Long getFeedBackId() {
		return feedBackId;
	}
	public void setFeedBackId(Long feedBackId) {
		this.feedBackId = feedBackId;
	}
	public String getReplyText() {
		return replyText;
	}
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}
	public Long getReplyingUserId() {
		return replyingUserId;
	}
	public void setReplyingUserId(Long replyingUserId) {
		this.replyingUserId = replyingUserId;
	}
	
	
}
