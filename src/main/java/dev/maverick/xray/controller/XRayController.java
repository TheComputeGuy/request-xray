package dev.maverick.xray.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import dev.maverick.xray.model.RequestResponse;
import dev.maverick.xray.repository.RequestResponseRepository;
import dev.maverick.xray.service.RequestForwarder;

/**
 * 
 * The XRay Controller that peeks into your request
 * 
 * @author Shubham Pharande
 *
 */
@Controller
public class XRayController {
	
	@Autowired
	RequestResponseRepository requestResponseRepository;
	
	@Autowired
	RequestForwarder requestForwarder;
	
	@GetMapping(value = "/interceptor/**")
	public ResponseEntity<?> getRequestXray(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
		
		String requestUrl = getForwardUrl(request);
		
		if (validateRequestUrl(requestUrl)) {
			try {
				RequestResponse requestResponse = new RequestResponse();
				requestResponse.setRequestType(HttpMethod.GET.name());
				requestResponse.setUrl(requestUrl);
				requestResponse.setRequestHeaders(headers.toString());
				
				ResponseEntity forwardedResponseEntity = requestForwarder.forwardRequest(headers, requestUrl, null, HttpMethod.GET);
				
				requestResponse.setResponseBody(forwardedResponseEntity.getBody().toString());
				requestResponse.setResponseHeaders(forwardedResponseEntity.getHeaders().toString());
				requestResponse.setResponseStatus(forwardedResponseEntity.getStatusCode().toString());
				
				try {
					requestResponseRepository.save(requestResponse);
				} catch (Exception e) {
					System.err.println("Exception while saving to DB!");
					e.printStackTrace();
					// do not fail
				}
				
				return forwardedResponseEntity;
				
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}			
		}
		System.err.println("Not a valid URL!");
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/interceptor/**")
	public ResponseEntity<?> postRequestXray(
			@RequestHeader HttpHeaders headers,
			@RequestBody Object requestBody,
			HttpServletRequest request
			) {
		
		String requestUrl = getForwardUrl(request);
		
		if (validateRequestUrl(requestUrl)) {
			try {
				RequestResponse requestResponse = new RequestResponse();
				requestResponse.setRequestType(HttpMethod.POST.name());
				requestResponse.setUrl(requestUrl);
				requestResponse.setRequestHeaders(headers.toString());
				requestResponse.setRequestBody(requestBody.toString());
				
				ResponseEntity forwardedResponseEntity = requestForwarder.forwardRequest(headers, requestUrl, requestBody, HttpMethod.POST);
				
				requestResponse.setResponseBody(forwardedResponseEntity.getBody().toString());
				requestResponse.setResponseHeaders(forwardedResponseEntity.getHeaders().toString());
				requestResponse.setResponseStatus(forwardedResponseEntity.getStatusCode().toString());
				
				try {
					requestResponseRepository.save(requestResponse);
				} catch (Exception e) {
					System.err.println("Exception while saving to DB!");
					e.printStackTrace();
					// do not fail
				}
				
				return forwardedResponseEntity;
				
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
			}			
		}
		System.err.println("Not a valid URL!");
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value="/store")
	public String getAllStoredRequests(Model model) {
		Iterable<RequestResponse> requestsInStore = requestResponseRepository.findAll();
		if (requestsInStore.iterator().hasNext()) {
			model.addAttribute("requests", requestsInStore);
		}
		return "store";
	}
	
	@DeleteMapping(value = "/store")
	public void emptyDatabase() {
		requestResponseRepository.deleteAll();
	}
	
	/**
	 * @param request
	 * @return the URL to proxy the request to
	 */
	private String getForwardUrl(HttpServletRequest request) {
		String fullUrl = request.getRequestURI();
		String partialUrl = fullUrl.split("/interceptor/")[1];
		String queryParams = request.getQueryString();
		
		if (queryParams != null) {
			return partialUrl + "?" + queryParams;
		} else {
			return partialUrl;
		}

	}
	
	/**
	 * @param url
	 * @return whether or not the request is valid
	 */
	private boolean validateRequestUrl(String url) {
		String regex = "\\b(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		
		try {
			return Pattern.matches(regex, url);
		} catch (Exception e) {
			return false;
		}
		
	}

}
