package com.news.common.core.constant;

import java.util.HashMap;
import java.util.Map;

public enum EYesNo implements BaseEnum<EYesNo, String> {
	NO("0", "否"), 
	YES("1", "是")
	;
	
	private String value;
	private String displayName;

	EYesNo(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
	
	static Map<String,EYesNo> enumMap = new HashMap<String, EYesNo>(); 
	static {
		for(EYesNo en : EYesNo.values()){
			enumMap.put(en.getValue(), en);
		}
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public String getDisplayName() {
		return this.displayName;
	}
	
	public static EYesNo getEnum(String value) {
		return enumMap.get(value);
	}
}
