package at.htlkaindorf.keskustelebackend.services.user;

import at.htlkaindorf.keskustelebackend.exceptions.EntityNotFoundException;
import at.htlkaindorf.keskustelebackend.exceptions.MissingAttributeException;
import at.htlkaindorf.keskustelebackend.models.User;
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
public interface UserService {
    User save(User o) throws MissingAttributeException;

    User createNew(User o) throws MissingAttributeException, EntityNotFoundException;

    List<User> saveAll(List<User> list);

    Optional<List<User>> getAll();

    Page<User> getAll(Pageable pageable);

    Optional<List<User>> getAll(Sort sort);


    Optional<User> getById(Long id);

}