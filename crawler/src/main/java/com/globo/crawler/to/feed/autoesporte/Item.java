package com.globo.crawler.to.feed.autoesporte;

import javax.xml.bind.annotation.XmlElement;

public class Item {
	
	@XmlElement
	public String title;
	@XmlElement
	public String link;
	@XmlElement(name="dc:creator")
	public String dccreator;
	@XmlElement
	public String guid;
	@XmlElement
	public String description;
}
