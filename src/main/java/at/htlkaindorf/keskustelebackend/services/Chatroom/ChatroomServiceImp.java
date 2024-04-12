package at.htlkaindorf.keskustelebackend.services.Chatroom;

import at.htlkaindorf.keskustelebackend.exceptions.EntityNotFoundException;
import at.htlkaindorf.keskustelebackend.exceptions.MissingAttributeException;
import at.htlkaindorf.keskustelebackend.models.Chatroom;
import at.htlkaindorf.keskustelebackend.models.User;
import at.htlkaindorf.keskustelebackend.repos.ChatroomRepo;
import at.htlkaindorf.keskustelebackend.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
public class ChatroomServiceImp implements ChatroomService {
    private final ChatroomRepo chatroomRepo;
    private final UserRepo userRepo;

    @Override
    public Chatroom save(Chatroom chatroom) throws MissingAttributeException {
        if (chatroom.getName() == null || chatroom.getName().trim().equalsIgnoreCase("")) throw new MissingAttributeException();
        return chatroomRepo.save(chatroom);
    }

    @Override
    public Chatroom createNew(Chatroom chatroom) throws MissingAttributeException, EntityNotFoundException {
        if (chatroom.getCreator() == null || chatroom.getCreator().getId() == null) throw new MissingAttributeException();
        Optional<User> creatorById = userRepo.findById(chatroom.getCreator().getId());
        if (creatorById.isEmpty()) throw new EntityNotFoundException();

        if (chatroom.getName() == null || chatroom.getName().trim().equalsIgnoreCase("")) throw new MissingAttributeException();

        if (chatroom.getMessages() == null) chatroom.setMessages(new ArrayList<>());

        return chatroomRepo.save(chatroom);
    }

    @Override
    public List<Chatroom> saveAll(List<Chatroom> chatrooms) {
        return chatroomRepo.saveAll(chatrooms);
    }

    @Override
    public Optional<List<Chatroom>> getAll() {
        return Optional.of(chatroomRepo.findAll());
    }

    @Override
    public Page<Chatroom> getAll(Pageable pageable) {
        return chatroomRepo.findAll(pageable);
    }

    @Override
    public Optional<List<Chatroom>> getAll(Sort sort) {
        return Optional.of(chatroomRepo.findAll(sort));
    }

    @Override
    public Optional<Chatroom> getById(Long id) {
        return chatroomRepo.findById(id);
    }

}
