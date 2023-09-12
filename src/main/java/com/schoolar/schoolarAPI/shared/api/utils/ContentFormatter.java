package com.schoolar.schoolarAPI.shared.api.utils;

import com.schoolar.schoolarAPI.shared.api.types.Answer;
import com.schoolar.schoolarAPI.shared.api.types.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContentFormatter {

    public List<Question> formatQuestions(String input) {
        List<Question> questions = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\d+\\. .*?)(\\d+\\..*?)(?=(?:\\d+\\.|$))", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String pergunta = matcher.group(1).trim();
            List<Answer> respostas = parseAnswers(matcher.group(2).trim());
            Question question = new Question(matcher.group(1).trim(), respostas);
            questions.add(question);
        }

        return questions;
    }

    private List<Answer> parseAnswers(String answerText) {
        List<Answer> answers = new ArrayList<>();
        String[] lines = answerText.split("\\n");
        for (String line : lines) {
            if (line.startsWith("Resposta:")) {
                boolean correta = line.contains("correta");
                String resposta = line.replace("Resposta:", "").trim();
                answers.add(new Answer(resposta, correta));
            }
        }

        return answers;
    }
}
