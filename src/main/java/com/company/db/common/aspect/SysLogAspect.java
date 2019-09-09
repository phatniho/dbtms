package com.company.db.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.db.common.annotation.RequiredLog;
import com.company.db.common.util.IPUtils;
import com.company.db.entity.SysLog;
import com.company.db.entity.SysUser;
import com.company.db.sys.dao.SysLogsDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @aspect 描述的类为一个切面类对象 1)切入点 (切入扩展功能的点) 2)通知 (扩展功能)
 * @author tarena
 *
 */
@Aspect // 1.标记此类为切面类
@Service // 2.将此切面类交给Spring管理
@Slf4j
public class SysLogAspect {

//	@Pointcut("bean(*ServiceImpl)")
//	@Pointcut("within(*ServiceImpl)")
//	@Pointcut("@annotation(com.company.db.common.annotation.RequiredLog)")
	@Pointcut("execution(* com.company..*ServiceImpl.*(..))") // 3.定义切入点
	public void logPointCut() {
	}

//	@After("logPointCut()")
//	@AfterThrowing("logPointCut()")
//	@AfterReturning("logPointCut()")
//	@Before("logPointCut()")
	@Around("logPointCut()") // 4.定义通知类型
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		try {
			long t1 = System.currentTimeMillis();
			log.info("start:" + t1);
			Object result = jp.proceed();
			long t2 = System.currentTimeMillis();
			log.info("after:" + t2);
			saveObject(jp, (t2 - t1));
			return result;
		} catch (Throwable e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Autowired
	private SysLogsDao sysLogsDao;

	private void saveObject(ProceedingJoinPoint jp, long time) throws Exception {
		// 1.获取用户行为信息
		// 1.1获取目标对象类型
		Class<?> targetCls = jp.getTarget().getClass();
		// 1.2获取方法名
		// 1.2.1获取方法签名对象
		MethodSignature ms = (MethodSignature) jp.getSignature();
		// 1.2.2获取目标方法名
		String methodName = targetCls.getName() + "." + ms.getName();
		String params = getRequestParams(jp);
		// 1.2.3获取操作名
		String operation = getOperation(targetCls, ms);
		// 2.封装用户行为信息
		SysLog log = new SysLog();
		log.setIp(IPUtils.getIpAddr());
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		log.setUsername(user.getUsername());
		log.setMethod(methodName);// 类全名+方法
		log.setParams(params);// 方法实际参数值
		log.setOperation(operation);// 添加,修改,删除,..
		log.setTime(time);
		log.setCreatedTime(new Date());
		// 3.将用户行为信息存储到数据库
		sysLogsDao.insertObject(log);
	}

	private String getOperation(Class<?> targetCls, MethodSignature ms) throws NoSuchMethodException {
		String operation = "operation";
		// Method method=ms.getMethod();//接口方法
		Method targetMethod = targetCls.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
		RequiredLog rLog = targetMethod.getDeclaredAnnotation(RequiredLog.class);
		if (rLog != null) {
			operation = rLog.value();
		}
		return operation;
	}

	private String getRequestParams(ProceedingJoinPoint jp) throws JsonProcessingException {
		Object[] args = jp.getArgs();
		String params = "[]";
		if (args.length > 0) {
			params = new ObjectMapper().writeValueAsString(args);
		}
		return params;
	}
}
