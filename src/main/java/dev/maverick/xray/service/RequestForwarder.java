package dev.maverick.xray.service;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * The service that forwards the request to the proxy endpoint
 * 
 * @author Shubham Pharande
 *
 */
@Service
public class RequestForwarder {
	
	public ResponseEntity<?> forwardRequest(HttpHeaders headers, String url, Object body, HttpMethod method) {
		
		try {
			RequestEntity<Object> entity = new RequestEntity<Object>(body, headers, method, new URI(url));
			RestTemplate restTemplate = new RestTemplate();
			
			return restTemplate.exchange(url, method, entity, String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
