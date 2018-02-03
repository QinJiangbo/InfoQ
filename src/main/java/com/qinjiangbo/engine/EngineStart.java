package com.qinjiangbo.engine;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @date: 07/12/2016 2:04 PM
 * @author: qinjiangbo@github.io
 */
public class EngineStart {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Queue<TitleUrlPair> titleUrlPairs = LinkParser.extractLinks("http://ppt.geekbang.org/qconsh2017");
        List<Thread> list = new LinkedList<>();

        for (TitleUrlPair titleUrlPair: titleUrlPairs) {
            Thread thread = new Downloader(titleUrlPair, "Shanghai2017");
            list.add(thread);
            thread.start();
        }

        titleUrlPairs = LinkParser.extractLinks("http://ppt.geekbang.org/assz2017");
        for (TitleUrlPair titleUrlPair: titleUrlPairs) {
            Thread thread = new Downloader(titleUrlPair, "Shenzhen2017");
            list.add(thread);
            thread.start();
        }

        // 等待所有线程完成
        for (Thread thread : list) {
            while (thread.isAlive()){

            };
        }
        long end = System.currentTimeMillis();
        System.out.println("下载完毕！耗时: " + (end - start)/60000 + "分钟");
    }
}
