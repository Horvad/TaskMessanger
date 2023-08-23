package by.itAcademy.userservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "feign")
public class FeignProperties {
    private String auditUrl;

    public String getAuditUrl() {
        return auditUrl;
    }

    public void setAuditUrl(String auditUrl) {
        this.auditUrl = auditUrl;
    }
}
