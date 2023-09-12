package com.schoolar.schoolarAPI.shared.api.types;

import java.util.List;

public record Question(
        String pergunta,
        List<Answer> respostas
) {

    public void setPergunta(String trim) {
    }

    public void setRespostas(List<Answer> answers) {
    }
}
