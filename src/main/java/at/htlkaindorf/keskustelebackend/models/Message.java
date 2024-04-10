package at.htlkaindorf.keskustelebackend.models;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 10/04/2024
 * Time: 8:12
 **/

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_seq")
    @SequenceGenerator(name = "message_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    private String content;
    private OffsetDateTime time;

    @ManyToOne(
            cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            foreignKey = @ForeignKey(name = "msg_author_fk")
    )
    private User author;


    @ManyToOne(
            cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            foreignKey = @ForeignKey(name = "msg_cr_fk")
    )
    private Chatroom chatroom;
}