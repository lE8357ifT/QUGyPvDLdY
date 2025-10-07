// 代码生成时间: 2025-10-07 20:31:53
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/health")
public class HealthRiskAssessmentService {

    // Health risk factors with default values
    private Map<String, Integer> riskFactors = new HashMap<>();

    public HealthRiskAssessmentService() {
        // Initialize with some default risk factors
        riskFactors.put("smoking", 10);
        riskFactors.put("highBloodPressure", 15);
        riskFactors.put("obesity", 20);
    }

    @GET
    @Path("/risk")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHealthRisk() {
        try {
            // Calculate the total risk score
            int totalRisk = calculateTotalRisk();

            // Create the response object
            Map<String, Object> response = new HashMap<>();
            response.put("totalRiskScore", totalRisk);
            response.put("riskFactors", riskFactors);

            // Return the response as JSON
            return Response.ok(response).build();
        } catch (Exception e) {
            // Handle any unexpected errors
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    // Method to calculate the total risk score based on risk factors
    private int calculateTotalRisk() {
        int totalRisk = 0;
        for (int risk : riskFactors.values()) {
            totalRisk += risk;
        }
        return totalRisk;
    }
}
