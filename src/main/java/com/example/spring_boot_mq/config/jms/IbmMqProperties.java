package com.example.spring_boot_mq.config.jms;

import lombok.Data;

@Data
public class IbmMqProperties {
    private final int CCSID = 1208;
    private String host;
    private int port;
    private String manager;
    private String channel;
}
