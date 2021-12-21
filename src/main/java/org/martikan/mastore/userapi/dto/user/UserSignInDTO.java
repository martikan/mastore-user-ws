package org.martikan.mastore.userapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link org.martikan.mastore.userapi.domain.User} login.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserSignInDTO implements Serializable {

    private static final long serialVersionUID = -3425557087370948382L;

    @EqualsAndHashCode.Include
    @Size(max = 255, message = "Email address cannot be grater then 255 characters.")
    @NotBlank(message = "Email address cannot be empty.")
    @Email(message = "Not valid email format.")
    private String email;

    @ToString.Exclude
    @Size(min = 6, max = 16, message = "Password length must be grater then 5 characters and less then 16 characters.")
    private String password;

}
