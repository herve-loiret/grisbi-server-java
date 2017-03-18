package grisbiweb.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY)
public class GrisbiFileException extends RuntimeException {

    private static final long serialVersionUID = -4385824820153863241L;

    public GrisbiFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
