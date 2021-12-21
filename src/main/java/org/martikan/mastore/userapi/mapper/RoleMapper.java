package org.martikan.mastore.userapi.mapper;

import org.mapstruct.Mapper;
import org.martikan.mastore.userapi.domain.Role;
import org.martikan.mastore.userapi.dto.role.RoleDTO;

/**
 * Mapper for {@link Role} and {@link RoleDTO}.
 */
@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<Role, RoleDTO> {
}
