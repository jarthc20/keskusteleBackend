package at.htlkaindorf.keskustelebackend.controller;

import at.htlkaindorf.keskustelebackend.exceptions.EntityNotFoundException;
import at.htlkaindorf.keskustelebackend.exceptions.MissingAttributeException;
import at.htlkaindorf.keskustelebackend.models.Message;
import at.htlkaindorf.keskustelebackend.services.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 14/04/2024
 * Time: 16:15
 **/

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MessageController {
    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/all")
    public ResponseEntity<List<Message>> getAllFromChatroom(@RequestParam(required = false) String chatroom) {
        return ResponseEntity.ok(chatroom == null ? messageService.getAll(Sort.by("time")).get() : messageService.getAllByChatroomName(chatroom));
    }

    @PostMapping("/post/{cr_name}")
    public ResponseEntity<Message> sendMessage(@PathVariable String cr_name,
                            @RequestBody Message message) {
        try {
            Message aNew = messageService.createNew(message);
            simpMessagingTemplate.convertAndSend("/chatroom/" + cr_name + "/messages", aNew);
            return ResponseEntity.ok(aNew);
        } catch (MissingAttributeException | EntityNotFoundException e) {
            simpMessagingTemplate.convertAndSend("/chatroom/" + cr_name + "/error", e.getMessage());
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


}
