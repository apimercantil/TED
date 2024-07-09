package com.bancomercantil.models;

public class MerchantIdentify {

	private Long integratorId;
	private Long merchantId;
	private String merchantName;
	private String terminalId;
	private String masterkey;
	private String accountnumber;
	
	public Long getIntegratorId() {
		return integratorId;
	}
	
	public void setIntegratorId(Long integratorId) {
		this.integratorId = integratorId;
	}
	
	public Long getMerchantId() {
		return merchantId;
	}
	
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	
	public String getMerchantName() {
		return merchantName;
	}
	
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	public String getTerminalId() {
		return terminalId;
	}
	
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	
	public String getMasterkey() {
		return masterkey;
	}
	
	public void setMasterkey(String masterkey) {
		this.masterkey = masterkey;
	}
	
	public String getAccountnumber() {
		return accountnumber;
	}
	
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	
}
