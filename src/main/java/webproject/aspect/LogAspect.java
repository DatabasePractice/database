package webproject.aspect;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;

import webproject.model.PageData;
import webproject.model.ResultBean;

/** 
* @author hts
* @version date：2017年9月4日 下午10:25:38 
* 
*/
@Aspect  
@Component
public class LogAspect {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	Gson gson=new Gson();
	@Pointcut("execution(* webproject.controller..*(..))") 
	    public void controllerMethodPointcut(){}  
	  @Before("controllerMethodPointcut()") 
		  public void methodBefore(JoinPoint joinPoint){
			   ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			   HttpServletRequest request = requestAttributes.getRequest();
			   //打印请求内容
			   logger.debug("===============请求内容===============");
			   logger.debug("请求地址:"+request.getRequestURL().toString());
			   logger.debug("请求方式:"+request.getMethod());
			   logger.debug("请求类方法:"+joinPoint.getSignature());
			   logger.debug("请求类方法参数:"+ Arrays.toString(joinPoint.getArgs()));
			   logger.debug("请求参数:"+new PageData(request).toString());
			   logger.debug("===============请求内容===============");			  
	  }
	  
	  @AfterReturning(returning = "o",pointcut = "controllerMethodPointcut()")
	  public void methodAfterReturing(Object o ){
		  logger.debug("--------------返回内容----------------");
		  logger.debug("Response内容:"+gson.toJson(o));
		  logger.debug("--------------返回内容----------------");
	  }
	  
	  @AfterThrowing(throwing="ex",pointcut="controllerMethodPointcut()")
	  @ResponseBody
	  public void handleException(JoinPoint jp,Throwable ex ) throws IOException{
		  logger.error(jp.getSignature()+"方法出现异常");
		  logger.error("系统异常",ex);
		  ResultBean<String> result=new ResultBean<String>();
		  result.setStatus(500);
		  result.setMsg("程序内部错误:"+ex.getLocalizedMessage());
		  HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		  response.setContentType("application/json; charset=utf-8");
		  response.setCharacterEncoding("utf-8");
		  PrintWriter writer=response.getWriter();		 
		  writer.write(gson.toJson(result));
		  writer.close();	  
	  }
}
