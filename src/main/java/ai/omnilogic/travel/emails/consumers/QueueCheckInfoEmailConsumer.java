package ai.omnilogic.travel.emails.consumers;

import ai.omnilogic.travel.emails.exceptions.SendEmailException;
import ai.omnilogic.travel.emails.models.MessageStatus;
import ai.omnilogic.travel.emails.models.log.Mail;
import ai.omnilogic.travel.emails.services.MandrillService;
import br.com.omnilogic.javautils.utils.Serializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class QueueCheckInfoEmailConsumer {

    private final MandrillService mandrillService;

    public QueueCheckInfoEmailConsumer(MandrillService mandrillService) {
        this.mandrillService = mandrillService;
    }

    @RabbitListener(queues = {"check-info-email"})
    public void receive(@Payload String fileBody) {
        System.out.println("Message " + fileBody);

        Map<Integer, List<String>> list = null;
        try {
            list = Serializer.fromJson(fileBody, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            mandrillService.verifyAndSyncInfo(list);
        } catch (SendEmailException e) {
            throw new RuntimeException(e);
        }

    }


}
