package at.htlkaindorf.keskustelebackend.repos;

import at.htlkaindorf.keskustelebackend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 10/04/2024
 * Time: 09:09
 **/
public interface MessageRepo extends JpaRepository<Message, Long> {
}
