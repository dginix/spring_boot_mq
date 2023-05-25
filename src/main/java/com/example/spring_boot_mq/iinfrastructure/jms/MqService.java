package com.example.spring_boot_mq.iinfrastructure.jms;

import javax.jms.Message;

public interface MqService {
    Message receive(Message message);
    void send(String message);
}
