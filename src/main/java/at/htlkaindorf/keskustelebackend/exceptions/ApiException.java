package at.htlkaindorf.keskustelebackend.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 10/04/2024
 * Time: 09:13
 **/

@Getter
@Setter
public class ApiException extends Exception {
    private HttpStatus status;

    public ApiException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;

    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
