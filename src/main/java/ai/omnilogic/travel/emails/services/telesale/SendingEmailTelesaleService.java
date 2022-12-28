package ai.omnilogic.travel.emails.services.telesale;

import ai.omnilogic.travel.emails.dto.telesale.TelesaleDTO;

public interface SendingEmailTelesaleService {

    void sendBudgetMail(TelesaleDTO telesale);

    void sendPreSaleMail(TelesaleDTO telesale);
}
