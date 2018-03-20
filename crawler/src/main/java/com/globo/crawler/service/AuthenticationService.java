package com.globo.crawler.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globo.crawler.repository.UserRepository;
@Service
public class AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;

	public boolean verifyUser(String authentication) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		authentication = authentication.replace("Basic ", "");
		byte[] byteAuthentication = Base64.decodeBase64(authentication);
		String strAuth = new String (byteAuthentication);
		String name = strAuth.split(":")[0];
		String hashedPassword = strAuth.split(":")[1];
		
		boolean response = userRepository.getUserByNameAndPassword(name, hashedPassword);
		
		return response;
	}
}
