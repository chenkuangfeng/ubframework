package com.ubsoft.framework.job;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

@Component
public  class GeneralBeanJobFactory extends SpringBeanJobFactory
		implements ApplicationContextAware {

	private transient AutowireCapableBeanFactory beanFactory;


	public void setApplicationContext(final ApplicationContext context) {
		beanFactory = context.getAutowireCapableBeanFactory();
	}


	protected Object createJobInstance(final TriggerFiredBundle bundle)
			throws Exception {
		final Object job = super.createJobInstance(bundle);
		beanFactory.autowireBean(job);
		return job;
	}
}
