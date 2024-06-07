package at.htlkaindorf.keskustelebackend.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 07/06/2024
 * Time: 08:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {
    private String email;

    private String password;
}
