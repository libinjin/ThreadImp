package com.youguu.threads.future.futureOrign;

public class FuturClient {


    public static void main(String[] args) {
        FuturClient futurClient = new FuturClient();
        Data data = futurClient.request("大话西游");//提交任务给线程池，等待结果返回

        System.out.println("2在等待下载时，主线程可以干点别的任务");
        //拿到下载好的电影
        String str = data.getRequest();

        System.out.println("6拿到了下载好的："+str);
    }

    //客户端提交任务
    public Data request(String requestData){

        System.out.println("1客户端要求看："+requestData+"这部电影");

        FutureData futureData = new FutureData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //子线程阻塞，进行电影的下载，等待3秒，继续往下执行
                DownLoadMovie downLoadMovie = new DownLoadMovie(requestData);

                //设置下载完毕
                futureData.setRealData(downLoadMovie);
            }
        }).start();
        //异步返回
        return futureData;
    }
}
