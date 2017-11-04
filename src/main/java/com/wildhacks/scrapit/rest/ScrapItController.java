package com.wildhacks.scrapit.rest;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildhacks.scrapit.data.Response;
import com.wildhacks.scrapit.data.Schema;

@RestController
@RequestMapping("api/scrapit")
public class ScrapItController {

	@RequestMapping(method = RequestMethod.POST)
	public final Schema scrap(@RequestParam("tokens") String tokens, @RequestBody String schema)
			throws JsonParseException, JsonMappingException, IOException {

		Response r = new Response();
		Schema s = new Schema();
		System.out.println(tokens);

		System.out.println(schema);

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> parsed = mapper.readValue(schema, new TypeReference<Map<String, Object>>() {
		});

		for (Entry<String, Object> E : parsed.entrySet()) {
			s.setDynamicProperty(E.getKey().toString(), E.getValue().toString());
			System.out.println(E.getKey() + "   " + E.getValue());

		}
		return s;
	}

}
