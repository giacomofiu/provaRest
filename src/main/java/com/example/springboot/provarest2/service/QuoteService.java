package com.example.springboot.provarest2.service;

import org.springframework.stereotype.Service;

import com.example.springboot.provarest2.Quote;
import com.example.springboot.provarest2.Value;

@Service
public class QuoteService {

	public QuoteService() {}
	
	
	public Quote testQuote() {

	//simulating access to db code...	
	Quote quote = new Quote();
	Value value = new Value();
	
	quote.setType("car");
	value.setId(0026L);
	value.setQuote("60000");
	quote.setValue(value);
	
	return quote;
	
	}
	
	
}
