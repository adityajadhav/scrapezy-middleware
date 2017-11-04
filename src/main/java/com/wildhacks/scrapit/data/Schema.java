package com.wildhacks.scrapit.data;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class Schema {
	
	private Map<String, String> props = new HashMap<String, String>();

	@JsonAnySetter
	public void setDynamicProperty(String key, String val) {
		props.put(key, val);
	}

	public Map<String, String> getProps() {
		return props;
	}

	public void setProps(Map<String, String> props) {
		this.props = props;
	}

}
