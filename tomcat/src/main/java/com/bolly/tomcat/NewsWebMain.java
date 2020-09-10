package com.bolly.tomcat;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class NewsWebMain {
private static Logger log = LoggerFactory.getLogger(NewsWebMain.class);


/**
   * 
   * Tomcat 启动主类方法
   * @author huangyan
   * @date 2018/6/5 11:39
   * @return void
   */
public static void main(String[] args) throws Exception {
    try {
      // 1.创建一个内嵌的Tomcat
      Tomcat tomcat = new Tomcat();


      // 2.设置Tomcat端口默认为8080
      tomcat.setPort(8080);


      // 3.设置工作目录,tomcat需要使用这个目录进行写一些东西
      String projectPath = new File("").getAbsolutePath() + "/tomcat";
      tomcat.setBaseDir(projectPath + "/build/tmp/tomcat");
      tomcat.getHost().setAutoDeploy(false);


      // 4. 设置webapp资源路径
      String webappDirLocation = projectPath + "/src/main/webapp/";
      StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
      log.info("configuring app with basedir: " + new File("" + webappDirLocation).getAbsolutePath());
      log.info("project dir:"+new File("").getAbsolutePath());


      // 5. 设置上下文路每径
      String contextPath = "";
      ctx.setPath(contextPath);
      ctx.addLifecycleListener(new Tomcat.FixContextListener());
//      ctx.setName("tomcat");


      System.out.println("child Name:" + ctx.getName());
      tomcat.getHost().addChild(ctx);


     /* File additionWebInfClasses = new File("");
      WebResourceRoot resources = new StandardRoot(ctx);
      resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
          additionWebInfClasses.getAbsolutePath() + "/classes", "/"));
      ctx.setResources(resources);
      */


      log.info("服务器加载完配置，正在启动中……");
      tomcat.start();
      log.info("服务器启动成功");
      tomcat.getServer().await();
    } catch (Exception exception) {
      log.error("服务器启动失敗", exception);
    }
  }
}
