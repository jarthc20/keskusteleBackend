package at.htlkaindorf.keskustelebackend.services.message;

import at.htlkaindorf.keskustelebackend.exceptions.EntityNotFoundException;
import at.htlkaindorf.keskustelebackend.exceptions.MissingAttributeException;
import at.htlkaindorf.keskustelebackend.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 12/04/2024
 * Time: 08:10
 **/
public interface MessageService {
    Message save(Message o) throws MissingAttributeException;

    Message createNew(Message o) throws MissingAttributeException, EntityNotFoundException;

    List<Message> saveAll(List<Message> list);

    Optional<List<Message>> getAll();
    List<Message> getAllByChatroomName(String cr_name);

    Page<Message> getAll(Pageable pageable);

    Optional<List<Message>> getAll(Sort sort);

    Optional<Message> getById(Long id);

}