package com.cerebrum.jsoupdemo.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ListLinks {
    private static final String URL = "https://www.google.com/search?q=country+flags+icons&sxsrf=ACYBGNTQ7exdWXYKx2t8OCGq6quVD0peTA:1580733905730&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiAzb3XtLXnAhVKA2MBHWXQBBkQ_AUoAXoECAwQAw&biw=1244&bih=929";

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements links = doc.select("a[href]");
            Elements media = doc.select("[src]");
            Elements imports = doc.select("link[href]");
            System.out.printf("%nMedia: (%d)%n", media.size());
            for(Element src: media) {
                if (src.tagName().equals("img") && !src.attr("abs:src").equals("")) {
                    System.out.printf(" <%s src=\"%s\" style=\"width=%s; height=%s\" alt=\"%s\"/>%n",
                            src.tagName(), src.attr("abs:src"),
                            src.attr("width"), src.attr("height"),
                            trim(src.attr("alt"), 20));
                }
            }
            for(Element link: links) {
                if (!link.attr("abs:href").equals(""))
                System.out.printf(" * a: <%s> (%s)%n", link.attr("abs:href"),
                        trim(link.text(), 35));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}
