package com.bolly.tomcat.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JasyptGui extends JFrame implements ClipboardOwner {

    private static final long serialVersionUID = -2698724411732809325L;

    public static final String DEFAULT_SALT = "LZ85fDZXTEzAFyuiB2NMAA==";

    private Clipboard clipboard;

    private JasyptGui() {
        setSize(360, 200);
        getContentPane().setLayout(null);
        setResizable(false);
        setLocation(400, 200);
        setDefaultCloseOperation(3);
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        JLabel pswLabel = new JLabel("输入密码：");
        pswLabel.setBounds(10, 10, 80, 25);

        final JTextField pswField = new JTextField();
        pswField.setBounds(90, 10, 240, 25);

        JLabel encryptLabel = new JLabel("加密结果");
        encryptLabel.setBounds(10, 40, 80, 25);

        final JTextArea encryptField = new JTextArea();
        encryptField.setBounds(90, 40, 240, 60);
        encryptField.setAutoscrolls(true);
        encryptField.setLineWrap(true);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(new Rectangle(90, 40, 240, 60));
        jScrollPane.setViewportView(encryptField);

        JButton encryptButton = new JButton("加密");
        encryptButton.setBounds(70, 120, 60, 25);
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
                stringEncryptor.setPassword(DEFAULT_SALT);
                String encrypted = stringEncryptor.encrypt(pswField.getText());
                encryptField.setText(encrypted);
            }
        });
        JButton copyButton = new JButton("复制");
        copyButton.setBounds(140, 120, 60, 25);
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection contents = new StringSelection(encryptField.getText());
                JasyptGui.this.clipboard.setContents(contents, JasyptGui.this);
            }
        });
        JButton exitButton = new JButton("退出");
        exitButton.setBounds(210, 120, 60, 25);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JasyptGui.this.dispose();
            }
        });
        add(pswLabel, null);
        add(pswField, null);
        add(encryptLabel, null);
        add(jScrollPane, null);

        add(encryptButton, null);
        add(copyButton, null);
        add(exitButton, null);

        setTitle("密码加密");
    }

    public static void main(String[] args) {
        JasyptGui w = new JasyptGui();
        w.setVisible(true);
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        // Do nothing
    }
}
