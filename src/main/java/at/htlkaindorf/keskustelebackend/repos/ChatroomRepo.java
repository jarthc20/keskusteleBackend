package at.htlkaindorf.keskustelebackend.repos;

import at.htlkaindorf.keskustelebackend.models.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 10/04/2024
 * Time: 09:08
 **/
public interface ChatroomRepo extends JpaRepository<Chatroom,Long> {
}
