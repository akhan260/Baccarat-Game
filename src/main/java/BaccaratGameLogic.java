//import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.Iterator;

public class BaccaratGameLogic extends BaccaratDealer
{

    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) //comparing the hands and seeing who won the round
    {
        String won = " ";
        int player_score = handTotal(hand1);    //finding the score the player has gotten
        int banker_score = handTotal(hand2);    //finding the score the banker has gotten

        if(player_score > banker_score)
        {
            won = "Player Won";                 //if player has higher score player wins....
        }
        else if (player_score < banker_score)
        {
            won = "Banker Won";                 //....banker has higher score then banker wins...
        }
        else if (player_score == banker_score)
        {
            won = "TIED";                       //....otherwise Game is Tied
        }
        return won;
    }
    public int handTotal(ArrayList<Card> hand)  //Find the total score of the hand
    {
        ArrayList<Card> temp = hand;
        int score = 0;
        int mod = 0;
        int i = 0;
        while( i < hand.size())         //until hand doesn't reach the size
        {
            score = score + hand.get(i).getValue(); //taking the score of each card
            mod = score % 10;   //then dividing it by 10 to use the last digit of integer
            i++;                //iterate
        }
        return mod;
    }
    public boolean first2Hand(int player, int banker) //determining if there is winner when Dealer has dealt first 2 hands
    {
        if(player == 7 && banker == 7) {        //If both got 7 they both must stay and therefore it will be a tie
            return true;
        }
        if(player == 8 || player == 9)          //Natural hand
        {
            return true;
        }
        if(banker == 8 || banker == 9 )     //Natural hand
        {
            return true;
        }
        if((banker == 8 && player == 8) || player == 9 && banker == 9) //Natural hand and also a tie
        {
            return true;
        }
        return false; //if none of the condition is true then the dealer will draw a card
    }
    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard)
    {
        if(handTotal(hand) < 3) //        Banker has total of 0, 1, 2: Banker always draws a third card.
        {
            return true;
        }
        if(handTotal(hand) == 3 && playerCard.getValue() != 8)
        {//        Banker has total of 3: Banker draws if Player's Third Card is 1-2-3-4-5-6-7-9-0 (not 8)

            return true;
        }
        if(handTotal(hand) == 4 && (playerCard.getValue() > 1 && playerCard.getValue() < 8))
        {//        Banker has total of 4: Banker draws if Player's Third Card is 2-3-4-5-6-7

            return true;
        }
        if(handTotal(hand) == 5 && (playerCard.getValue() > 3 && playerCard.getValue() < 8))
        {//        Banker has total of 5: Banker draws if Player's Third Card is 4-5-6-7

            return true;
        }
        if(handTotal(hand) == 6 && (playerCard.getValue() == 6 || playerCard.getValue() == 7))
        {//        Banker has total of 6: Banker draws if Player's Third Card is of 6-7

            return true;
        }
        if(handTotal(hand) == 7)
        {//        Banker has total of 7: Banker always stands.

            return false;
        }
        return false;
    }
    public boolean evaluatePlayerDraw(ArrayList<Card> hand)
    {
        if(handTotal(hand) < 6)
        {
            return true;
        }
//        If either the player or the banker has a total of an 8 or a 9 they both stand. There is no exceptions and this rule overrides all other rules.
//            If the player has total of 6 or 7, the player stands.
//        If the player stands, the banker hits on a total of 5 or less.
//        If the player has total of 5 or less, the player automatically hits and the banker gives the player a third card.
        return false;
    }
}
