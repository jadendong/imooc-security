package com.jadendong.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {
	
	private String placeOrder;
	
	private String complaceOrder;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public String getPlaceOrder() {
		return placeOrder;
	}

	public void setPlaceOrder(String placeOrder) throws Exception {
		new Thread(()->{
			
			logger.info(("接到下单请求, "+placeOrder));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.complaceOrder=placeOrder;
			logger.info("下单请求处理完毕， "+placeOrder);
			
		}).start();
	}

	public String getComplaceOrder() {
		return complaceOrder;
	}

	public void setComplaceOrder(String complaceOrder) {
		this.complaceOrder = complaceOrder;
	}
}
