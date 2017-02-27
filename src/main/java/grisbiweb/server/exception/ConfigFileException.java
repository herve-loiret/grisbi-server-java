package grisbiweb.server.exception;

public class ConfigFileException extends RuntimeException {

    private static final long serialVersionUID = -7940362862272948011L;

    public ConfigFileException(String message) {
        super(message);
    }

    public ConfigFileException(String message, Throwable cause) {
        super(message, cause);
    }


}
