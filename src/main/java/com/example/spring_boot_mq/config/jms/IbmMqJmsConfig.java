package com.example.spring_boot_mq.config.jms;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.common.CommonConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Configuration
public class IbmMqJmsConfig {

    @Bean
    @Qualifier("connectionFactoryIbmMq")
    public static ConnectionFactory connectionFactoryIbmMq(IbmMqProperties properties) throws JMSException {
        MQQueueConnectionFactory factory = new MQQueueConnectionFactory();
        factory.setTransportType(CommonConstants.WMQ_CM_CLIENT);
        factory.setHostName(properties.getHost());
        factory.setPort(properties.getPort());
        factory.setChannel(properties.getChannel());
        factory.setCCSID(properties.getCCSID());
        if (properties.getManager() != null) {
            factory.setQueueManager(properties.getManager());
        }
        return factory;
    }

    @Bean
    @ConfigurationProperties("integration.ibmmq")
    public IbmMqProperties ibmMqProperties() {
        return new IbmMqProperties();
    }

    @Bean
    @Qualifier("ibmMqJmsTemplate")
    public JmsTemplate ibmMqJmsTemplate(@Qualifier("connectionFactoryIbmMq") ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        // TODO add message converter here
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }

    @Bean
    @Qualifier("jmsListenerContainerFactoryIbmMq")
    public JmsListenerContainerFactory<?> jmsListenerContainerFactoryIbmMq(@Qualifier("connectionFactoryIbmMq")
                                                                      ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionTransacted(true);
        return factory;
    }
}
