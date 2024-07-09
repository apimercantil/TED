package com.bancomercantil.msg;

import com.google.gson.Gson;
import com.bancomercantil.models.ClientIdentify;
import com.bancomercantil.models.MerchantIdentify;

public class FileListRQ {

	private MerchantIdentify merchantIdentify;
	private ClientIdentify  clientIdentify;	
	private String fileId;
	private String fromDate;
	private String toDate;
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

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
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
