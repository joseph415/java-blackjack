package domain;

import domain.player.User;
import domain.player.Users;
import domain.player.Player;

import java.util.*;
import java.util.stream.Collectors;

public class WinningResult {
    private Map<String, Boolean> winningPlayerResult;

    public WinningResult(Users users) {
        winningPlayerResult = new HashMap<>();

        User dealer = users.getDealer();
        for (Player player : users.getPlayers()) {
            winningPlayerResult.put(player.getName(), DecisionWinner.compareWinner(player, dealer));
        }
    }

    public List<String> generateWinningUserResult() {
        int allUserWinCount = (int) winningPlayerResult.values().stream().filter(win -> win).count();
        int allUserLoseCount = winningPlayerResult.values().size() - allUserWinCount;

        List<String> result = new ArrayList<>(Collections.singletonList(String.format(
                "딜러: %d승 %d패",allUserLoseCount ,allUserWinCount)));
        result.addAll(winningPlayerResult.entrySet().stream()
                .map(entry -> winString(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));

        return Collections.unmodifiableList(result);
    }

    private String winString(String name, boolean isWin) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        if (isWin) {
            stringBuilder.append(": 승");
            return stringBuilder.toString();
        }
        stringBuilder.append(": 패");
        return stringBuilder.toString();
    }
}
