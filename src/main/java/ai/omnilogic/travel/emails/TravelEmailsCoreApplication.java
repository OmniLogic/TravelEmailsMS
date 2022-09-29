package ai.omnilogic.travel.emails;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TravelEmailsCoreApplication {

	public static void main(String[] args) {
		String env = System.getenv("OMNI_ENV");
		if(env == null)
			env = "prd";
		System.setProperty("spring.profiles.active", env);
		SpringApplication.run(TravelEmailsCoreApplication.class, args);
	}

}
