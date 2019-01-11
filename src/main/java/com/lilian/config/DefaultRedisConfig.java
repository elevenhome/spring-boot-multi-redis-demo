package com.lilian.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * llld-parent 配置默认Redis操作实例 到Spring中
 *
 * @Author 孙龙
 * @Date 2018/8/2
 */
@Configuration
@EnableCaching
public class DefaultRedisConfig extends RedisConfig {

    @Value("${spring.redis1.database}")
    private int dbIndex;

    @Value("${spring.redis1.host}")
    private String host;

    @Value("${spring.redis1.port}")
    private int port;

    @Value("${spring.redis1.password}")
    private String password;

    @Value("${spring.redis1.timeout}")
    private int timeout;

    /**
     * 配置redis连接工厂
     * @return
     */
    @Bean
    public RedisConnectionFactory defaultRedisConnectionFactory() {
//        return createJedisConnectionFactory(dbIndex, host, port, password, timeout);
        return createJedisConnectionFactory(dbIndex, host, port, password);
    }

    /**
     * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
     *
     * @return
     */
    @Bean(name = "defaultRedisTemplate")
    public RedisTemplate defaultRedisTemplate() {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(defaultRedisConnectionFactory());
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }


    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(defaultRedisConnectionFactory());
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }


}
