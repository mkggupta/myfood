/**
 * 
 */
package com.myfood.framework.exception.util;

/**
 * @author Admin
 * 
 */
public enum ErrorCodesEnum {

	/*
	 * Error codes 10001 - 10999 are reserved for system exceptions
	 */
	SEVERE_ERROR("ERR_10001", "Severe Error", "common.error.severe.error"),

	SERVICE_UNAVAILABLE("ERR_10002", "Service Unavailable", "common.program.service.unavailable"),

	DATABASE_CONNECTION_UNAVAILABLE("ERR_10003", "Database Connection Error", "common.error.db.connection.unavailable"),

	UNEXPECTED_ERROR("ERR_10004", "Unexpected Error", "common.error.unexpected.error"),

	DATABASE_LAYER_EXCEPTION("ERR_10005", "Database Layer Exception", "common.error.db.exception"),

	OPERATION_NOT_IMPLEMENTED_EXCEPTION("ERR_10006", "Operation Not Supported Yet Exception", "common.error.operation.notimplemented"),

	AUTHENTICATION_FAILED_EXCEPTION("ERR_10007", "Authentication failed", "common.error.authentication.failed"),

	CONNECTION_ERROR("ERR_10008", "Connection Error", "common.error.connection.failed"),

	INVALID_REQUEST_EXCEPTION("ERR_10009", "Invalid request params", "common.validation.invalid.request.error"),

	DATABASE_UNIQUE_CONSTRAINT_VOILATION_EXCEPTION("ERR_10010", "Unique constraint voilation Exception",
			"common.error.db.unique.constraint.voilation.exception"),

	EMAIL_SENDING_FAILED_EXCEPTION("ERR_10011", "Email sending failed", "common.error.mail.sending.failed.exception"),
	
	/*
	 * Error codes 11001 need to be used for business exceptions
	 */
	//
	BUSINESS_RULES_SERVICE_FAILED_EXCEPTION("ERR11001", "Exception in fetching business rules", "com.myfood.bisrul.service.failed"),

	BUSINESS_RULE_NOT_FOUND("ERR11002", "No business rule found", "com.myfood.bisrul.not.found"),

	// USER SERVICE
	USER_SERVICE_FAILED_EXCEPTION("ERR11101", "Exception in user service", "com.myfood.user.service.failed"),

	USER_NOT_FOUND_EXCEPTION("ERR11102", "User not found in the system", "com.myfood.user.not.found"),

	USER_STATUS_INACTIVE("ERR11103", "User  status is inactive", "com.myfood.user.inactive"),

	USER_ALREADY_EXIST("ERR11104", "User already present", "com.myfood.user.already.exist"),

	USER_IS_BLOCKED("ERR11105", "User is blocked", "com.myfood.user.blocked"),

	ONLINE_STATUS_CHANGE_FAILED_EXCEPTION("ERR11106", "Exception in changing user online status", "com.myfood.user.online.status.change.failed"),

	USER_STATUS_UPDATE_FAILED_EXCEPTION("ERR11107", "Exception in updating status for the user", "com.myfood.user.status.update.failed"),
	
	USER_COUNTER_SERVICE_FAILED_EXCEPTION("ERR11108", "Exception in user counter service", "com.myfood.user.service.failed"),
	
	USER_NOT_EXIST_EXCEPTION("ERR11109", "Invalid username/password", "com.myfood.user.not.found"),
	
	USER_PUSH_UPDATE_EXCEPTION("ERR11110", "Invalid userid", "com.myfood.user.push.update"),


	// media service
	MEDIA_UPDATE_SERVICE_FAILED_EXCEPTION("ERR11301", "Exception in updating media business rules", "com.myfood.service.media.bo.failed"),

	MEDIA_CREATION_FAILED_EXCEPTION("ERR11302", "Media creation failed due to invalid data.", "com.myfood.service.media.create.failed"),

	MEDIA_GET_FAILED_EXCEPTION("ERR11303", "Media get failed due to some reason.", "com.myfood.service.media.get.failed"),
	

	// User Action Service
	USER_ACTION_UPDATE_SERVICE_FAILED_EXCEPTION("ERR11601", "Exception in updating user action ", "com.myfood.service.user.action.failed"),

	USER_ACTION_GET_SERVICE_FAILED_EXCEPTION("ERR11602", "Exception in fetching user action ", "com.myfood.service.user.action.failed"),

	USER_ACTION_FAILED_EXCEPTION("ERR11603", "user action  does not support this", "com.myfood.service.user.action.failed"),

	USER_ACTION_SERVICE_FAILED_EXCEPTION("ERR11604", "Exception in user action service", "com.myfood.service.user.action.failed"),

	// Authentication Service
	AUTHENTICATION_SERVICE_FAILED_EXCEPTION("ERR11501", "Exception in authentication service", "com.myfood.authentication.service.failed"),

	INVALID_EMAIL_VERIFICATION_CREDENTIALS_EXCEPTION("ERR11502", "Invalid email verification credentials", "com.myfood.email.verification.credential.invalid"),

	LINK_EXPIRED_EXCEPTION("ERR11503", "Link expired", "com.myfood.link.expired"),

