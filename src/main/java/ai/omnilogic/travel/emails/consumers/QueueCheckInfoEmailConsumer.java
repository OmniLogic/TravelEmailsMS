package ai.omnilogic.travel.emails.consumers;

import ai.omnilogic.travel.emails.exceptions.SendEmailException;
import ai.omnilogic.travel.emails.services.MandrillService;
import br.com.omnilogic.javautils.utils.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class QueueCheckInfoEmailConsumer {
    protected static final Logger log = LoggerFactory.getLogger(QueueCheckInfoEmailConsumer.class);

    private final MandrillService mandrillService;

    public QueueCheckInfoEmailConsumer(MandrillService mandrillService) {
        this.mandrillService = mandrillService;
    }

    @RabbitListener(queues = {"check-info-email"})
    public void receive(@Payload String fileBody) {
        log.info(String.format("Message: %s", fileBody));

        Map<Integer, List<String>> list = null;
        try {
            list = Serializer.fromJson(fileBody, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*try {
            mandrillService.verifyAndSyncInfo(list);
        } catch (SendEmailException e) {
            throw new RuntimeException(e);
        }*/
        log.info("Result: Done");
    }


}
