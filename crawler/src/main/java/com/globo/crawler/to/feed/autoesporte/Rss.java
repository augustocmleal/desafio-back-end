package com.globo.crawler.to.feed.autoesporte;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rss {
	
	@XmlElement
	public Channel channel;
	
	
}
