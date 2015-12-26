/**
 * 
 */
package com.myfood.framework.success;

/**
 * @author manish
 * 
 */
public enum SuccessCodesEnum {

	COMMON_SUCCESS("SUCC_10001", "Operation completed successfully", "common.success"),
	
	// 11101 - USER SERVICE SUCCESS MESSAGES

	// 11201 - AUTHENTICATION SERVICE SUCCESS MESSAGES

	// 11301 - ADVT SERVICE SUCCESS MESSAGES
	
	
	NO_ADVT_TEMPLATE_SUCCESS("SUCC_11301", "Currently there is no advertisement template.", "no.advt.template.success"),
	LOG_REG_SUCCESS("SUCC_11302", "Register/Login sucessfully.", "log.reg.success"),
	NO_ADVT_SUCCESS("SUCC_11303", "Currently there is no advertisement.", "no.advt.success"),
	ADVT_STATUS_CHANGE_SUCCESS("SUCC_11304", "Advertisement status has been changed successfully.", "advt.status.change.success"),
	NO_ADVT_STATUS_SUCCESS("SUCC_11305", "No action taken.", "advt.no.action.success"),
	NO_CAT_SUCCESS("SUCC_11306", "Currently there is no category.", "no.cat.success"),
	NO_SUB_CAT_SUCCESS("SUCC_11307", "Currently there is no sub category.", "no.sub.cat.success"),
	NO_BUSINESS_SUCCESS("SUCC_11308", "Currently there is no business.", "no.business.success"),
	BUSS_STATUS_CHANGE_SUCCESS("SUCC_11309", "Business status has been changed successfully.", "buss.status.change.success"),
	BUSS_CHANGE_SUCCESS("SUCC_11310", "Business details has been changed successfully.", "buss.detail.change.success"),
	NO_BUSS_STATUS_SUCCESS("SUCC_11311", "No action taken.", "buss.no.action.success"),
	NO_ABOUT_SUCCESS("SUCC_11312", "Page is under construction.", "no.about.success"),
	NO_FEEDBACK_SUCCESS("SUCC_11313", "Currently there is no feedback.Write your own experience with this application.", "no.feedback.success"),
	LOG_REG_ALREADY_EXIST("SUCC_11314", "This Email address is already registered with us.", "log.reg.success"),

	;
	String successCode;
	String successMessage;
	String successLabelCode;

	SuccessCodesEnum(String successCode, String successMessage, String successLabelCode) {
		this.successCode = successCode;
		this.successMessage = successMessage;
		this.successLabelCode = successLabelCode;
	}

	/**
	 * @return the successCode
	 */
	public String getSuccessCode() {
		return successCode;
	}

	/**
	 * @param successCode
	 *            the successCode to set
	 */
	public void setSuccessCode(String successCode) {
		this.successCode = successCode;
	}

	/**
	 * @return the successMessage
	 */
	public String getSuccessMessage() {
		return successMessage;
	}

	/**
	 * @param successMessage
	 *            the successMessage to set
	 */
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	/**
	 * @return the successLabelCode
	 */
	public String getSuccessLabelCode() {
		return successLabelCode;
	}

	/**
	 * @param successLabelCode
	 *            the successLabelCode to set
	 */
	public void setSuccessLabelCode(String successLabelCode) {
		this.successLabelCode = successLabelCode;
	}

	public static SuccessCodesEnum getSuccessCodesEnum(String successCode) {
		SuccessCodesEnum[] successCodesEnumArr = SuccessCodesEnum.values();
		for (SuccessCodesEnum successCodesEnum : successCodesEnumArr) {
			if (successCodesEnum.getSuccessCode().equalsIgnoreCase(successCode)) {
				return successCodesEnum;
			}
		}

		return COMMON_SUCCESS;
	}

}
