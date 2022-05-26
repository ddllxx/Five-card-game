package comp1721.cwk2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

// Implement PokerHand class here
public class PokerHand {
    protected LinkedList<Card> poker = new LinkedList<>();
    private int two = 0;
    private int three = 0;
    private int four = 0;
    private boolean flush = false;
    private boolean st = false;

    public PokerHand() {
    }

    public PokerHand(String string) {
        if (string.length() > 14 || string.length() < 2) {
            throw new CardException("");

        } else {
            for (int n = 0; n < (string.length() + 1) / 3; n++) {
                String a = String.valueOf(string.charAt(3 * n)) + string.charAt(3 * n + 1);
                poker.add(new Card(a));
            }
        }
    }

    @Override
    public String toString() {
        String a = "";
        if (poker.size() == 0) {
            return a;
        }
        a = poker.get(0).toString();
        for (int i = 1; i < poker.size(); i++) {
            a = a.concat(" " + poker.get(i).toString());
        }
        return a;
    }

    public int size() {
        return poker.size();
    }

    public void discard() {
        if (poker.size() == 0) {
            throw new CardException("Wrong!");
        } else {
            poker.clear();
        }
    }

    public void discardTo(Deck d) {
        if (poker.size() == 0) {
            throw new CardException("Wrong!");
        } else {
            d.deck.addAll(poker);
            poker.clear();
        }
    }

    public int[] ranks() {
        int[] e = new int[5];
        for (int i = 0; i < poker.size(); i++) {
            if (poker.get(i).toString().charAt(0) == 'A') {
                e[i] = 1;
            } else if (poker.get(i).toString().charAt(0) == 'T') {
                e[i] = 10;
            } else if (poker.get(i).toString().charAt(0) == 'J') {
                e[i] = 11;
            } else if (poker.get(i).toString().charAt(0) == 'Q') {
                e[i] = 12;
            } else if (poker.get(i).toString().charAt(0) == 'K') {
                e[i] = 13;
            } else {
                e[i] = poker.get(i).toString().charAt(0) - '0';
            }
        }
        Arrays.sort(e);
        return e;
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
            int[] e = ranks();
            if (e[0] == e[1] && e[2] == e[3] && e[3] != e[4] && e[1] != e[2]) {
                return true;
            } else if (e[0] != e[1] && e[1] == e[2] && e[2] != e[3] && e[3] == e[4]) {
                return true;
            } else {
                return e[0] == e[1] && e[1] != e[2] && e[2] != e[3] && e[3] == e[4];
            }
        } else {
            return false;
        }

    }

    public boolean isThreeOfAKind() {
        if (this.isfivecard()) {
            int[] e = ranks();
            if (e[0] == e[2] && e[0] != e[3] && e[3] != e[4]) {
                return true;
            } else if (e[0] != e[1] && e[1] == e[3] && e[3] != e[4]) {
                return true;
            } else {
                return e[0] != e[1] && e[1] != e[2] && e[2] == e[4];
            }
        } else {
            return false;
        }
    }

    public boolean isFourOfAKind() {

        if (this.isfivecard()) {
            int[] e = ranks();
            if (e[0] == e[3] && e[3] != e[4]) {
                return true;
            } else {
                return e[0] != e[1] && e[1] == e[4];
            }
        } else {
            return false;
        }

    }

    public boolean isFullHouse() {
        if (this.isfivecard()) {
            int[] e = ranks();
            if (e[0] == e[2] && e[2] != e[3] && e[3] == e[4]) {
                return true;
            } else {
                return e[0] == e[1] && e[1] != e[4] && e[2] == e[4];
            }
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
        if (poker.size() == 0) {
            poker.add(deal);

        } else if (poker.size() == 5) {
            throw new CardException("wrong!");
        } else {
            for (Card s : poker) {
                if (Objects.equals(deal.toString(), s.toString())) {
                    throw new CardException("wrong!");
                }
            }
            poker.add(deal);
        }
    }

    public void divideranksandsuits() {
        StringBuilder all = new StringBuilder();
        StringBuilder rank = new StringBuilder();
        StringBuilder suit = new StringBuilder();
        for (Card s : poker) {
            all.append(s.toString());
        }
        for (int m = 0; m < all.toString().length() / 2; m++) {
            rank.append(all.charAt(2 * m));
            suit.append(all.charAt(2 * m + 1));
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
        for (int i : e) {
            sum += i;
        }
        Arrays.sort(e);

        for (int k = 0; k < e.length; k++) {
            if (k + 3 < e.length && (e[k] == e[k + 3])) {
                four++;
                break;
            } else if (k + 2 < e.length && (e[k] == e[k + 2])) {
                three++;
                k = k + 2;
            } else if (k + 1 < e.length && (e[k] == e[k + 1])) {
                two++;
                k = k + 1;
            }
        }
        if (e[0] != 0 && sum == e[0] * 5 + 10 && two + three + four == 0) {
            st = true;
        } else if (e[0] == 1 && e[1] == 10 && e[2] == 11 && e[3] == 12 && e[4] == 13) {
            st = true;
        }
        for (int k = 1, b = 0; k < suit.length(); k++) {
            if (suit.charAt(k) == suit.charAt(0)) {
                b++;
            }
            if (b == suit.length() - 1) {
                flush = true;
            }
        }

    }

    public boolean isfivecard() {
        return poker.size() == 5;
    }
}

