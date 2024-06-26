package at.htlkaindorf.keskustelebackend.services.user;

import at.htlkaindorf.keskustelebackend.exceptions.EntityNotFoundException;
import at.htlkaindorf.keskustelebackend.exceptions.MissingAttributeException;
import at.htlkaindorf.keskustelebackend.exceptions.UniqueKeyViolationException;
import at.htlkaindorf.keskustelebackend.models.User;
import at.htlkaindorf.keskustelebackend.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    //CONFIRM USER

    public boolean isValid (User user) throws Exception {
        String emailRegex = "([a-zA-Z0-9+._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)";
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";
        boolean isValid = true;

        if (!user.getPassword().matches(passwordRegex) || !user.getEmail().matches(emailRegex)) isValid = false;

        return isValid;
    }



    @Override
    public User save(User user) throws Exception {
        if (user.getUsername() == null || user.getUsername().trim().equalsIgnoreCase("")
                || user.getEmail() == null || user.getEmail().trim().equalsIgnoreCase("")
                || user.getPassword() == null || user.getPassword().trim().equalsIgnoreCase(""))
            throw new MissingAttributeException();

        if(!isValid(user))
            throw new Exception("User is invalid");


        return userRepo.save(user);
    }

    @Override
    public User createNew(User user) {
        if (user.getMessages() == null) user.setMessages(new ArrayList<>());
        user.setId(null);

        return userRepo.save(user);
    }

    @Override
    public Optional<User> updateUser(String id, User user) throws Exception {
        Optional<User> userOptional = getById(id);
        if (userOptional.isEmpty()) return Optional.empty();
        User userToUpdate = userOptional.get();
        user.setId(null);

        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = ReflectionUtils.getField(field, user);
            if (value != null && !(value instanceof ArrayList<?>)) {
                ReflectionUtils.setField(field, userToUpdate, value);
            }
        }

        return Optional.of(userRepo.save(userToUpdate));
    }

    @Override
    public List<User> saveAll(List<User> list) {
        return userRepo.saveAll(list);
    }

    @Override
    public Optional<List<User>> getAll() {
        return Optional.of(userRepo.findAll());
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public Optional<List<User>> getAll(Sort sort) {
        return Optional.of(userRepo.findAll(sort));
    }


    @Override
    public Optional<User> getById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> longin(User user) {
        Optional<User> userByEmail = userRepo.findUserByEmail(user.getEmail());
        if (userByEmail.isEmpty()) return Optional.empty();
        if (user.getPassword().equals(userByEmail.get().getPassword())) return Optional.empty();

        return userByEmail;
    }
}
