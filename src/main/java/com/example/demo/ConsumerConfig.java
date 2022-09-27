package com.example.demo;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import com.rabbitmq.stream.OffsetSpecification;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ConsumerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerConfig.class.getName());

    private final StreamBridge streamBridge;

    public ConsumerConfig(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostConstruct
    public void doStuff() {
        LOGGER.info("Sending message...");
        for(int i = 0; i <= 10; i++) {
            streamBridge.send("testProducer-out-0", "testing " + LocalDateTime.now());
        }
    }

    @Bean
    public Consumer<String> testConsumer() {
        return LOGGER::info;
    }

}
