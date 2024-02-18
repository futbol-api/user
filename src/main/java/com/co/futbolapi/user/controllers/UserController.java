package com.co.futbolapi.user.controllers;


import com.co.futbolapi.user.models.daos.UserDao;
import com.co.futbolapi.user.models.dtos.exceptions.RequestExceptions;
import com.co.futbolapi.user.models.dtos.rq.CreateUserRqDto;
import com.co.futbolapi.user.models.dtos.rs.CreateUserRsDto;
import com.co.futbolapi.user.models.dtos.rs.GetAllUserRsDto;
import com.co.futbolapi.user.models.dtos.rs.GetUserRsDTO;

import com.co.futbolapi.user.models.repositories.UserRepository;
import com.co.futbolapi.user.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


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
    private UserRepository userRepository;



    @PostMapping("/")
    public ResponseEntity<CreateUserRsDto> create(@RequestBody final CreateUserRqDto userRq) {
        return userService.create(userRq).map(ResponseEntity::ok)
                .orElseThrow(() -> new RequestExceptions("401","Error creating user"));
    }

   /* @GetMapping("/")
    public ResponseEntity<List<GetUserRsDTO>> findAll() {
        List<UserDao> users = userRepository.findAll();
        List<GetUserRsDTO> userRs = users.stream()
                .map(u -> GetUserRsDTO.builder().id(u.getId()).nickname(u.getNickname()).build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(userRs);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<GetUserRsDTO> getUserById(@PathVariable final UUID id) {
        Optional<GetUserRsDTO> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseThrow(() -> new RequestExceptions("404", "User not found"));
    }



    @GetMapping("/")
    public ResponseEntity<GetAllUserRsDto> getAll() {
        return userService.getAll().map(ResponseEntity::ok)
                .orElseThrow(() -> new RequestExceptions("404", "User not found"));
    }


    @GetMapping("/nick/{nickname}")
    public ResponseEntity<GetUserRsDTO> getUserByNickname(@PathVariable String nickname) {
        Optional<GetUserRsDTO> user = userService.getUserByNickname(nickname);
        return user.map(ResponseEntity::ok)
                .orElseThrow(() -> new RequestExceptions("404", "User with this nickname not found"));
    }



}
