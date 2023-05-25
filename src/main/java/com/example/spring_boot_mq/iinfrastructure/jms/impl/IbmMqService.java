package com.example.spring_boot_mq.iinfrastructure.jms.impl;

import com.example.spring_boot_mq.iinfrastructure.jms.MqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.Message;

@Service("ibmMqService")
@Slf4j
@RequiredArgsConstructor
public class IbmMqService implements MqService {

    @Qualifier("activeMqService")
    private final MqService mqService;

    @Override
    @JmsListener(destination = "${integration.ibmmq.input-queue}", containerFactory = "jmsListenerContainerFactoryIbmMq")
    public Message receive(Message message) {
        try {
            log.info("Message received: {}", message);
            String value = message.getBody(String.class);
            // TODO аdd object mapper here for convert message
            log.info("Body from message: {}", value);
            mqService.send(value);
        } catch (Exception ex) {
            log.error("Error on receive message {}, exception: {}", message, ex.getMessage());
        }
        return null;
    }

    @Override
    public void send(String message) {}
}
