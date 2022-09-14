package indi.xezzon.geom.core.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author xezzon
 */
@Configuration
@Profile("redis")
public class RedisConfig {

  @Bean
  public <T> RedisTemplate<String, T> redisTemplate(
      RedisConnectionFactory connectionFactory,
      RedisSerializer<?> redisKeySerializer,
      RedisSerializer<?> redisValueSerializer
  ) {
    RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(connectionFactory);
    redisTemplate.setKeySerializer(redisKeySerializer);
    redisTemplate.setValueSerializer(redisValueSerializer);
    redisTemplate.setHashKeySerializer(redisKeySerializer);
    redisTemplate.setHashValueSerializer(redisValueSerializer);
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  @Bean
  public RedisSerializer<?> redisKeySerializer() {
    return new StringRedisSerializer();
  }

  @Bean
  public RedisSerializer<?> redisValueSerializer(ObjectMapper objectMapper) {
    objectMapper.activateDefaultTyping(
        objectMapper.getPolymorphicTypeValidator(),
        DefaultTyping.NON_FINAL,
        As.PROPERTY
    );
    return new GenericJackson2JsonRedisSerializer(objectMapper);
  }
}
