package com.example.reportservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "feign")
public class FeignProperty {
    private String auditUrl;

    public String getAuditUrl() {
        return auditUrl;
    }

    public void setAuditUrl(String auditUrl) {
        this.auditUrl = auditUrl;
    }
}
