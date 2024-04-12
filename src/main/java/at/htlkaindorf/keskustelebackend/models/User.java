package at.htlkaindorf.keskustelebackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 10/04/2024
 * Time: 8:12
 **/

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(
            nullable = false,
            length = 16
    )
    private String username;

    @Column(
            unique = true,
            nullable = false
    )
    private String email;
    private String password;

    @OneToMany(
            mappedBy = "author",
            cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    private List<Message> messages;

    public boolean addMessage(Message message) {
        if (message == null) return false;
        if (message.getId() != null && !messages.stream().filter(m -> m.getId().equals(message.getId())).toList().isEmpty()) return false;
        return messages.add(message);
    }

    public boolean removeMessage(Message message) {
        if (message == null) return false;
        return messages.remove(message);
    }
}