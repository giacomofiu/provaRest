package com.example.springboot.provarest2.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.springboot.provarest2.Quote;
import com.example.springboot.provarest2.service.QuoteService;

@Controller
public class IndexController {
	
	//dependency injection in SpringBoot - il @Bean e' dichiarato nella classe ProvarestApplication che e' annotata con 
	//@SpringBootApplication e qui si richiama con @Autowired - non so se funziona se dichiari il Bean in un altra classe che non e' annotata con @SpringBootApplication
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	QuoteService quoteService;
	
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping(value={"/"}, method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
	public String home() {
		
		return "home-page";
	}
	
	
	//se non metti @ResponseBody spring torna errore perchè cerca la jsp chiamaRest.jsp e non la trova, nella pagina ci sarà il testo /provarest/WEB-INF/jsp/chiamaRest.jsp
	//il MIME type application/json credo sia facoltativo
	@ResponseBody		
	@RequestMapping(value={"/chiamaRest"}, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Quote restCall() {

		//non serve lo fa Spring
		//RestTemplate restTemplate = new RestTemplate();
		
		String randomRestUrl = "https://gturnquist-quoters.cfapps.io/api/random";  //doesn't work anymore
		//ResponseEntity<String> response = restTemplate.getForEntity(randomRestUrl, String.class);	 //se usi @RestController o @ResponseBody e ritorni il JSON come stringa
		Quote quote; 
		//quote = restTemplate.getForObject(randomRestUrl, Quote.class); //doesn't work anymore
		
		quote = quoteService.testQuote();
		
		//log.info(response.getBody());   //se usi @RestController o @ResponseBody e ritorni il JSON come stringa
		
		log.info("RISPOSTA REST Random:");		
		log.info(quote.toString());
		
		return quote;
	}
	
	
	//questo metodo torna lo status e il json nel body
	//da quanto ho capito cosi viene bypassata la dispatcher servlet perche' si modifica in modo quasi diretto la response http
	@ResponseBody
	@RequestMapping(value={"/chiamaRestStatus"}, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Quote> restCallWithStatus() {

		String randomRestUrl = "https://gturnquist-quoters.cfapps.io/api/random";  //does not work anymore
		//ResponseEntity<String> response = restTemplate.getForEntity(randomRestUrl, String.class);	 //se usi @RestController o @ResponseBody e ritorni il JSON come stringa
		Quote quote ;
		//quote = restTemplate.getForObject(randomRestUrl, Quote.class); //does not work anymore
		
		quote = quoteService.testQuote();
		
		//log.info(response.getBody());   //se usi @RestController o @ResponseBody e ritorni il JSON come stringa
		
		log.info("RISPOSTA REST Random:");		
		log.info(quote.toString());
		
		return ResponseEntity.status(HttpStatus.OK).body(quote); 
	}
	
	
	//manual response
	@RequestMapping(value={"/manualRes"}, method=RequestMethod.GET)
	public void manualRes(HttpServletResponse response) throws IOException {
	    response.setHeader("Custom-Header", "foo");
	    response.setStatus(200);
	    //response.getWriter().println("Hello World!");
	    
	    Quote quote = quoteService.testQuote();
	    response.getWriter().println(quote);
	}

}
