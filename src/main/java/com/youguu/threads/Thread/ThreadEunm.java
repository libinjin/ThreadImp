package com.youguu.threads.Thread;

public enum ThreadEunm {

    NEW(1),
    RUNNABLE(2),
    WAITING(3),//进入无时限的等待
    TIMED_WAITING(4),//进入有时限的等待
    TERMINATED(5);

    private int index ;

    ThreadEunm(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
