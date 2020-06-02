package com.cerebrum.jsoupdemo.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;


public class ParsingAndCrawlingJsoupDocument {

    public static void main(String[] args) {
        try {
            String html = "<html><head><title>First parse</title></head>"
                    + "<body><p>Parsed HTML into a doc.</p></body></html>";
            Document doc = Jsoup.parse(html);
            Elements elements = doc.select("p");
            System.out.println(elements.first().text());
            html = "<div><p>Lorem Ispum</p>";
            doc = Jsoup.parseBodyFragment(html);
            Element element = doc.body();
            System.out.println(element.text());
//            doc = Jsoup.connect("http://localhost:4200/").get();
//            System.out.println(doc);
            File in = new File("/home/hrachkhachatryan/IdeaProjects/spring-oath2-example/src/main/resources/templates/login.html");
            doc = Jsoup.parse(in, "UTF-8"); //, "http://localhost:8080/login"
            elements = doc.getElementsByTag("img");
            for(Element el: elements) {
                System.out.println(el.attr("src"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
