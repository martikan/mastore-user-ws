package org.martikan.mastore.userapi.service;

import lombok.extern.slf4j.Slf4j;
import org.martikan.mastore.userapi.domain.RoleName;
import org.martikan.mastore.userapi.domain.User;
import org.martikan.mastore.userapi.dto.user.EmailAvailabilityDTO;
import org.martikan.mastore.userapi.dto.user.UserDTO;
import org.martikan.mastore.userapi.dto.user.UserSignUpDTO;
import org.martikan.mastore.userapi.exception.BadRequestException;
import org.martikan.mastore.userapi.mapper.RoleMapper;
import org.martikan.mastore.userapi.mapper.UserMapper;
import org.martikan.mastore.userapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Service for {@link User} entity.
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseService<User, UserDTO> implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleService roleService;

    private final RoleMapper roleMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder, RoleService roleService, RoleMapper roleMapper) {
        super(userRepository, userMapper);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDTO signUpUser(final UserSignUpDTO dto) {

        userRepository.findByEmail(dto.getEmail())
                .ifPresent(e -> { throw new BadRequestException("Email address is already exist."); });

        // Set up default `USER` role.
        final var role = roleMapper.toEntity(roleService.findByRoleName(RoleName.ROLE_USER));

        final var newUser = userMapper.signUpDtoToEntity(dto);
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newUser.setRoles(Collections.singleton(role));

        return userMapper.toDTO(userRepository.save(newUser));
    }

    @Override
    public UserDTO getUserDetailsByEmail(final String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public EmailAvailabilityDTO existsUserByEmail(final String email) {
        return EmailAvailabilityDTO.builder()
                .emailAvailable(userRepository.existsUserByEmail(email))
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(e -> new org.springframework.security.core.userdetails.User(
                        e.getEmail(),
                        e.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        e.getRoles()
                ))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
