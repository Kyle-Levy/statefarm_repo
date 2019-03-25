import java.util.Scanner;

/**Responsible for starting the game of TicTacToe
 * @author Kyle Levy
 */
public class PlayTicTacToe {

    /** Lets the user choose the match setup (Human v Comp, Human v Human, Comp v Comp)
     *
     * @param args Input from console
     */
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        Player playerOne;
        Player playerTwo;
        boolean play = true;

        while(play) {
            System.out.println("TicTacToe\n---------\nChoose your match-up:");
            System.out.println("1.)Human vs Computer");
            System.out.println("2.)Human vs Human");
            System.out.println("3.)Computer vs Computer");
            System.out.println("4.)Exit game");

            int choice = -1;
            while (choice < 1 || choice > 4) {
                choice = reader.nextInt();
                if (choice < 1 || choice > 4) {
                    System.out.println("Try again");
                }
            }

            switch (choice) {
                case 1:
                    Board board = new Board();
                    playerOne = new HumanPlayer(1, board);
                    playerTwo = new ComputerPlayer(2, board);
                    playGame(playerOne, playerTwo, board);
                    play = playAgain();
                    break;

                case 2:
                    board = new Board();
                    playerOne = new HumanPlayer(1, board);
                    playerTwo = new HumanPlayer(2, board);
                    playGame(playerOne, playerTwo, board);
                    play = playAgain();
                    break;

                case 3:
                    board = new Board();
                    playerOne = new ComputerPlayer(1, board);
                    playerTwo = new ComputerPlayer(2, board);
                    playGame(playerOne, playerTwo, board);
                    play = playAgain();
                    break;

                case 4:
                    play = false;
                    break;
            }
        }



    }

    /** Responsible for starting the game of TicTacToe and allowing input until the game is over
     *
     * @param playerOne First player(Human or computer)
     * @param playerTwo Second player(Human or computer_
     * @param board The board the game is being played on
     */
    public static void playGame(Player playerOne, Player playerTwo, Board board) {
        while (!board.getGameOver()) {
            playerOne.makeMove();
            if (!board.getGameOver()) {
                System.out.println(board);
            }
            playerTwo.makeMove();
            if (!board.getGameOver()) {
                System.out.println(board);
            }
        }
        System.out.println(board);
    }

    /** Asks the user if they would like to play another game of tic tac toe and takes in their response
     *
     * @return True if they said yes, false otherwise.
     */
    public static boolean playAgain(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Would you like to play again?\ny/n");
        String answer = reader.nextLine();
        if(answer.substring(0,1).toUpperCase().equals("Y")){
            return true;
        }
        return false;
    }
}
