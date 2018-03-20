package com.globo.crawler.service.to;

import java.util.List;

public class ResponseItem{
	
	public String title;
	public String link;
	public List<ResponseDescription> description;
	@Override
	public String toString() {
		return "ResponseItem [title=" + title + ", link=" + link + ", description=" + description + "]";
	}
	
	
}
