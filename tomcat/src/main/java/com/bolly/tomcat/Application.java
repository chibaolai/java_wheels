package com.bolly.tomcat;

import com.bolly.tomcat.embedded.ServletContextInitializer;
import com.bolly.tomcat.embedded.TomcatServletWebServerFactory;
import com.bolly.tomcat.servlet.EmbededServerStarter;
import com.bolly.tomcat.web.ServletWebServerApplicationContext;

import java.io.File;

/**
 * 启动类
 */
public class Application {

    public static void main(String[] args) {

//        String projectPath = new File("").getAbsolutePath() + "/tomcat";
//        new EmbededServerStarter().baseDir(projectPath + "/build/tmp/tomcat").port(8080).addWebapp("",
//                projectPath + "/src/main/webapp/").start();

//        ServletWebServerApplicationContext servletWebServerApplicationContext = new ServletWebServerApplicationContext();
//        servletWebServerApplicationContext.onRefresh();
        new TomcatServletWebServerFactory().getWebServer(new ServletContextInitializer[]{}).start();
    }
}
