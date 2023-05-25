package com.example.spring_boot_mq.config.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import javax.jms.ConnectionFactory;

import static org.apache.activemq.RedeliveryPolicy.NO_MAXIMUM_REDELIVERIES;

@Configuration
public class ActiveMqJmsConfig {

    @Bean
    @Qualifier("connectionFactoryActiveMq")
    public static ConnectionFactory connectionFactoryActiveMq(ActiveMqProperties properties) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(properties.getBrokerUrl());
        factory.setUserName(properties.getUser());
        factory.setPassword(properties.getPassword());
        factory.getRedeliveryPolicy().setMaximumRedeliveries(NO_MAXIMUM_REDELIVERIES);
        return factory;
    }

    @Bean
    @ConfigurationProperties("integration.activemq")
    public ActiveMqProperties activeMqProperties() {
        return new ActiveMqProperties();
    }

    @Bean
    @Qualifier("activeMqJmsTemplate")
    public JmsTemplate activeMqJmsTemplate(@Qualifier("connectionFactoryActiveMq") ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        // TODO add message converter here
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }

    @Bean
    @Qualifier("jmsListenerContainerFactoryActiveMq")
    public JmsListenerContainerFactory<?> jmsListenerContainerFactoryActiveMq(@Qualifier("connectionFactoryActiveMq")
                                                                           ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionTransacted(true);
        return factory;
    }
}
