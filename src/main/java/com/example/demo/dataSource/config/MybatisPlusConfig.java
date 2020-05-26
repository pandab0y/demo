package com.example.demo.dataSource.config;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.example.demo.dataSource.DBTypeEnum;
import com.example.demo.dataSource.DynamicDataSource;
/***
 * mybatisplus配置类
 * 数据源配置
 * @author wangjingpei
 *
 */
@Configuration
@MapperScan("com.example.demo.dao*")
public class MybatisPlusConfig {
	 /***
     * plus 的性能优化
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
        performanceInterceptor.setMaxTime(1000);
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     * @Description : mybatis-plus分页插件
     * ---------------------------------
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * @Description : druid注入
     * ---------------------------------
     */
    @Bean(name="db1")
    @ConfigurationProperties("spring.datasource.user")
    public DataSource dataSourceDb1() {
        return DruidDataSourceBuilder
                .create()
                .build();
    }

    /**
     * @Description : druid注入
     * ---------------------------------
     */
    @Bean(name="db2")
    @ConfigurationProperties("spring.datasource.union")
    public DataSource dataSourceDb2() {
        return DruidDataSourceBuilder
                .create()
                .build();
    }
    
    
    /**
     * 动态数据源配置
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource (@Qualifier("db1") DataSource db1,
                                          @Qualifier("db2") DataSource db2 ) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map< Object, Object > targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.db1.getValue(), db1 );
        targetDataSources.put(DBTypeEnum.db2.getValue(), db2);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(db1);
        return dynamicDataSource;
    }
    
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource(dataSourceDb1(),dataSourceDb2()));
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*/*Mapper.xml"));

        MybatisConfiguration configuration = new MybatisConfiguration();
        //configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{ //PerformanceInterceptor(),OptimisticLockerInterceptor()
                paginationInterceptor() //添加分页功能
        });
//        sqlSessionFactory.setGlobalConfig(globalConfiguration());
        return sqlSessionFactory.getObject();
    }

    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setLogicDeleteValue("-1");
        conf.setLogicNotDeleteValue("1");
        conf.setIdType(0);
//        conf.setMetaObjectHandler(new MyMetaObjectHandler());
        conf.setDbColumnUnderline(true);
        conf.setRefresh(true);
        return conf;
    }
}
