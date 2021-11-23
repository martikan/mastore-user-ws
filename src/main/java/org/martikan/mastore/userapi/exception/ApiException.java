package org.martikan.mastore.userapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for INTERNAL_SERVER_ERROR.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiException extends RuntimeException {

    public ApiException(final String message) {
        super(message);
    }

    public ApiException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
