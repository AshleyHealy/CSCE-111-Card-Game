public class Crazy7 {
public static void main(String[] args){
  


  // Intro Statements
  System.out.println("Crazy 7s!");
  System.out.println("Objective: Get Rid of all your cards");
  System.out.println("Rules:");
  System.out.println("Two Players (you and the computer)");
  System.out.println("Each player is delt seven cards");
  System.out.println("To start the game a card is flipped from the deck");
  System.out.println("If the card is greater than seven plays a card greater than seven");
  System.out.println("If the card is less than seven plays a card less than seven");
  System.out.println("A player can play a seven at any time");
  System.out.println("If you play the same card that was flipped over you play any other card of your choosing");
  System.out.println("If a player can't play a necessary card the player draws one from the deck");
  System.out.println("If niether player can play then a new card is flipped from the deck");
  System.out.println("If an Ace or King is played then a new card is flipped over from the deck");




  // Create the Players hands and the Card deck
  
  ArrayList<String> cardDeck = new ArrayList<String>();
        String[] suit = { "H", "S", "C", "D" };
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
        System.out.println(cardDeck);




  
  }
}
