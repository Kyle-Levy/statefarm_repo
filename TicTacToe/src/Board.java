/**
 * Class that holds the game logic and the 3x3 matrix for TicTacToe
 *
 * @author Kyle Levy
 */
public class Board {
    /**
     * 2D array of integers (0 = empty, 1 = X, 2 = O)
     */
    private int[][] board;

    /**
     * The value that corresponds to which player won. 0 is game in progress, 1 is X, 2 is 0, and 3 is a scratch.
     */
    private int winner = 0;

    /**
     * If the game is still in progress
     */
    private boolean gameOver = false;

    /**
     * Determines if the board is full with no winner
     */
    private boolean scratch = false;

    /**
     * Creates a 3x3 2D array of integers with values all set to 0
     */
    public Board() {
        board = new int[3][3];
        printIndex();
        System.out.println("TicTacToe\n");

        System.out.println(toString());
    }

    /**
     * Attempts to fill a box with either an X(1) or an O(2)
     *
     * @param boxIndex Index number of box
     * @param piece    X = 1: O = 2
     * @return If the index was filled with an X or O, return true. If unsuccessful, return false.
     */
    public boolean makeMove(int boxIndex, int piece) {
        if (!gameOver) {
            int index = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (index == boxIndex) {
                        //Return false if board space is already filled
                        if (this.board[i][j] != 0) {
                            return false;
                        } else {
                            //If it is 0, it's a valid move, so change the board to the appropriate number corresponding to X or O, and return true
                            this.board[i][j] = piece;
                            return true;
                        }

                    }
                    index++;
                }
            }
        }
        return false;
    }

    //Return 0 if no winner
    //Return 1 if X winner
    //Return 2 if O winner
    //Return 3 if scratch

    /**
     * Checks all possibilities for a player to win
     *
     * @param playerNum The number of the player who just made a move
     */
    public void checkWinner(int playerNum) {

        if (!gameOver) {
            //Top row
            if (board[0][0] == playerNum && board[0][1] == playerNum && board[0][2] == playerNum) {
                this.winner = playerNum;
                gameOver = true;
            }//Middle row
            else if (board[1][0] == playerNum && board[1][1] == playerNum && board[1][2] == playerNum) {
                this.winner = playerNum;
                gameOver = true;
            }//Bottom row
            else if (board[2][0] == playerNum && board[2][1] == playerNum && board[2][2] == playerNum) {
                this.winner = playerNum;
                gameOver = true;
            }//First column
            else if (board[0][0] == playerNum && board[1][0] == playerNum && board[2][0] == playerNum) {
                this.winner = playerNum;
                gameOver = true;
            }//Second column
            else if (board[0][1] == playerNum && board[1][1] == playerNum && board[2][1] == playerNum) {
                this.winner = playerNum;
                gameOver = true;
            }//Third column
            else if (board[0][2] == playerNum && board[1][2] == playerNum && board[2][2] == playerNum) {
                this.winner = playerNum;
                gameOver = true;
            }//Top left to bottom right diagonal
            else if (board[0][0] == playerNum && board[1][1] == playerNum && board[2][2] == playerNum) {
                this.winner = playerNum;
                gameOver = true;
            }//Bottom left to top right
            else if (board[2][0] == playerNum && board[1][1] == playerNum && board[0][2] == playerNum) {
                this.winner = playerNum;
                gameOver = true;
            }
        }

        //If the game isn't over by a winner, check if the board is full(scratch)
        if (!gameOver) {
            checkScratch();
        }
    }

    /**
     * Prints the winner of the match if someone has won/scratched
     */
    public void printWinner() {
        if (this.winner == 1) {
            System.out.println("\n*-*-*-*-*\nX WINS!\n*-*-*-*-*\n");
        } else if (this.winner == 2) {
            System.out.println("\n*-*-*-*-*\nO WINS!\n*-*-*-*-*\n");
        } else if (this.winner == 3) {
            System.out.println("\nGAME OVER: SCRATCH\n");
        }
    }

    /**
     * Checks if the board is full of non-zero values
     */
    private void checkScratch() {
        scratch = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    scratch = false;
                    return;
                }
            }
        }
        if (scratch) {
            gameOver = true;
            this.winner = 3;
        }
    }

    /**
     * Prints the 'tutorial' for how to play TicTacToe in the console
     */
    private void printIndex() {
        int index = 1;
        System.out.println("This is the board and each corresponding index");
        System.out.print("---------\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print("|" + index + "|");
                index++;
            }
            System.out.print("\n---------\n");
        }
        System.out.println("\n");
    }

    /**
     * Gets game over status.
     *
     * @return Returns game over status.
     */
    public boolean getGameOver() {
        return gameOver;
    }

    /**
     * Prints the current status of the board.
     *
     * @return Current status of the board.
     */
    @Override
    public String toString() {
        String currentBoard = "";
        System.out.print("---------\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    currentBoard += "| |";
                } else if (board[i][j] == 1) {
                    currentBoard += "|X|";
                } else if (board[i][j] == 2) {
                    currentBoard += "|O|";
                }

            }
            currentBoard += "\n---------\n";
        }

        return currentBoard;
    }
}
