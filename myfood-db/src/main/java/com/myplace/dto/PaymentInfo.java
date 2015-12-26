package com.myfood.dto;

import java.util.Date;

public class PaymentInfo {
	private long advtCode;
	
	private Long user_id;
	private String name_on_card;
	private String country;
	private String city;
	private String state;
	private String zipcode;
	private String card_type;
	private String billing_name;
	private String billing_address;
	private String card_number;
	private Date transaction_date;
	private String transaction_id;
	private Boolean transaction_status;
	private Byte transaction_type;
	
	
	public Date getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}
	
	public Byte getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(Byte transaction_type) {
		this.transaction_type = transaction_type;
	}
	public long getAdvtCode() {
		return advtCode;
	}
	public void setAdvtCode(long advtCode) {
		this.advtCode = advtCode;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getName_on_card() {
		return name_on_card;
	}
	public void setName_on_card(String name_on_card) {
		this.name_on_card = name_on_card;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getBilling_name() {
		return billing_name;
	}
	public void setBilling_name(String billing_name) {
		this.billing_name = billing_name;
	}
	public String getBilling_address() {
		return billing_address;
	}
	public void setBilling_address(String billing_address) {
		this.billing_address = billing_address;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public Boolean getTransaction_status() {
		return transaction_status;
	}
	public void setTransaction_status(Boolean transaction_status) {
		this.transaction_status = transaction_status;
	}


}
