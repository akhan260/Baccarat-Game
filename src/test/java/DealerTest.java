import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DealerTest {

	BaccaratDealer test;
	BaccaratGameLogic gameLogic;
	BaccaratGame testBG;
	Card testCard, testCard1, testCard2, testCard3;
	@BeforeEach
	void init()
	{
	    //setting up test
		gameLogic = new BaccaratGameLogic();
		test = new BaccaratDealer();
		test.generateDeck();
        testBG = new BaccaratGame();

        testCard = new Card("Spade", 8);        //making new customized cards
		testCard1 = new Card("Heart", 2);
		testCard2 = new Card("Spade", 1);
		testCard3 = new Card("Heart", 0);
	}


	@Test
	void testValue()
	{
		assertEquals(1, test.deck.get(0).getValue()); //testing the top card value of a newly generated deck
	}
	@Test
	void testValue2()
	{
		assertEquals(0, test.deck.get(test.deckSize()-1).getValue()); //testing the value of bottom of the deck
	}

	@Test
	void testSuite()
	{
		test.drawOne();
		assertEquals("Heart", test.deck.get(0).getSuite()); //testing the suit of new generated deck
	}
	@Test
	void testSuite2()
	{
		assertEquals("Club", test.deck.get(test.deckSize()-1).getSuite()); //testing suite from bottom of deck
	}


	@Test
	void testDrawOne()
	{
		test.drawOne(); 		    //drawing one card from the deck
        assertEquals(2, test.deck.get(0).getValue()); // value of the second card from the deck
	}
	@Test
	void testDrawOne2()
	{
		for(int i = 0; i < 12; i ++)
		{
			test.drawOne(); //drawing first 12 cards 13th will be a face card and value will be zero
		}
		assertEquals(0, test.deck.get(0).getValue());
	}
	@Test
	void testDeckSize()
	{
		test.drawOne();
		assertEquals(51, test.deckSize()); //deck size after removing 1 card
	}
	@Test
	void testDeckSize2()
	{
		assertEquals(52, test.deckSize()); // deck size after being generated
	}
	@Test
	void testDealHand()
	{
		//Random 2 cards
		ArrayList<Card> hand = test.dealHand();
		assertNotEquals(1, test.deck.get(0).getValue(), "randomly matched");
		assertNotEquals(2, test.deck.get(1).getValue(), "randomly matched");

	}
	@Test
	void testDealHand2()
	{
		//Random 2 cards
		ArrayList<Card> hand = test.dealHand(); //dealing two cards to the hand
		assertEquals(2, hand.size(), "wrong size");

	}
	@Test
	void testGenerateDeck()
	{
		assertEquals(52, test.deckSize(), "Wrong amount"); //generating an empty deck
	}

	@Test
	void testGenerateDeck2()
	{
		test.emptyDeck();
		test.generateDeck(); //generating an empty deck
		assertEquals(52, test.deckSize(), "Wrong amount");      //checking the size of the deck
	}

	@Test
	void testEmptyDeck()
	{
		Card testCard = new Card("heart", 5);
		test.emptyDeck(); //empty deck
		test.deck.add(testCard); //adding one card into the deck
		test.emptyDeck(); //emptying it
		assertEquals(0, test.deckSize(), "Error"); //card size 0
	}
	@Test
	void testEmptyDeck2()
	{
		Card testCard = new Card("heart", 5);
		test.deck.add(testCard);         //adding a card into an empty deck
		test.generateDeck();            //generating a deck
		test.generateDeck();            //generating another deck
		test.emptyDeck();               //emptying it again

		assertEquals(0, test.deckSize(), "Error");
	}

	@Test
	void testShuffleDeck() //Shuffling deck
	{
		test.shuffleDeck();
		assertNotEquals(1, test.deck.get(0).getValue()); //Expected number is 1
	}

    @Test
    void testShuffleDeck2() //Shuffling deck
    {
        test.shuffleDeck();
        assertNotEquals(2, test.deck.get(1).getValue()); //expected number is 2
    }

	//Testing the constructors
	@Test
	void testInitBD()  //testing Baccarat Dealer constructor
	{
		assertEquals("BaccaratDealer", test.getClass().getName(), "did not initialize proper object");
	}

	@Test
	void testInitBGL() //Testing the Baccarat Game Logic constructor
	{
		assertEquals("BaccaratGameLogic", gameLogic.getClass().getName(), "did not initialize proper object");
	}

	@Test
	void testInitCard() //Testing the constructor of Card
	{
		assertEquals("Card", testCard.getClass().getName(), "did not initialize proper object");
	}

	@Test
	void testInitBG() //Testing the of Baccarat Game
	{
		assertEquals("BaccaratGame", testBG.getClass().getName(), "did not initialize proper object");
	}

	@Test
	void testInitwhoWon1()
	{
		ArrayList<Card> playerHand = new ArrayList<>(2); //creating two hands
		ArrayList<Card> bankerHand = new ArrayList<>(2);

		playerHand.add(testCard);   //playerHand add cards
		playerHand.add(testCard1);

		bankerHand.add(testCard2); //bankerHand add cards
		bankerHand.add(testCard1);
		//Banker has a higher value of cards
		assertEquals("Banker Won", gameLogic.whoWon(playerHand, bankerHand), "did not initialize proper object");
	}

	@Test
	void testInitwhoWon2()
	{
		ArrayList<Card> playerHand = new ArrayList<>(2); //two hands
		ArrayList<Card> bankerHand = new ArrayList<>(2); //one for banker and the other for player

		playerHand.add(testCard); //added cards for player
		playerHand.add(testCard1);
		playerHand.add(testCard2);


		bankerHand.add(testCard); //added cards for banker
		bankerHand.add(testCard1);
		bankerHand.add(testCard3);

		//both add up the same value
		assertEquals("TIED", gameLogic.whoWon(playerHand, bankerHand), "did not initialize proper object");
	}

	@Test
	void testInitHandTotal() //hand total
	{
		ArrayList<Card> playerHand = new ArrayList<>(3);//new hand

		playerHand.add(testCard); //add three cards to see the hand total
		playerHand.add(testCard1);
		playerHand.add(testCard2);

		assertEquals(1, gameLogic.handTotal(playerHand), "did not initialize proper object");
	}

	@Test
	void testInitHandTotal2()
	{
	    //test hand total
		ArrayList<Card> bankerHand = new ArrayList<>(3); //new hand
		bankerHand.add(testCard); //added card
		bankerHand.add(testCard1);
		//total will be 0
		assertEquals(0, gameLogic.handTotal(bankerHand), "did not initialize proper object");
	}

	@Test
	void testEvalBD()       //Created two hands
	{
		ArrayList<Card> bankerHand = new ArrayList<>(2);
		ArrayList<Card> playerHand = new ArrayList<>(2);
		Card testCard4 = new Card("diamond", 6);

		playerHand.add(testCard);       //added cards to each hands
		playerHand.add(testCard1);
		playerHand.add(testCard2);//1 value

		bankerHand.add(testCard3);
		bankerHand.add(testCard4); //6 total
        //will check if it needs to pick one card
		assertEquals(false, gameLogic.evaluateBankerDraw(bankerHand, testCard2), "did not initialize proper object");
	}
	@Test
	void testEvalBD2()
	{
		ArrayList<Card> bankerHand = new ArrayList<>(2); //created two hands
		ArrayList<Card> playerHand = new ArrayList<>(2);
		Card testCard4 = new Card("diamond", 6);

		playerHand.add(testCard); //added cards to hands
		playerHand.add(testCard2);
		playerHand.add(testCard4);//1 value

		bankerHand.add(testCard1);
		bankerHand.add(testCard3); //6 total
        //check to see if it will draw another card
		assertEquals(true, gameLogic.evaluateBankerDraw(bankerHand, testCard2), "did not initialize proper object");
	}
	@Test
	void testEvalPD()
	{
		ArrayList<Card> bankerHand = new ArrayList<>(2);  //created two hands
		ArrayList<Card> playerHand = new ArrayList<>(2);
		Card testCard4 = new Card("diamond", 6);

		playerHand.add(testCard);       //added cards
		playerHand.add(testCard2); //9 total Natural win

		bankerHand.add(testCard1);
		bankerHand.add(testCard3);

		assertEquals(false, gameLogic.evaluatePlayerDraw(playerHand), "did not initialize proper object");
	}

	@Test
	void testEvalPD2()
	{
		ArrayList<Card> bankerHand = new ArrayList<>(2);  //set up two hands added cards and check to see
		ArrayList<Card> playerHand = new ArrayList<>(2);   //if it will draw another card
		Card testCard4 = new Card("diamond", 6);

		playerHand.add(testCard);
		playerHand.add(testCard4); //9 total Natural win

		bankerHand.add(testCard3);
		bankerHand.add(testCard1);

		assertEquals(true, gameLogic.evaluatePlayerDraw(playerHand), "did not initialize proper object");
	}

	@Test
	void testEvalWin() //I tried testing it didn't work
	{
		ArrayList<Card> bankerHand = new ArrayList<>(2); //2 different hands
		ArrayList<Card> playerHand = new ArrayList<>(2);
		Card testCard4 = new Card("diamond", 6);
		int playerBid = 20;
		int bankerBid = 30;
		int tieBid = 10;
		playerHand = test.dealHand(); //dealing two cards
		bankerHand = test.dealHand();

		test.dealHand();
		assertEquals(40, testBG.evaluateWinning(), "did not initialize proper object");
	}






}
