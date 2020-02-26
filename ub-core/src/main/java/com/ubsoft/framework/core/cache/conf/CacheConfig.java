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
//    @Value("${spring.cache.ehcache.config}")
//    private  String ehcacheConfig;
//    @Value("${spring.redis.host}")
//    private  String redisHost ;
//    @Value("${spring.redis.port}")
//    private  int redisPort;
//    @Value("${spring.redis.cluster.nodes}")
//    private  String redisClusterNodes;
//    @Value("${spring.redis.database}")
//    private  int redisDataBase;
//    @Value("${spring.redis.timeout}")
//    private  int redisTimeout ;
//    @Value("${spring.redis.password}")
//    private  String redisPassword;
//    @Value("${spring.redis.pool.max-active}")
//    private  int redisPoolMaxActive;
//    @Value("${spring.redis.pool.max-wait}")
//    private  int redisPoolMaxWait;
//    @Value("${spring.redis.pool.max-idle}")
//    private  int redisPoolMaxIdle ;
//    @Value("${spring.redis.pool.min-idle}")
//    private  int redisPoolMinIde;
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

//    private  JedisConnectionFactory getConnectionFactory(String redisType){
//        JedisConnectionFactory factory =null;
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxIdle(redisPoolMaxIdle);
//        poolConfig.setMinIdle(redisPoolMinIde);
//        poolConfig.setMaxTotal(redisPoolMaxActive);
//        poolConfig.setMaxWaitMillis(redisPoolMaxWait);
//        //poolConfig.setMaxIdle();
//        switch(redisType){
//            case "redis-standalone":
//                RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//                redisStandaloneConfiguration.setHostName(redisHost);
//                redisStandaloneConfiguration.setPort(redisPort);
//                redisStandaloneConfiguration.setPassword(redisPassword);
//                redisStandaloneConfiguration.setDatabase(redisDataBase);
//                redisStandaloneConfiguration.se
//                //redisStandaloneConfiguration
//                factory=new  JedisConnectionFactory(redisStandaloneConfiguration);
//
//
//        }
//        factory.getPoolConfig().setMaxIdle(maxIdle);
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxIdle(redisPoolMaxIdle);
//        poolConfig.sett
//        factory.setHostName(redisHost);
//        factory.setPort(redisPort);
//        factory.setTimeout(retimeout); //设置连接超时时间
//        factory.setPassword(password);
//        factory.getPoolConfig().setMaxIdle(maxIdle);
//        factory.getPoolConfig().setMinIdle(minIdle);
//        factory.getPoolConfig().setMaxTotal(maxActive);
//        factory.getPoolConfig().setMaxWaitMillis(maxWait);
//
//        return factory;
//
//
//
//    }
}
