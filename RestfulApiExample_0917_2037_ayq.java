// 代码生成时间: 2025-09-17 20:37:35
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/api")
public class RestfulApiExample {

    // A simple in-memory store for demonstration purposes
    private static final Map<String, String> dataStore = new HashMap<>();

    @GET
    @Path("/items/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") String id) {
        if (dataStore.containsKey(id)) {
            return Response.ok(dataStore.get(id)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Item with ID '%s' not found.")
                    .build();
        }
    }

    @POST
    @Path("/items")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createItem(String item) {
        String id = String.valueOf(dataStore.size() + 1);
        dataStore.put(id, item);
        return Response.ok("Item with ID '%s' created successfully.").build();
    }

    @PUT
    @Path("/items/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItem(@PathParam("id") String id, String item) {
        if (dataStore.containsKey(id)) {
            dataStore.put(id, item);
            return Response.ok("Item with ID '%s' updated successfully.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Item with ID '%s' not found.")
                    .build();
        }
    }

    @DELETE
    @Path("/items/{id}")
    public Response deleteItem(@PathParam("id") String id) {
        if (dataStore.containsKey(id)) {
            dataStore.remove(id);
            return Response.ok("Item with ID '%s' deleted successfully.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Item with ID '%s' not found.")
                    .build();
        }
    }
}
