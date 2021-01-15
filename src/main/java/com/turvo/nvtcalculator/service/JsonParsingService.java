package com.turvo.nvtcalculator.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.turvo.nvtcalculator.model.Holding;
import com.turvo.nvtcalculator.model.HoldingPrice;

/**
 * @author parimal
 * This class is used to parsing json url
 *
 */
@Service
public class JsonParsingService implements ParsingService{

	@Autowired
	private RestTemplate restTemplate;
	

	/**
	 * This function is used for parsing Holding from url
	 */
	@Override
	public Map<String, List<Holding>> parseHolding(String url){
		ResponseEntity<List<Holding>> response =
		        restTemplate.exchange(url,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Holding>>() {});
		//convert code to list
		Map<String, List<Holding>> result = response.getBody().parallelStream().
				collect(Collectors.groupingBy(Holding::getDate));
		return result;
	}
	
	
	/**
	 *This function is used to parsing price of holding
	 */
	@Override
	public Map<String, Map<String, HoldingPrice>> parseHoldingPrice(String url) {
		ResponseEntity<List<HoldingPrice>> rateResponse =
		        restTemplate.exchange(url,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<HoldingPrice>>() {});
		
		Map<String, Map<String, HoldingPrice>> result = rateResponse.getBody().parallelStream().collect(Collectors.groupingBy(HoldingPrice::getDate,
				Collectors.toMap(HoldingPrice::getSecurity , price -> price)));

		return result;
	}

}
