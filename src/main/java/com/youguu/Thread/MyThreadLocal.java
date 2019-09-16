package com.youguu.Thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadLocal {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("");
    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();


    public static void main(String[] args) throws ParseException {

        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {

            if(threadLocal.get() == null){
                threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm::ss"));
            }
            Date t = threadLocal.get().parse("");

            service.execute(new ParseDate(i));
        }
    }

    public static class ParseDate implements Runnable{

        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                Date t = sdf.parse("2019-03-29 19:19:"+i%60);
                System.out.println(i+":"+t);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

}
