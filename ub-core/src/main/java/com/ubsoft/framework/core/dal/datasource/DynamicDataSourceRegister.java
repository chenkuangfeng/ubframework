package com.ubsoft.framework.core.dal.datasource;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    //指定默认数据源类型
    private static final String DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";
    DataSource defaultDataSource=null ;
    Map<String, DataSource> targetDataSources = new HashMap<>();
    @Override
    public void setEnvironment(Environment env) {
        //默认数据源
        Map<String, Object> defaultMap = new HashMap<>();
        defaultMap.put("driver", env.getProperty("spring.datasource.driver-class-name"));
        defaultMap.put("url", env.getProperty("spring.datasource.url"));
        defaultMap.put("username", env.getProperty("spring.datasource.username"));
        defaultMap.put("password", env.getProperty("spring.datasource.password"));
        defaultMap.put("type", env.getProperty("spring.datasource.type"));
        defaultDataSource=buildDataSource(defaultMap);

        String dsPrefixs = env.getProperty("spring.datasource.names");
        String [] names=dsPrefixs.split(",");
        //如果只有一个数据源，不注册动态数据源,使用默认数据源
        if(names.length==1){
            return;
        }

        for (String dsPrefix : names) {
            // 多个数据源,排除默认的
            if(dsPrefix.equals("driver")||dsPrefix.equals("url")||dsPrefix.equals("username")||dsPrefix.equals("password")||dsPrefix.equals("type")){
                continue;
            }
            Map<String, Object> dsMap = new HashMap<>();
            dsMap.put("driver", env.getProperty("spring.datasource." + dsPrefix + ".driver"));
            dsMap.put("url", env.getProperty("spring.datasource." + dsPrefix + ".url"));
            dsMap.put("username", env.getProperty("spring.datasource." + dsPrefix + ".username"));
            dsMap.put("password", env.getProperty("spring.datasource." + dsPrefix + ".password"));
            dsMap.put("type", env.getProperty("spring.datasource." + dsPrefix + ".type"));
            DataSource ds = buildDataSource(dsMap);
            targetDataSources.put(dsPrefix, ds);
            if (dsPrefixs.equals("default")) {
                defaultDataSource = ds;
            }

        }



    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        //注册 - BeanDefinitionRegistry
        if(beanDefinitionRegistry.containsBeanDefinition("dataSource")){
            beanDefinitionRegistry.removeBeanDefinition("dataSource");
        }
        beanDefinitionRegistry.registerBeanDefinition("dataSource", beanDefinition);

    }

    public DataSource buildDataSource(Map<String, Object> dataSourceMap) {
        try {
            Object type = dataSourceMap.get("type");
            if (type == null) {
                type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
            }
            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
            String driverClassName = dataSourceMap.get("driver").toString();
            String url = dataSourceMap.get("url").toString();
            String username = dataSourceMap.get("username").toString();
            String password = dataSourceMap.get("password").toString();
            // 自定义DataSource配置
            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
