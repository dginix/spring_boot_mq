package com.example.spring_boot_mq.iinfrastructure.jms.impl;

import com.example.spring_boot_mq.iinfrastructure.jms.MqService;
import com.example.spring_boot_mq.model.StoredMessage;
import com.example.spring_boot_mq.model.MessageStatus;
import com.example.spring_boot_mq.repository.StoredMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.jms.Message;

@Service("ibmMqService")
@Slf4j
@RequiredArgsConstructor
public class IbmMqService implements MqService {

    private final StoredMessageRepository storedMessageRepository;

    @Override
    @JmsListener(destination = "${integration.ibmmq.input-queue}", containerFactory = "jmsListenerContainerFactoryIbmMq")
    @Transactional
    public Message receive(Message message) {
        try {
            String value = message.getBody(String.class);
            if (StringUtils.hasText(value)) {
                storedMessageRepository.save(StoredMessage.builder()
                        .body(value)
                        .status(MessageStatus.RECEIVED)
                        .build());
                log.info("Message received: {}", value);
            } else {
                throw new RuntimeException("Message with empty body");
            }
            // TODO аdd object mapper here for convert message
        } catch (Exception ex) {
            log.error("Error on receive message {}, exception: {}", message, ex.getMessage());
        }
        return null;
    }

    @Override
    public void send(String message) {
    }
}
