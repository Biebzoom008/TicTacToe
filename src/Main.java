import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Initialization of the board and scanner, and also a boolean board that is used to check whether someone played on one spot or not
        String[][] board = new String[3][3];
        boolean[][] boardCheck = new boolean[3][3];

        //This loop puts the boolean value false everywhere in the array so that when a player chooses a spot its value turns to true to avoid playing multiple times on the same spot
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardCheck[i][j] = false;
            }
        }

        Scanner scanner = new Scanner(System.in);
        boolean boardNotFull = board[0][0] == null || board[0][1] == null || board[0][2] == null || board[1][0] == null || board[1][1] == null || board[1][2] == null || board[2][0] == null || board[2][1] == null || board[2][2] == null;

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



        //game
        while (boardNotFull) {

            //Player 1
            System.out.println("Player 1's turn");
            System.out.println("Choose a line: ");
            int line = scanner.nextInt();
            System.out.println("Choose a column: ");
            int column = scanner.nextInt();

            while ((line > 2 || column > 2)|| (boardCheck[line][column] == true)) {
                if (line > 2 || column > 2) {
                    System.out.println("Choose a valid position from these numbers: (0 - 1 - 2)");
                    System.out.println("Choose a line: ");
                    line = scanner.nextInt();
                    System.out.println("Choose a column: ");
                    column = scanner.nextInt();
                }
                if (boardCheck[line][column] == true) {
                    System.out.println("That position has already been used."); //Replays the turn if the spot chosen has already been taken
                    System.out.println("Choose a line: ");
                    line = scanner.nextInt();
                    System.out.println("Choose a column: ");
                    column = scanner.nextInt();
                }
            }
            board[line][column] = player1Symbol;
            boardCheck[line][column] = true;

            boardPrint(board);


            //Player 2 (The "Big" if is used to check if the board is full or not)
            if(boardNotFull) {
                System.out.println("Player 2's turn");
                System.out.println("Choose a line: ");
                line = scanner.nextInt();
                System.out.println("Choose a column: ");
                column = scanner.nextInt();

                while ((line > 2 || column > 2)|| (boardCheck[line][column] == true)){ //The while and if are used to check for "errors", mainly to avoid a ArrayOutOfBounds exception, or to avoid playing on a used spot
                    if (line > 2 || column > 2){
                        System.out.println("Choose a valid position from these numbers: (0 - 1 - 2)");
                        System.out.println("Choose a line: ");
                        line = scanner.nextInt();
                        System.out.println("Choose a column: ");
                        column = scanner.nextInt();
                    }
                    if (boardCheck[line][column] == true) {
                        System.out.println("That position has already been used."); //Replays the turn if the spot chosen has already been taken
                        System.out.println("Choose a line: ");
                        line = scanner.nextInt();
                        System.out.println("Choose a column: ");
                        column = scanner.nextInt();
                    }
                }

                board[line][column] = player2Symbol;
                boardCheck[line][column] = true;

                boardPrint(board);
            }

        }

        boardPrint(board);
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
}