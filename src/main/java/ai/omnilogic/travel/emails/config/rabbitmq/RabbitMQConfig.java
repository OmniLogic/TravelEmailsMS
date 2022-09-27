package ai.omnilogic.travel.emails.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue testeQueue() {
        return new Queue("myQueue", true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("hotel");
    }

    @Bean
    Binding testeBinding(Queue testeQueue, TopicExchange exchange) {
        return BindingBuilder.bind(testeQueue).to(exchange).with("routing-caete");
    }
}
