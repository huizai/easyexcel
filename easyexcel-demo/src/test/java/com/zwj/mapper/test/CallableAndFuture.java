package com.zwj.mapper.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture {
    static class MyThread implements Callable<String> {
        @Override
        public String call() throws Exception {
        	Thread.sleep(10*1000);
            return "Hello world";
        }
    }

    static class MyThread2 implements Runnable {
        @Override
        public void run() {

        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new MyThread());
        while(true) {
        	try {
        		System.out.println(future.isDone());
        		if(future.isDone()) {
        			System.out.println(future.get());
                    Thread.currentThread().sleep(500);
        		}
                
            } catch (Exception e) {

            } finally {
                threadPool.shutdown();
            }
        }
        
    }
}