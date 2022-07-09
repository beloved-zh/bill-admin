package com.beloved.core.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.beloved.common.model.BaseEntity;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-09 15:15
 * @Description: MybatisPlus代码生成
 */
public class MPGenerator {
    
    private static final String URL = "jdbc:mysql://124.222.68.23:3306/bill";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "beloved123...";
    
    private static final String AUTHOR = "Beloved";

    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final String PARENT_PACKAGE_NAME = "com.beloved";
    private static final String MODULE_NAME = "bill-autoGenerator";
    
    private static final String[] TABLE_NAMES = {"sys_user", "sys_role", "sys_user_role", "sys_menu", "sys_role_menu"};
    private static final String[] SUPER_ENTITY_COLUMNS = {"remark", "create_by", "create_time", "update_by", "update_time", "del_flag", "version"};

    
    
    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USER_NAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.fileOverride()      // 覆盖已生成文件
                            .disableOpenDir()   // 禁止打开输出目录
                            .outputDir(PROJECT_PATH)  // 指定输出目录
                            .author(AUTHOR)  // 设置作者
                            //.enableSwagger()  // 开启 swagger 模式
                            .commentDate("yyyy-MM-dd"); // 注释日期 
                })
                .packageConfig(builder -> {
                    builder.parent("")
                            .moduleName(MODULE_NAME);
                })
                .strategyConfig(builder -> {
                    builder.addInclude(TABLE_NAMES)
                            .entityBuilder()
                            .superClass(BaseEntity.class)
                            .enableLombok()
                            .enableRemoveIsPrefix()
                            .enableTableFieldAnnotation()
                            .versionColumnName("version")
                            .versionPropertyName("version")
                            .logicDeleteColumnName("del_flag")
                            .logicDeletePropertyName("delFlag")
                            .addSuperEntityColumns(SUPER_ENTITY_COLUMNS)
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("updateTime", FieldFill.INSERT_UPDATE))
                            .idType(IdType.AUTO);
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
    
}
