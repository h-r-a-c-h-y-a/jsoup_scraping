package com.cerebrum.jsoupdemo.controller;

import com.cerebrum.jsoupdemo.model.Image;
import com.cerebrum.jsoupdemo.model.TV;
import com.cerebrum.jsoupdemo.model.TvDto;
import com.cerebrum.jsoupdemo.service.TvService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class TvController {

    @Autowired
    private TvService tvService;

    @GetMapping("/items/upload")
    public ResponseEntity<?> uploadItems() throws IOException {
        Document document = Jsoup.connect("https://www.eldorado.ru/c/vse-televizory/f/4746043/").get();
        Element pageCount = document.select("div#__next div.sc-16czxg7-0 div.sc-1qp9ob0-0 div.gmoa76-0 div.o09yu3-0 div[name=\"listing-container\"] div.sc-2bwmto-0 ul li.next").prev().first();
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
            tvService.create(new TV(a.text(), new Image(
                    image.attr("alt"), image.attr("title"),
                    image.attr("abs:src")), span.text()));
        }
        for (int i = 0; i < Integer.parseInt(pageCount.text().trim()) -1 ; i++) {
            document = Jsoup.connect( document.select("div#__next div.sc-16czxg7-0 div.sc-1qp9ob0-0 div.gmoa76-0 div.o09yu3-0 div[name=\"listing-container\"] div.sc-2bwmto-0 ul li.next a").first().attr("abs:href")).get();
            itemList = document.select("div#__next div.sc-16czxg7-0 div.sc-1qp9ob0-0 div.gmoa76-0 div.o09yu3-0 div[name=\"listing-container\"] ul li[typeof=\"Product\"]");
            media = itemList.select("div div.sc-1iriqgr-3 a img");
            links = itemList.select("div div.sc-1iriqgr-23 div.sc-1iriqgr-6 div.sc-1iriqgr-7 a");
            prices = itemList.select("div div.sc-1iriqgr-23 div.sc-1gnxlbs-0 span.sc-1gnxlbs-1");
            if (media.size() != prices.size()) break;
            for (int y = 0; y < itemList.size() - 1; y++) {
                image = media.get(y);
                a = links.get(y);
                span = prices.get(y);
                tvService.create(new TV(a.text(), new Image(
                        image.attr("alt"), image.attr("title"),
                        image.attr("abs:src")), span.text()));
            }
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/items")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer page,
                                    @RequestParam(value = "limit") Integer limit) {
        List<TV> tvList = tvService.getAllBy(page, limit);
//        List<TvDto> tvDtos = tvList.stream().map(TvDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(tvList);

    }
}
