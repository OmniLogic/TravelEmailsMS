package ai.omnilogic.travel.emails.services.sendmail;

import ai.omnilogic.travel.emails.models.Mail;
import freemarker.template.TemplateException;

import java.io.IOException;

public interface SendingEmailService {

    void sendMail(Mail mailModel, String templateEmail) throws IOException, TemplateException;
}
