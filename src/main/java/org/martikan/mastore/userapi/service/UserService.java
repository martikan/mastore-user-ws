package org.martikan.mastore.userapi.service;

import org.martikan.mastore.userapi.dto.user.EmailAvailabilityDTO;
import org.martikan.mastore.userapi.dto.user.UserDTO;
import org.martikan.mastore.userapi.dto.user.UserSignUpDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Interface for {@link UserServiceImpl}.
 */
public interface UserService extends UserDetailsService {

    UserDTO signUpUser(final UserSignUpDTO dto);

    UserDTO getUserDetailsByEmail(final String email);

    EmailAvailabilityDTO existsUserByEmail(final String email);

}
