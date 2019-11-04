package com.youguu.GC.JVMMemory;

public class NewGenerationSetting {

    public static void main(String[] args) {

        //-Xms20m -Xmx20m -XX:+PrintGCDetails -XX:SurvivorRatio=2 -XX:+UseSerialGC -XX:NewRatio=2
        /**
         *
         * 新生代 : 老年代 1:2
         *
         * 6784        13696K
         */

        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 *1024];
            System.out.println("分配完毕:"+(i+1));
        }

    }
}
