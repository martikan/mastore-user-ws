package org.martikan.mastore.userapi.controller;

import org.martikan.mastore.userapi.domain.User;
import org.martikan.mastore.userapi.dto.user.UserDTO;
import org.martikan.mastore.userapi.dto.user.UserSignUpDTO;
import org.martikan.mastore.userapi.service.BaseService;
import org.martikan.mastore.userapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, UserDTO> {

    private final UserService userService;

    public UserController(BaseService<User, UserDTO> service, UserService userService) {
        super(service);
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUpUser(@Valid @RequestBody final UserSignUpDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUpUser(dto));
    }

}
