package com.momentu.momentuapi.emailer.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.momentu.momentuapi.emailer.AmazonSESEmailer;
import com.momentu.momentuapi.emailer.config.SESSettings;
import com.momentu.momentuapi.emailer.models.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmazonSESEmailerImpl implements AmazonSESEmailer {

    @Autowired
    SESSettings sesSettings;

    public boolean sendEmail(EmailMessage emailMessage) {
        try {
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(sesSettings.getAccessKeyId(), sesSettings.getSecretAccessKey());
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            .withRegion(Regions.fromName(sesSettings.getRegion()))
                            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                            .build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(emailMessage.getTo()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(emailMessage.getHtmlBody()))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(emailMessage.getTextBody())))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(emailMessage.getSubject())))
                    .withSource(emailMessage.getFrom());
            client.sendEmail(request);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
