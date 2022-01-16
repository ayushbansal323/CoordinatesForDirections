package com.locus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This is default controller to check whether server is up and running
 * @author Ayush
 */
@Controller
public class DefaultController {
	
	@RequestMapping(path="/", method = RequestMethod.GET)
	public ResponseEntity<String> start()
	{
		return ResponseEntity.ok("server has started");
	}
}
