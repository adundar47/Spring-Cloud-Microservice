package com.adundar.userservice.bus.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.adundar.userservice.model.User;

@Service
public class KafkaMessageProducerManager implements MessageProducerService {

    private static final Logger           LOG = LoggerFactory.getLogger(KafkaMessageProducerManager.class);

    @Value("${kafka.topic.createUser}")
    private String                        createUserTopic;

    @Value("${kafka.topic.updateUser}")
    private String                        updateUserTopic;

    @Value("${kafka.topic.deleteUser}")
    private String                        deleteUserTopic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Async
    @Override
    public void sendCreateUserEvent(final User user) {
        LOG.debug("[sendCreateUserEvent] User object is sending.. -> {}", user);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(createUserTopic, user);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                LOG.debug("[sendCreateUserEvent] sent message='{}' with offset={}", user, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOG.error("[sendCreateUserEvent] unable to send message='{}'", user, ex);
            }

        });
    }

    @Override
    @Async
    public void sendUpdateUserEvent(final User user) {
        LOG.debug("[sendUpdateUserEvent] User object is sending.. -> {}", user);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(updateUserTopic, user);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                LOG.debug("[sendUpdateUserEvent] sent message='{}' with offset={}", user, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOG.error("[sendUpdateUserEvent] unable to send message='{}'", user, ex);
            }

        });
    }

    @Override
    @Async
    public void sendDeleteUserEvent(final User user) {
        LOG.debug("[sendDeleteUserEvent] User object is sending.. -> {}", user);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(deleteUserTopic, user);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                LOG.debug("[sendDeleteUserEvent] sent message='{}' with offset={}", user, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOG.error("[sendDeleteUserEvent] unable to send message='{}'", user, ex);
            }

        });
    }

}
