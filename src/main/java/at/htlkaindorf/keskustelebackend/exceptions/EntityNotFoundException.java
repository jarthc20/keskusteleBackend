package at.htlkaindorf.keskustelebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 10/04/2024
 * Time: 09:13
 **/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends ApiException {
    public EntityNotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }

    public EntityNotFoundException() {
        super("There was no entity with the given ID found",HttpStatus.NOT_FOUND);
    }
}
