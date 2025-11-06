// UserService.java
public class UserService {
    private final UserRepository repository = new UserRepository();

    public Response createUser(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            return new Response("Email không được để trống", null);
        }
        if (repository.existsByEmail(user.getEmail())) {
            return new Response("Email đã tồn tại", null);
        }
        repository.add(user);
        return new Response("Tạo user thành công", user);
    }

    public Response getAllUsers() {
        return new Response("Danh sách user", repository.findAll());
    }
}