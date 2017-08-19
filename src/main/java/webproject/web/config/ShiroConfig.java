package webproject.web.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/** 
* @author hts
* @version date：2017年4月12日 下午6:39:33 
* 
*/
@Configuration
public class ShiroConfig {
	@Autowired
	MyShiroRealm myShiroRealm;
	@Bean(name="securityManager")
	public DefaultWebSecurityManager securityManager(){
		
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(myShiroRealm);
		return securityManager;
	}
	
	@Bean(name="shiroFilter")
public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
	ShiroFilterFactoryBean sf=new ShiroFilterFactoryBean();
	sf.setSecurityManager(securityManager);
	sf.setLoginUrl("/login");
	sf.setSuccessUrl("/index");
	sf.setUnauthorizedUrl("/login");
	Map<String, String> fMap = new LinkedHashMap<String, String>();
	fMap.put("/login", "anon");
	fMap.put("/dologin", "anon");
	fMap.put("/register", "anon");
	fMap.put("/doregister", "anon");
	fMap.put("/system/test", "anon");
	fMap.put("/js/*", "anon");
	fMap.put("/css/*", "anon");
	fMap.put("/img/*", "anon");
	fMap.put("/logout", "anon");
	fMap.put("/**", "authc");
	sf.setFilterChainDefinitionMap(fMap);
	
	return sf;
}
	
	
@Bean(name="shiroDialect")
public ShiroDialect shiroDialect(){
return new ShiroDialect();
}

@Bean
public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
    DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
    creator.setProxyTargetClass(true);
    return creator;
}
@Bean
public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
}
}