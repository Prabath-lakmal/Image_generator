package com.spring_AI.Image_generator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		// Exclude the Google GenAI chat auto-configuration so the test context can start
		// without a real Gemini API key (it requires api-key or Vertex AI config).
		"spring.autoconfigure.exclude="
				+ "org.springframework.ai.model.google.genai.autoconfigure.chat.GoogleGenAiChatAutoConfiguration"
})
class ImageGeneratorApplicationTests {

	@Test
	void contextLoads() {
	}

}
