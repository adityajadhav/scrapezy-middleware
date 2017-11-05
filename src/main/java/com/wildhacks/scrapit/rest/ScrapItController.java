package com.wildhacks.scrapit.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

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

	//final static String base = "http://localhost:8080/api/scrapit/";
	final static String base = "http://ec2-54-162-8-181.compute-1.amazonaws.com:8080/api/scrapit/";

	
	@Autowired
	private ScrapItEngine scrapItEngine;

	@Autowired
	private ScrapRepository scrapRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/history")
	public final ArrayList<String> history() {
		ArrayList<String> urls = new ArrayList<String>();
		List<ScrapManger> history = scrapRepository.findAll();

		for (ScrapManger scrapManger : history) {
			urls.add(base + scrapManger.getName());
		}

		return urls;

	}

	@RequestMapping(method = RequestMethod.POST)
	public final Response scrap(@RequestParam("tokens") String tokens, @RequestParam("url") String url,
			@RequestBody String schema) throws JsonParseException, JsonMappingException, IOException {
		String uri = "";
		Response r = new Response();
		ScrapManger sm = this.saveScrap(tokens, url, schema);
		uri = base + sm.getName();
		r.setUri(uri);
		r.setBase(sm.getUrl());
		return r;
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

	private ScrapManger saveScrap(String tokens, String url, String schema) {

		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		ScrapManger sm = new ScrapManger();
		sm.setTokens(getTokenArray(tokens));
		sm.setSchema(schema);
		sm.setName(randomUUIDString);
		sm.setUrl(url);
		scrapRepository.save(sm);
		return sm;
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
