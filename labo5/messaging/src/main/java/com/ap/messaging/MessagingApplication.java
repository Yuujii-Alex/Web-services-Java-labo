package com.ap.messaging;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ap.messaging.service.RedisService;

import static com.ap.messaging.config.RedisMessagingConfig.CHANNEL;

@SpringBootApplication
public class MessagingApplication implements CommandLineRunner{

	private final RedisService redisService;

	public MessagingApplication(RedisService redisService) {
		this.redisService = redisService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MessagingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		redisService.flushDb();
		redisService.sendMessage(CHANNEL, "Hello from Spring Boot");
	}

}
