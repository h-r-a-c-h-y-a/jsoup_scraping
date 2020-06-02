package com.cerebrum.jsoupdemo.util;

import com.cerebrum.jsoupdemo.model.Image;
import com.cerebrum.jsoupdemo.model.TV;
import com.cerebrum.jsoupdemo.repository.TvRepository;
import com.cerebrum.jsoupdemo.service.TvService;
import com.cerebrum.jsoupdemo.service.impl.TvServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class TvListScraper {

    @Autowired
    private static TvRepository repo;
    private static final List<TV> TV_LIST = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.eldorado.ru/c/vse-televizory/f/4746043/").get();
        Elements itemList = document.select("div#__next div.sc-16czxg7-0 div.sc-1qp9ob0-0 div.gmoa76-0 div.o09yu3-0 div[name=\"listing-container\"] ul li[typeof=\"Product\"]");
        Elements media = itemList.select("div div.sc-1iriqgr-3 a img");
        Elements links = itemList.select("div div.sc-1iriqgr-23 div.sc-1iriqgr-6 div.sc-1iriqgr-7 a");
        Elements prices = itemList.select("div div.sc-1iriqgr-23 div.sc-1gnxlbs-0 span.sc-1gnxlbs-1");
        Element image = null;
        Element a = null;
        Element span = null;
        for (int i = 0; i < itemList.size(); i++) {
            image = media.get(i);
            a = links.get(i);
            span = prices.get(i);
            TV_LIST.add(new TV(a.text(), new Image(image.attr("alt"), image.attr("title"),image.attr("abs:src")), span.text()));
        }
        TvService service = new TvServiceImpl();
        TV_LIST.forEach(tv -> service.create(tv));
    }
}
