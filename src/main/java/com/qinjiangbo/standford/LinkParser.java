package com.qinjiangbo.standford;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            Elements elements = document.select("a");
            for (Element element : elements) {
                String href = element.attr("href");
                Pattern pattern = Pattern.compile("video=(.+)?&speed=100");
                Matcher matcher = pattern.matcher(href);
                String title, link;
                if (matcher.find()) {
                    title = matcher.group(1);
                    link = "http://openclassroom.stanford.edu/MainFolder/courses/MachineLearning/videos/" + title + ".mp4";
                    linkQueue.offer(new TitleUrlPair(title, link));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linkQueue;
    }
}