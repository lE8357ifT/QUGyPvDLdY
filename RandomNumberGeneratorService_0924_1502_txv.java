// 代码生成时间: 2025-09-24 15:02:35
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;

@Path("/random")
public class RandomNumberGeneratorService {

    // Minimum value for the random number
    private static final int MIN_VALUE = 1;

    // Maximum value for the random number
    private static final int MAX_VALUE = 100;

    // Random number generator instance
    private final Random random = new Random();

    /**
     * Generates a random number between 1 and 100 and returns it as a JSON object.
     *
     * @return The generated random number.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateRandomNumber() {
        try {
            // Generate a random number within the specified range
            int randomNumber = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;

            // Return the random number as a JSON object
            String jsonResponse = String.format({"%s": %d}, "randomNumber", randomNumber);
            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            // Handle any exceptions that may occur during the generation process
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error generating random number").build();
        }
    }
}
