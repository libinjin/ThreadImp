package com.youguu.cal;

import java.util.ArrayList;
import java.util.List;

public class MATotalList {

    private List<String> get5High10List = new ArrayList<>();

    private List<String> getHigh250List = new ArrayList<>();

    private List<String> getDown250List = new ArrayList<>();

    public List<String> getGet5High10List() {
        return get5High10List;
    }

    public void setGet5High10List(List<String> get5High10List) {
        this.get5High10List = get5High10List;
    }

    public List<String> getGetHigh250List() {
        return getHigh250List;
    }

    public void setGetHigh250List(List<String> getHigh250List) {
        this.getHigh250List = getHigh250List;
    }

    public List<String> getGetDown250List() {
        return getDown250List;
    }

    public void setGetDown250List(List<String> getDown250List) {
        this.getDown250List = getDown250List;
    }
}
