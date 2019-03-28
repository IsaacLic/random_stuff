package deckofcards;

import java.util.ArrayList;
import java.util.List;

enum Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES
}

enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
}

public class Card implements Comparable<Card> {

    public static List<Card> Deck() {
        ArrayList deck = new ArrayList<Card>();
        for (Rank r : Rank.values()) {
            for (Suit s : Suit.values()) {
                deck.add(new Card(s, r));
            }
        }
        return deck;
    }

    private Suit suit;
    private Rank rank;

    public Card(Suit s, Rank r) {
        this.suit = s;
        this.rank = r;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    @Override
    public int compareTo(Card that) {
        if (this.rank != that.rank) {
            return this.rank.ordinal() - that.rank.ordinal();
        }
        return this.suit.ordinal() - that.suit.ordinal();
    }

    @Override
    public String toString() {
        return String.format("%s of %s", rank, suit);
    }
}
