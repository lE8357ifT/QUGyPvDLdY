// 代码生成时间: 2025-09-20 15:09:18
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

@Path("/test")
public class AutomationTestResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTest() {
        return "Hello, this is an automated test!";
    }
}

class AutomationTestSuite extends JerseyTest {

    @Override
    protected ResourceConfig configure() {
        // Configure the JerseyTest to use our resource configuration
        return new ResourceConfig(AutomationTestResource.class);
    }

    @Test
    public void testGetTest() {
        // Perform a GET request and verify the response
        Response response = target("test").request().get();
        assertEquals("Expected response does not match actual response",
                "Hello, this is an automated test!", response.readEntity(String.class));
    }
}
