package at.htlkaindorf.keskustelebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 10/04/2024
 * Time: 09:13
 **/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingAttributeException extends ApiException {
    public MissingAttributeException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST);
    }

    public MissingAttributeException() {
        super("A attribute which is needed is not filled out correctly",HttpStatus.BAD_REQUEST);
    }
}
