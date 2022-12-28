package ai.omnilogic.travel.emails.services;

import ai.omnilogic.travel.emails.models.log.LogEmail;
import ai.omnilogic.travel.emails.models.mail.Mail;
import ai.omnilogic.travel.emails.repositories.LogEmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogEmailService {
    private static Logger log = LoggerFactory.getLogger(LogEmailService.class);
    private final LogEmailRepository logEmailRepository;

    public LogEmailService(LogEmailRepository logEmailRepository) {
        this.logEmailRepository = logEmailRepository;
    }

    public void sent(Mail mailModel, Boolean sent, String statusExternal, List<String> mandrillId) {
        try {
            LogEmail logEmail = new LogEmail();

            if (Optional.ofNullable(mailModel.getHotelCode()).isPresent())
                logEmail.setHotelCode(mailModel.getHotelCode());

            if (Optional.ofNullable(mailModel.getReserveId()).isPresent())
                logEmail.setReserveId(mailModel.getReserveId());

            if (Optional.ofNullable(mailModel.getModel().get("created_by")).isPresent()) {
                String createdBy = (String) mailModel.getModel().get("created_by");
                 if (!createdBy.trim().isEmpty())
                    logEmail.setCreatedBy(createdBy);
            }

            logEmail.setTemplate(Optional.ofNullable(mailModel.getTemplate()).orElse(""));
            logEmail.setEmail(mailModel);
            logEmail.setSent(sent);
            logEmail.setStatusExternal(statusExternal);
            logEmail.setMandrillId(mandrillId);
            logEmailRepository.save(logEmail);
        } catch (Exception ex){
            log.error(String.format("ERROR SENT LOG EMAIL >> %s", ex.getMessage()));
        }
    }
}
