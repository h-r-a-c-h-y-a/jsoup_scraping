package com.cerebrum.jsoupdemo.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupExample {

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
            System.out.println(doc.title());
            Elements newsHeadLines = doc.select("#mp-itn b a");
            for(Element headLine: newsHeadLines) {
                System.out.printf("%s\t%s%n", headLine.attr("title"), headLine.absUrl("href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
