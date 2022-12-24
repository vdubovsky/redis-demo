package io.vdubovsky.redisdemo.configuration;

//import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {

    /*@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(CacheNames.getAllCacheNames());
    }*/

    /*@Bean
    public RedissonAutoConfigurationCustomizer redissonCustomizer(
            @Value("${event.cache.cleanup.delay:5}") int eventCacheCleanupDelay,
            @Value("${event.cache.cleanup.batch:300}") int eventCacheCleanupBatch
    ) {
        return configuration -> configuration
                .setMaxCleanUpDelay(eventCacheCleanupDelay)
                .setCleanUpKeysAmount(eventCacheCleanupBatch);
    }*/
}
