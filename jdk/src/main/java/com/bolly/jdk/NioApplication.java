package com.bolly.jdk;

import com.bolly.jdk.nio.DownLoadByNIO;

public class NioApplication {
    public static void main(String[] args) {
        String FILE_URL = "";
        String FILE_NAME = "/Users/bolly/Downloads/";
        new DownLoadByNIO().getFileByJavaIOJDK6(FILE_URL,FILE_NAME.concat("1.png"));
        new DownLoadByNIO().getFileByJavaIOJDK7(FILE_URL,FILE_NAME.concat("2.png"));
        new DownLoadByNIO().getFileByNIO(FILE_URL,FILE_NAME.concat("3.png"));
        new DownLoadByNIO().getFileContinue(FILE_URL,FILE_NAME.concat("100.png"));
    }
}
