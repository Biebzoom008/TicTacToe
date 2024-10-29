import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Initialization of the board and scanner, and also a boolean board that is used to check whether someone played on one spot or not
        String[][] board = new String[3][3];
        boolean[][] boardCheck = new boolean[3][3];
        int[][] boardWin = new int [3][3];

        int winner = 0; // initializes a variable used at the end when displaying who the winner is (1 = player 1, 2 = player 2)

        //This loop puts the boolean value false everywhere in the array so that when a player chooses a spot its value turns to true to avoid playing multiple times on the same spot
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardCheck[i][j] = false;
                board[i][j] = "empty";
                boardWin[i][j] = 0;
            }
        }

        Scanner scanner = new Scanner(System.in);
        boolean boardNotFull = board[0][0].equals("empty") || board[0][1].equals("empty") || board[0][2].equals("empty") || board[1][0].equals("empty") || board[1][1].equals("empty") || board[1][2].equals("empty") || board[2][0].equals("empty") || board[2][1].equals("empty") || board[2][2].equals("empty");

        System.out.println("***** TIC TAC TOE *****\n\nPlease choose your symbol, you'll be player 1:");
        String player1Symbol = scanner.next();
        while (player1Symbol.length() > 1) {
            System.out.println("Your symbol must be of size 1, no word or combination of symbols are allowed.");
            player1Symbol = scanner.next();
        }

        System.out.println("Please choose your symbol, you'll be player 2:");
        String player2Symbol = scanner.next();
        while (player2Symbol.length() > 1) {
            System.out.println("Your symbol must be of size 1, no word or combination of symbols are allowed.");
            player2Symbol = scanner.next();
        }

        boardPrintCheck(boardCheck); //Shows the initialization of the "memory" of the spots already taken in the game


        //Game
        while (boardNotFull && !win(boardWin)) { //If the board is full, or if someone won, then the loop will stop

            //Player 1
            System.out.println("Player 1's turn");
            System.out.println("Choose a line: ");
            int line = scanner.nextInt();
            System.out.println("Choose a column: ");
            int column = scanner.nextInt();

            while ((line > 2 || column > 2)|| (boardCheck[line][column] == true)) { //Replays the turn if the spot chosen has already been taken or if the chosen position is not valid
                if (line > 2 || column > 2) {
                    System.out.println("Choose a valid position from these numbers: (0 - 1 - 2)");
                }
                if (boardCheck[line][column] == true) {
                    System.out.println("That position has already been used.");
                }
                System.out.println("Choose a line: ");
                line = scanner.nextInt();
                System.out.println("Choose a column: ");
                column = scanner.nextInt();
            }
            //assigns the player 1 symbol to the position chosen, and its position is set to true
            board[line][column] = player1Symbol;
            boardCheck[line][column] = true;
            boardWin[line][column] = 1;
            winner = 1;
            boardPrint(board);
            boardIntPrint(boardWin);
            System.out.println(win(boardWin));





            //Player 2 (The "Big" if is used to check if the board is full or not)
            //if yes, then player 2 won't play again and again
            if(boardNotFull && !win(boardWin)) {
                System.out.println("Player 2's turn");
                System.out.println("Choose a line: ");
                line = scanner.nextInt();
                System.out.println("Choose a column: ");
                column = scanner.nextInt();

                while ((line > 2 || column > 2)|| (boardCheck[line][column] == true)){ //The while and if are used to check for "errors", mainly to avoid a ArrayOutOfBounds exception, or to avoid playing on a used spot
                    if (line > 2 || column > 2){
                        System.out.println("Choose a valid position from these numbers: (0 - 1 - 2)");
                    }
                    if (boardCheck[line][column] == true) {
                        System.out.println("That position has already been used."); //Replays the turn if the spot chosen has already been taken
                    }
                    System.out.println("Choose a line: ");
                    line = scanner.nextInt();
                    System.out.println("Choose a column: ");
                    column = scanner.nextInt();
                }

                board[line][column] = player2Symbol;
                boardCheck[line][column] = true;
                boardWin[line][column] = 7;
                winner = 2;
                boardPrint(board);
                boardIntPrint(boardWin);

                System.out.println();
            }

        }

        if (winner == 1){
            System.out.println("Congratulations, player 1 wins!!");
        } else {
            System.out.println("Congratulations, player 2 wins!!");
        }


    }



    //This method prints the "memory" of the spots already taken in the board
    public static void boardPrintCheck (boolean[][] board){
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
                counter += 1;
                if (counter == 3) {
                    System.out.println("\n");
                    counter = 0;
                }

            }

        }
    }


    //This method prints the board in a 3 by 3 array
    public static void boardPrint (String[][] board){
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
                counter += 1;
                if (counter == 3) {
                    System.out.println("\n");
                    counter = 0;
                }

            }

        }
    }

    public static void boardIntPrint (int[][] board){
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
                counter += 1;
                if (counter == 3) {
                    System.out.println("\n");
                    counter = 0;
                }

            }

        }
    }

    //This method checks all possible win conditions each player loop and returns true if there is at least one
    public static boolean win (int[][] board){
                int win1 =(board [0][0] + board[0][1] + board[0][2]);
                int win2 = (board[1][0] + board[1][1] + board[1][2]);
                int win3 = (board[2][0] + board[2][1] + board[2][2]);
                int win4 = board[0][0] + board[1][0] + board[2][0];
                int win5 = board[0][1] + board[1][1] + board[2][1];
                int win6 = board[0][2] + board[1][2] + board[2][2];
                int win7 = board[0][0] + board[1][1] + board[2][2];
                int win8 = board[0][2] + board[1][1] + board[2][0];

                if(win1 == 3 || win2 == 3 || win3 == 3 || win4 == 3 || win5 == 3 || win6 == 3 || win7 == 3 || win8 == 3){
                    return true;
                } else if (win1 == 21 || win2 == 21 || win3 == 21 || win4 == 21 || win5 == 21 || win6 == 21 || win7 == 21 || win8 == 21){
                    return true;
                }


        return false;
    }

}