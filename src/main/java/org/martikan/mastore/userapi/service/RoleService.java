package org.martikan.mastore.userapi.service;

import org.martikan.mastore.userapi.domain.RoleName;
import org.martikan.mastore.userapi.dto.role.RoleDTO;

/**
 * Interface for {@link RoleServiceImpl}.
 */
public interface RoleService {

    RoleDTO findByRoleName(final RoleName roleName);

}
