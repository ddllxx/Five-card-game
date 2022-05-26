package comp1721.cwk2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

// Implement Deck class here
public class Deck {
    protected LinkedList<Card> deck = new LinkedList<>();

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

    public int size() {
        return deck.size();
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

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

    public void discard() {
        if (deck.size()==0) {
            throw new CardException("Wrong!");
        } else {
            deck.clear();
        }
    }

    public Card deal(){
        if (deck.size()==0) {
            throw new CardException("Wrong!");
        } else {
            return deck.pollFirst();
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void add(Card card) throws CardException {
        deck.add(card);
    }
}
