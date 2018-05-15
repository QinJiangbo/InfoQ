package com.qinjiangbo.douban;

import com.google.common.io.Files;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @date: 15/05/2018 11:12 AM
 * @author: qinjiangbo@github.io
 * @description:
 */
public class DoubanSpider {

    private static final String SAVE_PATH = "/Users/richard/Documents/DATA/DouBanJ/";
    private static final String FILE_EXT = ".txt";
    private static List<String> seedUrls = new LinkedList<>();
    private static List<String> visitedQueue = new LinkedList<>();
    private static List<String> visitingQueue = new LinkedList<>();

    private static Map<String, String> headers = new HashMap<String, String>(){
        {
            put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            put("accept-encoding", "gzip, deflate, br");
            put("accept-language", "zh-CN,zh;q=0.9,zh-TW;q=0.8");
            put("cache-control", "no-cache");
            put("connection", "keep-alive");
            put("cookie", "ll=\"118254\"; bid=ImRQbQmrNxA; _vwo_uuid_v2=DB3DEC39321E358395CC99C6EB5486C1A|c7c3417d62d33ff580da3e561100c32f; _ga=GA1.2.1000281982.1521273972; push_noty_num=0; push_doumail_num=0; __utmv=30149280.8834; __utmz=30149280.1524915562.13.11.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmc=30149280; _gid=GA1.2.12198537.1526283234; dbcl2=\"88343330:WjqoLnTTA2s\"; ck=CDW2; __utmc=223695111; ap=1; __utma=30149280.1000281982.1521273972.1526283227.1526289212.15; __utmt=1; __utmb=30149280.2.10.1526289212; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1526289215%2C%22https%3A%2F%2Fwww.douban.com%2F%22%5D; _pk_ses.100001.4cf6=*; __utma=223695111.504387865.1521273972.1526283257.1526289215.6; __utmb=223695111.0.10.1526289215; __utmz=223695111.1526289215.6.5.utmcsr=douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/; _pk_id.100001.4cf6=d82ab270b721c214.1521273972.6.1526289345.1526283382.");
            put("host","movie.douban.com");
            put("pragma", "no-cache");
            put("referer", "https://movie.douban.com/top250?start=0&filter=");
            put("upgrade-insecure-requests", "1");
            put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
        }
    };

    public static void main(String[] args) throws IOException {
        start();
        System.out.println("completed!");
    }

    private static void start() throws IOException {
        for(int i=1; i < 11; i++) {
            int num = 25 * (i - 1);
            seedUrls.add("https://movie.douban.com/top250?start=" + num + "&filter=");
        }

        for(String seed : seedUrls) {
            System.out.println("extracting " + seed);
            extractLinks(seed);
        }

        for(String url : visitingQueue) {
            System.out.println("crawling " + url);
            Map<String, String> data = parseContents(url);
            saveContents(data);
        }
    }

    private static void extractLinks(String url) throws IOException {
        Connection connection = Jsoup.connect(url);
        connection.headers(headers);
        Document doc = connection.get();
        Elements elements = doc.select("div.item div.hd a");
        for (Element element : elements) {
            String link = element.attr("href");
            if (!visitedQueue.contains(link)) {
                visitingQueue.add(link);
            }
        }
    }

    private static Map<String, String> parseContents(String url) {
        Connection connection = Jsoup.connect(url);
        connection.headers(headers);
        Document doc = null;
        try {
            doc = connection.get();
        } catch (IOException e) {
            return null;
        }
        String title = doc.select("h1 span").first().text();
        String rating = doc.select("div#interest_sectl strong").first().text();
        String content = doc.select("div#link-report span").first().text();
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("rating", rating);
        map.put("content", content);
        return map;
    }

    private static void saveContents(Map<String, String> data) throws IOException {
        if (data != null) {
            String fileName = "[" + data.get("rating") + "]" + processTitle(data.get("title"))
                    + FILE_EXT;
            File file = new File(SAVE_PATH + fileName);
            Files.write(data.get("content").getBytes(), file);
        }
    }

    private static String processTitle(String title) {
        title = title.replace("...", "");
        title = title.replace("/","|");
        return title;
    }
}
