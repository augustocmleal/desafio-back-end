package com.globo.crawler.service;

import org.junit.Test;

public class FeedXmlServiceTest {

	
	@Test
	public void getFeedTest(){
		
		AutoEsporteFeedService service = new AutoEsporteFeedService();
		
		service.getAutoEsporteFeed();
	}
}
