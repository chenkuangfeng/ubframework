package com.ubsoft.framework.core.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 应用程序配置类
 */

@Configuration
@PropertySource("classpath:cache.properties")
public class CacheConfig {

}
