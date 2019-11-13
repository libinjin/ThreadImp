package com.youguu.threads.future.futureOrign;

/**
 * 当线程想要获取电影时，要等到电影被下载完毕，
 * 否则一致被阻塞
 */
public class FutureData extends Data {

    //下载标识
    private boolean flag = false;

    private DownLoadMovie downLoadMovie;

    //下载电影
    public synchronized void setRealData(DownLoadMovie downLoadMovie){

        if(flag){//下载完毕
            return;
        }
        //没有下载完，继续下载
        this.downLoadMovie = downLoadMovie;
        flag = true;//下载完毕
        notify();
    }

    /**
     * 返回线程执行结果，比如返回下载好的电影
     * @return
     */
    @Override
    public synchronized String getRequest() {
        try {
            while (!flag){
                wait();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return downLoadMovie.getRequest();
    }
}
