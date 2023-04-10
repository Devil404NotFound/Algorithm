package com.ljp.learning.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class LockTest {
    private static volatile ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<String,Long>();
    private static final AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    private static long total1 = 0L;
    private static long total2 = 0L;

//    {
//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                if(map.isEmpty()) {
//                    return;
//                }
//                ConcurrentHashMap<String, Long> newMap = new ConcurrentHashMap<>();
//                synchronized (map) {
//                    for (Map.Entry<String, Long> entry :map.entrySet()) {
//                        String key = entry.getKey();
//                        Long value = entry.getValue();
//                        newMap.put(key, value);
//                        map.remove(key);
//                    }
//                }
//                // todo
//                for (Map.Entry<String, Long> entry :newMap.entrySet()) {
//                    String key = entry.getKey();
//                    long value = entry.getValue();
//                    if(key.equals("key1")) {
//                        total1 += value;
//                        System.out.println("=======================key1 = " + key + ", total1 = " + value);
//                    }
//                    if(key.equals("key2")) {
//                        total2 += value;
//                        System.out.println("=======================key2 = " + key + ", total2 = " + value);
//                    }
//                }
//            }
//        }, 1,1, TimeUnit.SECONDS);
//    }

    public static void main(String[] args) throws InterruptedException {
        init();
        for (int i = 0; i < 1000; i++) {
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    add("key1");
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    add("key1");
                }
            });
            thread1.start();
            thread2.start();
            Thread.sleep(10);
        }
        Thread.sleep(1000000);
    }

    private static void add(String key) {
        boolean flag = false;
        while(!flag) {
            map.putIfAbsent(key, 0L);
            long value = map.get(key);
            long newValue = value + 1;
            if(atomicBoolean.get()) {
                flag = map.replace(key, value, newValue);
            }
        }
//        System.out.println("============================");
    }

    private static void init() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(map.isEmpty()) {
                    return;
                }
                ConcurrentHashMap<String, Long> newMap = new ConcurrentHashMap<>(map.size());
                atomicBoolean.set(false);

                /*for (Map.Entry<String, Long> entry :map.entrySet()) {
                        String key = entry.getKey();
                        Long value = entry.getValue();
                        newMap.put(key, value);
                        map.remove(key);
                    }*/
                ConcurrentHashMap<String, Long> temp = map;
                map = newMap;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                newMap = temp;
                atomicBoolean.set(true);
                // todo
                for (Map.Entry<String, Long> entry :newMap.entrySet()) {
                    String key = entry.getKey();
                    long value = entry.getValue();
                    if(key.equals("key1")) {
                        total1 += value;
                        System.out.println("=======================key1 = " + key + ", total1 = " + total1);
                    }
                    if(key.equals("key2")) {
                        total2 += value;
                        System.out.println("=======================key2 = " + key + ", total2 = " + total2);
                    }
                }
            }
        }, 1,2, TimeUnit.SECONDS);
    }
}
