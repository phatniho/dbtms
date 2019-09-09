package com.company.db.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class SpringShiroConfig {

	@Bean
	public DefaultWebSecurityManager newSecurityManager(@Autowired Realm realm, @Autowired CacheManager cManager) {
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		sManager.setCacheManager(cManager);
		sManager.setRememberMeManager(newRememberMeManager());
		sManager.setSessionManager(newSessionManager());
		return sManager;
	}

	@Bean
	public SimpleCookie newCookie() {
		SimpleCookie c = new SimpleCookie("rememberMe");
		c.setMaxAge(24 * 60 * 60);
		return c;
	}

	@Bean
	public CookieRememberMeManager newRememberMeManager() {
		CookieRememberMeManager cookieR = new CookieRememberMeManager();
		cookieR.setCookie(newCookie());
		return cookieR;
	}

	public DefaultWebSessionManager newSessionManager() {
		DefaultWebSessionManager sManager = new DefaultWebSessionManager();
		sManager.setGlobalSessionTimeout(60 * 60 * 1000);
		return sManager;
	}

	@Bean
	public CacheManager newCacheManager() {
		return new MemoryConstrainedCacheManager();
	}

	@Bean("shiroFilterFactory")
	public ShiroFilterFactoryBean newShiroFilterFactoryBean(@Autowired SecurityManager securityManager) {
		ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
		sfBean.setSecurityManager(securityManager);
		sfBean.setLoginUrl("/doLoginUI");
		LinkedHashMap<String, String> fMap = new LinkedHashMap<>();
		fMap.put("/bower_components/**", "anon");
		fMap.put("/build/**", "anon");
		fMap.put("/dist/**", "anon");
		fMap.put("/myjs/**", "anon");
		fMap.put("/plugins/**", "anon");
		fMap.put("/user/doLogin", "anon");
		fMap.put("/doLogout", "logout");
		fMap.put("/**", "user");// authc 过滤所有
		sfBean.setFilterChainDefinitionMap(fMap);
		return sfBean;
	}

	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor newLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@DependsOn("lifecycleBeanPostProcessor")
	@Bean
	public DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor newAuthorizationAttributeSourceAdvisor(
			@Autowired SecurityManager sManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//		advisor.setSecurityManager(sManager);
		return advisor;
	}
}
