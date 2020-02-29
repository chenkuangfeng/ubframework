package com.ubsoft.framework.core.cache.conf;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubsoft.framework.core.cache.ICache;
import com.ubsoft.framework.core.cache.impl.Ehcache;
import com.ubsoft.framework.core.cache.impl.RedisCache;
import com.ubsoft.framework.core.context.AppContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 应用程序配置类
 */

@Configuration
@PropertySource("classpath:cache.properties")
@EnableCaching
public class CacheConfig {
    @Value("${spring.cache.type}")
    private String cacheType;
    private  ICache cache;
    @Bean
    ICache cache() {
        switch (cacheType) {
            case "redis":
                cache = new RedisCache(AppContext.getBean("redisTemplate", RedisTemplate.class));
                break;
            default:
                cache = new Ehcache();
        }
        return cache;
    }

    @Bean
    public  RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        if(cacheType.equals("redis")) {
            RedisTemplate template = new RedisTemplate();
            template.setConnectionFactory(redisConnectionFactory);
            //template.setConnectionFactory(getConnectionFactory(redisType));
            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

            ObjectMapper om = new ObjectMapper();
            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

            jackson2JsonRedisSerializer.setObjectMapper(om);
            StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

            // key采用String的序列化方式
            template.setKeySerializer(stringRedisSerializer);
            // hash的key也采用String的序列化方式
            template.setHashKeySerializer(stringRedisSerializer);
            // value序列化方式采用jackson
            template.setValueSerializer(jackson2JsonRedisSerializer);
            // hash的value序列化方式采用jackson
            template.setHashValueSerializer(jackson2JsonRedisSerializer);
            template.afterPropertiesSet();
            return template;
        }
        return null;
    }


}
