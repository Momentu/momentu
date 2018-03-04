package com.momentu.momentuapi.emailer;

import com.momentu.momentuapi.emailer.models.EmailMessage;

public interface AmazonSESEmailer {

    public boolean sendEmail(EmailMessage email);
}
