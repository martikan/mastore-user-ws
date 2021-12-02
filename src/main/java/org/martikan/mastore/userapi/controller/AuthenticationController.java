package org.martikan.mastore.userapi.controller;

import lombok.RequiredArgsConstructor;
import org.martikan.mastore.userapi.dto.user.UserDTO;
import org.martikan.mastore.userapi.dto.user.UserSignUpDTO;
import org.martikan.mastore.userapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller for `Authentication`.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUpUser(@Valid @RequestBody final UserSignUpDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUpUser(dto));
    }

}
