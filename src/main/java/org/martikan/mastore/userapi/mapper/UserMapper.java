package org.martikan.mastore.userapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.martikan.mastore.userapi.domain.User;
import org.martikan.mastore.userapi.dto.user.UserDTO;
import org.martikan.mastore.userapi.dto.user.UserSignUpDTO;
import org.springframework.stereotype.Component;

/**
 * Mapper for {@link User} and {@link UserDTO}
 */
@Component
@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {

    @Mapping(target = "password", ignore = true)
    User signUpDtoToEntity(UserSignUpDTO dto);

}
