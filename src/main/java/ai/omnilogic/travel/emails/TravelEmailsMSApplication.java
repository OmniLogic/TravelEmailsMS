package ai.omnilogic.travel.emails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelEmailsMSApplication {

	public static void main(String[] args) {
		String env = System.getenv("OMNI_ENV");
		if(env == null)
			env = "prd";
		System.setProperty("spring.profiles.active", env);
		SpringApplication.run(TravelEmailsMSApplication.class, args);
	}

}
