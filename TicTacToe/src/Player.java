/** Generic Parent class for ComputerPlayer and HumanPlayer
 * @author Kyle Levy
 */
public abstract class Player {
    /** Player number of this player object
     *
     */
    private int playerNum;

    /** Board that moves will be made on
     *
     */
    private Board board;

    /** Initialize the player's number and the board that will be played on
     *
     * @param num Player number
     * @param board Board moves will be made on
     */
    public Player(int num, Board board){
        this.playerNum = num;
        this.board = board;
    }

    /** Gets player number
     *
     * @return Player's number
     */
    public int getPlayerNum(){
        return this.playerNum;
    }

    /** Gets the board that is being played on
     *
     * @return Reference to board being played on.
     */
    public Board getBoard() {
        return board;
    }

    /** Abstract method to be used by HumanPlayer and ComputerPlayer
     *
     */
    public abstract void makeMove();

}
