package com.turvo.nvtcalculator.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.nvtcalculator.model.Holding;
import com.turvo.nvtcalculator.model.HoldingPrice;
import com.turvo.nvtcalculator.service.ParsingService;
import com.turvo.nvtcalculator.utils.Utils;

@RestController
public class NavCalculatorController {
	private static final String JSON_HOLDING_URL = "https://raw.githubusercontent.com/arcjsonapi/HoldingValueCalculator/master/api/holding";
	private static final String JSON_PRICE_URL = "https://raw.githubusercontent.com/arcjsonapi/HoldingValueCalculator/master/api/pricing";
	@Autowired
	private ParsingService parsingService;
	
	public Map<String, List<Holding>> getHolding() {
		Map<String, List<Holding>> holdingsByDate = parsingService.parseHolding(JSON_HOLDING_URL);
		System.out.println(holdingsByDate);
		return holdingsByDate;
	}
	
	public Map<String, Map<String, HoldingPrice>> getHoldingPrice() {
		Map<String, Map<String, HoldingPrice>> priceBySecurityAndDate = parsingService.parseHoldingPrice(JSON_PRICE_URL);
		System.out.println(priceBySecurityAndDate);
		return priceBySecurityAndDate;
	}
	
	
	/**
	 * @param date
	 * @return
	 * This function is used to return sum of holding for holding date
	 */
	@PostMapping("/date")
	public String getHoldingSumByDate(@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy")Date date) {
		String parseDate = Utils.parseDateToString(date);
		Double holdingValue = sumOfHoldingAndPrice(parseDate);
		return "Holding Value: " + holdingValue;
	}
	
	private Double sumOfHoldingAndPrice(String date) {
		List<Holding>  holdings = getHolding().get(date);
		Map<String, HoldingPrice> priceList = getHoldingPrice().get(date);
		return holdings.stream().collect(
			     Collectors.summingDouble(holding ->holding.getQuantity() * priceList.get(holding.getSecurity()).getPrice()));		
	}
}
