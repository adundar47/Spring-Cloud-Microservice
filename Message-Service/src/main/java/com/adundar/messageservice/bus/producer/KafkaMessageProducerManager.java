package com.adundar.messageservice.bus.producer;

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

import com.adundar.messageservice.model.MessageEvent;

@Service
public class KafkaMessageProducerManager implements MessageProducerService {

    private static final Logger           LOG = LoggerFactory.getLogger(KafkaMessageProducerManager.class);

    @Value("${kafka.topic.createMessage}")
    private String                        createMessageTopic;

    @Value("${kafka.topic.deleteMessage}")
    private String                        deleteMessageTopic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Async
    @Override
    public void sendCreateMessageEvent(MessageEvent messageEvent) {
        LOG.debug("[sendCreateMessageEvent] MessageEvent object is sending.. -> {}", messageEvent);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(createMessageTopic, messageEvent);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                LOG.debug("[sendCreateMessageEvent] sent message='{}' with offset={}", messageEvent, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOG.error("[sendCreateMessageEvent] unable to send message='{}'", messageEvent, ex);
            }

        });

    }

    @Async
    @Override
    public void sendDeleteMessageEvent(MessageEvent messageEvent) {
        LOG.debug("[sendDeleteMessageEvent] MessageEvent object is sending.. -> {}", messageEvent);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(deleteMessageTopic, messageEvent);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                LOG.debug("[sendDeleteMessageEvent] sent message='{}' with offset={}", messageEvent, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOG.error("[sendDeleteMessageEvent] unable to send message='{}'", messageEvent, ex);
            }

        });

    }

}
