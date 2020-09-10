package com.bolly.tomcat.jasypt;

public interface Encryptor {

    String encrypt(String plaintMsg);

    String encryptInt(int plaintMsg);

    String encryptLong(long plaintMsg);

    String decrypt(String encryptedMsg);

    int decryptInt(String encryptedMsg);

    long decryptLong(String encryptedMsg);


}
