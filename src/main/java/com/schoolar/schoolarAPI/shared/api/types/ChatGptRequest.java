package com.schoolar.schoolarAPI.shared.api.types;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record ChatGptRequest(
        String model,
        String prompt,
        Double temperature,
        Integer maxTokens,
        Double topP,
        Double frequencyPenalty,
        Double presencePenalty
) {
}
