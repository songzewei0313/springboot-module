package com.song.generate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author szw
 * @date 2022/4/30 21:55
 */
public class MysqlGenerator {
    private static String[] tables = {"users"};//表名，必填如：actions
    //操作人,必填如：song
    private static String author = "songzw";

    private static String url = "jdbc:mysql://192.168.0.100:3306/song_mysql?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&autoReconnect=true";
    //数据库用户名
    private static String username = "root";
    //数据库密码
    private static String password = "song1997@";

    //子工程名称,必填如：arrangement-center
    private static String module = "springboot-graphics";
    //包名 多模块项目或者微服务
    private static String parentPackage = "com.song." + module.replaceAll("-", "");

    //单体项目
//    private static String parentPackage = "com.song";

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/" + module;
//       projectPath = projectPath + "\\exchange-center";
        System.out.println("projectPath====" + projectPath);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setSwagger2(true);//实体属性 Swagger2 注解
        gc.setAuthor(author);   // 作者
        gc.setOpen(false);      //生成代码后是否打开文件夹
        gc.setIdType(IdType.AUTO);//主键策略
        gc.setFileOverride(true);//文件覆盖
        gc.setServiceName("%sService");  // 设置Service接口生成名称,这样生成接口前面就不会有 I
        gc.setBaseResultMap(false);//生成resultMap
        mpg.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("exchange-center"); // 模块名称, 这里可以根据不同模块来写
        pc.setParent(parentPackage); // 父包名
        pc.setController("controller");
        pc.setMapper("dao");
        pc.setEntity("domain.entity");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {

            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        templateConfig.setController("templates/controller.java");

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//表名映射到实体的命名策略(下划线到驼峰)
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//表字段映射属性名策略(未指定按naming)
        strategy.setEntityLombokModel(true);//使用lombok
        strategy.setInclude(tables);  // 如果要生成多个,这里可以传入String[]
        strategy.setRestControllerStyle(true); //生成 @RestController 控制器
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
