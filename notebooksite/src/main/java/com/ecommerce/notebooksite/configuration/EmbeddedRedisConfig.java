package com.ecommerce.notebooksite.configuration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.IOException;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@Configuration
public class EmbeddedRedisConfig {

  private RedisServer redisServer;

  @PostConstruct
  public void startRedis() throws IOException {
    redisServer = new RedisServer(6379); // Match your application.yml port
    redisServer.start();
  }

  @PreDestroy
  public void stopRedis() {
    if (redisServer != null) {
      redisServer.stop();
    }
  }
}
