package by.itAcademy.userservice.repositories.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mail")
public class MailEntity {
    @Id
    private Long id;
    private String mail;
    private boolean send;

    public MailEntity() {
    }

    public MailEntity(String mail, boolean send) {
        this.mail = mail;
        this.send = send;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }
}
