package com.example.demo;

import com.rabbitmq.stream.OffsetSpecification;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;

@Configuration
public class RabbitConfig {

    @Bean
    public ListenerContainerCustomizer<MessageListenerContainer> consumer() {
        return (container, destinationName, group) -> {
            StreamListenerContainer streamContainer = (StreamListenerContainer) container;
            streamContainer.setConsumerCustomizer((name, builder) -> {
                builder.offset(OffsetSpecification.first());
            });
        };
    }

}
