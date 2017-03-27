package com.qinjiangbo.ofo;

import java.util.concurrent.TimeUnit;

/**
 * @date: 27/03/2017 9:49 PM
 * @author: qinjiangbo@github.io
 */
public class DeadLock {

    private static Object obj1 = new Object();
    private static Object obj2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new T1());
        Thread t2 = new Thread(new T2());
        t1.start();
        t2.start();
    }

    static class T1 implements Runnable {

        @Override
        public void run() {

            try {
                synchronized (DeadLock.obj1) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Trying lock obj2...");
                    synchronized (DeadLock.obj2) {
                        System.out.println("Got lock obj2.");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class T2 implements Runnable {

        @Override
        public void run() {
            try {
                synchronized (DeadLock.obj2) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Trying lock obj1...");
                    synchronized (DeadLock.obj1) {
                        System.out.println("Got lock obj1.");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
