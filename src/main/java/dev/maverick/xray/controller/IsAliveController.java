package dev.maverick.xray.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * App healthcheck endpoint 
 * 
 * @author Shubham Pharande
 *
 */
@RestController
public class IsAliveController {
	
	@GetMapping("/isAlive")
	public String getHealth() {
		return "Ready to peek!";
	}

}
