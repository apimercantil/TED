package com.bancomercantil.models;

public class Mobile {

	private String manufacturer;
	private String model;
	private String os_version;
	private Location location;
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getOs_version() {
		return os_version;
	}
	
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
