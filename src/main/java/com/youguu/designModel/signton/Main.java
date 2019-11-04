package com.youguu.designModel.signton;

public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            new Thread(){
                @Override
                public void run() {
                    User01 u1 = User01.getInstance();
                    User01 u2 = User01.getInstance();
                    System.out.println(u1 == u2);
                }
            }.start();
        }

    }

}
