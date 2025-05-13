package ai.aibackend;

import lombok.AllArgsConstructor;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageService {

    private final OpenAiImageModel openAiImageModel;

    public ImageResponse generateImage(String prompt,Integer n, Integer width, Integer height) {

        ImageResponse response = openAiImageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .withModel("dall-e-2")
                                .withN(n)
                                .withHeight(height)
                                .withWidth(width)
                                .build()));

        return response;
    }
}
