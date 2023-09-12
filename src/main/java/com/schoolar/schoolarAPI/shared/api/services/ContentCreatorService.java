package com.schoolar.schoolarAPI.shared.api.services;

import com.schoolar.schoolarAPI.shared.api.types.ChatGptRequest;
import com.schoolar.schoolarAPI.shared.api.types.ChatGptResponse;
import com.schoolar.schoolarAPI.shared.api.utils.ContentFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ContentCreatorService {
    private final WebClient webClient;
    private final ContentFormatter formatter;

    @Autowired
    public ContentCreatorService(WebClient.Builder builder, @Value("${openai.api.key}") String apiKey, ContentFormatter formatter) {
        this.formatter = formatter;
        this.webClient = builder
                .baseUrl("https://api.openai.com/v1/completions")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

     public Mono<String> generateContent(Integer questionsNumber, String theme, String subject) {
      ChatGptRequest request = createContentRequest(questionsNumber, theme, subject);
      Mono<ChatGptResponse> response = webClient.post().bodyValue(request).retrieve().bodyToMono(ChatGptResponse.class);


      return response.map(res -> res.choices().get(0).text());

     }

     private ChatGptRequest createContentRequest(Integer questionsNumber, String theme, String subject) {
       String question = "A partir de agora você deve agir como um professor de " + subject + ". Você deve ser capaz de criar questões de alternativa sobre qualquer assunto relacionado a " + subject + "Crie " + questionsNumber + "questões de alternativa referentes ao tema: " + theme + ", com cada questão contendo 5 alternativas." + "Você deve informar qual é a alternativa correta. Preciso que você me retorne as perguntas com elas sendo um array de objetos, seguindo esse modelo:\n" +
               "\n" +
               "[\n" +
               "\t{\n" +
               "\t\t\"question\": \"Pergunta 1\",\n" +
               "\t\t\"answers\": [\n" +
               "\t\t\t{\n" +
               "\t\t\t\t\"answer\": \"Alternativa A\",\n" +
               "\t\t\t\t\"correct\": false\n" +
               "\t\t\t},\n" +
               "\t\t\t{\n" +
               "\t\t\t\t\"answer\": \"Alternativa B\",\n" +
               "\t\t\t\t\"correct\": false\n" +
               "\t\t\t},\n" +
               "\t\t\t{\n" +
               "\t\t\t\t\"answer\": \"Alternativa C\",\n" +
               "\t\t\t\t\"correct\": true\n" +
               "\t\t\t},\n" +
               "\t\t\t{\n" +
               "\t\t\t\t\"answer\": \"Alternativa D\",\n" +
               "\t\t\t\t\"correct\": false\n" +
               "\t\t\t},\n" +
               "\t\t\t{\n" +
               "\t\t\t\t\"answer\": \"Alternativa E\",\n" +
               "\t\t\t\t\"correct\": false\n" +
               "\t\t\t}\n" +
               "\t\t]\n" +
               "\t}\n" +
               "]\n" +
               "\n" +
               "Cada pergunta deve possuir um objeto dentro do array, onde a proprieda \"pergunta\" refere-se a pergunta que esta sendo feita e a propriedade respostas sendo as alternativas da pergunta, cada alternativa deve estar em um objeto, contendo o campo \"resposta\", qeu deve ser o texto da alternativa e o campo correta que deve ser \"true\" somenta no objeto referente a alternativa correta.";

       return new ChatGptRequest("text-davinci-003", question, 0.3, 3000, 1.0, 0.0, 0.0);
     }
}
