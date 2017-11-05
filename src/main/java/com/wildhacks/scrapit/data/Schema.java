package com.wildhacks.scrapit.data;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class Schema {

	private Map<String, String> scrapezy = new HashMap<String, String>();

	@JsonAnySetter
	public void setDynamicProperty(String key, String val) {
		scrapezy.put(key, val);
	}

	public Map<String, String> getScrapezy() {
		return scrapezy;
	}

	public void setScrapezy(Map<String, String> scrapezy) {
		this.scrapezy = scrapezy;
	}

}
