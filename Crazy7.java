
import java.util.*;

public class crazy7 {

    public static void main(String[] args) {
        // Intro Statements
        Random r = new Random(0);

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

        // Create The Card deck
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
        Collections.shuffle(cardDeck, r);

        // Create Player Hand
        for (int i = 0; i < 7; i++) {
            playerHand.add(cardDeck.get(i));
            cardDeck.remove(i);
            computerHand.add(cardDeck.get(i));
            cardDeck.remove(i);
        }
        String flippedCard = cardDeck.get(0);
        cardDeck.remove(0);
        flippedCards.add(flippedCard);
        // Method for Flipping over the Top card
        // Display the flipped over cardD

        while (!computerHand.isEmpty() && !playerHand.isEmpty()) {
            System.out.println("Computer Hand: " + computerHand);
            System.out.println("Flipped over card is: " + flippedCard);
            System.out.println("Your Cards: " + playerHand);
            System.out.println("What would you like to play? Type 'Draw' if you need to draw");
            flippedCard = PlayerTurn(flippedCard, playerHand);
            int playerCount = 0;
            if (flippedCard.equals("Invalid")) {
                System.out.println("Please choose a valid card.");
                flippedCard = flippedCards.get(flippedCards.size() - 1);
                flippedCard = PlayerTurn(flippedCard, playerHand);
                flippedCards.add(flippedCard);
                playerHand.remove(flippedCard);
            } else if (flippedCard.equals("DRAW")) {
                playerHand.add(cardDeck.get(0));
                cardDeck.remove(0);
                playerCount = 1;
                flippedCard = flippedCards.get(flippedCards.size() - 1);
            } else if (flippedCard.charAt(1) == '7') {
                System.out.println("Play again:");
                flippedCards.add(flippedCard);
                playerHand.remove(flippedCard);
                flippedCard = PlayerTurn(flippedCard, playerHand);
                flippedCards.add(flippedCard);
                playerHand.remove(flippedCard);
                System.out.println("Successful turns!");
            } else {
                flippedCards.add(flippedCard);
                playerHand.remove(flippedCard);
                playerCount = 0;
                System.out.println("Successful turn!");
            }
            // checks if users hand is empty
            if (playerHand.isEmpty()) {
                System.out.println("Player wins!");
                System.exit(0);
            }
            // If Card is between 7 and 13 play any card higher than 7, if 1 through 7 play
            // any card lower than 7
            // after computers turn
            flippedCard = ComputerTurn(flippedCard, computerHand);
            int compCount = 0;
            if (flippedCard.equals("Draw")) {
                computerHand.add(cardDeck.get(0));
                cardDeck.remove(0);
                compCount = 1;
                flippedCard = flippedCards.get(flippedCards.size() - 1);
            } else if (flippedCard.charAt(1) == '7') {
                System.out.println("Computer Plays Again");
                flippedCards.add(flippedCard);
                computerHand.remove(flippedCard);
                flippedCard = ComputerTurn(flippedCard, computerHand);
                flippedCards.add(flippedCard);
                computerHand.remove(flippedCard);
            } else {
                flippedCards.add(flippedCard);
                computerHand.remove(flippedCard);
                compCount = 0;
                System.out.println("Computer Played: " + flippedCard);
            }
            // if neither players can't play, flip a new card from the deck.
            if ((playerCount == 1) && (compCount == 1)) {
                System.out.print("Flip a new card from the deck: ");
                flippedCard = cardDeck.get(0);
                cardDeck.remove(0);
                System.out.println(flippedCard);
                flippedCards.add(flippedCard);
                playerCount = 0;
                compCount = 0;
            }
        }
        if (playerHand.isEmpty()) {
            System.out.println("Player wins!");
            System.exit(0);
        } else {
            System.out.println("Computer wins!");
            System.exit(0);
        }
    }

    // Computers turn method
    public static String ComputerTurn(String flippedCard, ArrayList<String> computerHand) {
        String computerPlay = "";
        char cardNumber = flippedCard.charAt(1);
        if ((Character.isDigit(cardNumber) && (Character.getNumericValue(cardNumber) > 7 || Character.getNumericValue(cardNumber) == 1))
                || cardNumber == 'J' || cardNumber == 'Q' || cardNumber == 'K') {
            for (String card : computerHand) {
                char compCardNumber = card.charAt(1);
                if ((Character.isDigit(compCardNumber) && (Character.getNumericValue(compCardNumber) >= 7 || (Character.getNumericValue(compCardNumber) == 1)))
                        || compCardNumber == 'J' || compCardNumber == 'Q' || compCardNumber == 'K') {
                    computerPlay = card;
                    return computerPlay;
                }
            }

        } else if ((Character.isDigit(cardNumber) && (Character.getNumericValue(cardNumber) < 7 && Character.getNumericValue(cardNumber) != 1))
                || cardNumber == 'A') {
            for (String card : computerHand) {
                char compCardNumber = card.charAt(1);
                if ((Character.isDigit(compCardNumber) && Character.getNumericValue(compCardNumber) <= 7
                        && Character.getNumericValue(cardNumber) != 1)
                        || compCardNumber == 'A') {
                    computerPlay = card;
                    return computerPlay;
                }
            }
        } else if (computerHand.contains("H7") || computerHand.contains("D7") || computerHand.contains("S7")
                || computerHand.contains("C7")) {
            for (String card : computerHand) {
                char compCardNumber = card.charAt(1);
                if (compCardNumber == 7) {
                    computerPlay = card;
                    return computerPlay;
                }
            }
        }
        computerPlay = "Draw";
        System.out.println("Computer had to draw!");

        return computerPlay;
    }

    // Players turn method
    public static String PlayerTurn(String flippedCard, ArrayList<String> playerHand) {
        String validatedPlay = "";
        Scanner scnr = new Scanner(System.in);
        String playerPlay = scnr.nextLine();
        playerPlay = playerPlay.toUpperCase();
        char cardNumber = flippedCard.charAt(1);
        char playerNumber = playerPlay.charAt(1);
        if ((cardNumber > '7' || cardNumber == '1' || cardNumber == 'K' || cardNumber == 'Q' || cardNumber == 'J') && (playerNumber > '7' || playerNumber == '1' || playerNumber == 'J' || playerNumber == 'Q' || playerNumber == 'K')) {
            validatedPlay = playerPlay;
        } else if ((cardNumber < '7' || cardNumber == 'A')
                && (playerNumber < '7' || playerNumber == 'A')) {
            validatedPlay = playerPlay;
        } else if (cardNumber == '7') {
            validatedPlay = playerPlay;
        } else if (playerPlay.equals("DRAW")) {
            validatedPlay = playerPlay;
        } else {
            validatedPlay = "Invalid";
        }
        return validatedPlay;
    }
}
