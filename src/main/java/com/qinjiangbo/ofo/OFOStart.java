package com.qinjiangbo.ofo;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @date: 12/12/2016 7:26 PM
 * @author: qinjiangbo@github.io
 */
public class OFOStart {

    Timer timer;
    ScrapyOFO scrapyOFO;

    public OFOStart() {
        timer = new Timer();
        System.out.println("定时任务开始了...");
        timer.scheduleAtFixedRate(new RepeatTask(), 0, 30 * 60 * 1000);
        scrapyOFO = new ScrapyOFO();
    }

    class RepeatTask extends TimerTask {

        @Override
        public void run() {
            try {
                System.out.println("@" + new Date().toLocaleString());
                scrapyOFO.request();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new OFOStart();
    }
}
