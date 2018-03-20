package com.globo.crawler.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.globo.crawler.entity.feed.autoesporte.Item;
import com.globo.crawler.entity.feed.autoesporte.Rss;
import com.globo.crawler.service.to.AutoEsporteFeedServiceResponse;
import com.globo.crawler.service.to.ResponseDescription;
import com.globo.crawler.service.to.ResponseItem;
@Service
public class AutoEsporteFeedService {
	
	public AutoEsporteFeedServiceResponse getAutoEsporteFeed(){
		
		try {
			
			URL url = new URL("https://revistaautoesporte.globo.com/rss/ultimas/feed.xml"); 

	        URLConnection si = url.openConnection();
	        InputStream is=si.getInputStream();
	       
	        JAXBContext context = JAXBContext.newInstance(Rss.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Rss rss = (Rss) unmarshaller.unmarshal(is);

            return autoEsporteJsonResponse(rss);
		
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
		
		
	}
	
	public AutoEsporteFeedServiceResponse autoEsporteJsonResponse(Rss rss){
		
		AutoEsporteFeedServiceResponse autoEsporteFeedServiceResponse = new AutoEsporteFeedServiceResponse();
		autoEsporteFeedServiceResponse.feed = new ArrayList<HashMap<String, Object>>();
		
		for(Item item : rss.channel.itens){
			ResponseItem responseItem = new ResponseItem();
			
			responseItem.title = item.title;
			responseItem.link = item.link;
			responseItem.description = new ArrayList<ResponseDescription>();
			
			Document doc = Jsoup.parse(item.description, "UTF-8");
			
			Elements ps = doc.getElementsByTag("p");
			for (Element p : ps){
				ResponseDescription responseDescription = new ResponseDescription();
				responseDescription.type = "text";
				responseDescription.content = p.text();
				responseItem.description.add(responseDescription);
			}
			
			Elements imgs = doc.getElementsByTag("img");
			for (Element img : imgs){
				ResponseDescription responseDescription = new ResponseDescription();
				responseDescription.type = "img";
				responseDescription.content = img.attr("src");
				responseItem.description.add(responseDescription);
			}
			
			Elements uls = doc.getElementsByTag("ul");
			for (Element ul : uls){
				Elements lis = ul.getElementsByTag("li");
				
				ResponseDescription responseDescription = new ResponseDescription();
				
				String[] contents = new String[lis.size()];
				for (int i =0; i< lis.size(); i++){
					Elements as = lis.get(i).getElementsByTag("a");
					
					if(!as.isEmpty()){
						if(as.get(0).attr("href") != null){
							responseDescription.type = "link";
							contents[i] = as.get(0).attr("href");
						}
					}
					
				}
			
				responseDescription.content = contents;
				responseItem.description.add(responseDescription);
			}
			HashMap<String, Object> hashItem = new HashMap<String, Object>();
			hashItem.put("Item", responseItem);
			autoEsporteFeedServiceResponse.feed.add(hashItem);
			
		}
		
		
		return autoEsporteFeedServiceResponse;
	}
	
	
}
