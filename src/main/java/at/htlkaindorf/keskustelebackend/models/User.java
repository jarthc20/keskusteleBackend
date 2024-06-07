package at.htlkaindorf.keskustelebackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "user")
public class User implements UserDetails {
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
    @JsonIgnore
    @ToString.Exclude
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

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @JsonIgnore
    public String getRealUsername() {
        return this.username;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}