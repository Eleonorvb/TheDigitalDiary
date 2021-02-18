package com.julias.diary.diary;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/*
The client response model for the diary request
 */

public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        CONTEXT=context;
    }

    public static Object getBean(String beanName){
        return CONTEXT.getBean(beanName);
    }
}
