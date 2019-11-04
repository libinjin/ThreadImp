package com.youguu.designModel.signton;

/**
 * 枚举防反射攻击
 * 单例模式
 */
enum HttpEum {

    HTTP_200(200, "请求成功"),HTTP_500(500, "请求失败");

    HttpEum(Integer httpCode, String httpMsg){
        //永远只执行一次
        System.out.println("初始化");
        this.httpCode = httpCode;
        this.httpMsg = httpMsg;
    }

    private Integer httpCode;
    private String httpMsg;

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getHttpMsg() {
        return httpMsg;
    }

    public void setHttpMsg(String httpMsg) {
        this.httpMsg = httpMsg;
    }

    public static void main(String[] args) {
        System.out.println(HttpEum.HTTP_200.httpCode);
        System.out.println(HttpEum.HTTP_200.httpMsg);
    }

}
