package com.turvo.nvtcalculator.service;

import java.util.List;
import java.util.Map;

import com.turvo.nvtcalculator.model.Holding;
import com.turvo.nvtcalculator.model.HoldingPrice;

public interface ParsingService {
	Map<String, List<Holding>>  parseHolding(String url);
	
	Map<String, Map<String, HoldingPrice>>  parseHoldingPrice(String url);
}

