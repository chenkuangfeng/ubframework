package com.ubsoft.framework.core.context;

import com.ubsoft.framework.core.cache.ICache;
import com.ubsoft.framework.core.conf.AppConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppContext implements ApplicationContextAware {
        private static ApplicationContext applicationContext;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            if(AppContext.applicationContext == null) {
                AppContext.applicationContext = applicationContext;
            }
        }
        //获取applicationContext
        public static ApplicationContext getApplicationContext() {
            return applicationContext;
        }

        //通过name获取 Bean.
        public static Object getBean(String name){
            return getApplicationContext().getBean(name);
        }

        //通过class获取Bean.
        public static <T> T getBean(Class<T> clazz){
            return getApplicationContext().getBean(clazz);
        }

        //通过name,以及Clazz返回指定的Bean
        public static <T> T getBean(String name,Class<T> clazz){

            return getApplicationContext().getBean(name, clazz);
        }
        public static <T> T getBeanOfType(Class<T> type) {
            ApplicationContext context = getApplicationContext();
            if (context != null) {
                Map beans = context.getBeansOfType(type);
                if (beans.size() == 1) {
                    return (T) beans.values().iterator().next();
                }

            }
            return null;
       }

       public static AppConfig getAppConfig(){
         return getBeanOfType(AppConfig.class);
       }
       public  static ICache getCache(){
            return  getBean("cache",ICache.class);
       }

    }

