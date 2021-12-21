package org.martikan.mastore.userapi.service;

public interface KafkaService {

    void sendMessage(final String topicName, final Object message);

}
