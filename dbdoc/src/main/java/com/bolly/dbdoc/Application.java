package com.bolly.dbdoc;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // 数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://121.196.181.134:33065/sjyydb");
        hikariConfig.setUsername("elho");
        hikariConfig.setPassword("5N2OV6nX");
        // 设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(hikariConfig);

        // 1、生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir("/Users/chibaolai/Downloads/db")
                // 打开目录
                .openOutputDir(false)
                // 文件类型
                .fileType(EngineFileType.WORD)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker).build();

        // 忽略表名
        List<String> ignoreTableName = Arrays.asList("temp","test_names","newtable","schema_version","num");
        // 忽略表前缀
        List<String> ignorePrefix = Arrays.asList("act_", "BATCH_","qrtz_","sys_","spider_","gen_","program_");
        // 忽略表后缀
        List<String> ignoreSuffix = Arrays.asList("_data", "test");

        // 2、配置想要忽略的表
        ProcessConfig processConfig = ProcessConfig.builder().ignoreTableName(ignoreTableName)
                .ignoreTablePrefix(ignorePrefix).ignoreTableSuffix(ignoreSuffix).build();

        // 3、生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder().version("1.0.0").description("数据库设计文档").dataSource(dataSource)
                .engineConfig(engineConfig).produceConfig(processConfig).build();

        // 4、执行生成
        new DocumentationExecute(config).execute();
    }

}
