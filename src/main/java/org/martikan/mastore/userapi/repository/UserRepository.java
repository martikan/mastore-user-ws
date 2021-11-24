package org.martikan.mastore.userapi.repository;

import org.martikan.mastore.userapi.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for {@link User} entity.
 */
@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByEmail(final String email);

}
