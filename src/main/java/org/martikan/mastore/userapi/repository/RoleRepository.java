package org.martikan.mastore.userapi.repository;

import org.martikan.mastore.userapi.domain.Role;
import org.martikan.mastore.userapi.domain.RoleName;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for {@link Role} entity.
 */
@Repository
public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> findByRoleName(final RoleName roleName);

}
