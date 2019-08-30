
import java.applet.AudioClip;
import java.awt.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;

import javax.swing.text.html.Option;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class BaccaratGame extends Application
{
	BorderPane border_Pane;
	Label WinningTotal, BiddingTotal, whoWon_label,  betting;
	Label Player_score, Banker_score, label_bidder;
	Scene scene;
	VBox v_Box, v_Box1, v_Box_middle, v_BoxA, v_BoxB, winner;
	HBox h_Box;
	Button btn1, btn2, btn3;
	Button player, banker, tie;
	Button draw, rebate, dealHand;
	TextField textFieldTotal, textFieldBid;
	Card temp;
	Card temp2;

	int timer = 0;
	int biddingTotal;
	double winningTotal = 1000;
	int playerBid = 0, bankerBid = 0, tieBid = 0, allBid = 0;
	private ArrayList<Card> playerHand;
	private ArrayList<Card> bankerHand;
	private BaccaratGameLogic gameLogic;

	private int playerTotal = 0;
	private int bankerTotal = 0;


	// Main Method
	public static void main(String args[])
	{
		// launch the application
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception
	{
		// TODO Auto-generated method stub
		BaccaratDealer deck = new BaccaratDealer();

		deck.generateDeck(); //generate a new deck of card
		deck.shuffleDeck(); //shuffling a new deck of card
		gameLogic = new BaccaratGameLogic();



		textFieldTotal = new TextField("1000"); 		//This will show the total amount of money in your bank
		textFieldBid = new TextField("");				//No bid in the game yet
		textFieldTotal.setPrefWidth(75);
		textFieldBid.setPrefWidth(75);

		border_Pane = new BorderPane();
		Menu Options = new Menu("Options");			//Options
		MenuItem exit = new MenuItem("Exit");			//Exit the game
		MenuItem restart = new MenuItem("restart");	//Restart the game

		Options.getItems().add(exit);			//Adding menuItem inside the Menu
		Options.getItems().add(restart);
		MenuBar menu = new MenuBar(Options);	//setting MenuBar
		border_Pane.setTop(menu);				//adding the menu on top of the application

		primaryStage.setTitle("Baccarat Game"); //Title of the application


		//These are labels I use on my application they are pretty self-explanatory
		WinningTotal = new Label("Winning Total");
		WinningTotal.setTextFill(Color.web("#980"));
		WinningTotal.setStyle("-fx-font: 18 arial;");

		BiddingTotal = new Label("Bidding Total");
		BiddingTotal.setTextFill(Color.web("#980"));
		BiddingTotal.setStyle("-fx-font: 18 arial;");

		betting = new Label("Bet Amount");
		betting.setTextFill(Color.web("#980"));
		betting.setStyle("-fx-font: 18 arial;");

		label_bidder = new Label("Bidder");
		label_bidder.setTextFill(Color.web("#980"));
		label_bidder.setStyle("-fx-font: 18 arial;");

		Player_score = new Label("Player Score: " + Integer.toString(playerTotal)); //I change the int to string..
		Player_score.setTextFill(Color.web("#980"));
		Player_score.setStyle("-fx-font: 18 arial;");

		Banker_score = new Label("Banker Score: " + Integer.toString(bankerTotal)); //to print it out
		Banker_score.setTextFill(Color.web("#980"));
		Banker_score.setStyle("-fx-font: 18 arial;");


		whoWon_label = new Label("");
		whoWon_label.setTextFill(Color.web("#980"));
		whoWon_label.setStyle("-fx-font: 18 arial;");




		h_Box = new HBox(50); //setting an HBox

		//This part of vBox contains all the options user has to bid on the game
		v_Box = new VBox(10);
		v_Box.setAlignment(Pos.CENTER);

		//This part of vBox is where the Player will get the cards and print the Player's score
		v_BoxA = new VBox();
		v_BoxA.setAlignment(Pos.CENTER);

		//This part of vBox is where it will print out winner name Bidding Amount and Winning amount
		//Deal Draw and Rebate buttons
		v_Box_middle = new VBox(20);
		v_Box_middle.setAlignment(Pos.BOTTOM_CENTER);
		winner = new VBox(400);

		//This part of vBox is where the Banker will get the cards and print the Banker's score
		v_BoxB = new VBox();
		v_BoxB.setAlignment(Pos.CENTER);

		//This part of vBox contains all the options user has to choose the bidder
		v_Box1 = new VBox(10);
		v_Box1.setAlignment(Pos.CENTER);


											//Bidding amount options
		btn1 = new Button("$5");
		btn2 = new Button("$25");
		btn3 = new Button("$100");

											//predicting who will win
		player = new Button("PLAYER");
		banker = new Button("BANKER");
		tie = new Button ("TIE");

		//the dealHand button so the dealer can deal hand and then draw the hand and after the round the use can rebate
		draw = new Button("DRAW");
		dealHand = new Button("Deal Hand");
		rebate = new Button("REBATE");

												//adding the Menu
		v_Box.getChildren().add(border_Pane);
		v_Box.setAlignment(Pos.TOP_LEFT);

										//adding the betting buttons
		VBox bet = new VBox(10);
		bet.getChildren().add(betting);
		bet.getChildren().add(btn1);
		bet.getChildren().add(btn2);
		bet.getChildren().add(btn3);
		bet.setAlignment(Pos.CENTER);
		VBox change = new VBox(300, v_Box, bet);

		//Player Score add
		v_BoxA.getChildren().add(Player_score);


		//These are Hbox I am making it to fit them in pane properly
		HBox center = new HBox(15);
		HBox top = new HBox(20);
		HBox top2 = new HBox(25);

		//setting one hbox to make the textfield which will show the winning amount and bidding amount
		top.setAlignment(Pos.CENTER);
		top.getChildren().add(textFieldTotal);
		top.getChildren().add(textFieldBid);

		//setting one hbox to make the label which will show the text of winning amount and bidding amount
		top2.setAlignment(Pos.CENTER);
		top2.getChildren().add(WinningTotal);
		top2.getChildren().add(BiddingTotal);

		//these are the buttons I am adding which will make the user deal hand, draw or rebate
		center.setAlignment(Pos.CENTER);
		center.getChildren().addAll(dealHand, draw, rebate);
		winner.getChildren().add(whoWon_label);
		winner.setAlignment(Pos.CENTER);

		//adding the winner winning/bidding amount and options of dealing cards
		v_Box_middle.getChildren().add(winner);
		v_Box_middle.getChildren().add(top2);
		v_Box_middle.getChildren().add(top);
		v_Box_middle.getChildren().add(center);

										//Banker Score Label
		v_BoxB.getChildren().add(Banker_score);

		//adding the bidders and this where the user will  choose whom to bid on
		v_Box1.getChildren().add(label_bidder);
		v_Box1.getChildren().add(player);
		v_Box1.getChildren().add(banker);
		v_Box1.getChildren().add(tie);

		dealHand.setDisable(true);
		player.setDisable(true);
		banker.setDisable(true);
		tie.setDisable(true);
		//Here I set the button on setOnMouse Pressed button and ....
		btn1.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				player.setDisable(false);
				banker.setDisable(false);
				tie.setDisable(false);
				int counter = 0;
				counter++;			//..it will count how many times user pressed the button
				biddingTotal = biddingTotal + counter*5; //set the bidding
				textFieldBid.setText(Integer.toString(biddingTotal)); //print the bidding total
			}
		});
		btn2.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				player.setDisable(false);
				banker.setDisable(false);
				tie.setDisable(false);
				int counter = 0;
				counter++; //..it will count how many times user pressed the button
				biddingTotal = biddingTotal + counter*25; //set the bidding
				textFieldBid.setText(Integer.toString(biddingTotal)); //print the bidding total
			}
		});
		btn3.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				player.setDisable(false);
				banker.setDisable(false);
				tie.setDisable(false);
				int counter = 0;
				counter++;			//..it will count how many times user pressed the button
				biddingTotal = biddingTotal + counter*100; //set the bidding
				textFieldBid.setText(Integer.toString(biddingTotal)); //print the bidding total
				System.out.println(biddingTotal);

			}
		});

		//This will be setOnAction to choose who the player is
		player.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				dealHand.setDisable(false);
				playerBid = biddingTotal;		//this is used to find out the bidding total
				allBid = playerBid + tieBid + bankerBid; // all bid
				winningTotal = winningTotal - playerBid;//subtract from all the total
				textFieldTotal.setText(Double.toString(winningTotal));
				biddingTotal = 0;
				textFieldBid.setText("");
			}
		});

		banker.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				dealHand.setDisable(false);
				bankerBid = biddingTotal; //this is used to find out the bidding total
				allBid = playerBid + tieBid + bankerBid; // all bid
				winningTotal = winningTotal - bankerBid; //subtract from all the total
				textFieldTotal.setText(Double.toString(winningTotal));
				biddingTotal = 0;
				textFieldBid.setText("");
			}
		});
		tie.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				dealHand.setDisable(false);
				tieBid = biddingTotal; //this is used to find out the bidding total
				allBid = playerBid + tieBid + bankerBid; // all bid
				winningTotal = winningTotal - tieBid; //subtract from all the total
				textFieldTotal.setText(Double.toString(winningTotal));
				biddingTotal = 0;
				textFieldBid.setText("");
			}
		});

		rebate.setDisable(true);
		draw.setDisable(true);
		dealHand.setOnAction(e->{
			dealHand.setDisable(true);
			if(deck.deckSize() < 6)//checking if deck is almost empty
			{
				deck.emptyDeck();
				deck.generateDeck();
			}
			draw.setDisable(true);
			rebate.setDisable(true);
			playerHand = deck.dealHand();//dealing hand
			bankerHand = deck.dealHand();
			playerTotal = gameLogic.handTotal(playerHand);//findinding handtotal
			bankerTotal = gameLogic.handTotal(bankerHand);
			int temp1 = playerHand.get(0).getValue();
			int temp2 = bankerHand.get(0).getValue();


			String temp_1 = Integer.toString(temp1);
			String temp_2 = Integer.toString(temp2);

			String temp_3 = Integer.toString(playerTotal);
			String temp_4 = Integer.toString(bankerTotal);
			timer = 0;
			PauseTransition pause = new PauseTransition(Duration.seconds(1)); //setting a pause transition in a loop
			pause.setOnFinished(event->{
				timer++;
				if(timer == 2)
				{
					v_BoxA.getChildren().add(playerHand.get(0).drawImage()); //first card for player
					Player_score.setText("Player Score: " +temp_1);
				}
				if(timer == 3)
				{
					v_BoxB.getChildren().add(bankerHand.get(0).drawImage()); //first card for banker
					Banker_score.setText("Banker Score: " +temp_2);

				}
				if(timer == 4)
				{
					v_BoxA.getChildren().add(playerHand.get(1).drawImage()); //second card for player
					Player_score.setText("Player Score: " +temp_3);

				}
				if(timer == 5)
				{
					v_BoxB.getChildren().add(bankerHand.get(1).drawImage()); //second card for banker
					Banker_score.setText("Banker Score: " +temp_4);
				}
				if(timer > 5)
				{
					if(gameLogic.first2Hand(playerTotal, bankerTotal)) //if no cards are drawn then deciding who wins
					{
						String whoWon = gameLogic.whoWon(playerHand, bankerHand);
						whoWon_label.setText(whoWon);
						winningTotal = evaluateWinning();
						textFieldTotal.setText(Double.toString(winningTotal));
						draw.setDisable(true);
						rebate.setDisable(false);
						return;
					}
					dealHand.setDisable(true);
					draw.setDisable(false);
					return;
				}
				pause.play();
			});
			pause.play();

		});

		draw.setOnAction(e->{
			timer = 0;
			PauseTransition pause = new PauseTransition(Duration.seconds(1)); //Pause Transition loop

			pause.setOnFinished(event->
			{
				timer++;  //
				if (gameLogic.evaluatePlayerDraw(playerHand) && timer==2) //evaluate if player draws
				{
					temp = deck.drawOne();
					playerHand.add(temp);
					playerTotal = gameLogic.handTotal(playerHand);
					v_BoxA.getChildren().add(playerHand.get(2).drawImage());     //player draws
					Player_score.setText("Player Score: " + Integer.toString(playerTotal)); //score updates
				}
				if (gameLogic.evaluateBankerDraw(bankerHand, playerHand.get(playerHand.size() - 1)) && timer==3)
				{		//evaluate if banker draws
					temp2 = deck.drawOne();
					bankerHand.add(temp2);
					bankerTotal = gameLogic.handTotal(bankerHand); //score updates
					v_BoxB.getChildren().add(bankerHand.get(2).drawImage());
					Banker_score.setText("Banker Score: " + Integer.toString(bankerTotal));
				}
				if(timer == 4)
				{

					String whoWon = gameLogic.whoWon(playerHand, bankerHand); //determining who wins
					whoWon_label.setText(whoWon);
					winningTotal = evaluateWinning();
					textFieldTotal.setText(Double.toString(winningTotal));
					return;			//return
				}
				pause.play();
			});
			pause.play();


			dealHand.setDisable(true);
			draw.setDisable(true);
			rebate.setDisable(false);

		});

		rebate.setOnAction(event ->					///Starting a new round
		{
			dealHand.setDisable(true);
			player.setDisable(true);
			banker.setDisable(true);
			tie.setDisable(true);						//Resetting everything
			v_BoxA.getChildren().size();
			v_BoxA.getChildren().remove(0, v_BoxA.getChildren().size());
			v_BoxB.getChildren().remove(0, v_BoxB.getChildren().size());
			Player_score.setText("Player Score: " + Integer.toString(playerTotal = 0));
			Banker_score.setText("Banker Score: " + Integer.toString(bankerTotal = 0));
			whoWon_label.setText("");

			v_BoxA.getChildren().add(Player_score);
			v_BoxB.getChildren().add(Banker_score);

		});

		//closing the application
		exit.setOnAction(e -> {
			primaryStage.close();

		});
		restart.setOnAction(e-> {
			try {//resetting everything
				playerTotal = 0;
				bankerTotal = 0;
				winningTotal = 1000;			//basically restart but with information still stored
				Player_score.setText("Player Score: " + Integer.toString(playerTotal));
				Banker_score.setText("Banker Score: " + Integer.toString(bankerTotal));

				restart(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});


		//setting all vbox into Hbox
		h_Box.getChildren().addAll(change, v_BoxA, v_Box_middle,v_BoxB, v_Box1);
		scene = new Scene(h_Box, 870, 800);

		//Creating Background Image
		BackgroundImage myBI= new BackgroundImage(new Image("bc2.jpg",800,800,false,true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
//then you set to your node
		h_Box.setBackground(new Background(myBI));

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	//This method will determine if the user won or lost their bet and return the amount won or
	//lost based on the value in currentBet
	public double evaluateWinning()
	{
		//Evaluating the total
		if(gameLogic.whoWon(playerHand, bankerHand) == "Player Won")
		{
			winningTotal = winningTotal + playerBid*2; //total if won
		}
		if(gameLogic.whoWon(playerHand, bankerHand) == "Banker Won")
		{
			winningTotal = winningTotal + bankerBid*2;//...
		}
		if(gameLogic.whoWon(playerHand, bankerHand) == "TIED")
		{
			winningTotal = winningTotal + tieBid*8;//...
		}
		return winningTotal; //returning the total
	}
	//restarting
	public void restart(Stage primaryStage) throws Exception {
		start(primaryStage);
	}

}