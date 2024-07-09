package com.bancomercantil.msg;

import com.google.gson.Gson;
import com.bancomercantil.models.ClientIdentify;
import com.bancomercantil.models.MerchantIdentify;

/*
 * Purpose: Modelo para request de subir archivo.
 */

public class UploadFileRQ {

	private MerchantIdentify merchantIdentify;
	private ClientIdentify  clientIdentify;	
	private String fileContent;
	private String fileName;
	private String orderType;
	private String shaChecksum;
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

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getShaChecksum() {
		return shaChecksum;
	}

	public void setShaChecksum(String shaChecksum) {
		this.shaChecksum = shaChecksum;
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
