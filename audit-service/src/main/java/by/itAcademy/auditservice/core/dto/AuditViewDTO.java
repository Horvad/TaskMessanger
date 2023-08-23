package by.itAcademy.auditservice.core.dto;


public class AuditViewDTO {
    private String uuid;
    private String userRole;
    private UserAuditDTO userAuditDTO;
    private String text;
    private String id;

    public AuditViewDTO() {
    }

    public AuditViewDTO(String uuid, String userRole, UserAuditDTO userAuditDTO, String text, String id) {
        this.uuid = uuid;
        this.userRole = userRole;
        this.userAuditDTO = userAuditDTO;
        this.text = text;
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public UserAuditDTO getUserAuditDTO() {
        return userAuditDTO;
    }

    public void setUserAuditDTO(UserAuditDTO userAuditDTO) {
        this.userAuditDTO = userAuditDTO;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
