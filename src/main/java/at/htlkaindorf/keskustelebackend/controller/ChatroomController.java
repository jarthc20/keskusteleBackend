package at.htlkaindorf.keskustelebackend.controller;

import at.htlkaindorf.keskustelebackend.exceptions.EntityNotFoundException;
import at.htlkaindorf.keskustelebackend.exceptions.MissingAttributeException;
import at.htlkaindorf.keskustelebackend.models.Chatroom;
import at.htlkaindorf.keskustelebackend.services.Chatroom.ChatroomService;
import at.htlkaindorf.keskustelebackend.services.Chatroom.ChatroomServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Project: keskusteleBackend
 * Created by: Emma Bauer
 * Date: 11/05/2024
 * Time: 22:19
 **/
@RestController

@RequestMapping("/chatroom")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class ChatroomController {
    private final ChatroomServiceImp chatroomServiceImp;
    @GetMapping("/all")
    public ResponseEntity getAllChatrooms() {
        return ResponseEntity.ok(chatroomServiceImp.getAll().get());
    }

    @PostMapping("/post")
    public ResponseEntity postChatroom(@RequestBody Chatroom chatroom) throws EntityNotFoundException, MissingAttributeException
    {
        return ResponseEntity.ok(chatroomServiceImp.createNew(chatroom));
    }














}
