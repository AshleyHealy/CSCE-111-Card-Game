
import java.util.*;

public class crazy7 {

    public static void main(String[] args) {
        // Intro Statements
        //Luke R
        //Random r = new Random(22);

        System.out.println("\t\tCrazy 7s!");
        System.out.println("Objective: Get Rid of all your cards");
        System.out.println("Rules:");
        System.out.println("\tTwo Players (you and the computer)");
        System.out.println("\tEach player is dealt seven cards");
        System.out.println("\tTo start the game a card is flipped from the deck");
        System.out.println("\tIf the card is greater than seven plays a card greater than seven");
        System.out.println("\tIf the card is less than seven plays a card less than seven");
        System.out.println("\tA player can play a seven at any time");
        System.out.println("\tIf you play a seven you play any other card of your choosing");
        System.out.println("\tIf a player can't play a necessary card the player draws one from the deck");
        System.out.println("\tIf neither player can play then a new card is flipped from the deck");
        System.out.println("\tIf an Ace or King is played then a new card is flipped over from the deck \n");
        System.out.println("\tIf you play a seven or the same value card as the discard, only one additional card can be played.\n");

        // Create The Card deck
        //Olivia Knapp
        ArrayList<String> cardDeck = new ArrayList<String>();
        ArrayList<String> playerHand = new ArrayList<String>();
        ArrayList<String> flippedCards = new ArrayList<String>();
        ArrayList<String> computerHand = new ArrayList<String>();
        String[] suit = {"H", "S", "C", "D"};
        // for each element in the suit array...
        for (String element : suit) {
            cardDeck.add(element + "A");
            // assign the number value cards (2-9)
            for (int num = 2; num <= 10; num++) {
                cardDeck.add(element + num);
            }
            // assign the face value cards and increment index for the next suit
            cardDeck.add(element + "J");
            cardDeck.add(element + "Q");
            cardDeck.add(element + "K");
        }
        // Shuffling the Card Deck
        Collections.shuffle(cardDeck);

        // Create Player Hand
        for (int i = 0; i < 7; i++) {
            playerHand.add(cardDeck.get(i));
            cardDeck.remove(i);
            computerHand.add(cardDeck.get(i));
            cardDeck.remove(i);
        }
        // Method for Flipping over the Top card
        // Display the flipped over card
        String flippedCard = cardDeck.get(0);
        cardDeck.remove(0);
        flippedCards.add(flippedCard);

        // while loop that keeps the game running
        //Olivia Knapp
        while (!computerHand.isEmpty() || !playerHand.isEmpty()) {
            System.out.println("Computer Hand: " + computerHand);
            // shows the flipped over card
            System.out.println("Flipped over card is: " + flippedCard);
            // shows you your hand
            System.out.println("Your Cards: " + playerHand);
            // asks for input
            System.out.println("What would you like to play? Type 'Draw' if you need to draw");
            // variables for tracking draw
            // Luke R
            int playerCount = 0;
            int computerCount = 0;
            // calling player turn
            flippedCard = PlayerTurn(flippedCard, playerHand);
            // checking if players play was valid
            //Olivia Knapp
            if (flippedCard.contains("Invalid")) {
                while (flippedCard.contains("Invalid")) {
                    System.out.println("Please choose a valid card.");
                    flippedCard = flippedCards.get(flippedCards.size() - 1);
                    flippedCard = PlayerTurn(flippedCard, playerHand);
                }
                flippedCards.add(flippedCard);
                playerHand.remove(flippedCard);
            } // checking if the player drew
            else if (flippedCard.equals("Draw")) {
                playerHand.add(cardDeck.get(0));
                cardDeck.remove(0);
                flippedCard = flippedCards.get(flippedCards.size() - 1);
                playerCount = 1;
            } // checking if the player played a seven 
            else if (flippedCard.charAt(1) == '7') {
                // checks if they just played their last card and don't have another to play
                if (playerHand.isEmpty()) {
                    System.out.println("Player wins!");
                    System.exit(0);
                }
                System.out.println("Play again:");
                flippedCards.add(flippedCard);
                playerHand.remove(flippedCard);
                flippedCard = PlayerTurn(flippedCard, playerHand);
                flippedCards.add(flippedCard);
                playerHand.remove(flippedCard);
                System.out.println("Successful turns!");
                playerCount = 0;
            } //checking if the user played a king or an ace
                // Luke R
            else if (flippedCard.charAt(1) == 'K' || flippedCard.charAt(1) == 'A') {
                flippedCards.add(flippedCard);
                playerHand.remove(flippedCard);
                System.out.println("Successful turn!");
                playerCount = 0;
                computerCount = 0;
                System.out.print("Flip a new card from the deck: ");
                flippedCard = cardDeck.get(0);
                cardDeck.remove(0);
                System.out.println(flippedCard);
                flippedCards.add(flippedCard);

                // Ashley H. , juiyun 
                // If player plays the same card as flipped they can play again
            } else if (flippedCard.charAt(1) == (flippedCards.get(flippedCards.size() - 1).charAt(1))) {
                // checks if they just played their last card and don't have another to play
                if (playerHand.isEmpty()) {
                    System.out.println("Player wins!");
                    System.exit(0);
                }
                System.out.println("Play again:");
                flippedCards.add(flippedCard);
                playerHand.remove(flippedCard);
                flippedCard = PlayerTurn(flippedCard, playerHand);
                if (flippedCard.contains("Invalid")) {
                    flippedCard = flippedCard.substring(0, 2);
                }
                flippedCards.add(flippedCard);
                playerHand.remove(flippedCard);
                System.out.println("Successful turns!");
                playerCount = 0;

            } // any other allowed turn 
            else {
                flippedCards.add(flippedCard);
                playerHand.remove(flippedCard);
                System.out.println("Successful turn!");
                playerCount = 0;
            }// cheks if the player is out of cards
            // Luke R
            if (playerHand.isEmpty()) {
                System.out.println("Player wins!");
                System.exit(0);
            }
            //Olivia Knapp
            // calling computers turn
            flippedCard = ComputerTurn(flippedCard, computerHand);
            // checking if the computer drew
            if (flippedCard.equals("Draw")) {
                computerHand.add(cardDeck.get(0));
                cardDeck.remove(0);
                computerCount = 1;
                flippedCard = flippedCards.get(flippedCards.size() - 1);
            } // checking if the computer played a seven
             // Ashley H. 
            else if (flippedCard.charAt(1) == '7') {
                System.out.println("Computer Played: " + flippedCard);
                // checks if they just played their last card and don't have another to play
                if (computerHand.isEmpty()) {
                    System.out.println("Computer wins!");
                    System.exit(0);
                }
                System.out.println("Computer Plays Again");
                flippedCards.add(flippedCard);
                computerHand.remove(flippedCard);
                flippedCards.remove("Draw");
                flippedCard = ComputerTurn(flippedCard, computerHand);
                System.out.println("Computer Played: " + flippedCard);
                flippedCards.add(flippedCard);
                computerHand.remove(flippedCard);
                computerCount = 0;
            } // checking if the computer played a king or an ace
                // Luke R
            else if (flippedCard.charAt(1) == 'K' || flippedCard.charAt(1) == 'A') {
                System.out.println("Computer Played: " + flippedCard);
                flippedCards.add(flippedCard);
                computerHand.remove(flippedCard);
                playerCount = 0;
                computerCount = 0;
                System.out.print("Flip a new card from the deck: ");
                flippedCard = cardDeck.get(0);
                cardDeck.remove(0);
                System.out.println(flippedCard);
                flippedCards.add(flippedCard);
            } // checking if the computer played the same card as the previous one
            else if (flippedCard.charAt(1) == (flippedCards.get(flippedCards.size() - 1).charAt(1))) {
                System.out.println("Computer Played: " + flippedCard);
                // checks if they just played their last card and don't have another to play
                if (computerHand.isEmpty()) {
                    System.out.println("Computer wins!");
                    System.exit(0);
                }
                flippedCards.add(flippedCard);
                computerHand.remove(flippedCard);
                flippedCard = ComputerTurn(flippedCard, computerHand);
                flippedCards.add(flippedCard);
                computerHand.remove(flippedCard);
                playerCount = 0;
            } // any other valid play 
                //Olivia Knapp
            else {
                flippedCards.add(flippedCard);
                computerHand.remove(flippedCard);
                System.out.println("Computer Played: " + flippedCard);
                computerCount = 0;
            }
            // if neither players can't play, flip a new card from the deck.
            // Lukke R, juiyun
            if ((playerCount == 1) && (computerCount == 1)) {
                System.out.print("Flip a new card from the deck: ");
                flippedCard = cardDeck.get(0);
                cardDeck.remove(0);
                System.out.println(flippedCard);
                flippedCards.add(flippedCard);
                playerCount = 0;
                computerCount = 0;
            }
            // checking to see if a player or computers hand is empty
            if (playerHand.isEmpty()) {
                System.out.println("Player wins!");
                System.exit(0);
            } else if (computerHand.isEmpty()) {
                System.out.println("Computer wins!");
                System.exit(0);
            }
        }
    }
    // Ashley H., Olivia Knapp
    // Computers turn method

