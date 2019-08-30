import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.Group;

import java.util.Stack;


class Card{

    private String suite;
    private int value;
    private String diffCard;
    StackPane test1;
    String valueDisplay;

    Card(String theSuite, int theValue){
        this.suite = theSuite;

        if(theValue > 9 )       //setting the Value of the card specially face card
        {
            this.value = 0;
            if(theValue == 10)
            {
                this.diffCard = "10";
            }
            else if(theValue == 11)
            {
                this.diffCard = "Jack";
            }
            else if(theValue == 12)
            {
                this.diffCard = "Queen";
            }
            else if(theValue == 13) {
                this.diffCard = "King";
            }

        }
        else if (theValue >= 2 && theValue <= 9)
        {
            this.value = theValue;
            this.diffCard = Integer.toString(theValue);  //changing the int value into String
        }
        else
        {
            this.value = 1;  //Setting the Ace Value
            this.diffCard = "Ace";
        }
    }

    public StackPane drawImage() {
        Label test = new Label(this.diffCard + " of " +  this.suite); // Combining the Card into a Label to print in the application
        test.setAlignment(Pos.CENTER);
        //setting the style of the card
        if(this.suite == "Heart" || this.suite == "Diamond")
        {
            test.setTextFill(Color.RED);
            test.setStyle("-fx-font: 15 arial;");
        }
        if(this.suite == "Club" || this.suite == "Spade")
        {
            test.setTextFill(Color.BLACK);
            test.setStyle("-fx-font: 15 arial;");
        }
        //stack pane rectangle
        Rectangle rectangle = new Rectangle(50d, 70.0d, 125.0d, 150.0d);

        // set fill for rectangle
        rectangle.setFill(Color.WHITE);

        // set rounded corners
        rectangle.setArcHeight(10.0d);
        rectangle.setArcWidth(10.0d);

        // create a Group
        StackPane group = new StackPane(rectangle, test);
        return group;
    }


    public void setValue(int value)
    {
        this.value = value;  //setting the suit of the card
    }

    public void setSuite(String suite)
    {
        this.suite = suite;  //setting the suite of the card
    }

    public int getValue()
    {
        return this.value;   //Value of the card that not the number The Value
    }

    public String getSuite()
    {
        return this.suite;  //get the Suite of the Card
    }

    public String faceValue()
    {
        return this.diffCard;  //this card has the face value so the user sees which card is being used
                                //this was made for faceValues
    }


}
