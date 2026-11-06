package com.spring_AI.Image_generator.service;

import okio.Path;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.google.genai.image.GoogleGenAiImageOptions;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class ImageService {

    private final ImageModel imageModel;
    private final Logger logger = LoggerFactory.getLogger(ImageService.class);


    public ImageService(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public String generateImage(String prompt) throws IOException {

        GoogleGenAiImageOptions imageOptions = GoogleGenAiImageOptions.builder()
                .n(1)
                .aspectRatio("16:9")
                .model("")
                // NOTE: outputMimeType / outputCompressionQuality / imageSize are
                // ONLY supported in Vertex AI (Enterprise) mode, NOT with an API key.
                .build();

        logger.info("image options {}", imageOptions);

        ImageResponse imageResponse = imageModel.call(new ImagePrompt(
                prompt,
                imageOptions

        ));

        logger.info("image response {}", imageResponse);
        logger.info("image response {}",imageResponse.getResult());

        convertAndWrite(imageResponse.getResult().getOutput().getB64Json());

        return "Image Generated!";

//        ImageResponse response = imageModel.call(imagePrompt);
//
//        byte[] imageBytes = response.getResult().getOutput().getBytes();
//        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public String uploadToCloud(String b64_json){
        return "url"; //upload image to cloud logic
    }

    void convertAndWrite(String b64Json)throws IOException {
        byte[] decode = Base64.getDecoder().decode(b64Json);
        Files.write(Paths.get("image.png"), decode);
        logger.info("file saved successfully");
    }
}
