package ai.omnilogic.travel.emails.services;

import ai.omnilogic.travel.emails.exceptions.SendEmailException;
import ai.omnilogic.travel.emails.models.MessageStatus;
import ai.omnilogic.travel.emails.models.log.Mail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface IPlayerService {
    List<MessageStatus> send(Map<String, String> msg, Mail mailModel) throws SendEmailException;

    void verifyAndSyncInfo(Map<Integer, List<String>> ids) throws SendEmailException;
}
