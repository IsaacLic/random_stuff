//Isaac Lichter
package deckofcards;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeckOfCardsTest {

    DeckOfCards deck;

    public DeckOfCardsTest() {
    }

    @Before
    public void setUp() {
        deck = new DeckOfCards();
    }

    @Test
    public void testSort1() {
        System.out.println("sort1");

        Card[] tempCardList = new Card[52];
        for (int i = 0; i < 52; i++) {
            tempCardList[i] = deck.getCard(i);
        }

        deck.shuffle();
        boolean isTheSame = true;

        for (int i = 0; i < 52; i++) {
            if (!(tempCardList[i].toString()).equals(deck.getCard(i).toString())) {
                isTheSame = false;
            }
        }
        assertFalse(isTheSame);

        deck.sort1();

        isTheSame = true;

        for (int i = 0; i < 52; i++) {
            if (!(tempCardList[i].toString()).equals(deck.getCard(i).toString())) {
                isTheSame = false;
            }
        }
        assertTrue(isTheSame);

    }

    @Test
    public void testSort2() {
        System.out.println("sort2");

        Card[] tempCardList = new Card[52];
        int tempCounter = 0;
        for (int i = 0; i < 13; i++) {
            for (int r = 0; r < 4; r++) {
                tempCardList[(i * 4) + r] = deck.getCard(tempCounter);
                tempCounter++;
            }
        }

        deck.shuffle();
        boolean isTheSame = true;

        for (int i = 0; i < 52; i++) {
            if (!(tempCardList[i].toString()).equals(deck.getCard(i).toString())) {
                isTheSame = false;
            }
        }
        assertFalse(isTheSame);

        deck.sort1();

        isTheSame = true;

        for (int i = 0; i < 52; i++) {
            if (!(tempCardList[i].toString()).equals(deck.getCard(i).toString())) {
                isTheSame = false;
            }
        }
        assertTrue(isTheSame);
    }

    @Test
    public void testReverseSort1() {
        System.out.println("reverseSort1");

        Card[] tempCardList = new Card[52];
        for (int i = 0; i < 52; i++) {
            tempCardList[51 - i] = deck.getCard(i);
        }

        deck.shuffle();
        boolean isTheSame = true;

        for (int i = 0; i < 52; i++) {
            if (!(tempCardList[51 - i].toString()).equals(deck.getCard(i).toString())) {
                isTheSame = false;
            }
        }
        assertFalse(isTheSame);

        deck.sort1();

        isTheSame = true;

        for (int i = 0; i < 52; i++) {
            if (!(tempCardList[51 - i].toString()).equals(deck.getCard(i).toString())) {
                isTheSame = false;
            }
        }
        assertTrue(isTheSame);
    }

    @Test
    public void testReverseSort2() {
        System.out.println("reverseSort2");
        Card[] tempCardList = new Card[52];
        int tempCounter = 51;
        for (int i = 0; i < 13; i++) {
            for (int r = 0; r < 4; r++) {
                tempCardList[(i * 4) + r] = deck.getCard(tempCounter);
                tempCounter--;
            }
        }

        deck.shuffle();
        boolean isTheSame = true;

        for (int i = 0; i < 52; i++) {
            if (!(tempCardList[51 - i].toString()).equals(deck.getCard(i).toString())) {
                isTheSame = false;
            }
        }
        assertFalse(isTheSame);

        deck.sort1();

        isTheSame = true;

        for (int i = 0; i < 52; i++) {
            if (!(tempCardList[51 - i].toString()).equals(deck.getCard(i).toString())) {
                isTheSame = false;
            }
        }
        assertTrue(isTheSame);
    }

    @Test
    public void testShuffle() {
        System.out.println("shuffle");

        Card[] tempCardList = new Card[52];
        for (int i = 0; i < 52; i++) {
            tempCardList[i] = deck.getCard(i);
        }

        int shuffleFailCounter = 0;

        for (int trial = 0; trial < 10000; trial++) {
            deck.shuffle();
            boolean isTheSame = true;
            for (int i = 0; i < 52; i++) {
                if (!(tempCardList[i].toString()).equals(deck.getCard(i).toString())) {
                    isTheSame = false;
                }
                tempCardList[i] = deck.getCard(i);
            }
            if (isTheSame)
                shuffleFailCounter++;
        }
        
        assertTrue(shuffleFailCounter < 3);
    }

}
