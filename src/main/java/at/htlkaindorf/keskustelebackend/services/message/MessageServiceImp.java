package at.htlkaindorf.keskustelebackend.services.message;

import at.htlkaindorf.keskustelebackend.exceptions.EntityNotFoundException;
import at.htlkaindorf.keskustelebackend.exceptions.MissingAttributeException;
import at.htlkaindorf.keskustelebackend.models.Message;
import at.htlkaindorf.keskustelebackend.models.User;
import at.htlkaindorf.keskustelebackend.repos.MessageRepo;
import at.htlkaindorf.keskustelebackend.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 12/04/2024
 * Time: 08:18
 **/
@Service
@RequiredArgsConstructor
public class MessageServiceImp implements MessageService {
    private final MessageRepo messageRepo;
    private final UserRepo userRepo;

    @Override
    public Message save(Message message) throws MissingAttributeException {
        if (message.getContent() == null || message.getContent().trim().equalsIgnoreCase("")) throw new MissingAttributeException();
        return messageRepo.save(message);
    }

    @Override
    public Message createNew(Message message) throws MissingAttributeException, EntityNotFoundException {
        if (message.getChatroom() == null || message.getChatroom().getId() == null) throw new MissingAttributeException();
        Optional<Message> messageById = messageRepo.findById(message.getChatroom().getId());
        if (messageById.isEmpty()) throw new EntityNotFoundException();

        if (message.getAuthor() == null || message.getAuthor().getId() == null) throw new MissingAttributeException();
        Optional<User> authorById = userRepo.findById(message.getAuthor().getId());
        if (authorById.isEmpty()) throw new EntityNotFoundException();

        if (message.getContent() == null || message.getContent().trim().equalsIgnoreCase("")) throw new MissingAttributeException();

        return messageRepo.save(message);
    }

    @Override
    public List<Message> saveAll(List<Message> messages) {
        return messageRepo.saveAll(messages);
    }

    @Override
    public Optional<List<Message>> getAll() {
        return Optional.of(messageRepo.findAll());
    }

    @Override
    public Page<Message> getAll(Pageable pageable) {
        return messageRepo.findAll(pageable);
    }

    @Override
    public Optional<List<Message>> getAll(Sort sort) {
        return Optional.of(messageRepo.findAll(sort));
    }

    @Override
    public Optional<Message> getById(Long id) {
        return messageRepo.findById(id);
    }

}
