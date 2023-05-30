package com.example.spring_boot_mq.service.impl;

import com.example.spring_boot_mq.iinfrastructure.jms.MqService;
import com.example.spring_boot_mq.model.StoredMessage;
import com.example.spring_boot_mq.repository.StoredMessageRepository;
import com.example.spring_boot_mq.service.StoredMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoredMessageServiceImpl implements StoredMessageService {

    @Qualifier("activeMqService")
    private final MqService mqService;

    private final StoredMessageRepository storedMessageRepository;

    @Override
    public void sendMessageToActiveMq(Long messageId) {
        StoredMessage storedMessage = storedMessageRepository.findById(messageId).orElseThrow(() ->
                new MessageConversionException("Message with id: " + messageId + " not found")
        );

        mqService.send(storedMessage.toString());
    }

    @Override
    public List<StoredMessage> getAllMessages() {
        return storedMessageRepository.findAll();
    }
}
