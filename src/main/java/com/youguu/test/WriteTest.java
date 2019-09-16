package com.youguu.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WriteTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 3000; i++) {
            list.add(i);
        }

        Map<Integer, TechniStock> map = new ConcurrentHashMap<>();

        List<Integer> list1 = list.subList(0, 1000);
        List<Integer> list2 = list.subList(1000, 2000);
        List<Integer> list3 = list.subList(2000, 3000);

        ExecutorService service = Executors.newFixedThreadPool(3);

        long start = System.currentTimeMillis();

        Future<Map<Integer, TechniStock>> future = service.submit(new Receive(list1, map));

        map =  future.get();

        Future<Map<Integer, TechniStock>> future2 = service.submit(new Receive(list2, map));

        map = future2.get();

        Future<Map<Integer, TechniStock>> future3 = service.submit(new Receive(list3, map));

        map = future3.get();

        System.out.println(future.isDone());
        System.out.println(future2.isDone());
        System.out.println(future3.isDone());

        List<Integer> integerList = new ArrayList<>();
        int k = 0;
        for (Map.Entry entry: map.entrySet()){

            System.out.println(k++);
            integerList.add((Integer) entry.getKey());
        }

        Collections.sort(integerList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1>=o2?1:-1;
            }
        });

        for (int i = 0; i < integerList.size(); i++) {
            System.out.println(integerList.get(i));
        }

        long end = System.currentTimeMillis();

        System.out.println("耗时："+(end-start)+"ms");

        service.shutdown();

        System.out.println(service.isShutdown());
    }


}

class Receive implements Callable<Map<Integer, TechniStock>> {

    private List<Integer> stockCodeList;

    private Map<Integer, TechniStock> map;

    public  Receive(List<Integer> stockCodeList, Map<Integer, TechniStock> map){
        this.stockCodeList = stockCodeList;
        this.map = map;
    }

    @Override
    public Map<Integer, TechniStock> call() throws Exception {

        for (int j = 0; j <stockCodeList.size() ; j++) {

            //System.out.println("计算股票："+stockCodeList.get(j));

            Thread.sleep(1);

            map.put(stockCodeList.get(j), new TechniStock(stockCodeList.get(j)));
            //System.currentTimeMillis();
        }

        return map;
    }
}