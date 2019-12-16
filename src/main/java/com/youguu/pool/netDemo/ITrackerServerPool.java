package com.youguu.pool.netDemo;

public interface ITrackerServerPool {

    public TrackerServer geTrackerServer() throws Exception;
    public TrackerServer geTrackerServer(long timeout) throws Exception;
    public boolean close(TrackerServer server) throws Exception;
    public void reset() throws Exception;
}
