package at.htlkaindorf.keskustelebackend.services.Chatroom;

import at.htlkaindorf.keskustelebackend.exceptions.EntityNotFoundException;
import at.htlkaindorf.keskustelebackend.exceptions.MissingAttributeException;
import at.htlkaindorf.keskustelebackend.models.Chatroom;
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
public interface ChatroomService {
    Chatroom save(Chatroom o) throws MissingAttributeException;

    Chatroom createNew(Chatroom o) throws MissingAttributeException, EntityNotFoundException;

    List<Chatroom> saveAll(List<Chatroom> list);

    Optional<List<Chatroom>> getAll();

    Page<Chatroom> getAll(Pageable pageable);

    Optional<List<Chatroom>> getAll(Sort sort);


    Optional<Chatroom> getById(Long id);

}