package com.example.springboot.provarest2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.springboot.provarest2.service.QuoteService;


@SpringBootApplication
public class ProvarestApplication {	//per Tomcat si deve mettere extends SpringBootServletInitializer - funziona anche senza?

	private static final Logger log = LoggerFactory.getLogger(ProvarestApplication.class);
	
	public static void main(String[] args) {
		//SpringApplication.run(ProvarestApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(ProvarestApplication.class, args);
		/*Value v = context.getBean(Value.class);
		v.display();*/
	}

	
	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public QuoteService getQuoteService() {
		return new QuoteService();
	}

	
	//Global CORS configuration
	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/greeting").allowedOrigins("http://192.168.178.23:8080");
			}
		};
	}*/
	
	/*
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject(
					"https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			log.info("RISPOSTA REST Random:");
			log.info(quote.toString());
		};
	}*/
}
