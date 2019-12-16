package com.youguu.pool.netDemo;

import org.omg.DynamicAny.NameValuePair;

import java.io.File;

public interface IDFSClient {
    public String upload(File file) throws Exception;
    public String upload(File file, NameValuePair... metaList) throws Exception;
    public File download(String fileName,String localPath) throws Exception;
    public boolean remove(String fileName) throws Exception;
    public NameValuePair[] getFileMate(String fileName) throws Exception;

}
