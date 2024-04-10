package at.htlkaindorf.keskustelebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 10/04/2024
 * Time: 09:13
 **/
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UniqueKeyViolationException extends ApiException {
    public UniqueKeyViolationException(String msg) {
        super(msg, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public UniqueKeyViolationException() {
        super("Unique Constraint Violation - The Entity has an already used UniqueKey",HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
