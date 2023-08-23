package by.itAcademy.auditservice.core.dto;


public class AuditSendDTO {
    private String uuid;
    private String uuidEditor;
    private String role;
    private String text;

    private String userUuid;
    private String userFio;
    private String userMail;
    private String userRole;
    private String userStatus;

    public AuditSendDTO() {
    }

    public AuditSendDTO(String uuid, String uuidEditor, String role, String text, String userUuid,
                        String userFio, String userMail, String userRole, String userStatus) {
        this.uuid = uuid;
        this.uuidEditor = uuidEditor;
        this.role = role;
        this.text = text;
        this.userUuid = userUuid;
        this.userFio = userFio;
        this.userMail = userMail;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuidEditor() {
        return uuidEditor;
    }

    public void setUuidEditor(String uuidEditor) {
        this.uuidEditor = uuidEditor;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getUserFio() {
        return userFio;
    }

    public void setUserFio(String userFio) {
        this.userFio = userFio;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}