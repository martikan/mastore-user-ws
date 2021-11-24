package org.martikan.mastore.userapi.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.martikan.mastore.userapi.domain.User} entity.
 */
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -3805263383108701433L;

    @EqualsAndHashCode.Include
    private Long id;

    @EqualsAndHashCode.Include
    @Size(max = 255, message = "Email address cannot be grater then 255 characters.")
    @NotBlank(message = "Email address cannot be empty.")
    @Email(message = "Not valid email format.")
    private String email;

    @Size(max = 100, message = "First name cannot be grater then 100 characters.")
    @NotBlank(message = "First name cannot be empty.")
    private String firstName;

    @Size(max = 100, message = "Last name cannot be grater then 100 characters.")
    @NotBlank(message = "Last name cannot be empty.")
    private String lastName;

    @Size(max = 20, message = "Phone number cannot be grater then 20 characters.")
    private String phoneNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
