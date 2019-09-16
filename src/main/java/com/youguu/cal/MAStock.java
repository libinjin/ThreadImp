package com.youguu.cal;

import java.util.ArrayList;
import java.util.List;

public class MAStock {

    private String stockCode;
    private MATotalList maTotalList;

    public MAStock(String stockCode, MATotalList maTotalList) {
        this.stockCode = stockCode;
        this.maTotalList = maTotalList;
        this.calculate();
    }

    public void calculate(){

        //计算一次除权，在这里把  均线, kdj, macd 都计算  stock, list
        maTotalList.getGet5High10List().add(stockCode);

    }

    public static void main(String[] args) {
        List<Integer>  a = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            a.add(i);
        }
        a = a.subList(a.size()-51, a.size());
        System.out.println(a);
    }

}
