package com.bolly.jdk.nio;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class DownLoadByNIO {

    /**
     * 每一次用read()方法读取一个字节时，都会调用一次底层文件系统，
     * 所以每当JVM调用read()的时候，程序执行上下文都会从用户模式切换到内核模式，执行结束后再切换回来。
     *
     * @param FILE_URL
     * @param FILE_NAME
     */
    public void getFileByJavaIOJDK6(String FILE_URL, String FILE_NAME) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 缺点：所有的缓存字节都会直接存储在内存中
     *
     * @param FILE_URL
     * @param FILE_NAME
     */
    public void getFileByJavaIOJDK7(String FILE_URL, String FILE_NAME) {
        try {
            InputStream in = new URL(FILE_URL).openStream();
            Files.copy(in, Paths.get(FILE_NAME), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 数据可以直接移动到文件系统而不需要复制任何字节到程序的内存栈中
     * 尤其是在Linux或者Unix操作系统中，这种方式使用了一种称之为zero-copy的技术，来减少上下文在内核模式和用户模式之间的切换次数
     *
     * @param FILE_URL
     * @param FILE_NAME
     */
    public void getFileByNIO(String FILE_URL, String FILE_NAME) {
        try {
            ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(FILE_URL).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 断点续存
     *
     * @param FILE_URL
     * @param FILE_NAME
     */
    public void getFileContinue(String FILE_URL, String FILE_NAME) {
        try {
            File outputFile = new File(FILE_NAME);
            URL url = new URL(FILE_URL);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("HEAD");
            long remoteFileSize = httpConnection.getContentLengthLong();

            long existingFileSize = outputFile.length();
            if (existingFileSize < remoteFileSize) {
                httpConnection.setRequestProperty("Range", "bytes=" + existingFileSize + "-" + remoteFileSize);
            }

            FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME, true);
            ReadableByteChannel readableByteChannel = Channels.newChannel(httpConnection.getInputStream());
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
