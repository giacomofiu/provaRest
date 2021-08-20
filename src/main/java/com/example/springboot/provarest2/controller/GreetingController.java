package com.example.springboot.provarest2.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.provarest2.servizirest.Greeting;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	//QUINDI funziona solo se il client javascript fa una richiesta che parte da un server avviato in localhost, altrimenti
	//se lo fai partire senza server solo da un browser il protocollo sara' file://...path/.../provaCORS.html e non funzionera' con questa origin
	//per funzionare senza server mettere origins="*" o togliere tutto, lasciare solo @CrossOrigin e Spring permettera' l'accesso di default a tutte le origins 
	@CrossOrigin(origins = "http://localhost") 
	@RequestMapping(value={"/greeting"}, method=RequestMethod.GET)
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
}
