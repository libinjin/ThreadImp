package com.youguu.pool.netDemo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class AbstractClientImpl implements IDFSClient {

    protected String getFileTypeName(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return fileName;
    }
    /**
     *
     * @Description: TODO(文件转换为字节数组)
     * @author LiuYi
     *  @param file
     *  @return  byte[]
     */
    protected byte[] file2Byte(File file) {
        FileInputStream fis = null;
        ByteArrayOutputStream ops = null;
        BufferedInputStream bis = null;
        try {
            byte[] temp = new byte[2048];
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ops = new ByteArrayOutputStream(2048);
            int n;
            while ((n = bis.read(temp)) != -1) {
                ops.write(temp, 0, n);
            }
            return ops.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ops != null) {
                    ops.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    /**
     *
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author LiuYi
     *  @param buf
     *  @param filePath
     *  @param fileName  void
     */
    public  File byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
}
