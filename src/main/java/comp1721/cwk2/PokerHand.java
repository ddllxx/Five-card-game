package comp1721.cwk2;

import java.util.LinkedList;
import java.util.Objects;

// Implement PokerHand class here
public class PokerHand {
    LinkedList<String> PokerHand = new LinkedList<>();

    public PokerHand() {
    }

    public PokerHand(String string) {
        if (string.length() > 14 || string.length() < 2) {
            throw new CardException("");

        } else {
            for (int n = 0; n < (string.length() + 1) / 3; n++) {
                PokerHand.add(String.valueOf(string.charAt(3 * n)) + string.charAt(3 * n + 1));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder m = new StringBuilder();
        for (int i = 0; i < PokerHand.size(); i++) {
            if (i == PokerHand.size() - 1) {
                m.append(PokerHand.get(i));
            } else {
                m.append(PokerHand.get(i));
                m.append(" ");
            }
        }
        return m.toString();
    }

    public int size() {
        return PokerHand.size();
    }

    public void discard() {
        if (PokerHand.size() == 0) {
            throw new CardException("Wrong!");
        } else {
            PokerHand.clear();
        }
    }

    public void discardTo(Deck d) {
        if (PokerHand.size() == 0) {
            throw new CardException("Wrong!");
        } else {
            d.deck.addAll(PokerHand);
            PokerHand.clear();
        }
    }

    public boolean isPair() {

        return false;
    }

    public boolean isTwoPairs() {
        return false;
    }

    public boolean isThreeOfAKind() {
        return false;
    }

    public boolean isFourOfAKind() {
        return false;
    }

    public boolean isFullHouse() {
        return false;
    }

    public boolean isFlush() {
        return false;
    }

    public boolean isStraight() {
        return false;
    }

    public void add(Card deal) {
        if (PokerHand.size() == 0) {
            PokerHand.add(deal.toString());

        } else if (PokerHand.size() == 5) {
            throw new CardException("wrong!");
        } else {
            for (String s : PokerHand) {
                if (Objects.equals(deal.toString(), s)) {
                    throw new CardException("wrong!");
                }
            }
            PokerHand.add(deal.toString());
        }
    }
}