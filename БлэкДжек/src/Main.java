import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int quit;
        int balance = 10000;
        int bet = 0;
        Scanner sc = new Scanner(System.in);

        // init new deck
        int numOfDecks = 2;
        Deck deck = new Deck(numOfDecks);
        int sizeOfDeck = deck.size();

        System.out.println("Welcome to BlackJack\n");

        while (true) {
            if (deck.size() < sizeOfDeck / 2) {
                deck.shuffle();
            }

            System.out.println("If you want to start press 1");
            System.out.println("If you want to quit press 0");

            quit = sc.nextInt();

            if (quit == 0) {
                break;
            }

            // bet method
            bet = bet(balance, sc);

            int sumOfPlayerCards = 0;
            int sumOfDealerCards = 0;
            ArrayList<Card> playerCards = new ArrayList<Card>();
            ArrayList<Card> dealerCards = new ArrayList<Card>();

            Random rng = new Random();
            int index;

            // player and dealer get their first two cards
            for (int i = 0; i < 4; i++) {
                // player gets his cards
                index = rng.nextInt(deck.size());
                if (i == 0 || i == 3) {
                    playerCards.add(deck.getCard(index));
                    sumOfPlayerCards += deck.getCard(index).getNum();
                }
                // dealer gets his first card
                else {
                    dealerCards.add(deck.getCard(index));
                    sumOfDealerCards += deck.getCard(index).getNum();
                }
                deck.removeCard(index);
            }

            System.out.print("Player cards:   ");
            printCards(playerCards);
            System.out.println("Dealer cards:   " + dealerCards.get(0).toString() + "    -----\n");

            int choice = 0;
            boolean statement = true;

            // BlackJack
            if (sumOfPlayerCards == 21 && sumOfDealerCards != 21) {
                System.out.println("BLACKJACK");
                System.out.println("\n===============================================================\n");
                balance += 2 * bet;
                continue;
            }

            // Insurance in case if dealer's first card is A, K, Q, J or 10
            if (dealerCards.get(0).getNum() == 11 || dealerCards.get(0).getNum() == 10) {
                statement = false;
                System.out.println("Insurance");
                System.out.println("Yes: 1");
                System.out.println("No: 0");
                choice = sc.nextInt();
                System.out.println();

                // Dealer has blackjack and player insured => player doesn't loos money
                if (choice == 1 && sumOfDealerCards == 21 && sumOfPlayerCards < 21) {
                    System.out.print("Dealer cards:   ");
                    printCards(dealerCards);
                    System.out.println("Dealer wins");
                    System.out.println("\n===============================================================\n");
                    balance -= bet;
                    continue;
                }
                // Dealer doesn't have blackjack and player insured => player looses his insurance ( bet / 2 )
                else if (choice == 1 && sumOfDealerCards != 21) {
                    balance -= (bet / 2);
                }
                // Dealer has blackjack and player didn't insurance => player looses his bet
                else if (choice == 0 && sumOfDealerCards == 21 && sumOfPlayerCards < 21) {
                    System.out.print("Dealer cards:   ");
                    printCards(dealerCards);
                    System.out.println("Dealer wins");
                    System.out.println("\n===============================================================\n");
                    balance -= bet;
                    continue;
                }
            }

            // EVEN MONEY
            if (dealerCards.get(0).getNum() == 11 && sumOfPlayerCards == 21 && choice == 0) {
                System.out.println("Even money");
                System.out.println("Yes: 1");
                System.out.println("No: 0");
                choice = sc.nextInt();
                System.out.println();

                // Dealer's first card is A, player has blackjack, but he choose even money => player wins bet
                if (choice == 1) {
                    System.out.println("\n===============================================================\n");
                    balance += bet;
                    continue;
                }
                else if (choice == 0 && sumOfDealerCards == 21) {
                    System.out.print("Player cards:   ");
                    printCards(playerCards);
                    System.out.print("Dealer cards:   ");
                    printCards(dealerCards);
                    System.out.println("Tie");
                    System.out.println("\n===============================================================\n");
                    continue;
                }
                else {
                    System.out.println("BLACKJACK!!!");
                    System.out.println("\n===============================================================\n");
                    balance += 2 * bet;
                    continue;
                }
            }

            // Enough
            System.out.println("Enough: 0");

            // Take cards
            System.out.println("Take card: 1");

            // Surrender
            if (statement) {
                System.out.println("Surrender: 2");
            }

            // Double
            System.out.println("Double: 3");

            // Split
            if (playerCards.get(0).getNum() == playerCards.get(1).getNum()) {
                System.out.println("Split: 4");
            }

            choice = sc.nextInt();
            System.out.println();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            if (choice == 0) {
                // Dealer takes cards
                while (sumOfDealerCards < 17) {
                    sumOfDealerCards += takeACard(rng, deck, dealerCards);
                }

                System.out.print("Player cards:   ");
                printCards(playerCards);
                System.out.print("Dealer cards:   ");
                printCards(dealerCards);

                if (sumOfDealerCards > 21 || sumOfPlayerCards > sumOfDealerCards) {
                    System.out.println("You win");
                    balance += bet;
                }
                else if (sumOfPlayerCards < sumOfDealerCards) {
                    System.out.println("You loos");
                    balance -= bet;
                }
                else {
                    System.out.println("Tie");
                }
            }
            else if (choice == 1) {
                // Player take one card
                statement = false;

                while (choice == 1) {
                    sumOfPlayerCards += takeACard(rng, deck, playerCards);

                    System.out.print("Player cards:   ");
                    printCards(playerCards);
                    System.out.println("Dealer cards:   " + dealerCards.get(0).toString() + "    -----");

                    if (sumOfPlayerCards > 21) {
                        System.out.println("You loos");
                        balance -= bet;
                        statement = true;
                        break;
                    }

                    System.out.println("\nTake card: 1");
                    System.out.println("Stop: 2");
                    choice = sc.nextInt();
                    System.out.println();
                }

                if (statement) {
                    System.out.println("\n===============================================================\n");
                    continue;
                }

                // Dealer takes cards
                while (sumOfDealerCards < 17) {
                    sumOfDealerCards += takeACard(rng, deck, dealerCards);
                }

                System.out.print("Player cards:   ");
                printCards(playerCards);
                System.out.print("Dealer cards:   ");
                printCards(dealerCards);

                if (sumOfDealerCards > 21 || sumOfPlayerCards > sumOfDealerCards) {
                    System.out.println("You win");
                    balance += bet;
                }
                else if (sumOfPlayerCards < sumOfDealerCards) {
                    System.out.println("You loos");
                    balance -= bet;
                }
                else {
                    System.out.println("Tie");
                }
            }
            else if (choice == 2) {
                balance -= bet / 2;
                System.out.println("===============================================================\n");
                continue;
            }
            else if (choice == 3) {
                // Player take one card
                sumOfPlayerCards += takeACard(rng, deck, playerCards);

                if (sumOfPlayerCards > 21) {
                    System.out.print("Player cards:   ");
                    printCards(playerCards);
                    System.out.println("Dealer cards:   " + dealerCards.get(0).toString() + "    -----");

                    System.out.println("You loos");
                    System.out.println("\n===============================================================\n");
                    balance -= 2 * bet;
                    continue;
                }

                // Dealer takes cards
                while (sumOfDealerCards < 17) {
                    sumOfDealerCards += takeACard(rng, deck, dealerCards);
                }

                System.out.print("Player cards:   ");
                printCards(playerCards);
                System.out.print("Dealer cards:   ");
                printCards(dealerCards);

                if (sumOfDealerCards > 21 || sumOfDealerCards < sumOfPlayerCards) {
                    System.out.println("You win");
                    balance += 2 * bet;
                }
                else if (sumOfDealerCards > sumOfPlayerCards) {
                    System.out.println("You loos");
                    balance -= 2 * bet;
                }
                else {
                    System.out.println("Tie");
                }
            }
            else if (choice == 4) {
                int sumOfSplit_1 = 0;
                int sumOfSplit_2 = 0;
                ArrayList<Card> split_1 = new ArrayList<Card>();
                ArrayList<Card> split_2 = new ArrayList<Card>();

                split_1.add(playerCards.get(0));
                sumOfSplit_1 += playerCards.get(0).getNum();
                split_2.add(playerCards.get(1));
                sumOfSplit_2 += playerCards.get(1).getNum();

                // Add second card to split_1
                sumOfSplit_1 += takeACard(rng, deck, split_1);

                // Add second card to split_2
                sumOfSplit_2 += takeACard(rng, deck, split_2);

                System.out.print("Split 1:   ");
                printCards(split_1);
                System.out.print("Split 2:   ");
                printCards(split_2);
                System.out.println("Dealer cards:   " + dealerCards.get(0).toString() + "    -----\n");

                // Take card to split_1
                System.out.println("Take card: 1");
                System.out.println("Stop: 2");
                choice = sc.nextInt();
                System.out.println();

                // Player take one card to split_1
                boolean statement_1 = false;

                while (choice == 1) {
                    sumOfSplit_1 += takeACard(rng, deck, split_1);

                    System.out.print("Split 1:   ");
                    printCards(split_1);
                    System.out.print("Split 2:   ");
                    printCards(split_2);
                    System.out.println("Dealer cards:   " + dealerCards.get(0).toString() + "    -----");

                    if (sumOfSplit_1 > 21) {
                        System.out.println("You loos first split");
                        balance -= bet;
                        statement_1 = true;
                        break;
                    }

                    System.out.println("\nTake card: 1");
                    System.out.println("Stop: 2");
                    choice = sc.nextInt();
                    System.out.println();
                }

                // Take card to split_2
                System.out.println("Take card: 1");
                System.out.println("Stop: 2");
                choice = sc.nextInt();
                System.out.println();

                // Player take one card to split_2
                boolean statement_2 = false;

                while (choice == 1) {
                    sumOfSplit_2 += takeACard(rng, deck, split_2);

                    System.out.print("Split 1:   ");
                    printCards(split_1);
                    System.out.print("Split 2:   ");
                    printCards(split_2);
                    System.out.println("Dealer cards:   " + dealerCards.get(0).toString() + "    -----");

                    if (sumOfSplit_2 > 21) {
                        System.out.println("You loos second split");
                        balance -= bet;
                        statement_2 = true;
                        break;
                    }

                    System.out.println("\nTake card: 1");
                    System.out.println("Stop: 2");
                    choice = sc.nextInt();
                    System.out.println();
                }

                if (statement_1 && statement_2) {
                    System.out.println("===============================================================\n");
                    continue;
                }

                statement = false;
                // Dealer takes cards
                while (sumOfDealerCards < 17) {
                    sumOfDealerCards += takeACard(rng, deck, dealerCards);
                }

                System.out.print("Split 1:   ");
                printCards(split_1);
                System.out.print("Split 2:   ");
                printCards(split_2);
                System.out.print("Dealer cards:   ");
                printCards(dealerCards);

                if (sumOfDealerCards > 21 && !statement_1) {
                    System.out.println("You win the first split");
                    balance += bet;
                    statement = true;
                }

                if (sumOfDealerCards > 21 && !statement_2) {
                    System.out.println("You win the second split");
                    balance += bet;
                    statement = true;
                }

                if (statement) {
                    System.out.println("\n===============================================================\n");
                    continue;
                }

                if (sumOfSplit_1 > sumOfDealerCards && !statement_1) {
                    System.out.println("You win split 1");
                    balance += bet;
                }
                else if (sumOfSplit_1 < sumOfDealerCards) {
                    System.out.println("You loos split 1");
                    balance -= bet;
                }
                else {
                    System.out.println("Split 1 is tie");
                }

                if (sumOfSplit_2 > sumOfDealerCards && !statement_2) {
                    System.out.println("You win split 2");
                    balance += bet;
                }
                else if (sumOfSplit_2 < sumOfDealerCards) {
                    System.out.println("You loos split 2");
                    balance -= bet;
                }
                else {
                    System.out.println("Split 2 is tie");
                }
            }

            System.out.println("\n===============================================================\n");
        }
    }

    // Take a card from deck and return his num
    public static int takeACard(Random rng, Deck deck, ArrayList<Card> cards) {
        int index;
        int sum;

        index = rng.nextInt(deck.size());
        cards.add(deck.getCard(index));
        sum = deck.getCard(index).getNum();
        deck.removeCard(index);

        return sum;
    }

    public static void printCards(ArrayList<Card> cards) {
        for (Card c: cards) {
            System.out.print(c + "    ");
        }
        System.out.println();
    }

    public static int bet(int balance, Scanner sc) {
        int bet = 0;
        boolean statement = true;

        while (statement) {
            statement = false;
            System.out.println("\nYour balance: " + balance);
            System.out.print("Input your bet: ");
            bet = sc.nextInt();
            System.out.println();

            if (bet > balance) {
                System.out.println("Your bet can not be more than your balance.");
                System.out.println("Please input correct bet.");
                statement = true;
            }
        }

        return bet;
    }
}
