package com.huai.link;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * Created by liangyh on 3/29/16.
 */
public class Queue {

    public void testTransferQueue() throws InterruptedException {
        final TransferQueue<String> transferQueue = new LinkedTransferQueue<>();

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("sub thread2 wait!");
                try {
                    transferQueue.transfer("test2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sub thread2 finished");
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("sub thread wait!");
                    transferQueue.transfer("test");
                    System.out.println("sub thread finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.sleep(1000);

        System.out.println(transferQueue.hasWaitingConsumer());
        System.out.println("queue size = "+transferQueue.size());

        String test = transferQueue.take();
        System.out.println("main thread finished :"+ test);
        String test2 = transferQueue.take();
        System.out.println("main thread finished :"+ test2);

        System.out.println();
        System.out.println("main thread end!");

        executorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        Queue q = new Queue();
        q.testTransferQueue();
    }
}
