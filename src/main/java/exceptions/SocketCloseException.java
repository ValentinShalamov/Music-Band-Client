package exceptions;

import java.io.IOException;

public class SocketCloseException extends IOException {
    public SocketCloseException(String message) {
        super(message);
    }

}
