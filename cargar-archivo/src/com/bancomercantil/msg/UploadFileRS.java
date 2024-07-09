package com.bancomercantil.msg;

import com.google.gson.Gson;

import com.bancomercantil.models.MerchantIdentify;


/*
 * Purpose: Modelo para response de subir archivo.
 */

public class UploadFileRS {

	private String processingDate;
	private MerchantIdentify merchantIdentify;
	private String fileId;	
	
	public String getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(String processingDate) {
		this.processingDate = processingDate;
	}

	public MerchantIdentify getMerchantIdentify() {
		return merchantIdentify;
	}

	public void setMerchantIdentify(MerchantIdentify merchantIdentify) {
		this.merchantIdentify = merchantIdentify;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
