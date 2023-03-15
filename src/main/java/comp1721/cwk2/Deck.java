package comp1721.cwk2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
/**
 *The deck class represents a standard deck of playing cards
 */
public class Deck {
    /**
     *A linked list of deck
     */
    protected LinkedList<Card> deck = new LinkedList<>();
    /**
     *A default constructor that creates a deck.
     */
    public Deck() {
        ArrayList<String> suit = new ArrayList<>();
        suit.add("C");
        suit.add("D");
        suit.add("H");
        suit.add("S");
        String n;
        for (int s = 0; s <= 3; s++) {
            for (int i = 1; i < 14; i++) {
                if (i == 1) {
                    n = "A";
                } else if (i == 10) {
                    n = "T";
                } else if (i == 11) {
                    n = "J";
                } else if (i == 12) {
                    n = "Q";
                } else if (i == 13) {
                    n = "K";
                } else {
                    n = i + "";
                }
                deck.add(new Card(n + suit.get(s)));
            }
        }
    }
    /**
     *size() returns the number of cards in the deck
     * @return the number of cards in the deck
     */
    public int size() {
        return deck.size();
    }
    /**
     *isEmpty() returns true if the deck is empty of cards, false otherwise.
     *@return The result of if the deck is empty of cards
     */
    public boolean isEmpty() {
        return deck.isEmpty();
    }
    /**
     *contains() method returns true if the deck contains the specified card, false otherwise.
     *@return The result of if the deck contains the specified card
     * @param m the card to be determined
     */
    public boolean contains(Card m) throws CardException {
        boolean re = false;
        for (Card s : deck) {
            if (m.toString().equals(s.toString())) {
                re = true;
                break;
            }
        }
        return re;

    }
    /**
     *discard() empties the deck of all its cards.
     */
    public void discard() {
        if (deck.size() == 0) {
            throw new CardException("Wrong!");
        } else {
            deck.clear();
        }
    }
    /**
     *deal() removes the first card in the deck and returns it.
     * @return the first card in the deck
     */
    public Card deal() {
        if (deck.size() == 0) {
            throw new CardException("Wrong!");
        } else {
            return deck.pollFirst();
        }
    }
    /**
     *shuffle() rearranges cards in the deck randomly.
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }
    /**
     *add() returns the card to the deck
     * @param  card the card to be added
     */
    public void add(Card card) throws CardException {
        deck.add(card);
    }
}
