package com.news.common.core.dto;

import java.util.UUID;

public class IdentityDto extends BaseDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String identity;

	public String getIdentity() {
		if(identity==null || "".equals(identity.trim())){
			identity = UUID.randomUUID().toString().replaceAll("-", "");
		}
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
}
