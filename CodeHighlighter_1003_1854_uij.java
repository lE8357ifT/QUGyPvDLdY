// 代码生成时间: 2025-10-03 18:54:53
 * It provides an API to receive plain text code and returns the code with syntax highlighting.
 * 
 * @author Your Name
 * @version 1.0
 */

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/highlight")
public class CodeHighlighter {

    // Define the supported programming languages for syntax highlighting
    private static final String[] SUPPORTED_LANGUAGES = { "java", "python", "javascript", "html", "css" };

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/code")
    public Response highlightCode(String plainTextCode, @javax.ws.rs.QueryParam("language") String language) {
        // Check if the provided language is supported
        if (language == null || !isLanguageSupported(language)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Unsupported language. Please choose from: " +
                    java.util.Arrays.toString(SUPPORTED_LANGUAGES)).build();
        }

        try {
            // Apply syntax highlighting to the plain text code
            // This is a placeholder for the actual highlighting logic, which would involve
            // parsing the code and applying appropriate HTML styles or span tags based on the language syntax
            String highlightedCode = applySyntaxHighlighting(plainTextCode, language);
            return Response.ok(highlightedCode).build();
        } catch (Exception e) {
            // Handle any exceptions that occur during the highlighting process
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error highlighting code: " + e.getMessage()).build();
        }
    }

    /*
     * Check if the provided language is supported for syntax highlighting
     */
    private boolean isLanguageSupported(String language) {
        for (String supportedLanguage : SUPPORTED_LANGUAGES) {
            if (supportedLanguage.equalsIgnoreCase(language)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Apply syntax highlighting to the plain text code
     * This method is a placeholder and should be replaced with actual highlighting logic.
     */
    private String applySyntaxHighlighting(String plainTextCode, String language) throws Exception {
        // Placeholder logic for applying syntax highlighting
        // In a real-world scenario, you would use a library or a service to highlight the code based on the language
        // For demonstration purposes, we'll just wrap the code in a span with a class that represents the language
        return "<span class='highlighted-code language-" + language + "'>" + plainTextCode + "</span>";
    }
}
