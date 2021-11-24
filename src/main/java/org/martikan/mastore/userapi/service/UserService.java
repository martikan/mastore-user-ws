package org.martikan.mastore.userapi.service;

import org.martikan.mastore.userapi.dto.user.UserDTO;
import org.martikan.mastore.userapi.dto.user.UserSignUpDTO;

public interface UserService {

    UserDTO signUpUser(final UserSignUpDTO dto);

}
