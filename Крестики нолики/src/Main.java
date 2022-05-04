import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] board = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printBoard(board);

        System.out.print("Enter a place from 1-9: ");
        Scanner scanner = new Scanner(System.in);
        int place = scanner.nextInt();
        start(board, place, scanner, 1);
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void start(char[][] board, int place, Scanner scanner, int player) {
        char c;

        if(player % 2 != 0) {
            c = 'X';
        }
        else {
            c = 'O';
        }

        switch(place) {
            case 1:
                board[0][0] = c;
                break;
            case 2:
                board[0][2] = c;
                break;
            case 3:
                board[0][4] = c;
                break;
            case 4:
                board[2][0] = c;
                break;
            case 5:
                board[2][2] = c;
                break;
            case 6:
                board[2][4] = c;
                break;
            case 7:
                board[4][0] = c;
                break;
            case 8:
                board[4][2] = c;
                break;
            case 9:
                board[4][4] = c;
                break;
        }

        printBoard(board);

        if(player == 9) {
            System.out.println("Tie");
            return;
        }
        else if(board[0][0] != ' ' && board[0][0] == board[0][2] && board[0][2] == board[0][4]) {
            if(player % 2 != 0) {
                System.out.println("Player 1 wins!!!");
            }
            else {
                System.out.println("Player 2 wins!!!");
            }
            return;
        }
        else if(board[2][0] != ' ' && board[2][0] == board[2][2] && board[2][2] == board[2][4]) {
            if(player % 2 != 0) {
                System.out.println("Player 1 wins!!!");
            }
            else {
                System.out.println("Player 2 wins!!!");
            }
            return;
        }
        else if(board[4][0] != ' ' && board[4][0] == board[4][2] && board[4][2] == board[4][4]) {
            if(player % 2 != 0) {
                System.out.println("Player 1 wins!!!");
            }
            else {
                System.out.println("Player 2 wins!!!");
            }
            return;
        }
        else if(board[0][0] != ' ' && board[0][0] == board[2][0] && board[2][0] == board[4][0]) {
            if(player % 2 != 0) {
                System.out.println("Player 1 wins!!!");
            }
            else {
                System.out.println("Player 2 wins!!!");
            }
            return;
        }
        else if(board[0][2] != ' ' && board[0][2] == board[2][2] && board[2][2] == board[4][2]) {
            if(player % 2 != 0) {
                System.out.println("Player 1 wins!!!");
            }
            else {
                System.out.println("Player 2 wins!!!");
            }
            return;
        }
        else if(board[0][4] != ' ' && board[0][4] == board[2][4] && board[2][4] == board[4][4]) {
            if(player % 2 != 0) {
                System.out.println("Player 1 wins!!!");
            }
            else {
                System.out.println("Player 2 wins!!!");
            }
            return;
        }
        else if(board[0][0] != ' ' && board[0][0] == board[2][2] && board[2][2] == board[4][4]) {
            if(player % 2 != 0) {
                System.out.println("Player 1 wins!!!");
            }
            else {
                System.out.println("Player 2 wins!!!");
            }
            return;
        }
        else if(board[0][4] != ' ' && board[0][4] == board[2][2] && board[2][2] == board[4][0]) {
            if(player % 2 != 0) {
                System.out.println("Player 1 wins!!!");
            }
            else {
                System.out.println("Player 2 wins!!!");
            }
            return;
        }

        System.out.print("Enter a place from 1-9: ");
        place = scanner.nextInt();

        start(board, place, scanner, ++player);
    }
}