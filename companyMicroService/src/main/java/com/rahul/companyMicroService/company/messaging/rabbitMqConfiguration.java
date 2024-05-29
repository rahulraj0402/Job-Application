package com.rahul.companyMicroService.company.messaging;



import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// This is the configuration  class for setting up the rabbit Mq
// This class will allow to define a beans | this will help to control and configure the behaviour
// of our messaging service

@Configuration
public class rabbitMqConfiguration {

    // there will be 3 meathod defination

    // 1. Create a queue
    // 2. doing the message conversion | so this will convert the message into JSON format
    // so will be serialize and deserialize the message
    // 3. this will give the instance of rabbit Template | its a helper class



    @Bean
    public Queue companyRatingQueue(){
        return new Queue("companyRatingQueue");
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


}
