package com.qinjiangbo.standford;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

/**
 * @date: 15/03/2017 2:13 PM
 * @author: qinjiangbo@github.io
 */
public class YoutubeTracker {

    public static void parseLinks(String html) throws IOException {
        Document document = Jsoup.parse(html);
        Elements elements = document.select("td.pl-video-title a");
        StringBuilder content = new StringBuilder();
        for (Element element : elements) {
            String href = element.attr("href");
            if (href.startsWith("/watch?")) {
                content.append("https://www.youtube.com" + element.attr("href") + "\n");
            }
        }
        writeVideoList(content.toString(), "/Users/Richard/Downloads/videoList.txt");
    }

    private static String extractFile(String location) throws IOException {
        File file = new File(location);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String newLine = "";
        while ((newLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(newLine);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    private static void writeVideoList(String content, String location) throws IOException {
        File file = new File(location);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(content);
        bufferedWriter.close();
    }

    public static void main(String[] args) throws IOException {
        parseLinks(extractFile("/Users/Richard/Downloads/ml-list.txt"));
    }
}
