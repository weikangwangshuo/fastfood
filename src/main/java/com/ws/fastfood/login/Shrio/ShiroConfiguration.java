package com.ws.fastfood.login.Shrio;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;


/**   
*    
* 项目名称：login   
* 类名称：ShiroConfiguration   
* 类描述：shiro配置组件   
* 创建人：wangshuo   
* 创建时间：2018年3月13日 上午9:32:35   
* 修改人：   
* 修改时间：   
* 修改备注：   
* @version    
*    
*/
@Configuration
public class ShiroConfiguration {
	 private static final Logger log = LoggerFactory.getLogger(ShiroConfiguration.class);

	    /**
	     * 注入Realm
	     * @return MyRealm
	     */
	    @Bean(name = "myRealm")
	    public MyRealm myAuthRealm() {
	        MyRealm myRealm = new MyRealm();
	        log.info("myRealm注册完成");
	        return myRealm;
	    }


	    /**
	     * 注入SecurityManager
	     * @param myRealm
	     * @return SecurityManager
	     */
	    @Bean(name = "securityManager")
	    public SecurityManager securityManager(@Qualifier("myRealm")MyRealm myRealm) {
	        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
	        manager.setRealm(myRealm);
	        log.info("securityManager注册完成");
	        return manager;
	    }

	    /**
	     * 注入Filter
	     * @param securityManager
	     * @return ShiroFilterFactoryBean
	     */
	    @Bean(name = "shiroFilter")
	    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
	        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
	        filterFactoryBean.setSecurityManager(securityManager);
	        // 配置登录的url和登录成功的url
	        filterFactoryBean.setLoginUrl("/auth/login");
	        filterFactoryBean.setSuccessUrl("/home");
	        // 配置未授权跳转页面
	        filterFactoryBean.setUnauthorizedUrl("/errorPage/403");
	        // 配置访问权限
	        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
	        filterChainDefinitionMap.put("/css/**", "anon"); // 表示可以匿名访问
	        filterChainDefinitionMap.put("/fonts/**", "anon");
	        filterChainDefinitionMap.put("/imgs/**", "anon");
	        filterChainDefinitionMap.put("/js/**", "anon");
	        filterChainDefinitionMap.put("/auth/**", "anon");
	        filterChainDefinitionMap.put("/errorPage/**", "anon");
	        filterChainDefinitionMap.put("/demo/**", "anon");
	        filterChainDefinitionMap.put("/swagger-*/**", "anon");
	        filterChainDefinitionMap.put("/swagger-ui.html/**", "anon");
	        filterChainDefinitionMap.put("/webjars/**", "anon");
	        filterChainDefinitionMap.put("/v2/**", "anon");
	        filterChainDefinitionMap.put("/admin/**", "roles[admin]");// 表示admin权限才可以访问，多个加引号用逗号相隔
	        filterChainDefinitionMap.put("/*", "authc");// 表示需要认证才可以访问
	        filterChainDefinitionMap.put("/**", "authc");
	        filterChainDefinitionMap.put("/*.*", "authc");
	        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	        log.info("shiroFilter注册完成");
	        return filterFactoryBean;
	    }

}
