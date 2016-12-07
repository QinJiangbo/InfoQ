package com.qinjiangbo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @date: 07/12/2016 1:10 PM
 * @author: qinjiangbo@github.io
 */
public class LinkParser {

    private static Queue<TitleUrlPair> linkQueue;

    public static Queue<TitleUrlPair> extractLinks(String url) {
        // 初始化
        linkQueue = new LinkedList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select(".download");
            for (Element element : elements) {
                Element ele0 = element.select("a").get(0);
                Element ele1 = element.select("a").get(1);
                linkQueue.offer(new TitleUrlPair(ele0.text(), ele1.attr("abs:href")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linkQueue;
    }
}