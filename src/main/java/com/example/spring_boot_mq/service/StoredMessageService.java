package com.example.spring_boot_mq.service;

import com.example.spring_boot_mq.model.StoredMessage;

import java.util.List;

public interface StoredMessageService {

    void sendMessageToActiveMq(Long messageId);

    List<StoredMessage> getAllMessages();
}
