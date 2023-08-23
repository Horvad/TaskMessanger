package by.itAcademy.userservice.service;

import by.itAcademy.userservice.core.dto.AuditSendDTO;
import by.itAcademy.userservice.core.dto.UserViewDTO;
import by.itAcademy.userservice.core.enums.UserRole;
import by.itAcademy.userservice.core.exception.FeignException;
import by.itAcademy.userservice.inPoint.untils.JwtTokenHandler;
import by.itAcademy.userservice.service.api.IAuditFeignClient;
import by.itAcademy.userservice.service.api.IAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
public class AuditService implements IAuditService {
    @Autowired
    private final IAuditFeignClient auditFeignClient;
    private final JwtTokenHandler handler;

    public AuditService(IAuditFeignClient auditFeignClient, JwtTokenHandler handler) {
        this.auditFeignClient = auditFeignClient;
        this.handler = handler;
    }

    @Override
    public void sendForAudit(UUID uuid, UserRole userRole, String nameOperation, UserViewDTO userViewDTO) {
        AuditSendDTO auditSendDTO = new AuditSendDTO(
                uuid.toString(),
                userRole.toString(),
                getTextOperation(nameOperation),
                userViewDTO.getUuid().toString(),
                userViewDTO.getFio(),
                userViewDTO.getMail(),
                userViewDTO.getUserRole().toString(),
                userViewDTO.getUserStatus().toString()
        );
        String token = generateToken();try {
            auditFeignClient.sendUser(auditSendDTO, token);
        } catch (RuntimeException e){
            System.out.println(e);
            throw new FeignException("Внутрення ошибки сервера");
        }
    }

    private String getTextOperation(String operation){
        String textOperation;
        switch (operation){
            case ("save") :
                textOperation = "Сохранен новый пользователь";
                break;
            case ("edit") :
                textOperation = "Изменен пользователь";
                break;
            case ("verification") :
                textOperation = "Пройдена верификация";
                break;
            default:
                textOperation = "Неизвесная операция";
                break;
        }
        return textOperation;
    }

    private String generateToken(){
        Map<String, Object> map = new HashMap<>();
        map.put("mail","taskService");
        map.put("role","SERVICE");
        String token = handler.generateAccessToken(map);
        return "Bearer "+token;
    };
}
