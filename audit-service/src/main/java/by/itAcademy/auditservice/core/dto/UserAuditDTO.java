package by.itAcademy.auditservice.core.dto;

public class UserAuditDTO {
    private String uuid;
    private String fio;
    private String mail;
    private String role;
    private String status;

    public UserAuditDTO() {
    }

    public UserAuditDTO(String uuid, String fio, String mail, String role, String status) {
        this.uuid = uuid;
        this.fio = fio;
        this.mail = mail;
        this.role = role;
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}