package ai.omnilogic.travel.emails.consumers;

import ai.omnilogic.travel.emails.exceptions.SendEmailException;
import ai.omnilogic.travel.emails.models.MessageStatus;
import ai.omnilogic.travel.emails.models.mail.Mail;
import ai.omnilogic.travel.emails.services.MandrillService;
import br.com.omnilogic.javautils.utils.Serializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class QueueSendEmailConsumer {
    protected static final Logger log = LoggerFactory.getLogger(QueueSendEmailConsumer.class);

    private final MandrillService mandrillService;

    public QueueSendEmailConsumer(MandrillService mandrillService) {
        this.mandrillService = mandrillService;
    }

    @RabbitListener(queues = {"send-email"})
    public void receive(@Payload String fileBody) {
        log.info(String.format("Message: %s", fileBody));

        Map payload = null;
        String[] split = fileBody.split("\\|");
        try {
            payload = new ObjectMapper().readValue(split[0], Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Mail mail = null;
        try {
            mail = Serializer.fromJson(split[1], Mail.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<MessageStatus> send = null;
        try {
            send = mandrillService.send(payload, mail);
        } catch (SendEmailException e) {
            throw new RuntimeException(e);
        }

        log.info(String.format("Result: %s", send));
    }


}
