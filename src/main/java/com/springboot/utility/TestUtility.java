package com.springboot.utility;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class TestUtility {

	public String utilityName;
	
	public void setUtilityName(String utilityName) {
		this.utilityName = utilityName;
	}
	
	public String getUtilityName() {
		return this.utilityName;
	}
}
