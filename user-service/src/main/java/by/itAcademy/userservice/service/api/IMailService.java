package by.itAcademy.userservice.service.api;

public interface IMailService{
    void send(String mail, String subject, String text);

    void sendActivationCode(String mail, int codeVerificotion);
}
