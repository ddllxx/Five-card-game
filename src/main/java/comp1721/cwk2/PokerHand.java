package comp1721.cwk2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

// Implement PokerHand class here
public class PokerHand {
    LinkedList<String> PokerHand = new LinkedList<>();
    int two = 0;
    int three = 0;
    int four = 0;
    boolean flush = false;
    boolean st = false;

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
                Card c = new Card(PokerHand.get(i));
                m.append(c);
            } else {
                Card c = new Card(PokerHand.get(i));
                m.append(c);
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
        if (this.isfivecard()) {
            this.divideranksandsuits();
            return two == 1 && three == 0 && four == 0;
        } else {
            return false;
        }

    }

    public boolean isTwoPairs() {
        if (this.isfivecard()) {
            this.divideranksandsuits();
            return two == 2 && three == 0 && four == 0;
        } else {
            return false;
        }

    }

    public boolean isThreeOfAKind() {
        if (this.isfivecard()) {
            this.divideranksandsuits();
            return two == 0 && three == 1 && four == 0;
        } else {
            return false;
        }
    }

    public boolean isFourOfAKind() {
        if (this.isfivecard()) {
            this.divideranksandsuits();
            return two == 0 && three == 0 && four == 1;
        } else {
            return false;
        }

    }

    public boolean isFullHouse() {
        if (this.isfivecard()) {
            this.divideranksandsuits();
            return two == 1 && three == 1 && four == 0;
        } else {
            return false;
        }
    }

    public boolean isFlush() {
        if (this.isfivecard()) {
            this.divideranksandsuits();
            return flush;
        } else {
            return false;
        }
    }

    public boolean isStraight() {
        if (this.isfivecard()) {
            this.divideranksandsuits();
            return st;
        } else {
            return false;
        }
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

    public void divideranksandsuits() {
        StringBuilder all = new StringBuilder();
        StringBuilder rank = new StringBuilder();
        StringBuilder suit = new StringBuilder();
        StringBuilder straight = new StringBuilder();
        for (int i = 0; i < PokerHand.size(); i++) {
            all.append(PokerHand.get(i));
        }
        for (int m = 0; m < all.toString().length() / 2; m++) {
            rank.append(all.charAt(2 * m));
            suit.append(all.charAt(2 * m + 1));
        }
        for (int k = 0; k < rank.length(); k++) {
            if (k + 3 < rank.length() && (rank.charAt(k) == rank.charAt(k + 3))) {
                four++;
                break;
            } else if (k + 2 < rank.length() && (rank.charAt(k) == rank.charAt(k + 2))) {
                three++;
                k = k + 2;
            } else if (k + 1 < rank.length() && rank.charAt(k) == rank.charAt(k + 1)) {
                two++;
                k = k + 1;
            }
        }
        for (int k = 1, b = 0; k < suit.length(); k++) {
            if (suit.charAt(k) == suit.charAt(0)) {
                b++;
            }
            if (b == suit.length() - 1)
                flush = true;
        }
        int[] e = new int[5];
        int sum = 0;
        for (int i = 0; i < rank.length(); i++) {
            if (rank.charAt(i) == 'A') {
                e[i] = 1;
            } else if (rank.charAt(i) == 'T') {
                e[i] = 10;
            } else if (rank.charAt(i) == 'J') {
                e[i] = 11;
            } else if (rank.charAt(i) == 'Q') {
                e[i] = 12;
            } else if (rank.charAt(i) == 'K') {
                e[i] = 13;
            } else {
                e[i] = rank.charAt(i) - '0';
            }
        }

        for (int mm = 0; mm < e.length; mm++) {
            sum += e[mm];
        }
        Arrays.sort(e);
        if (e[0] != 0 && sum == e[0] * 5 + 10) {
            st = true;
        } else if (e[0] == 1 && sum == 47) {
            st = true;
        }
    }

    public boolean isfivecard() {
        return PokerHand.size() == 5;
    }
}