	INVALID_PASSWORD_VERIFICATION_CREDENTIALS_EXCEPTION("ERR11504", "Invalid password verification credentials",
			"com.myfood.password.verification.credential.invalid"),

	INVALID_AUTH_KEY_EXCEPTION("ERR11505", "Invalid auth key", "com.myfood.authkey.verification.credential.invalid"),

	INVALID_REQUEST_TYPE_EXCEPTION("ERR11506", "Invalid request type", "com.myfood.requesttype.invalid"),
	

	
	//advt service 
	
	ADVT_SERVICE_FAILED_EXCEPTION("ERR11901", "Myplace may be over capacity or experiencing a momentary hiccup. Please try again.", "com.myfood.adv.service.failed"),
	
	//category service
	CATEGORY_SERVICE_FAILED_EXCEPTION("ERR11921", "Myplace may be over capacity or experiencing a momentary hiccup. Please try again.", "com.myfood.cat.service.failed"),
	SUB_CATEGORY_SERVICE_FAILED_EXCEPTION("ERR11922", "Myplace may be over capacity or experiencing a momentary hiccup. Please try again.", "com.myfood.cat.service.failed"),
	
	//about service
	ABOUT_SERVICE_FAILED_EXCEPTION("ERR11923", "Myplace may be over capacity or experiencing a momentary hiccup. Please try again.", "com.myfood.about.service.failed"),
	
	//feedback service
	
	FEEDBACK_SERVICE_FAILED_EXCEPTION("ERR11924", "Myplace may be over capacity or experiencing a momentary hiccup. Please try again.", "com.myfood.feedback.service.failed"),
	
	
	//business service
	BUSINESS_SERVICE_FAILED_EXCEPTION("ERR11931", "Myplace may be over capacity or experiencing a momentary hiccup. Please try again.", "com.myfood.business.service.failed"),
	BUSINESS_NOT_FOUND_EXCEPTION("ERR11932", "Business not found in the system", "com.myfood.business.not.found"),
	
	//business search service
	BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION("ERR11941", "Myplace may be over capacity or experiencing a momentary hiccup. Please try again.", "com.myfood.business.search.service.failed"),

	// Request Log

	REQUEST_LOG_SERVICE_FAILED_EXCEPTION("ERR11605", "Myplace may be over capacity or experiencing a momentary hiccup. Please try again.", "com.myfood.requestlog.service.failed"),

	

	// VAlidation exceptions
	/*
	 * Error codes 11001 need to be used for business exceptions
	 */
	//
	USERNAME_MISSING("ERR21001", "Username is missing", "common.error.validation.username.missing"),

	PASSWORD_MISSING("ERR21002", "Password is missing", "common.error.validation.password.missing"),

	LOGIN_MODE_MISSING("ERR21003", "Login mode is missing", "common.error.validation.loginMode.missing"),

	APP_ID_MISSING("ERR21004", "App Id is missing", "common.error.validation.appId.missing"),

	PARTNER_USER_KEY_MISSING("ERR21005", "Partner user key is missing", "common.error.validation.partnerUserKey.missing"),

	USER_SERVICE_VALIDATION_FAILED_EXCEPTION("ERR21006", "Myplace may be over capacity or experiencing a momentary hiccup. Please try again.", "common.error.validation.user.service"),

	FIRST_NAME_MISSING("ERR21007", "First name is missing", "common.error.validation.firstname.missing"),
	
	USER_GENDER_MISSING("ERR21008", "Gender is missing", "common.error.validation.gender.missing"),

	FORGOT_PASSWORD_ID_MISSING("ERR21008", "Forgot password id is missing", "common.error.validation.forgotPasswordId.missing"),

	USERID_MISSING("ERR21009", "UserId is missing", "common.error.validation.userId.missing"),

	VERIFICTION_CODE_MISSING("ERR21010", "Verification code is missing", "common.error.validation.verificationCode.missing"),
	
	USER_DOB_MISSING("ERR21011", "User DOB is missing", "common.error.validation.dob.missing"),
	
	STATS_DATA_MISSING("ERR21101", "Stats data is missing", "common.error.validation.stats.data.missing"),

	OLD_PASSWORD_MISSING("ERR21102", "Old Password is missing", "common.error.validation.old.password.missing"),
	
	USER_PASSWORD_WRONG("ERR21103", "The username or password you entered is incorrect.", "common.error.login.fail"),

	;

	String errorCode;
	String errorMessage;
	String errorLabelCode;

	ErrorCodesEnum(String errorCode, String errorMessage, String errorLabelCode) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorLabelCode = errorLabelCode;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorLabelCode
	 */
	public String getErrorLabelCode() {
		return errorLabelCode;
	}

	/**
	 * @param errorLabelCode
	 *            the errorLabelCode to set
	 */
	public void setErrorLabelCode(String errorLabelCode) {
		this.errorLabelCode = errorLabelCode;
	}

	public static ErrorCodesEnum getErrorCodesEnum(String errorCode) {
		ErrorCodesEnum[] errorCodesEnumArr = ErrorCodesEnum.values();
		for (ErrorCodesEnum errorCodesEnum : errorCodesEnumArr) {
			if (errorCodesEnum.getErrorCode().equalsIgnoreCase(errorCode)) {
				return errorCodesEnum;
			}
		}

		return UNEXPECTED_ERROR;
	}

}
