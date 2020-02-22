package com.ubsoft.framework.core.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 应用程序配置类
 */

@Configuration
@PropertySource("classpath:jdbc.properties")
public class JdbcConfig {

}
