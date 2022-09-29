package ai.omnilogic.travel.emails.services;

import ai.omnilogic.travel.emails.exceptions.InvalidParameterException;
import ai.omnilogic.travel.emails.models.Message;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public abstract class AbstractPlayerService {
    protected static final Logger log = LoggerFactory.getLogger(AbstractPlayerService.class);
    public static final String TO = "to";
    public static final String FROM = "from";
    public static final String SUBJECT = "subject";
    public static final String BODY = "body";
    public static final String CC = "cc";
    public static final String BCC = "bcc";

    protected <OUT> Message<OUT> generateMail(Map<String, String> msg, Message<OUT> message) {

        Map<String, String> headers = new HashMap<>();

        if(msg.get("etag") != null) headers.put("X-ETag", msg.get("etag"));
        if(msg.get("list_unsubscribe") != null) headers.put("List-Unsubscribe", String.format("<%s>", msg.get("list_unsubscribe")));
        if(msg.get("reply_to") != null) headers.put("Reply-To", msg.get("reply_to"));

        if(msg.get(FROM).contains(" <")) {
            String[] fromSplit = msg.get(FROM).split(" <");
            String fromEmail = fromSplit[1].replace(">", "");
            String fromName = fromSplit[0];
            message.setFromEmail(fromEmail);
            message.setFromName(fromName);
        } else {
            message.setFromEmail(msg.get(FROM));
            message.setFromName(msg.get(FROM));
        }

        message.setHeaders(headers);
        message.setHtml(msg.get(BODY));
        message.setSubject(msg.get(SUBJECT));
        message.setAutoHtml(false);

        MandrillMessage.Recipient recipient = new MandrillMessage.Recipient();
        recipient.setEmail(msg.get(TO));
        recipient.setName(msg.get(TO));
        recipient.setType(MandrillMessage.Recipient.Type.TO);

        message.setTo(new ArrayList<>());
        message.getTo().add(recipient);

        if(msg.get(CC) != null && !msg.get(CC).trim().isEmpty()) {
            String [] ccList = msg.get(CC).split(",");
            for(String cc : ccList) {
                recipient = new MandrillMessage.Recipient();
                recipient.setEmail(cc);
                recipient.setName(cc);
                recipient.setType(MandrillMessage.Recipient.Type.CC);
                message.getTo().add(recipient);
            }
        }

        if(msg.get(BCC) != null) {
            recipient = new MandrillMessage.Recipient();
            recipient.setEmail(msg.get(BCC));
            recipient.setName(msg.get(BCC));
            recipient.setType(MandrillMessage.Recipient.Type.BCC);
            message.getTo().add(recipient);
        }

        return message;
    }

    protected void prepareMessage(Map<String, String> msg) throws InvalidParameterException {

        checkRequiredFields(msg);

        msg.put("encoding", "UTF-8");
        msg.put("alt_encoding", "UTF-8");
        msg.put("content_type", "text/html");
        msg.put("alt_content_type", "text/plain");
    }

    private void checkRequiredFields(Map<String, String> msg) throws InvalidParameterException {
        List<String> requiredFields = Arrays.asList(TO, FROM, SUBJECT, BODY);

        for (String field : requiredFields) {
            if(!msg.containsKey(field) || msg.get(field) == null) {
                throw new InvalidParameterException(String.format("Missing message parameter \"%s\"", field));
            }
        }
    }
}
