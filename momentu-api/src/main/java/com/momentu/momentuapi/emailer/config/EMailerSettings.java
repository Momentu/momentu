package com.momentu.momentuapi.emailer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "momentu.emailer")
public class EMailerSettings {
    private String resetLinkPrefix;

    public String getResetLinkPrefix() {
        return resetLinkPrefix;
    }

    public void setResetLinkPrefix(String resetLinkPrefix) {
        this.resetLinkPrefix = resetLinkPrefix;
    }
}