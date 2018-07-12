package com.tigerjoys.news.service.dto;

import java.util.List;

public class HuaWeiPackageDto {

	private String firmware;
	private String type;
	private String date;
	private String fullUrl;
	private String publicUrl;
	private List<String> typeUrl;
	public String getFirmware() {
		return firmware;
	}
	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFullUrl() {
		return fullUrl;
	}
	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}
	public String getPublicUrl() {
		return publicUrl;
	}
	public void setPublicUrl(String publicUrl) {
		this.publicUrl = publicUrl;
	}
	public List<String> getTypeUrl() {
		return typeUrl;
	}
	public void setTypeUrl(List<String> typeUrl) {
		this.typeUrl = typeUrl;
	}

	
	

}
