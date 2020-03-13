package domain;

import domain.player.Player;

import java.util.Arrays;

public enum AnswerType {
    YES("y"),
    NO("n");

    private String answer;

    AnswerType(String answer) {
        this.answer = answer;
    }

    public static AnswerType findAnswerType(String answer) {
        return Arrays.stream(values())
                .filter(answerType -> answerType.answer.equals(answer))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isEqualsAnswer(AnswerType answerType){
        return equals(answerType);
    }

}
