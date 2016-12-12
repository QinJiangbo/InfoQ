package com.qinjiangbo.ofo;

import java.io.IOException;

/**
 * @date: 12/12/2016 7:26 PM
 * @author: qinjiangbo@github.io
 */
public class OFOStart {

    public static void main(String[] args) throws IOException {
        ScrapyOFO scrapyOFO = new ScrapyOFO();
        scrapyOFO.request();
    }
}
