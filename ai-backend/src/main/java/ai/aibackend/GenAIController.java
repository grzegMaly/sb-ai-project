package ai.aibackend;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class GenAIController {

    private final ChatService chatService;
    private final ImageService imageService;
    private final RecipeService recipeService;

    @GetMapping("/ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    @GetMapping("/ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt) {
        return chatService.getResponseOptions(prompt);
    }

    @GetMapping("/generate-image")
    public List<String> generateImages(HttpServletResponse response,
                                       @RequestParam String prompt,
                                       @RequestParam(defaultValue = "1") Integer n,
                                       @RequestParam(defaultValue = "1024") Integer width,
                                       @RequestParam(defaultValue = "1024") Integer height
    ) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt, n, width, height);

        //Streams to get urls from ImageResponse
        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();

        return imageUrls;
    }

    @GetMapping("/recipe-creator")
    public String recipeCreator(
            @RequestParam String ingredients,
            @RequestParam(defaultValue = "any") String cuisine,
            @RequestParam(defaultValue = "") String dietaryRestrictions
    ){
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
    }
}
