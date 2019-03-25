import java.util.Random;
import java.util.concurrent.TimeUnit;

/** Specific type of the generic parent class Player
 * @author Kyle Levy
 */
public class ComputerPlayer extends Player {

    /** Used for the computer to make a move
     *
     */
    private Random randomizer;


    /** Constructor for creating a computer player
     *
     * @param playerNum Whether the computer is player one or two
     * @param board Board moves are being made on
     */
    public ComputerPlayer(int playerNum, Board board){
        super(playerNum, board);
        randomizer = new Random();
    }

    /** Computer tries to make moves until a valid move is made
     *
     */
    @Override
    public void makeMove(){
        if(!getBoard().getGameOver()) {
            System.out.println("Computer " + getPlayerNum() + " is making a move.");
            boolean madeValidMove = false;
            int indexChoice;
            try
            {
                Thread.sleep(1500);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            while (!madeValidMove) {
                indexChoice = randomizer.nextInt(9);
                madeValidMove = this.getBoard().makeMove(indexChoice, this.getPlayerNum());
            }
            getBoard().checkWinner(this.getPlayerNum());
            getBoard().printWinner();
        }
    }
}
