package com.youguu.future.futureOrign;

/**
 * 下载电影
 */
public class DownLoadMovie extends Data{


    private String result;

    public DownLoadMovie(String data){
        System.out.println("3准备下载名为："+data+"的电影");
        try {
            System.out.println("4模拟下载耗时");
            Thread.sleep(3000);
            System.out.println("5下载完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取返回下载好的电影
        this.result = "很好看的电影";
    }

    @Override
    public String getRequest() {
        return result;
    }
}
