package com.example.demo;

import java.util.function.Consumer;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ConsumerConfig {

    private static final Logger LOGGER = Logger.getLogger(ConsumerConfig.class.getName());

    private final StreamBridge streamBridge;

    public ConsumerConfig(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostConstruct
    public void doStuff() {
        LOGGER.info("Sending message..");
        streamBridge.send("testProducer-out-0", "testing..");
    }

    @Bean
    public Consumer<Flux<String>> testConsumer() {
        return flux -> flux.doOnEach(LOGGER::info);
    }

}
