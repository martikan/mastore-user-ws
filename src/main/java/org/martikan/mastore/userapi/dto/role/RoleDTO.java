package org.martikan.mastore.userapi.dto.role;

import lombok.Builder;
import lombok.Data;
import org.martikan.mastore.userapi.domain.RoleName;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link org.martikan.mastore.userapi.domain.Role} entity.
 */
@Builder
@Data
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 2428674255987280273L;

    private Long id;

    @Enumerated(EnumType.STRING)
    @Size(max = 100, message = "Name of the role cannot be grater then 100 characters.")
    @NotBlank(message = "Name of the role cannot be empty.")
    private RoleName roleName;

}
