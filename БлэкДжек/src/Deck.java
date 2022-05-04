import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    private int numOfCards;
    private int numOfDecks;

    public Deck(int numOfDecks) {
        this.numOfDecks = numOfDecks;
        numOfCards = numOfDecks * 52;
        this.shuffle();
    }

    public void shuffle() {
        deck = new ArrayList<Card>();

        // for each deck
        for (int d = 0; d < numOfDecks; d++) {
            // for each suit
            for (int s = 0; s < 4; s++) {
                // for each number
                for (int n = 2; n <= 14; n++) {
                    // add a new card to the deck
                    String str = "Error";

                    switch (n){
                        case 2:
                            str = "2";
                            break;
                        case 3:
                            str = "3";
                            break;
                        case 4:
                            str = "4";
                            break;
                        case 5:
                            str = "5";
                            break;
                        case 6:
                            str = "6";
                            break;
                        case 7:
                            str = "7";
                            break;
                        case 8:
                            str = "8";
                            break;
                        case 9:
                            str = "9";
                            break;
                        case 10:
                            str = "10";
                            break;
                        case 11:
                            str = "J";
                            break;
                        case 12:
                            str = "Q";
                            break;
                        case 13:
                            str = "K";
                            break;
                        case 14:
                            str = "A";
                            break;
                    }

                    deck.add(new Card(str , Suit.values()[s]));
                }
            }
        }
    }

    public Card getCard(int index) {
        return deck.get(index);
    }

    public void removeCard(int index) {
        deck.remove(index);
    }

    public int size() {
        return deck.size();
    }
}
