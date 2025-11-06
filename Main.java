// Main.java
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.Gson;
import java.io.*;

@WebServlet("/api/user")
public class Main extends HttpServlet {

    private static final Gson gson = new Gson();
    private static final UserService userService = new UserService();

    private void addCorsHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        addCorsHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        addCorsHeaders(resp);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String body = req.getReader().lines().collect(java.util.stream.Collectors.joining());
        User user = gson.fromJson(body, User.class);
        Response response = userService.createUser(user);

        resp.setStatus(response.getData() == null && response.getMessage().contains("tồn tại") ? 400 : 200);
        resp.getWriter().print(gson.toJson(response));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        addCorsHeaders(resp);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Response response = userService.getAllUsers();
        resp.getWriter().print(gson.toJson(response));
    }
}