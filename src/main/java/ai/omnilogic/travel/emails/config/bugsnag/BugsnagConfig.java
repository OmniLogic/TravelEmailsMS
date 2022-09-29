package ai.omnilogic.travel.emails.config.bugsnag;

import com.bugsnag.Bugsnag;
import com.bugsnag.BugsnagSpringConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BugsnagSpringConfiguration.class)
public class BugsnagConfig {

    //@Value("${OMNI_ENV}")
    @Value("${bugsnag.release-stage}")
    private String releaseStage;

    @Bean
    public Bugsnag bugsnag() {
        Bugsnag bugsnag = new Bugsnag("5d2ca107dab632846642d4227c058312");
        bugsnag.setNotifyReleaseStages("prd", "dev");
        bugsnag.setReleaseStage(releaseStage);
        return bugsnag;
        //return new Bugsnag("5d2ca107dab632846642d4227c058312");
    }
}
