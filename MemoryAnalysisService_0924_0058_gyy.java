// 代码生成时间: 2025-09-24 00:58:40
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/memory")
public class MemoryAnalysisService {

    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    /**
     * Retrieves and returns memory usage information in JSON format.
     *
     * @return Memory usage information as a JSON string.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMemoryUsage() {
        try {
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
            MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
            return String.format("{"heapMemoryUsed": %d, "heapMemoryCommitted": %d, "nonHeapMemoryUsed": %d, "nonHeapMemoryCommitted": %d}",
                    heapMemoryUsage.getUsed(), heapMemoryUsage.getCommitted(),
                    nonHeapMemoryUsage.getUsed(), nonHeapMemoryUsage.getCommitted());
        } catch (Exception e) {
            // Log the exception and return an error message
            // For simplicity, we're returning a plain text error message here.
            // In a real-world application, you might want to use a logging framework.
            return "{"error": "Failed to retrieve memory usage information"}";
        }
    }
}
