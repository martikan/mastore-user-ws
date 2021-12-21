package org.martikan.mastore.userapi.controller;

import org.martikan.mastore.userapi.aspect.IsAdmin;
import org.martikan.mastore.userapi.domain.User;
import org.martikan.mastore.userapi.dto.user.EmailAvailabilityDTO;
import org.martikan.mastore.userapi.dto.user.UserDTO;
import org.martikan.mastore.userapi.service.BaseService;
import org.martikan.mastore.userapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for {@link User} entity.
 * Override all methods to be secured.
 */
@IsAdmin
@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, UserDTO> {

    private final UserService userService;

    public UserController(BaseService<User, UserDTO> service, UserService userService) {
        super(service);
        this.userService = userService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<UserDTO>> findAll(@RequestParam(name = "page", defaultValue = "0") final int page,
                                                 @RequestParam(name = "size", defaultValue = "20") final int size,
                                                 @RequestParam(name = "sort", defaultValue = "id") final String sort) {
        return super.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UserDTO> findById(@PathVariable("id") final Long id) {
        return super.findById(id);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<UserDTO> update(@PathVariable("id") final Long id, @Valid @RequestBody final UserDTO dto) {
        return super.update(id, dto);
    }

    @PostMapping
    @Override
    public ResponseEntity<UserDTO> save(@Valid @RequestBody final UserDTO dto) {
        return super.save(dto);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteById(@PathVariable final Long id) {
        return super.deleteById(id);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/checkEmailAvailability")
    public ResponseEntity<EmailAvailabilityDTO> checkEmailAvailability(@RequestParam(name = "email") final String email) {
        return ResponseEntity.ok(userService.existsUserByEmail(email));
    }

}
