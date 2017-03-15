package com.qinjiangbo.standford;

/**
 * @date: 07/12/2016 1:28 PM
 * @author: qinjiangbo@github.io
 */
public class TitleUrlPair {

    public TitleUrlPair(String title, String url) {
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "TitleUrlPair{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
