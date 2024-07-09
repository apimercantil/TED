package com.bancomercantil.msg;


import com.bancomercantil.models.ClientIdentify;
import com.bancomercantil.models.MerchantIdentify;
import com.google.gson.Gson;


public class InboxListRQ {

	private MerchantIdentify merchantIdentify;
	private ClientIdentify  clientIdentify;	
	private String inboxType;
	private String clientId;

	
	public MerchantIdentify getMerchantIdentify() {
		return merchantIdentify;
	}


	public void setMerchantIdentify(MerchantIdentify merchantIdentify) {
		this.merchantIdentify = merchantIdentify;
	}


	public ClientIdentify getClientIdentify() {
		return clientIdentify;
	}


	public void setClientIdentify(ClientIdentify clientIdentify) {
		this.clientIdentify = clientIdentify;
	}


	public String getInboxType() {
		return inboxType;
	}


	public void setInboxType(String inboxType) {
		this.inboxType = inboxType;
	}


	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
