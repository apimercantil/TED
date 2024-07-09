package com.bancomercantil.msg;

import java.util.List;

import com.google.gson.Gson;

import com.bancomercantil.models.MerchantIdentify;
import com.bancomercantil.models.FileDetailOut;

public class FileListRS {

	private String processingDate;
	private MerchantIdentify merchantIdentify;
	private List<FileDetailOut> fileList;
	
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

	public List<FileDetailOut> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileDetailOut> fileList) {
		this.fileList = fileList;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
