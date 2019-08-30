//import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.layout.StackPane;
import java.util.Random;

class BaccaratDealer{
    ArrayList<Card> deck;
    Card new_card = new Card("hearts", 1);
    String ace, king, queen, jack, ten;
    private int card;

    //Constructor
    BaccaratDealer()
    {
        deck = new ArrayList<>(); //initializing the deck
    }

    //private ArrayList<Card> deck;
    public void generateDeck()
    {
        String suite;
//        deck = new ArrayList<Card>();
        int k = 0;
        //Using nested for-loop where to make 4 layers of the deck of card
        for (int j = 1; j <= 4; j++)
        {
            for (int i = 1; i <= 13; i++)
            {
                if (j == 1) {
                    suite = "Heart";   //heart
                } else if (j == 2) {
                    suite = "Spade";    //spade
                } else if (j == 3) {
                    suite = "Diamond";  //diamond
                } else //if (j == 4)
                {
                    suite = "Club";     //club
                }
                deck.add(new Card(suite, i));  //these are inputting the suite and number Not the value
            }
        }

    }
    public ArrayList<Card> dealHand()
    {
        ArrayList<Card> hand = new ArrayList<>(2); //dealing hands for player and banker
        for(int i = 0; i < 2; i++)
        {
            hand.add(drawOne());  //the array is adding to the random cards to the array of player or banker
        }
//        shuffleDeck();
        return hand;
    }
    public Card drawOne()
    {
        return deck.remove(0); //removing the top card from the deck and returning that card
    }
    public void shuffleDeck()
    {
        Collections.shuffle(deck); //randomly shuffling

    }
    public void emptyDeck()
    {
        deck.clear();
//       for(int i = 0; i < 52; i++) //this for-loop will empty out the deck and is
//       {
//           System.out.println(deckSize() + " " + i);
//           deck.remove(i);
//       }
    }

    public int deckSize()
    {
        return deck.size(); //deck size
    }

}
