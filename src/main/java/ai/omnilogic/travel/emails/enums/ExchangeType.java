package ai.omnilogic.travel.emails.enums;

import java.util.Arrays;

public enum ExchangeType {
    AMQ_SEND_CAETE("exchange-hotel", "send-email", "routing-caete"),
    AMQ_SEND_ATIBAIA("exchange-hotel", "send-email", "routing-caete"),
    AMQ_SEND_ARAXA("exchange-hotel", "send-email", "routing-caete"),
    AMQ_SEND_ALEXANIA("exchange-hotel", "send-email", "routing-caete"),
    AMQ_SEND_ALEGRO("exchange-hotel", "send-email", "routing-caete"),
    AMQ_SEND_GENERIC("exchange-hotel", "send-email", "routing-generic"),


    AMQ_CHECK_CAETE("check-info", "check-info-email", "routing-caete"),
    AMQ_CHECK_ATIBAIA("check-info", "check-info-email", "routing-caete"),
    AMQ_CHECK_ARAXA("check-info", "check-info-email", "routing-caete"),
    AMQ_CHECK_ALEXANIA("check-info", "check-info-email", "routing-caete"),
    AMQ_CHECK_ALEGRO("check-info", "check-info-email", "routing-caete"),
    AMQ_CHECK_GENERIC("check-info", "check-info-email", "routing-generic");

    private final String exchange;
    private final String queue;
    private final String routing;

    ExchangeType(String exchange, String queue, String routing) {
        this.exchange = exchange;
        this.queue = queue;
        this.routing = routing;
    }

    public String getExchange() {
        return exchange;
    }

    public String getQueue() {
        return queue;
    }

    public String getRouting() {
        return routing;
    }

}
