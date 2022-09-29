package ai.omnilogic.travel.emails.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import lombok.Data;

import java.util.*;

@Data
public class Message<T> {
    private T object;
    public Message(T object) {
        this.object = object;
    }

    private String subject;
    private String html;
    private String text;

    @JsonProperty("from_email")
    private String fromEmail;

    @JsonProperty("from_name")
    private String fromName;

    private List<MandrillMessage.Recipient> to;
    private Map<String, String> headers;
    private Boolean important;

    @JsonProperty("track_opens")
    private Boolean trackOpens;

    @JsonProperty("track_clicks")
    private Boolean trackClicks;

    @JsonProperty("auto_text")
    private Boolean autoText;

    @JsonProperty("auto_html")
    private Boolean autoHtml;

    @JsonProperty("inline_css")
    private Boolean inlineCss;

    @JsonProperty("url_strip_qs")
    private Boolean urlStripQs;

    @JsonProperty("preserve_recipients")
    private Boolean preserveRecipients;

    @JsonProperty("view_content_link")
    private Boolean viewContentLink;

    @JsonProperty("bcc_address")
    private String bccAddress;

    @JsonProperty("tracking_domain")
    private String trackingDomain;

    @JsonProperty("signing_domain")
    private String signingDomain;

    @JsonProperty("return_path_domain")
    private String returnPathDomain;
    private Boolean merge;

    @JsonProperty("merge_language")
    private String mergeLanguage;

    @JsonProperty("global_merge_vars")
    private List<MandrillMessage.MergeVar> globalMergeVars;

    @JsonProperty("merge_vars")
    private List<MandrillMessage.MergeVarBucket> mergeVars;

    private List<String> tags;
    private String subaccount;

    @JsonProperty("google_analytics_domains")
    private List<String> googleAnalyticsDomains;

    @JsonProperty("google_analytics_campaign")
    private String googleAnalyticsCampaign;

    private Map<String, String> metadata;

    @JsonProperty("recipient_metadata")
    private List<MandrillMessage.RecipientMetadata> recipientMetadata;

    private List<MandrillMessage.MessageContent> attachments;
    private List<MandrillMessage.MessageContent> images;

}
