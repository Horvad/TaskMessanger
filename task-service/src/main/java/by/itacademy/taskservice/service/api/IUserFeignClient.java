package by.itacademy.taskservice.service.api;

import by.itacademy.taskservice.core.dto.other.ImplementerDTO;
import by.itacademy.taskservice.core.dto.other.ManagerDTO;
import by.itacademy.taskservice.core.dto.other.StaffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;
@FeignClient(name = "user-service", url = "${feign.user.url}")
public interface IUserFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/manager/{uuid}")
    ManagerDTO getListManager(@PathVariable(name = "uuid") UUID uuid,
                              @RequestHeader("Authorization") String Authorization);

    @RequestMapping(method = RequestMethod.GET, value = "/staff/{uuid}")
    ResponseEntity<StaffDTO> getListStaff(@PathVariable(name = "uuid") UUID uuid,
                                          @RequestHeader("Authorization") String Authorization);

    @RequestMapping(method = RequestMethod.GET, value = "/implementer/{uuid}")
    ResponseEntity<ImplementerDTO> getListImplementer(@PathVariable(name = "uuid") UUID uuid,
                                                      @RequestHeader("Authorization") String Authorization);
}