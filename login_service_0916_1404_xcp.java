// 代码生成时间: 2025-09-16 14:04:42
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/login")
public class LoginService {

    // 模拟的用户存储，实际情况下应该从数据库或其他存储介质获取
    private static final JSONObject userCredentials = new JSONObject();
    static {
        userCredentials.put("username", "admin");
        userCredentials.put("password", "password123");
    }

    /**
     * Retrieves a simple text response with a message saying the service is up.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello() {
        return Response.ok("Login Service is up and running.").build();
    }

    /**
     * Performs user login verification.
     * If the provided username and password match the stored credentials,
     * it returns a success message; otherwise, it returns an error response.
     *

     * @param username The username provided by the user for login.
     * @param password The password provided by the user for login.
     * @return A response with the login result.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String username, String password) {
        JSONObject loginResponse = new JSONObject();
        try {
            // Check if the provided credentials match the stored credentials
            if (userCredentials.getString("username").equals(username) 
                    && userCredentials.getString("password").equals(password)) {
                loginResponse.put("message", "Login successful.");
                return Response.ok(loginResponse.toString()).build();
            } else {
                loginResponse.put("error", "Invalid username or password.");
                return Response.status(Response.Status.UNAUTHORIZED).entity(loginResponse.toString()).build();
            }
        } catch (Exception e) {
            loginResponse.put("error", "An error occurred during login: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(loginResponse.toString()).build();
        }
    }
}
