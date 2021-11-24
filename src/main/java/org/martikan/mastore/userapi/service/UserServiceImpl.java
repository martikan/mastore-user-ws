package org.martikan.mastore.userapi.service;

import lombok.extern.slf4j.Slf4j;
import org.martikan.mastore.userapi.domain.User;
import org.martikan.mastore.userapi.dto.user.UserDTO;
import org.martikan.mastore.userapi.dto.user.UserSignUpDTO;
import org.martikan.mastore.userapi.exception.BadRequestException;
import org.martikan.mastore.userapi.mapper.UserMapper;
import org.martikan.mastore.userapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * Service for {@link User} entity.
 */
@Slf4j
@Transactional
@Service
public class UserServiceImpl extends BaseService<User, UserDTO> implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        super(userRepository, userMapper);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO signUpUser(final UserSignUpDTO dto) {

        userRepository.findByEmail(dto.getEmail())
                .ifPresent(e -> { throw new BadRequestException("Email address is already exist."); });

        final var newUser = userMapper.signUpDtoToEntity(dto);
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userMapper.toDTO(userRepository.save(newUser));
    }

    @Override
    public UserDTO getUserDetailsByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(e -> new org.springframework.security.core.userdetails.User(
                        e.getEmail(),
                        e.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        Collections.emptyList()
                ))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
