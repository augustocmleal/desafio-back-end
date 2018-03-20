package com.globo.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages="com.globo.crawler")
@ImportResource("classpath:applicationcontext.xml")
public class App 
{
    public static void main( String[] args ) throws Exception 
    {	
    	 SpringApplication.run(App.class, args);
    	 
    }
}
