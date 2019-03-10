package com.bolly.app;

import com.bolly.app.servlet.EmbededServerStarter;

import java.io.File;

public class Application {

    public static void main(String[] args) {
        String projectPath = new File("").getAbsolutePath();
        new EmbededServerStarter().baseDir(projectPath + "/build/tmp/tomcat").port(8088).addWebapp("",
                projectPath + "/src/main/webapp/").start();
    }
}
