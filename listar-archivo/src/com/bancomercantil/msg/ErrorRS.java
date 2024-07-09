package com.bancomercantil.msg;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.bancomercantil.models.MerchantIdentify;
import com.bancomercantil.models.Status;

/*
 * Purpose: Modelo para response de error.
 */

public class ErrorRS {

	private String processingDate;
	private MerchantIdentify merchantIdentify;
	private ArrayList<Status> errorList=  new ArrayList<Status> ();
	
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

	public ArrayList<Status> getErrorList() {
		return errorList;
	}

	public void setErrorList(ArrayList<Status> errorList) {
		this.errorList = errorList;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
