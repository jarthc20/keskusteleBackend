package at.htlkaindorf.keskustelebackend;

import at.htlkaindorf.keskustelebackend.models.Chatroom;
import at.htlkaindorf.keskustelebackend.models.Message;
import at.htlkaindorf.keskustelebackend.models.User;
import at.htlkaindorf.keskustelebackend.services.Chatroom.ChatroomServiceImp;
import at.htlkaindorf.keskustelebackend.services.message.MessageService;
import at.htlkaindorf.keskustelebackend.services.message.MessageServiceImp;
import at.htlkaindorf.keskustelebackend.services.user.UserServiceImp;
import com.github.javafaker.Faker;
import com.github.javafaker.Team;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Map;

@SpringBootApplication
public class KeskusteleBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeskusteleBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ChatroomServiceImp chatroomServiceImp, UserServiceImp userServiceImp, MessageServiceImp messageServiceImp) {
        return args -> {
            Faker faker = Faker.instance();

            User admin = new User(null, "admin", "admin@cool.com", "12345tJ!", new ArrayList<>(), Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now()));
            Chatroom cr = new Chatroom(null, "CR", "", admin, new ArrayList<>());
            Message message = new Message(null, "Hello World!", OffsetDateTime.now(), admin, null);
            cr.addMessage(message);

            chatroomServiceImp.save(cr);
        };
    }

}
