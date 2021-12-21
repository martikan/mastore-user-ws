package org.martikan.mastore.userapi.service;

import org.martikan.mastore.userapi.domain.Role;
import org.martikan.mastore.userapi.domain.RoleName;
import org.martikan.mastore.userapi.dto.role.RoleDTO;
import org.martikan.mastore.userapi.exception.ResourceNotFoundException;
import org.martikan.mastore.userapi.mapper.RoleMapper;
import org.martikan.mastore.userapi.repository.RoleRepository;
import org.springframework.stereotype.Service;

/**
 * Service for {@link Role} entity.
 */
@Service
public class RoleServiceImpl extends BaseService<Role, RoleDTO> implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        super(roleRepository, roleMapper);
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDTO findByRoleName(final RoleName roleName) {
        return roleRepository.findByRoleName(roleName).map(roleMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), "roleName", roleName));
    }
}
