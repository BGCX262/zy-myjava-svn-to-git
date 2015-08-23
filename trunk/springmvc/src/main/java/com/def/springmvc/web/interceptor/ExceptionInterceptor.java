package com.def.springmvc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.def.springmvc.web.interceptor.AuthorityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

public class ExceptionInterceptor extends AbstractHandlerExceptionResolver {
	private static Logger log = LoggerFactory
			.getLogger(ExceptionInterceptor.class);

	private String page;
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception e) {
		log.error(e.getMessage());
		
		String name=e.toString();
		String location=e.getLocalizedMessage();
		String cause="";
		if(e.getCause()!=null){
			cause=e.getCause().toString();
			if(e.getCause().getCause()!=null){
				cause=cause+" preCause:"+e.getCause().getCause().toString();
			}
		}
		
		String message=e.getMessage();
		StackTraceElement[] eles=e.getStackTrace();
		String stackTrace="";
		logger.error(":::Exception Log Begin");
		logger.error("name:"+name);
		

		logger.error(location);
		logger.error(cause);
		logger.error(message);
		logger.error(":::Exception Log End");
		ModelAndView model = new ModelAndView(page);
		
		if(e instanceof NullPointerException){
			model.addObject("beforUrl", request.getHeader("referer"));
			model.addObject("NullPointerException", true);
		}else if(e instanceof AuthorityException){
			model.addObject("AuthorityException", true);
		}
		
		model.addObject("EXCEPTION_THROWN", e.getClass().getName());
		
		return model;

	}

}
