package com.oversea.api.init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class LoadSystemConfig implements ApplicationListener<ContextRefreshedEvent> {
	
	public void init() {
		//
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if(event.getApplicationContext().getParent() != null){
            return;
        }
	}
}
