package com.peliculas.prueba.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.peliculas.prueba.controller.PeliculaLikeController;

@Component
public class Consumer {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${endpoint.validate-token}")
	private String validateTokenEndpoint;

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	public boolean validateToken(String token) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", token);
	    HttpEntity<String> request = new HttpEntity<>(headers);
	    try {
	        ResponseEntity<String> response = restTemplate.exchange(validateTokenEndpoint, HttpMethod.POST, request, String.class);
	        logger.info(response.getBody());
	        if(response.getStatusCode() == HttpStatus.OK) {
	            return true;
	        }
	    } catch (HttpClientErrorException e) {
	        if(e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
	            return false;
	        }
	    }
	    return false;
	}
}
