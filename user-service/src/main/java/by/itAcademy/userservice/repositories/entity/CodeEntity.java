package by.itAcademy.userservice.repositories.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "code")
public class CodeEntity {
    @Id
    @Column(name = "mail")
    private String mail;

    @Column(name = "code")
    String code;

    public CodeEntity(String mail, String code) {
        this.mail = mail;
        this.code = code;
    }

    public CodeEntity() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
