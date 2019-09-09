package com.company.db.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.db.sys.service.SysUserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Aspect
@Slf4j
public class SysCacheAspect {

	@Autowired
	SysUserService sysUserService;

//	@Pointcut("execution(* com.company.db.sys.service..*.find*(..))")
	@Pointcut("@annotation(com.company.db.common.annotation.RequiredCache)")
	public void sysCache() {
	}

	@Around("sysCache()")
	public Object around(ProceedingJoinPoint pj) throws Throwable {
		Object result = null;
		log.info("查找缓存");
		result = pj.proceed();
		log.info("存入缓存");
		return result;
	}
}
