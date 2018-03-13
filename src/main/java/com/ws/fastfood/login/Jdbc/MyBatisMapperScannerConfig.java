package com.ws.fastfood.login.Jdbc;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ws.fastfood.login.Annotation.Repository1;
import com.ws.fastfood.login.Annotation.Repository2;

/**
 * 配置MyBatis Mapper Scanner
 * @author wagnshuo
 *
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

	/**
	 * - 设置SqlSessionFactory；
	 * - 设置dao所在的package路径；
	 * - 关联注解在dao类上的Annotation名字；
	 */
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer1() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory1");
		mapperScannerConfigurer.setBasePackage("com.ws.fastfood.login.Dao");
		mapperScannerConfigurer.setAnnotationClass(Repository1.class);
		return mapperScannerConfigurer;
	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer2() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory2");
		mapperScannerConfigurer.setBasePackage("com.ws.fastfood.login.Dao");
		mapperScannerConfigurer.setAnnotationClass(Repository2.class);
		return mapperScannerConfigurer;
	}
	
}