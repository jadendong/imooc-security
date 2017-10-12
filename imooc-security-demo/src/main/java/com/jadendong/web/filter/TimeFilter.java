package com.jadendong.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//@Component
public class TimeFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("my filter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("my filter start");
		long start=new Date().getTime();
		chain.doFilter(request, response);
		System.out.println("my filter 耗时:"+(new Date().getTime()-start));
		System.out.println("my filter finish");
	}

	@Override
	public void destroy() {
		System.out.println("my filter destory");
	}

}
