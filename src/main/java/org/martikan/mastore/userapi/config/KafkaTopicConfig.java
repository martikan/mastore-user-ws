package org.martikan.mastore.userapi.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;

/**
 * Initialize required topics for the microservice.
 */
@Configuration
public class KafkaTopicConfig {

    public static final String TOPIC_REGISTERED_USERS = "registeredUsers";

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {

        var configs = new HashMap<String, Object>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);

        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic registeredUsers() {
        return new NewTopic(TOPIC_REGISTERED_USERS, 10, (short) 1);
    }

}
