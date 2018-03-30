package com.qinjiangbo.acs;

import com.google.common.io.Files;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @date: 30/03/2018 7:20 PM
 * @author: qinjiangbo@github.io
 * @description:
 */
public class AcsPostTracker {

    // 淘宝浏览器UA
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11";
    // 网站URL前缀
    private static final String URL_PREFIX = "https://acs.qinjiangbo.com";
    // 保存本机地址
    private static final String SAVING_FOLDER = "/Users/richard/Downloads/ACS-Posts/";

    /**
     * 得到链接列表
     * @param url
     * @return
     * @throws IOException
     */
    private static List<String> listLinks(String url) throws IOException {
        List<String> postLinks = new ArrayList<>();
        Document document = Jsoup.connect(url)
                .header("User-Agent", USER_AGENT)
                .get();
        Elements elements = document.select("div[class=blog-list]");
        for (Element element : elements) {
            postLinks.add(element.select("a").first().attr("href"));
        }
        return postLinks;
    }

    /**
     * 获取页面内容
     * @param links
     */
    private static void parsePosts(List<String> links) throws IOException,
            InterruptedException {
        int size = links.size();
        for (int i = 0; i < size; i++) {
            Document document = Jsoup.connect(links.get(i))
                    .header("User-Agent", USER_AGENT)
                    .get();
            Elements elements = document.select("div[class=blog-post]");
            String text = elements.first().text();
            File file = new File(SAVING_FOLDER + (i + 1) + ".txt");
            Files.write(text.getBytes(), file);
            // 每隔30个链接暂停一分钟
            if ((i+1) % 30 == 0) {
                System.out.println("Starting sleeping for 60 seconds...");
                Thread.sleep(65000);
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> links = new ArrayList<>();
        for (int i = 11; i < 111; i++) {
            links.add(URL_PREFIX + "/sports/post/" + i + ".html");
        }
        parsePosts(links);
    }
}
