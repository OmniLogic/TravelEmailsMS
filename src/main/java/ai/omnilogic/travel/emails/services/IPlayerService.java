package ai.omnilogic.travel.emails.services;

import ai.omnilogic.travel.emails.models.MessageStatus;

import java.util.Map;

public interface IPlayerService {
    public MessageStatus send(Map<String, String> msg);
}
