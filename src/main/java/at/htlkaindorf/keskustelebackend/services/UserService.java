package at.htlkaindorf.keskustelebackend.services;

import at.htlkaindorf.keskustelebackend.exceptions.MissingAttributeException;
import at.htlkaindorf.keskustelebackend.exceptions.UniqueKeyViolationException;
import at.htlkaindorf.keskustelebackend.models.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 10/04/2024
 * Time: 09:10
 **/
public interface UserService {
    User save(User o) ;


    User createNew(User o);

    List<User> saveAll(List<User> list);

    Optional<List<User>> getAll();

    Page<User> getAll(Pageable pageable);

    Optional<List<User>> getAll(Sort sort);


    Optional<User> getById(Long id);

    Optional<User> update(Long id, User o);

    void delete(User o);

    void deleteAll();

    void deleteById(Long id);
}
