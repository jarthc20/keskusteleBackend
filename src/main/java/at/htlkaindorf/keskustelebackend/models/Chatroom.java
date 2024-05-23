package at.htlkaindorf.keskustelebackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 10/04/2024
 * Time: 8:12
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chatroom")
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatroom_seq")
    @SequenceGenerator(name = "chatroom_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(
            unique = true
    )
    private String name;
    private String pic_path;
    @ManyToOne(
            cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            foreignKey = @ForeignKey(name = "cr_id_fk")
    )
    private User creator;

    @OneToMany(
            mappedBy = "chatroom",
            cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JsonIgnore
    private List<Message> messages;

    public boolean addMessage(Message message) {
        if (message == null) return false;
        if (message.getId() != null && !messages.stream().filter(m -> m.getId().equals(message.getId())).toList().isEmpty()) return false;
        message.setChatroom(this);
        return messages.add(message);
    }
    public boolean removeMessage(Message message) {
        if (message == null) return false;
        message.setChatroom(null);
        return messages.remove(message);
    }
}