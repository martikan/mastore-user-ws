package org.martikan.mastore.userapi.dto.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmailAvailabilityDTO {

    private boolean emailAvailable;

}
