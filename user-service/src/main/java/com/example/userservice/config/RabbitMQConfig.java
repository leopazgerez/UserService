package com.example.userservice.config;

import com.example.userservice.Utils.RabbitValues;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Autowired
    private RabbitValues rabbitValues;

    @Bean
    public Queue registeredUserQueue() {
        return new Queue(rabbitValues.getRegisteredUserQueue(), false);
    }

    /*Exchange = Buzón -> el que va a mandar los mensajes a determinadas colas dependiendo el binding */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(rabbitValues.getExchange());
    }

    @Bean
    public Binding registeredUserBindingQueue() {
        return BindingBuilder.bind(registeredUserQueue())
                .to(exchange())
                .with(rabbitValues.getRegisteredUserRoutingKey());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}

