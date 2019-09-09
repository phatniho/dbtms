package com.company.db.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class WebFilterConfig {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public FilterRegistrationBean newFilerRegistrationBean() {
		FilterRegistrationBean fBean = new FilterRegistrationBean();
		DelegatingFilterProxy filter = new DelegatingFilterProxy("shiroFilterFactory");
		fBean.setFilter(filter);
//		fBean.setEnabled(true);
//		fBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		fBean.addUrlPatterns("/*");
		return fBean;
	}
}
