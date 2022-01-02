package com.ymt.sample;

import java.util.concurrent.CountDownLatch;

/**
 * @Description The sample of CountDownLatch
 * @author yumingtao
 * @date 2022-01-02 17:41
 */
public class LatchSample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 0; i < 5; ++i){
            Thread t = new Thread(new FirstBathWorker(latch));
            t.start();
        }

        for (int i = 0; i < 5; ++i){
            Thread t = new Thread(new SecondBatchWorker(latch));
            t.start();
        }

        while(latch.getCount() != 1){
            Thread.sleep(100L);
        }
        System.out.println("Wait for first batch finish.");
        latch.countDown();
        Thread.sleep(1000L);
        System.out.println("Main finish.");
    }
}

class FirstBathWorker implements Runnable{
    private CountDownLatch latch;

    public FirstBathWorker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("First batch executed!");
        latch.countDown();
    }
}

class SecondBatchWorker implements Runnable{
    private CountDownLatch latch;

    public SecondBatchWorker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("Latch count:" + latch.getCount());
            System.out.println("Second batch executed!");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}