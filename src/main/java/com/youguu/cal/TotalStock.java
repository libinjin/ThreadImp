package com.youguu.cal;


import java.util.ArrayList;
import java.util.List;

/**
 * 各种策略的汇总
 */
public class TotalStock {

    public void getTotal(){

        List<String> stockCodeList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            stockCodeList.add("11600000");
        }

        MATotalList maTotalList = new MATotalList();

        for (String stockCode : stockCodeList) {

            //计算出每只股票的 macd kdj 均线，并放入各个List中
            MAStock maStock = new MAStock(stockCode, maTotalList);

        }

        List<String> get5High10List = maTotalList.getGet5High10List();

        System.out.println(get5High10List);
    }

    public static void main(String[] args) {
        TotalStock totalStock = new TotalStock();
        totalStock.getTotal();
    }
}
