package domain.player;

import domain.card.Card;
import domain.card.CardCalculator;
import domain.money.Money;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class User {
    protected static final int MAX_WINNING_COUNT = 21;
    protected static final int BLACK_JACK = 21;
    protected static final int START_CARD_DECK_SIZE = 2;
    protected static final int BLACK_JACK_CARD_SIZE = 2;

    protected String name;
    protected final List<Card> cards;
    protected Money money;

    public User(List<Card> userCardDeck) {
        if (userCardDeck == null || userCardDeck.size() < START_CARD_DECK_SIZE) {
            throw new IllegalArgumentException("2장의 카드를 정상적으로 받지 않았습니다.");
        }
        this.cards = userCardDeck;
        validateDuplicateCard();
    }

    public String userResult() {
        return String.format("%s - 결과: %s", cardReport(), CardCalculator.sumCardDeck(this.cards));
    }

    public String cardReport() {
        return String.format("%s카드: %s",
                this.name, cards.stream().map(Card::toString).collect(Collectors.joining(",")));
    }

    public List<Card> getCard() {
        return Collections.unmodifiableList(this.cards);
    }

    public String getName() {
        return this.name;
    }

    public double getMoney() {
        return money.getMoney();
    }

    public boolean isBlackJack() {
        return this.cards.stream().anyMatch(Card::isAce)
                && this.cards.size() == BLACK_JACK_CARD_SIZE
                && CardCalculator.sumCardDeck(this.cards) == BLACK_JACK;
    }

    public void drawCard(Card card) {
        this.cards.add(card);
        validateDuplicateCard();
    }

    protected void validateDuplicateCard() {
        Set<Card> cards = new HashSet<>(this.cards);
        if (cards.size() != this.cards.size()) {
            throw new IllegalArgumentException("카드를 중복으로 받았습니다.");
        }
    }

    public abstract boolean isDrawCard();
}
