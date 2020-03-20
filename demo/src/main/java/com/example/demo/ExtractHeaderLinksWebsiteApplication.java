package com.example.demo;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExtractHeaderLinksWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtractHeaderLinksWebsiteApplication.class, args);
		try {
			String url = "https://www.apple.com/iphone/";
			for (String link : findLinks(url)) {
				String[] lastpart = url.split(".com/");
				for (String element : lastpart) {
					if (link.contains(element))
						System.out.println(link);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static Set<String> findLinks(String url) throws IOException {
		Set<String> links = new HashSet<>();
		Document doc = Jsoup.connect(url).data("query", "Java").userAgent("Mozilla").cookie("auth", "token")
				.timeout(3000).get();
		Elements elements = doc.select("a[href]");
		for (Element element : elements) {
			links.add(element.attr("href"));
		}
		return links;
	}
}