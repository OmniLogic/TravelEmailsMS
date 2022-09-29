package ai.omnilogic.travel.emails.config.rabbitmq;

import ai.omnilogic.travel.emails.enums.ExchangeType;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue sendEmailQueue() {
        return new Queue(ExchangeType.AMQ_SEND_CAETE.getQueue(), true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(ExchangeType.AMQ_SEND_CAETE.getExchange());
    }

    @Bean
    Binding bindingCaete(@Qualifier("sendEmailQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ExchangeType.AMQ_SEND_CAETE.getQueue());
    }

    @Bean
    Binding bindingAtibaia(@Qualifier("sendEmailQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ExchangeType.AMQ_SEND_ATIBAIA.getQueue());
    }

    @Bean
    Binding bindingAraxa(@Qualifier("sendEmailQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ExchangeType.AMQ_SEND_ARAXA.getQueue());
    }

    @Bean
    Binding bindingAlexania(@Qualifier("sendEmailQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ExchangeType.AMQ_SEND_ALEXANIA.getQueue());
    }

    @Bean
    Binding bindingAlegro(@Qualifier("sendEmailQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ExchangeType.AMQ_SEND_ALEGRO.getQueue());
    }

    @Bean
    Binding bindingGeneric(@Qualifier("sendEmailQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ExchangeType.AMQ_SEND_GENERIC.getQueue());
    }

    @Bean
    public Queue checkInfoQueue() {
        return new Queue(ExchangeType.AMQ_CHECK_GENERIC.getQueue(), true);
    }

    @Bean
    CustomExchange exchangeCheckInfo() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(ExchangeType.AMQ_CHECK_GENERIC.getExchange(), "x-delayed-message", true, false, args);
    }

    @Bean
    Binding bindingCheckInfoCaete(@Qualifier("checkInfoQueue") Queue queue, CustomExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ExchangeType.AMQ_CHECK_CAETE.getRouting()).noargs();
    }

    @Bean
    Binding bindingCheckInfoAtibaia(@Qualifier("checkInfoQueue") Queue queue, CustomExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ExchangeType.AMQ_CHECK_ATIBAIA.getRouting()).noargs();
    }

    @Bean
    Binding bindingCheckInfoAraxa(@Qualifier("checkInfoQueue") Queue queue, CustomExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ExchangeType.AMQ_CHECK_ARAXA.getRouting()).noargs();
    }

    @Bean
    Binding bindingCheckInfoAlexania(@Qualifier("checkInfoQueue") Queue queue, CustomExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ExchangeType.AMQ_CHECK_ALEXANIA.getRouting()).noargs();
    }

    @Bean
    Binding bindingCheckInfoAlegro(@Qualifier("checkInfoQueue") Queue queue, CustomExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ExchangeType.AMQ_CHECK_ALEGRO.getRouting()).noargs();
    }

    @Bean
    Binding bindingCheckInfoGeneric(@Qualifier("checkInfoQueue") Queue queue, CustomExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ExchangeType.AMQ_CHECK_GENERIC.getRouting()).noargs();
    }
}
