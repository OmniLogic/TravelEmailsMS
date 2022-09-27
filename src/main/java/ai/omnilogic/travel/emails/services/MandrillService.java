package ai.omnilogic.travel.emails.services;

import ai.omnilogic.travel.emails.models.MessageStatus;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MandrillService extends AbstractPlayerService implements IPlayerService{


    @Override
    public MessageStatus send(Map<String, String> msg) {
        prepareMessage(msg);
        MandrillMessage mail = generateMail(msg);
        return deliverMail(mail);
    }

    public MessageStatus deliverMail(Object message) {



        return null;
    }

}
