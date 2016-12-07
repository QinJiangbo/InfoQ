package com.qinjiangbo;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @date: 07/12/2016 1:09 PM
 * @author: qinjiangbo@github.io
 */
public class Downloader extends Thread {

    private String saveLocation = "/Users/Richard/Downloads/InfoQ-ArchSummit/";
    private TitleUrlPair titleUrlPair;

    public Downloader(TitleUrlPair titleUrlPair, String saveLocation) {
        this.titleUrlPair = titleUrlPair;
        this.saveLocation += saveLocation + "/";
    }

    @Override
    public void start() {
        try {
            URL url = new URL(this.titleUrlPair.getUrl());
            InputStream inputStream = url.openStream();
            String name = this.titleUrlPair.getTitle();
            File file = new File(saveLocation + name + ".pdf");
            OutputStream outputStream = new FileOutputStream(file);
            copyStream(inputStream, outputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从输入流导入输出流
     * @param inputStream
     * @param outputStream
     */
    private void copyStream(InputStream inputStream, OutputStream outputStream) {
        byte[] buf = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
