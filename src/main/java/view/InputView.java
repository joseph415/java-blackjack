package view;

import dto.Answer;
import dto.PlayerName;
import dto.ResponsePlayerDTO;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static PlayerName inputPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        try {
            String playerName = SCANNER.nextLine();
            validatePlayerName(playerName);
            return new PlayerName(playerName);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPlayerName();
        }
    }

    private static void validatePlayerName(String playerName) {
        if (playerName == null || playerName.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public static Answer inputAnswer(ResponsePlayerDTO responsePlayerDTO) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y,아니오는 n)",responsePlayerDTO.getName());
        try {
            String answer = SCANNER.nextLine();
            validateAnswer(answer);
            return new Answer(answer);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return  inputAnswer(responsePlayerDTO);
        }
    }

    private static void validateAnswer(String answer) {
        if( !answer.equals("y") && !answer.equals("n")){
            throw new IllegalArgumentException("예는 y,아니오는 n 로 입력해주세요");
        }
    }

}

