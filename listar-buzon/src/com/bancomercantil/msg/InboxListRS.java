package com.bancomercantil.msg;

import java.util.List;

import com.bancomercantil.models.MerchantIdentify;
import com.google.gson.Gson;

public class InboxListRS {

	private String processingDate;
	private MerchantIdentify merchantIdentify;
	private List<String> inboxList;
	
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



	public List<String> getInboxList() {
		return inboxList;
	}



	public void setInboxList(List<String> inboxList) {
		this.inboxList = inboxList;
	}



	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
