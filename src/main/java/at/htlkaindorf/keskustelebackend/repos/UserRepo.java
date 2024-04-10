package at.htlkaindorf.keskustelebackend.repos;

import at.htlkaindorf.keskustelebackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 10/04/2024
 * Time: 09:10
 **/
public interface UserRepo extends JpaRepository<User, String> {
}
