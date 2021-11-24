package org.martikan.mastore.userapi.service;

import lombok.extern.slf4j.Slf4j;
import org.martikan.mastore.userapi.domain.User;
import org.martikan.mastore.userapi.dto.user.UserDTO;
import org.martikan.mastore.userapi.dto.user.UserSignUpDTO;
import org.martikan.mastore.userapi.exception.BadRequestException;
import org.martikan.mastore.userapi.mapper.UserMapper;
import org.martikan.mastore.userapi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
