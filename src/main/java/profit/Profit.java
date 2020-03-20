package profit;

import domain.DecisionWinner;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.User;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

public enum Profit {
    BLACK_JACK_DRAW(
            value -> value * 0d,
            (dealer,user) -> (dealer.isBlackJack() && user.isBlackJack())
    ),
    BLACK_JACK(
            value -> value * 1.5d,
            (dealer,user) -> (user.isBlackJack())
    ),
    WIN(
            value -> value * 1d,
            (dealer,user) -> (DecisionWinner.compareWinner(user,dealer))
    ),
    LOOSE(
            value -> value * -1d,
            (dealer,user) -> (!DecisionWinner.compareWinner(user,dealer))
    );

    private Function<Double,Double> profit;
    private BiPredicate<User,User> predicate;

    Profit(Function<Double, Double> profit, BiPredicate<User,User> predicate) {
        this.profit = profit;
        this.predicate = predicate;
    }

    public static Profit valueOf(Dealer dealer, Player player){
        return Arrays.stream(values())
                .filter(profit -> profit.predicate.test(dealer,player))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public double getProfit(Player player){
        return this.profit.apply(player.getMoney());
    }

}
