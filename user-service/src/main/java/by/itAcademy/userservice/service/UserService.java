package by.itAcademy.userservice.service;

import by.itAcademy.userservice.core.dto.PageDTO;
import by.itAcademy.userservice.core.dto.UserDTO;
import by.itAcademy.userservice.core.dto.UserViewDTO;
import by.itAcademy.userservice.core.enums.UserRole;
import by.itAcademy.userservice.core.exception.IncorrectDataException;
import by.itAcademy.userservice.core.exception.NotFoundException;
import by.itAcademy.userservice.core.exception.VersionException;
import by.itAcademy.userservice.repositories.api.IUserRepository;
import by.itAcademy.userservice.repositories.entity.UserEntity;
import by.itAcademy.userservice.service.api.IAuditService;
import by.itAcademy.userservice.service.api.IUserService;
import by.itAcademy.userservice.service.converters.UserDTOToEntityConverter;
import by.itAcademy.userservice.service.converters.UserEntityToUserDTOConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final UserEntityToUserDTOConverter userEntityToUserDTOConverter;
    private final UserDTOToEntityConverter userDTOToEntityConverter;
    private final PasswordEncoder passwordEncoder;
    private final IAuditService auditService;

    private final String EMAIL_REGEX =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

//    private final IAuditFeignClient auditFeignClient;


    public UserService(IUserRepository userRepository, UserEntityToUserDTOConverter userEntityToUserDTOConverter,
                       UserDTOToEntityConverter userDTOToEntityConverter, PasswordEncoder passwordEncoder, IAuditService auditService) {
        this.userRepository = userRepository;
        this.userEntityToUserDTOConverter = userEntityToUserDTOConverter;
        this.userDTOToEntityConverter = userDTOToEntityConverter;
        this.passwordEncoder = passwordEncoder;
        this.auditService = auditService;
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO getPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<UserEntity> userEntities = userRepository.findAll(pageable);
        List<UserViewDTO> context = userEntities
                .getContent()
                .stream()
                .map(s->userEntityToUserDTOConverter.convert(s))
                .collect(Collectors.toList());
        PageDTO pageDTO = new PageDTO(
                userEntities.getNumber(),
                userEntities.getSize(),
                userEntities.getTotalPages(),
                userEntities.getTotalElements(),
                userEntities.isFirst(),
                userEntities.getNumberOfElements(),
                userEntities.isLast(),
                context
        );
        return pageDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public UserViewDTO getByUuid(UUID uuid) {
        Optional<UserEntity> userEntity = userRepository.findById(uuid);
        return userEntityToUserDTOConverter.convert(userEntity.orElseThrow(
                ()-> new NotFoundException("Пользователя не существует")));
    }

    @Transactional
    @Override
    public void edit(UUID uuid, LocalDateTime dtUpdate, UserDTO userDTO, UUID uuidEditor, UserRole userRoleEditor) {
        validation(userDTO);
        Optional<UserEntity> userEntity = userRepository.findById(uuid);
        System.out.println(userEntity.get().getDtUpdate());
        userEntity.orElseThrow(()-> new NotFoundException("Пользователя не существует"));
        if(!(dtUpdate.isEqual((userEntity.get().getDtUpdate())))){
            new VersionException("Пользователь был изменен");
        }
        if(!userEntity
                .orElseThrow(()-> new NotFoundException("Пользователя не существует"))
                .getDtUpdate()
                .isEqual(dtUpdate)){
            throw new NotFoundException("Не правильная версия");
        };
        UserEntity editUser = userDTOToEntityConverter.convert(userDTO);
        editUser.setUuid(userEntity.get().getUuid());
        editUser.setDtUpdate(userEntity.get().getDtUpdate());
        editUser.setDtCreate(userEntity.get().getDtCreate());
        editUser.setPassword(passwordEncoder.encode(userEntity.get().getPassword()));
        userRepository.save(editUser);
        auditService.sendForAudit(
                uuidEditor,
                userRoleEditor, "edit",
                userEntityToUserDTOConverter.convert(userEntity.get()));
    }

    @Transactional
    @Override
    public void save(UserDTO userDTO, UUID uuidSaver, UserRole userRoleSaver) {
        validation(userDTO);
        Optional<UserEntity> userEntity = userRepository.findByMail(userDTO.getMail());
        if(userEntity.isPresent()){
            throw new IncorrectDataException("Пользователь с такой почтой существует");
        }
        userEntity = Optional.of(userDTOToEntityConverter.convert(userDTO));
        userEntity.get().setUuid(UUID.randomUUID());
        userEntity.get().setPassword(passwordEncoder.encode(userEntity.get().getPassword()));
        userRepository.save(userEntity.get());
        auditService.sendForAudit(
                uuidSaver,
                userRoleSaver,
                "save",
                userEntityToUserDTOConverter.convert(userEntity.get()));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserEntity> findByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    @Transactional
    @Override
    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    private void validation(UserDTO userDTO){
        Matcher matcher = EMAIL_PATTERN.matcher(userDTO.getMail());
        if(!matcher.matches()){
            throw new IncorrectDataException("не верный email");
        }
        if(userDTO==null){
            throw new IncorrectDataException("не введены данные");
        }
        if(userDTO.getMail()==null||userDTO.getPassword()==null){
            throw new IncorrectDataException("не введено имя пользователя или пароль");
        }
        if(userDTO.getFio()==null){
            throw new IncorrectDataException("Нет фио");
        }
        if(userDTO.getRole()==null){
            throw new IncorrectDataException("нет роли");
        }
        if(userDTO.getStatus()==null){
            throw new IncorrectDataException("нет статуса");
        }
    }
}
