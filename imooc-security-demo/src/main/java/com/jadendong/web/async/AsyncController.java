package com.jadendong.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/order")
	public DeferredResult<String> order() throws Exception {
		
		logger.info("主线程开始了");
//		Callable<String> result=new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				logger.info("副线程开始了");
//				Thread.sleep(1000);
//				logger.info("副线程结束了");
//				return "success";
//			}
//		};
		
		String orderNumber=RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		
		DeferredResult<String> result=new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumber, result);
		
		logger.info("主线程返回");
		
		return result;
	}
}
