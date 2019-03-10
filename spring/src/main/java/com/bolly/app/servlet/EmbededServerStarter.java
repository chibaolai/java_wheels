package com.bolly.app.servlet;

import java.util.Scanner;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;


public class EmbededServerStarter {

    private static final int DEFAULT_PORT = 8080;

    private static final int DEFAULT_SSL_PORT = 1443;

    private static final Log LOGGER = LogFactory.getLog(EmbededServerStarter.class);

    private Tomcat tomcat;

    private boolean isOpenSSL = false;

    private Connector httpsConnector;

    public EmbededServerStarter() {
        tomcat = new Tomcat();
        tomcat.setPort(DEFAULT_PORT);
    }

    public EmbededServerStarter port(int port) {
        tomcat.setPort(port);
        return this;
    }

    public EmbededServerStarter baseDir(String baseDir) {
        tomcat.setBaseDir(baseDir);
        return this;
    }

    public EmbededServerStarter addWebapp(String contextPath, String docBase) {
        try {
            tomcat.addWebapp(contextPath, docBase);
        } catch (ServletException e) {
//            throw new UnexpectedException(e);
        }
        return this;
    }

    public EmbededServerStarter openSSL(String keystorePass,String keystoreFileDir) {
        this.isOpenSSL = true;
        if(httpsConnector == null) {
            httpsConnector = new Connector();
        }
        httpsConnector.setPort(DEFAULT_SSL_PORT);
        httpsConnector.setSecure(true);
        httpsConnector.setScheme("https");
        httpsConnector.setAttribute("keystorePass", keystorePass);
        httpsConnector.setAttribute("keystoreFile", keystoreFileDir);
        httpsConnector.setAttribute("clientAuth", "false");
        httpsConnector.setAttribute("sslProtocol", "TLS");
        httpsConnector.setAttribute("SSLEnabled", true);
        return this;
    }

    public EmbededServerStarter setSSLPort(int sslPort) {
        if(httpsConnector == null) {
            httpsConnector = new Connector();
        }
        httpsConnector.setPort(sslPort);
        return this;
    }

    public void start() {
        if(isOpenSSL) {
            tomcat.getService().addConnector(httpsConnector);
//            tomcat.getConnector().setRedirectPort(httpsConnector.getPort());
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    tomcat.start();
                    LOGGER.info("The EmbedServer is started. Press Enter to stop it.");
                    tomcat.getServer().await();
                } catch (LifecycleException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }.start();
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.nextLine();
        }
        stop();
    }

    private void stop() {
        try {
            tomcat.stop();
            LOGGER.info("The EmbedServer is stopped");
        } catch (LifecycleException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
