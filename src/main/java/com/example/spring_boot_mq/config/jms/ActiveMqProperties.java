package com.example.spring_boot_mq.config.jms;

import lombok.Data;

@Data
public class ActiveMqProperties {
    private String brokerUrl;
    private String user;
    private String password;
}
