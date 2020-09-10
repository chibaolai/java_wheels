package com.bolly.tomcat.jasypt;

import com.bolly.support.utils.UnexpectedException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jasypt.encryption.StringEncryptor;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

public class EncryptorImpl implements Encryptor {

    private static final String CHAR_SET_UTF_8 = "UTF-8";

    private StringEncryptor jasyptEncryptor;

    @PostConstruct
    void init() {
        if (jasyptEncryptor == null) {
            throw new IllegalStateException("jasyptEncryptor must be set.");
        }
    }

    public void setJasyptEncryptor(StringEncryptor jasyptEncryptor) {
        this.jasyptEncryptor = jasyptEncryptor;
    }

    @Override
    public String encrypt(String plaintMsg) {
        String encryptFirst = jasyptEncryptor.encrypt(plaintMsg);
        try {
            return Base64.encodeBase64URLSafeString(encryptFirst.getBytes(CHAR_SET_UTF_8));
        } catch (UnsupportedEncodingException e) {
            throw new UnexpectedException(e);
        }
    }

    @Override
    public String encryptInt(int plaintMsg) {
        return encrypt(String.valueOf(plaintMsg));
    }

    @Override
    public String encryptLong(long plaintMsg) {
        return encrypt(String.valueOf(plaintMsg));
    }

    @Override
    public String decrypt(String encryptedMsg) {
        try {
            String decryptFirst = new String(Base64.decodeBase64(encryptedMsg), CHAR_SET_UTF_8);
            return jasyptEncryptor.decrypt(decryptFirst);
        } catch (UnsupportedEncodingException e) {
            throw new UnexpectedException(e);
        }
    }

    @Override
    public int decryptInt(String encryptedMsg) {
        return Integer.parseInt(decrypt(encryptedMsg));
    }

    @Override
    public long decryptLong(String encryptedMsg) {
        return Long.parseLong(decrypt(encryptedMsg));
    }

}
