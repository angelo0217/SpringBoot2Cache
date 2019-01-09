package com.demo.cache.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * DB連線設定
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Configuration
@MapperScan(value={"com.demo.cache.dao"}, sqlSessionFactoryRef = "mySqlSessionFactory")
public class MysqlConfig {

    @Value("${spring.datasource.mysql.xmlpath}")
    public String xmlPath;

    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix="spring.datasource.mysql")
    @Primary
    public DataSource mysqlDataSource() throws SQLException {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mySqlSessionFactory")
    @Primary
    public SqlSessionFactory mySqlSessionFactory() throws Exception {

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mysqlDataSource());
        sessionFactory.setMapperLocations(resolver.getResources(xmlPath));
        return sessionFactory.getObject();
    }
}