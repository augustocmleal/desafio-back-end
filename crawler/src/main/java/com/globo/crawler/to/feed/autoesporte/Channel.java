package com.globo.crawler.to.feed.autoesporte;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Channel {

	@XmlElement
	public String title;
	@XmlElement
	public String link;
	@XmlElement
	public String description;
	@XmlElement
	public String language;
	@XmlElement
	public String copyright;
	@XmlElement
	public Image image;
	@XmlElement(name="item")
	public List<Item> itens;
}
