package by.itAcademy.userservice.service;

import by.itAcademy.userservice.core.dto.UserDTO;
import by.itAcademy.userservice.core.dto.UserViewDTO;
import by.itAcademy.userservice.core.enums.UserRole;
import by.itAcademy.userservice.core.enums.UserStatus;
import by.itAcademy.userservice.core.exception.IncorrectDataException;
import by.itAcademy.userservice.core.exception.LoginException;
import by.itAcademy.userservice.core.exception.NotFoundException;
import by.itAcademy.userservice.core.exception.VerificationException;
import by.itAcademy.userservice.repositories.api.ICodeRepository;
import by.itAcademy.userservice.repositories.entity.*;
import by.itAcademy.userservice.service.api.IAuditService;
import by.itAcademy.userservice.service.api.IMailService;
import by.itAcademy.userservice.service.api.IPersonalUserService;
import by.itAcademy.userservice.service.api.IUserService;
import by.itAcademy.userservice.service.converters.UserDTOToEntityConverter;
import by.itAcademy.userservice.service.converters.UserEntityToUserDTOConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserPersonalService implements IPersonalUserService {
    private final IUserService userService;
    private final ICodeRepository codeRepository;
    private final IMailService mailService;
    private final UserDTOToEntityConverter userDTOToEntityConverter;
    private final UserEntityToUserDTOConverter userEntityToUserDTOConverter;
    private final PasswordEncoder encoder;
    private final IAuditService auditService;
    private final String EMAIL_REGEX =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public UserPersonalService(IUserService userService, ICodeRepository codeRepository,
                               IMailService mailService, UserDTOToEntityConverter userDTOToEntityConverter,
                               UserEntityToUserDTOConverter userEntityToUserDTOConverter,
                               PasswordEncoder encoder, IAuditService auditService) {
        this.userDTOToEntityConverter = userDTOToEntityConverter;
        this.userEntityToUserDTOConverter = userEntityToUserDTOConverter;
        this.codeRepository = codeRepository;
        this.mailService = mailService;
        this.userService = userService;
        this.encoder = encoder;
        this.auditService = auditService;
    }

    @Transactional
    @Override
    public void save(UserDTO userDTO) {
        String mail = userDTO.getMail().toLowerCase();
        userDTO.setMail(mail);
        userDTO.setStatus(UserStatus.WAITING_ACTIVATION);
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        userDTO.setRole(UserRole.USER);
        if(userDTO.getRole().equals(UserRole.ADMIN)){
            throw new IncorrectDataException("Пользователь не может быть админом");
        }
        int codeVerificotion = generateCode();
        codeRepository.save(new CodeEntity(userDTO.getMail(),String.valueOf(codeVerificotion)));
        UserEntity userEntity = userDTOToEntityConverter.convert(userDTO);
        userEntity.setUuid(UUID.randomUUID());
        userService.save(userEntity);
        mailService.sendActivationCode(userDTO.getMail(), codeVerificotion);
        auditService.sendForAudit(
                userEntity.getUuid(),
                userEntity.getRole(),
                "save",
                userEntityToUserDTOConverter.convert(userEntity)
        );
    }

    @Transactional
    @Override
    public void verification(String code, String mail) {
        String mailLoverCase = mail.toLowerCase();
        Optional<CodeEntity> codeEntity = codeRepository.findByMail(mailLoverCase);
        if(!codeEntity.orElseThrow(()-> new IncorrectDataException("Не верный mail")).getCode().equals(code)){
            throw new VerificationException("Не верный код верификации");
        }
        codeRepository.delete(codeEntity.get());
        Optional<UserEntity> userEntity = userService.findByMail(mailLoverCase);
        userEntity.orElseThrow(
                ()->new NotFoundException("Такого пользователя на существует")
        ).setStatus(UserStatus.ACTIVATED);
        userService.save(userEntity.get());
        auditService.sendForAudit(
                userEntity.get().getUuid(),
                userEntity.get().getRole(),
                "verification",
                userEntityToUserDTOConverter.convert(userEntity.get())
        );
    }

    @Transactional(readOnly = true)
    @Override
    public UserViewDTO login(UserDTO userDTO) {
        String mail = userDTO.getMail().toLowerCase();
        Optional<UserEntity> userEntity = userService.findByMail(mail);
        userEntity.orElseThrow(()->new LoginException("Не верный логин или пароль"));
        if(!encoder.matches(userDTO.getPassword(),userEntity.get().getPassword())){
            throw new LoginException("Не верный логин или пароль");
        }
        if(!userEntity.get().getStatus().equals(UserStatus.ACTIVATED)){
            throw new IncorrectDataException("Ваш акккаунт не активен");
        }
        return userEntityToUserDTOConverter.convert(userEntity.get());
    }

    @Transactional(readOnly = true)
    @Override
    public UserViewDTO get(UUID uuid) {
        return userService.getByUuid(uuid);
    }

    @Transactional(readOnly = true)
    @Override
    public UserViewDTO get(String mail) {
        String mailLover = mail.toLowerCase();
        Optional<UserEntity> userEntity = userService.findByMail(mailLover);
        UserViewDTO userViewDTO = userEntityToUserDTOConverter.convert(userEntity.orElseThrow(
                ()-> new NotFoundException("Такого пользователя не существует")
        ));
        return userViewDTO;
    }

    private int generateCode(){
        return (int)(Math.random()*1000000);
    }

    private void validation(UserDTO userDTO){
        Matcher matcher = EMAIL_PATTERN.matcher(userDTO.getMail());
        if(!matcher.matches()){
            throw new VerificationException("не верный email");
        }
        if(userDTO==null){
            throw new VerificationException("не введены данные");
        }
        if(userDTO.getMail()==null||userDTO.getPassword()==null){
            throw new VerificationException("не введено имя пользователя или пароль");
        }
        if(userDTO.getFio()==null){
            throw new VerificationException("Нет фио");
        }
        if(userDTO.getRole()==null){
            throw new VerificationException("нет роли");
        }
        if(userDTO.getStatus()==null){
            throw new VerificationException("нет статуса");
        }
    }
}
