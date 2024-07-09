package com.bancomercantil.msg;


import java.math.BigDecimal;

import com.google.gson.Gson;

import com.bancomercantil.models.MerchantIdentify;

public class DownloadFileRS {

	private String processingDate;
	private MerchantIdentify merchantIdentify;
	private String shaChecksum;
	private String fileContent;
	private String fileName;
	private String fileType;
	private BigDecimal moreFiles;
	
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
	
	public String getShaChecksum() {
		return shaChecksum;
	}

	public void setShaChecksum(String shaChecksum) {
		this.shaChecksum = shaChecksum;
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

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public BigDecimal getMoreFiles() {
		return moreFiles;
	}

	public void setMoreFiles(BigDecimal moreFiles) {
		this.moreFiles = moreFiles;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
