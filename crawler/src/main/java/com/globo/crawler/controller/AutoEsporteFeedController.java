package com.globo.crawler.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globo.crawler.service.AuthenticationService;
import com.globo.crawler.service.AutoEsporteFeedService;
import com.globo.crawler.service.to.AutoEsporteFeedServiceResponse;

@RestController
@RequestMapping("/auto-esporte")
public class AutoEsporteFeedController {
	
	@Autowired
	private AutoEsporteFeedService feedService;
	
	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "/feed")
	public AutoEsporteFeedServiceResponse getAutoEsporteFeed(@RequestHeader String authentication ) throws Exception{
		
		boolean auth = authenticationService.verifyUser(authentication);
		
		if(auth)
			return feedService.getAutoEsporteFeed();
		else
			throw new AuthenticationException("Erro de autenticação!");
	}
}
