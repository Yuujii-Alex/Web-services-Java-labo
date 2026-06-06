package com.ap.messaging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.ap.messaging.controller.RedisController;

@Configuration
public class RedisMessagingConfig {
    
    public static final String CHANNEL = "edu:ap:redis";

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new ChannelTopic(CHANNEL));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RedisController controller) {
        return new MessageListenerAdapter(controller, "onMessage");
    }
}
