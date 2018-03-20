package com.globo.crawler.service;

import org.junit.Assert;
import org.junit.Test;

import com.globo.crawler.service.to.AutoEsporteFeedServiceResponse;


public class FeedXmlServiceTest {

	
	@Test
	public void getFeedTest(){
		
		AutoEsporteFeedService service = new AutoEsporteFeedService();
		
		AutoEsporteFeedServiceResponse autoEsporteFeedServiceResponse = service.getAutoEsporteFeed();
		
	
		Assert.assertTrue(!autoEsporteFeedServiceResponse.feed.isEmpty());
		
	}
}
