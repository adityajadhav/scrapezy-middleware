package com.wildhacks.scrapit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class ScrapItEngine {

	public HashMap<String, String> scrap(String url, List<String> selectors) throws IOException {
		HashMap<String, String> map = new HashMap<String, String>();
		Document doc = Jsoup.connect(url).get();
		String title = doc.title();

		for (String selector : selectors) {
			Element ele = doc.select(selector).first();
			map.put(selector, ele.html());
		}
		return map;

	}

}
