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

    @MessageMapping("/messages/{cr_name}")
    public void sendMessage(@DestinationVariable String cr_name,
                            Message message) {
        try {
            Message aNew = messageService.createNew(message);
            simpMessagingTemplate.convertAndSend("/chatroom/" + cr_name + "/messages", aNew);
        } catch (MissingAttributeException | EntityNotFoundException e) {
            simpMessagingTemplate.convertAndSend("/chatroom/" + cr_name + "/error", e.getMessage());
        }
    }


}
