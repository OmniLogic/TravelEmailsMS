package ai.omnilogic.travel.emails;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableRabbit
@SpringBootApplication
public class TravelEmailsCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelEmailsCoreApplication.class, args);
	}

}
