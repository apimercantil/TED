package com.bancomercantil.models;

public class ClientIdentify {

	private String ipaddress;
	private String browserAgent;
	private Mobile mobile;
	
	public String getIpaddress() {
		return ipaddress;
	}
	
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	public String getBrowserAgent() {
		return browserAgent;
	}

	public void setBrowserAgent(String browserAgent) {
		this.browserAgent = browserAgent;
	}

	public Mobile getMobile() {
		return mobile;
	}
	
	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}
	
	
}
