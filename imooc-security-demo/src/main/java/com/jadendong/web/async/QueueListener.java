package com.jadendong.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private MockQueue mockQueue;

	@Autowired
	private DeferredResultHolder deferredResultHolder;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		new Thread(() -> {
			while (true) {
				
				if (StringUtils.isBlank(mockQueue.getComplaceOrder())) {
					String orderNumer = mockQueue.getComplaceOrder();
					logger.info("返回订单处理结果：" + orderNumer);
					deferredResultHolder.getMap().get(orderNumer).setResult("place order success");

					mockQueue.setComplaceOrder(null);
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
