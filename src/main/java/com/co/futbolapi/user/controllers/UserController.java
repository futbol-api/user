package com.co.futbolapi.user.controllers;

import com.co.futbolapi.user.models.dtos.rq.CreateUserRqDto;
import com.co.futbolapi.user.models.dtos.rs.CreateUserRsDto;
import com.co.futbolapi.user.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * The user entrypoint.
 *
 * @author luis.bolivar
 */
@RestController
@RequestMapping("/user")
@Slf4j
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<CreateUserRsDto> create(@RequestBody final CreateUserRqDto userRq) {
        return userService.create(userRq).map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Error creating user"));
    }
}
