package com.wildhacks.scrapit.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildhacks.scrapit.ScrapItEngine;
import com.wildhacks.scrapit.data.Response;
import com.wildhacks.scrapit.data.Schema;
import com.wildhacks.scrapit.data.ScrapManger;
import com.wildhacks.scrapit.repo.ScrapRepository;

@RestController
@RequestMapping("api/scrapit")
public class ScrapItController {

	@Autowired
	private ScrapItEngine scrapItEngine;

	@Autowired
	private ScrapRepository scrapRepository;

	@RequestMapping(method = RequestMethod.POST)
	public final void scrap(@RequestParam("tokens") String tokens, @RequestParam("url") String url,
			@RequestBody String schema) throws JsonParseException, JsonMappingException, IOException {

		this.saveScrap(tokens, url, schema);
		/*
		 * ArrayList<String> sel = new ArrayList<String>();
		 * 
		 * Response r = new Response(); Schema s = new Schema();
		 * System.out.println(tokens);
		 * 
		 * System.out.println(schema);
		 * 
		 * ObjectMapper mapper = new ObjectMapper(); Map<String, String> parsed =
		 * mapper.readValue(schema, new TypeReference<Map<String, String>>() { });
		 * 
		 * for (Entry<String, String> E : parsed.entrySet()) {
		 * s.setDynamicProperty(E.getKey().toString(), E.getValue().toString());
		 * System.out.println(E.getKey() + "   " + E.getValue());
		 * 
		 * } String[] tokensStr = tokens.split(","); for (String str : tokensStr) {
		 * sel.add(str); }
		 * 
		 * scrapItEngine.scrap(url, sel);
		 */
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public final Schema scrap(@PathVariable("id") String id)
			throws JsonParseException, JsonMappingException, IOException {

		Schema s = new Schema();
		ObjectMapper mapper = new ObjectMapper();

		List<ScrapManger> scraps = scrapRepository.findByName(id);
		ScrapManger sm = scraps.get(0);
		HashMap<String, String> selectorMap = scrapItEngine.scrap(sm.getUrl(), sm.getTokens());

		Map<String, String> schemaMap = mapper.readValue(sm.getSchema(), new TypeReference<Map<String, String>>() {
		});

		for (Entry<String, String> E : schemaMap.entrySet()) {
			String val = selectorMap.get(E.getValue());
			if (val != null)
				schemaMap.put(E.getKey(), val);
		}

		for (Entry<String, String> E : schemaMap.entrySet()) {
			s.setDynamicProperty(E.getKey().toString(), E.getValue().toString());

		}

		return s;
	}

	private void saveScrap(String tokens, String url, String schema) {
		ScrapManger sm = new ScrapManger();
		sm.setTokens(getTokenArray(tokens));
		sm.setSchema(schema);
		sm.setName("abc");
		sm.setUrl(url);
		scrapRepository.save(sm);
	}

	private ArrayList<String> getTokenArray(String tokens) {
		String[] tokensList = tokens.split(",");
		ArrayList<String> tokensArray = new ArrayList<String>();
		for (String token : tokensList) {
			tokensArray.add(token);
		}
		return tokensArray;
	}

}
