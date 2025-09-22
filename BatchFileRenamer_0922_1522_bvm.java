// 代码生成时间: 2025-09-22 15:22:54
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/rename")
public class BatchFileRenamer {

    private static final String DIRECTORY = "path_to_directory"; // Change this to your directory path

    @GET
    @Path("/files")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFiles() {
        File directory = new File(DIRECTORY);
        if (!directory.isDirectory()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Directory not found.").build();
        }

        List<String> fileNames = new LinkedList<>();
        for (File file : directory.listFiles()) {
            fileNames.add(file.getName());
        }

        return Response.ok(fileNames).build();
    }

    @POST
    @Path("/rename")
    @Produces(MediaType.APPLICATION_JSON)
    public Response renameFiles(@QueryParam("oldPattern") String oldPattern,
                              @QueryParam("newPattern") String newPattern) {
        try {
            File directory = new File(DIRECTORY);
            if (!directory.isDirectory()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Directory not found.").build();
            }

            List<String> renamedFiles = new LinkedList<>();
            for (File file : directory.listFiles()) {
                if (file.getName().matches(oldPattern)) {
                    String newName = file.getName().replaceAll(oldPattern, newPattern);
                    Files.move(file.toPath(), Paths.get(file.getAbsolutePath().replace(file.getName(), newName)));
                    renamedFiles.add(newName);
                }
            }

            return Response.ok(renamedFiles).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred: " + e.getMessage()).build();
        }
    }

    // Additional methods for validation, error handling, and logging can be added here
}
