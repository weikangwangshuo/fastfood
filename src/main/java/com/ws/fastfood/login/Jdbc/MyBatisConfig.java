package com.ws.fastfood.login.Jdbc;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.github.pagehelper.PageHelper;
import com.ws.fastfood.login.Util.PropertiesUtils;


/**
 * DataSource、SqlSessionFactory和Transaction Manager 配置
 * @author wangshuo
 *
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer{

	/**
     * 配置dataSource，使用Druid连接池
     */
    @Bean(destroyMethod = "close")
    @Primary
    public DataSource dataSource1(){
        DruidXADataSource dataSource = new DruidXADataSource();
        dataSource.setDriverClassName(PropertiesUtils.findPorpertyByKey("jdbc1.driverClassName","config/datasources1.properties").trim());
        dataSource.setUrl(PropertiesUtils.findPorpertyByKey("jdbc1.jdbcUrl","config/datasources1.properties").trim());
        dataSource.setUsername(PropertiesUtils.findPorpertyByKey("jdbc1.userName","config/datasources1.properties").trim());
        dataSource.setPassword(PropertiesUtils.findPorpertyByKey("jdbc1.password","config/datasources1.properties").trim());
        dataSource.setInitialSize(5); // 连接池启动时创建的初始化连接数量（默认值为0）
        dataSource.setMaxActive(20); // 连接池中可同时连接的最大的连接数
        //dataSource.setMaxIdle(12); // 连接池中最大的空闲的连接数，超过的空闲连接将被释放，如果设置为负数表示不限
        dataSource.setMinIdle(0); // 连接池中最小的空闲的连接数，低于这个数量会被创建新的连接
        dataSource.setMaxWait(60000); // 最大等待时间，当没有可用连接时，连接池等待连接释放的最大时间，超过该时间限制会抛出异常，如果设置-1表示无限等待
        dataSource.setRemoveAbandonedTimeout(180); // 超过时间限制，回收没有用(废弃)的连接
        dataSource.setRemoveAbandoned(true); // 超过removeAbandonedTimeout时间后，是否进 行没用连接（废弃）的回收
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        //dataSource.setValidationQuery("SELECT 1");
        dataSource.setTimeBetweenEvictionRunsMillis(1000 * 60 * 30); // 检查无效连接的时间间隔 设为30分钟
        return dataSource;
    }


    /**
     * 配置dataSource，使用Druid连接池
     */
    @Bean(destroyMethod = "close")
    public DataSource dataSource2(){
        DruidXADataSource dataSource = new DruidXADataSource();
        dataSource.setDriverClassName(PropertiesUtils.findPorpertyByKey("jdbc2.driverClassName","config/datasources2.properties").trim());
        dataSource.setUrl(PropertiesUtils.findPorpertyByKey("jdbc2.jdbcUrl","config/datasources2.properties").trim());
        dataSource.setUsername(PropertiesUtils.findPorpertyByKey("jdbc2.userName","config/datasources2.properties").trim());
        dataSource.setPassword(PropertiesUtils.findPorpertyByKey("jdbc2.password","config/datasources2.properties").trim());
        dataSource.setInitialSize(5); // 连接池启动时创建的初始化连接数量（默认值为0）
        dataSource.setMaxActive(20); // 连接池中可同时连接的最大的连接数
        //dataSource.setMaxIdle(12); // 连接池中最大的空闲的连接数，超过的空闲连接将被释放，如果设置为负数表示不限
        dataSource.setMinIdle(0); // 连接池中最小的空闲的连接数，低于这个数量会被创建新的连接
        dataSource.setMaxWait(60000); // 最大等待时间，当没有可用连接时，连接池等待连接释放的最大时间，超过该时间限制会抛出异常，如果设置-1表示无限等待
        dataSource.setRemoveAbandonedTimeout(180); // 超过时间限制，回收没有用(废弃)的连接
        dataSource.setRemoveAbandoned(true); // 超过removeAbandonedTimeout时间后，是否进 行没用连接（废弃）的回收
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1000 * 60 * 30); // 检查无效连接的时间间隔 设为30分钟
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory1() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource1());

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "false");
        properties.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(properties);
        bean.setPlugins(new Interceptor[]{pageHelper});
        try {
            //指定mapper xml目录
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            bean.setMapperLocations(resolver.getResources("classpath*:Mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory2() {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource2());

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "false");
        properties.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(properties);
        bean.setPlugins(new Interceptor[]{pageHelper});

        try {
            //指定mapper xml目录
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            bean.setMapperLocations(resolver.getResources("classpath*:Mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Transaction 相关配置
     * 因为有两个数据源，所有使用ChainedTransactionManager把两个DataSourceTransactionManager包括在一起。
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager dtm1 = new DataSourceTransactionManager(dataSource1());
        DataSourceTransactionManager dtm2 = new DataSourceTransactionManager(dataSource2());
        ChainedTransactionManager ctm = new ChainedTransactionManager(dtm1, dtm2);
        return ctm;
    }
}