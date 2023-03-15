package comp1721.cwk2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
/**
 *PokerHand class represent a hand of cards in a game of five-card draw poker
 */
public class PokerHand {
    /**
     *A linked list of poker on the hand
     */
    protected LinkedList<Card> poker = new LinkedList<>();
    private boolean st = false;
    /**
     *A default constructor that creates an empty hand.
     */
    public PokerHand() {
    }
    /**
     *A constructor with a String parameter that specifies the cards that should be added to the hand
     * @param string a String that specifies the cards that should be added to the hand
     */
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
    /**
     *toString() returns a string in which cards are shown in two-character form, separated by spaces
     * @return a string in which cards are shown in two-character form, separated by spaces
     */
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
    /**
     *size() returns the number of cards in the hand
     * @return the number of cards in the hand
     */
    public int size() {
        return poker.size();
    }
    /**
     *discard() empties all cards in the hand.
     */
    public void discard() {
        if (poker.size() == 0) {
            throw new CardException("Wrong!");
        } else {
            poker.clear();
        }
    }
    /**
     *discardTo(Deck d) empties the hand of cards and returns each of them to the specified deck
     *@param d the deck to be discarded
     */
    public void discardTo(Deck d) {
        if (poker.size() == 0) {
            throw new CardException("Wrong!");
        } else {
            d.deck.addAll(poker);
            poker.clear();
        }
    }
    /**
     *The ranks method gets the rank of all hands and stores it in an array and returns
     * @return an array of the rank of all hands
     */
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
    /**
     *The suits method gets the suit of all hands and stores it in an array and returns
     * @return an array of the suit of all hands
     */
    public int[] suits() {
        int[] e = new int[5];
        for (int i = 0; i < poker.size(); i++) {
            e[i] = poker.get(i).toString().charAt(1) - '0';
        }
        return e;

    }
    /**
     *Determining if the hand poker is a poker with a pair
     * @return the result of if the hand poker is a poker with a pair
     */
    public boolean isPair() {
        if (this.isfivecard()) {
            int[] e = ranks();
            if (e[0] == e[1] && e[1] != e[2] && e[2] != e[3] && e[3] != e[4]) {
                return true;
            } else if (e[0] != e[1] && e[1] == e[2] && e[2] != e[3] && e[3] != e[4]) {
                return true;
            } else if (e[0] != e[1] && e[1] != e[2] && e[2] == e[3] && e[3] != e[4]) {
                return true;
            } else {
                return e[0] != e[1] && e[1] != e[2] && e[2] != e[3] && e[3] == e[4];
            }
        } else {
            return false;
        }

    }
     /**
     *Determining if the hand poker is a poker with TwoPairs
      *@return the result of if the hand poker is a poker with TwoPairs
     */
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
    /**
     *Determining if the hand poker is a poker with Three Of A Kind
     *@return the result of if the hand poker is a poker with Three Of A Kind
     */
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
    /**
     *Determining if the hand poker is a poker with Four Of A Kind
     *@return the result of if the hand poker is a poker with Four Of A Kind
     */
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
    /**
     *Determining if the hand poker is a poker with Full House
     *@return the result of if the hand poker is a poker with Full House
     */
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
    /**
     *Determining if the hand poker is a poker with Flush
     *@return the result of if the hand poker is a poker with Flush
     */
    public boolean isFlush() {
        if (this.isfivecard()) {
            int[] suit = suits();
            int b = 0;
            for (int k = 1; k < suit.length; k++) {
                if (suit[k] == suit[0]) {
                    b++;
                }
            }
            return b == suit.length - 1;

        } else {
            return false;
        }
    }
    /**
     *Determining if the hand poker is a poker with Straight
     * @return the result of if the hand poker is a poker with Straight
     */
    public boolean isStraight() {
        if (this.isfivecard()) {
            int sum = 0;
            int[] e = ranks();
            for (int i : e) {
                sum += i;
            }
            if (e[0] != 0 && sum == e[0] * 5 + 10 && e[0] == e[1] - 1 && e[1] == e[2] - 1
                    && e[2] == e[3] - 1 && e[3] == e[4] - 1) {
                st = true;
            } else if (e[0] == 1 && e[1] == 10 && e[2] == 11 && e[3] == 12 && e[4] == 13) {
                st = true;
            }
            return st;
        } else {
            return false;
        }
    }
    /**
     *add() returns the card to the pocker
     * @param  deal a card to be added
     */
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
    /**
     *isfivecard() determine if the hand has 5 cards
     * @return the result of if the hand has 5 cards
     */
    public boolean isfivecard() {
        return poker.size() == 5;
    }
}

