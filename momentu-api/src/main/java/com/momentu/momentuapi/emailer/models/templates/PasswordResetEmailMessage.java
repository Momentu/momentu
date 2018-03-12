package com.momentu.momentuapi.emailer.models.templates;

import com.momentu.momentuapi.emailer.models.EmailMessage;

public class PasswordResetEmailMessage extends EmailMessage {

    public PasswordResetEmailMessage(String from, String to, String link) {
        super(from, to, null, null, null);

        String htmlBody = "<h3>Momentu</h3>" +
                "<p>Please use the following link to reset your email:</p>" +
                "<p><a href=\"" + link + "\">" + link + "</a></p>";
        String textBody = "";

        setSubject("Momentu Password Reset");
        setHtmlBody(htmlBody);
        setTextBody(textBody);
    }
}
