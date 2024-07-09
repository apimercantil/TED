package com.bancomercantil.msg;

import com.google.gson.Gson;
import com.bancomercantil.models.ClientIdentify;
import com.bancomercantil.models.MerchantIdentify;

public class DownloadFileRQ {

	private MerchantIdentify merchantIdentify;
	private ClientIdentify  clientIdentify;	
	private String fileId;
	private String orderType;

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
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
