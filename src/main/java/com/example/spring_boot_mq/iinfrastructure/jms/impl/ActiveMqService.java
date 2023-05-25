package com.example.spring_boot_mq.iinfrastructure.jms.impl;

import com.example.spring_boot_mq.iinfrastructure.jms.MqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Message;

@Service("activeMqService")
@Slf4j
@RequiredArgsConstructor
public class ActiveMqService implements MqService {

    @Qualifier("activeMqJmsTemplate")
    private final JmsTemplate jmsTemplate;

    @Value("${integration.activemq.output-queue}")
    private String outputQueue;

    @Override
    public Message receive(Message message) {
        return null;
    }

    @Override
    public void send(String message) {
        log.info("Sending via ActiveMq message {}", message);
        jmsTemplate.convertAndSend(outputQueue, message);
    }
}
