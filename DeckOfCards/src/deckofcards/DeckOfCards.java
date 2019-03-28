//Isaac Lichter
package deckofcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class DeckOfCards {
    
    public static void main(String[] args) {
        System.out.println("What would you like to do with your deck of cards?");
        System.out.println("Here are your options: ");
        System.out.println("(1) sort1");
        System.out.println("(2) sort2");
        System.out.println("(3) reverseSort1");
        System.out.println("(4) reverseSort2");
        System.out.println("(5) shuffle");
        System.out.println("Type 1, 2, 3, 4, or 5");
        
        Scanner kb = new Scanner(System.in);
        
        DeckOfCards deck = new DeckOfCards();
        
        char c = kb.nextLine().charAt(0);
        switch (c) {
            case '1': deck.sort1();
            case '2': deck.sort2();
            case '3': deck.reverseSort1();
            case '4': deck.reverseSort2();
            case '5': deck.shuffle();
            default: System.out.println("That wasn't a choice. Goodbye!");
        }
    }

    private ArrayList<Card> deck = new ArrayList<Card>();

    public DeckOfCards() {
        for (Rank r : Rank.values()) {
            for (Suit s : Suit.values()) {
                deck.add(new Card(s, r));
            }
        }
    }

    private static class UnnaturalOrderComparator implements Comparator<Card> {

        public UnnaturalOrderComparator() {
        }

        @Override
        public int compare(Card card1, Card card2) {
            if (card1.getSuit() != card2.getSuit()) {
                return card1.getSuit().ordinal() - card2.getSuit().ordinal();
            }
            return card1.getRank().ordinal() - card2.getRank().ordinal();
        }

    }
    
    public Card getCard(int index){
        return deck.get(index);
    }

    public void sort1() {
        Collections.sort(deck);
    }

    public void sort2() {
        UnnaturalOrderComparator c = new UnnaturalOrderComparator();
        Collections.sort(deck, c);
    }

    public void reverseSort1() {
        Collections.sort(deck);
        Collections.reverse(deck);
    }

    public void reverseSort2() {
        UnnaturalOrderComparator c = new UnnaturalOrderComparator();
        Collections.sort(deck, c);
        Collections.reverse(deck);
    }

    public void shuffle() {
        //I was trying to think of a more efficient way of sorting, but I couldn't think of a way that didn't violate the randomness
        
        Card[] tempCardList = new Card[52];
        for(int i = 0; i < 52; i++){
            int index = (int) (Math.random() * (52));
            while (tempCardList[index] != null)
                 index = (int) (Math.random() * (52));
            tempCardList[index] = deck.get(i);
        }
        for (int i = 0; i < 52; i++){
            deck.set(i, tempCardList[i]);
        }
    }

}