    public static String ComputerTurn(String flippedCard, ArrayList<String> computerHand) {
        String computerPlay = "";
        // getting the number associated with the card
        String cardNumber = flippedCard.substring(1);
        // cchecking if the card number is bigger than or equal to 7 
        if ((Character.isDigit(cardNumber.charAt(0)) && (Integer.parseInt(cardNumber) >= 7))
                || flippedCard.charAt(1) == 'J' || flippedCard.charAt(1) == 'Q' || flippedCard.charAt(1) == 'K') {
            for (String card : computerHand) {
                // getting the card number for the card in the hand
                String compCardNumber = card.substring(1);
                // checking if the card can be played
                if ((Character.isDigit(compCardNumber.charAt(0)) && Integer.parseInt(compCardNumber) >= 7)
                        || compCardNumber.equals("J") || compCardNumber.equals("Q") || compCardNumber.equals("K")) {
                    // returning a valid card
                    return card;
                }
            }
        } // cchecking if the card number is less than or equal to 7 
        else if ((Character.isDigit(cardNumber.charAt(0)) && Integer.parseInt(cardNumber) <= 7)
                || flippedCard.charAt(1) == 'A') {
            for (String card : computerHand) {
                // getting the card number for the card in the hand
                String compCardNumber = card.substring(1);
                // checking if the card can be played
                if ((Character.isDigit(compCardNumber.charAt(0)) && Integer.parseInt(compCardNumber) <= 7)
                        || compCardNumber.equals("A")) {
                    // returning a valid card
                    return card;
                }
            }
        } //checking if the computer has a seven 
        else if (computerHand.contains("H7") || computerHand.contains("D7") || computerHand.contains("S7")
                || computerHand.contains("C7")) {
            for (String card : computerHand) {
                // getting the card number for the card in the hand
                String compCardNumber = card.substring(1);
                // checking if the card can be played
                if (compCardNumber.equals("7")) {
                    // returning a valid card
                    return card;
                }
            }
        }
        // drawing if the computer has no playable cards
        System.out.println("Computer had to draw!");
        computerPlay = "Draw";
        return computerPlay;
    }
    // Ashley H., Olivia Knapp
    // Players turn method
    public static String PlayerTurn(String flippedCard, ArrayList<String> playerHand) {
        String validatedPlay = "";
        // importing thee scanner
        Scanner scnr = new Scanner(System.in);
        // getting input from the player
        String playerPlay = scnr.next();
        // getting the number from the flipped card and the players card
        String cardNumber = flippedCard.substring(1);
        String playerNumber = playerPlay.substring(1);
        // checking if they are both greater than or equal to seven
        if (((Character.isDigit(cardNumber.charAt(0)) && (Integer.parseInt(cardNumber) >= 7))
                || flippedCard.charAt(1) == 'J' || flippedCard.charAt(1) == 'Q' || flippedCard.charAt(1) == 'K')
                && ((Character.isDigit(playerNumber.charAt(0)) && (Integer.parseInt(playerNumber) >= 7))
                || playerPlay.charAt(1) == 'J' || playerPlay.charAt(1) == 'Q' || playerPlay.charAt(1) == 'K')) {
                    //returning the play if it is valid
            validatedPlay = playerPlay;
        } // checking if they are both less than or equal to seven 
        else if (((Character.isDigit(cardNumber.charAt(0)) && Integer.parseInt(cardNumber) <= 7)
                || flippedCard.charAt(1) == 'A')
                && ((Character.isDigit(playerNumber.charAt(0)) && Integer.parseInt(playerNumber) <= 7)
                || playerPlay.charAt(1) == 'A')) {
                    //returning the play if it is valid
            validatedPlay = playerPlay;
        } //checking if the user played a seven 
        else if (playerNumber.equals("7")) {
            //returning the play if it is valid
            validatedPlay = playerPlay;
        } // checking if the player drew 
        else if (playerPlay.equals("Draw")) {
            //returning the play if it is valid
            validatedPlay = playerPlay;
        } // returning invalid if play meets no criteria 
        else {
            validatedPlay = playerPlay + " is Invalid";
        }
        return validatedPlay;
    }
}
