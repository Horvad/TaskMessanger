package by.itacademy.taskservice.service.user;

import by.itacademy.taskservice.controller.untils.JwtTokenHandler;
import by.itacademy.taskservice.core.dto.other.ImplementerDTO;
import by.itacademy.taskservice.core.dto.other.ManagerDTO;
import by.itacademy.taskservice.core.dto.other.StaffDTO;
import by.itacademy.taskservice.core.exception.FeignException;
import by.itacademy.taskservice.core.exception.NotFoundException;
import by.itacademy.taskservice.service.api.IUserFeignClient;
import by.itacademy.taskservice.service.user.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@EnableFeignClients
public class UserService implements IUserService {

    @Autowired
    private final IUserFeignClient feingClient;
    private final JwtTokenHandler handler;

    public UserService(IUserFeignClient feingClient, JwtTokenHandler handler) {
        this.feingClient = feingClient;
        this.handler = handler;
    }

    @Override
    public ImplementerDTO getImplementers(UUID uuids) {
        String token = generateToken();
        try {
            ImplementerDTO implementerDTOS = feingClient.getListImplementer(uuids, token).getBody();
            if(implementerDTOS==null)
                throw new IllegalArgumentException("Данных пользователей нет в системе");
            return implementerDTOS;
        } catch (RuntimeException e){
            System.out.println(e);
            throw new FeignException("Ошибка проверки пользователей");
        }
    }

    @Override
    public ManagerDTO getManager(UUID uuids) {
        String token = generateToken();
        try {
            ManagerDTO managerDTOS = feingClient.getListManager(uuids, token);
            if(managerDTOS==null)
                throw new NotFoundException("Данных пользователей нет в системе");
            return managerDTOS;
        } catch (RuntimeException e){
            throw new FeignException("Ошибка проверки пользователей");
        }
    }

    @Override
    public StaffDTO getStaff(UUID uuids) {
        String token = generateToken();
//        try {
            StaffDTO staffDTOS = feingClient.getListStaff(uuids,token).getBody();
            if(staffDTOS == null){
                throw new NotFoundException("Данных пользователей нет в системе");
            }
            return staffDTOS;
//        } catch (RuntimeException e){
//            throw new FeignException("Ошибка проверки пользователей");
//        }
    }

    private String generateToken(){
        Map<String, Object> map = new HashMap<>();
        map.put("mail","taskService");
        map.put("role","SERVICE");
        String token = handler.generateAccessToken(map);
        return "Bearer "+token;
    };
}
