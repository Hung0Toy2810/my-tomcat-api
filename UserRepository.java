// UserRepository.java
import java.util.*;

public class UserRepository {
    private static final List<User> users = new ArrayList<>();

    public void add(User user) {
        users.add(user);
    }

    public boolean existsByEmail(String email) {
        return users.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public List<User> findAll() {
        return new ArrayList<>(users); // Trả về bản sao
    }
}