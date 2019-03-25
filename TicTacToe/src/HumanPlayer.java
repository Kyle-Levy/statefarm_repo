import java.util.Scanner;

/** Specific type of the generic parent class Player
 * @author Kyle Levy
 */
public class HumanPlayer extends Player {

    /** Allows input from the player
     *
     */
    private Scanner reader;

    /** Constructs object of a human player
     *
     * @param playerNum The player's number
     * @param board The board the player is interacting with
     */
    public HumanPlayer(int playerNum, Board board){
        super(playerNum, board);
        reader = new Scanner(System.in);
    }

    /** Player attempts to make a move until a valid move has been entered.
     *
     */
    @Override
    public void makeMove(){
        if(!getBoard().getGameOver()) {
            System.out.println("Make a move Player " + getPlayerNum());
            boolean madeValidMove = false;
            int indexChoice = -1;

            while (!madeValidMove) {
                while (indexChoice < 1 || indexChoice > 9) {
                    indexChoice = reader.nextInt();
                }
                madeValidMove = this.getBoard().makeMove(indexChoice-1, this.getPlayerNum());
                if(!madeValidMove){
                    System.out.println("Spot filled, try again!");
                    indexChoice = -1;
                }
            }
            getBoard().checkWinner(this.getPlayerNum());
            getBoard().printWinner();
        }
    }
}
