import java.util.ArrayList;
import java.util.Arrays;

public class Board {



    //Default locations of the pieces
    private int[][] _PieceLocations = new int[][] {
            {0,2,0,2,0,2,0,2},
            {2,0,2,0,2,0,2,0},
            {0,2,0,2,0,2,0,2},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0}
    };

    //Players Turn
    private int _playerTurn = 1;

    //Pieces in Board
    private ArrayList<Piece> pieces = new ArrayList<Piece>();

    //Players
    private Player[] gamePlayers;

    private static Board board = null;

    private Board(){ }

    //Only called when board is first called
    public static Board getInstance() {
        if (board == null)
            board = new Board();

        return board;
    }


    //Start Game
    public void startGame(Player player1, Player player2) {
        setUpBoard(player1,player2);

        makeTurn();
    }


    //Sends Locations
    public int[][] sendLocations() {
        return _PieceLocations;
    }

    //Sets up initial settings for the board
    private void setUpBoard(Player player1, Player player2) {

        //Gives default locations and IDs to pieces
        assignPieces();

        //Creates Players
        gamePlayers = new Player[]{player1,player2};

        //Send IDs to Gwindow
    }

    //Assigns Pieces to players and gives them IDs. NOTE-Add colors
    private void assignPieces() {
        int pieceID = 0;

        for (int row = 0; row < _PieceLocations.length; row++){
            for (int col = 0; col < _PieceLocations [row].length; col++) {
                if(_PieceLocations[row][col] != 0) { //Only creates a piece if the cell is not empy
                    pieces.add(new Piece(row, col, _PieceLocations[row][col], pieceID, "Black", "White"));
                    pieceID++;
                }
            }
        }

        /*for (Piece piece : pieces){
            System.out.println(Arrays.toString(piece.sendID()));
        }*/

    }

    //Calls on Players to make a turn
    private void makeTurn() {
        do {
            if (_playerTurn == 1) {
                _playerTurn = gamePlayers[0].makeMove();
            } else {
                _playerTurn = gamePlayers[1].makeMove();
            }


        }while(gameNotOver());//Players still have pieces


    }

    //Checks to see that both players still have pieces on the board
    private boolean gameNotOver() {
        int playerOneP = 0,
            playerTwoP = 0;

        for (Piece piece : pieces){
            if(piece.sendID()[2] == 1)
                playerOneP++;
            else if(piece.sendID()[2] == 2)
                playerTwoP++;
        }

        System.out.println("Player P1 " + playerOneP + "Player P2 " + playerTwoP);

        if (playerOneP != 0 && playerTwoP != 0)
            return  true;
        else
            return false;
    }

    //Sends available pieces
    //public ArrayList<int[]> getAvailablePieces(int playerID){
    public ArrayList<Piece> getAvailablePieces(int playerID) {

        ArrayList<int[]> tempAvailablePieces = new ArrayList<int[]>();

        ArrayList<Piece> tempAvailableP = new ArrayList<>();

        for (Piece piece : pieces) {
            if (piece.sendID()[2] == playerID){
                //Gets row and col
                tempAvailablePieces.add(new int[]{piece.sendID()[0], piece.sendID()[1]});
                tempAvailableP.add(piece);
            }
        }

        //return tempAvailablePieces;
        return tempAvailableP;

    }

    //Gets available moves
    public ArrayList<int[]> getAllAvailableMoves(int row, int col, int playerID){

        ArrayList<int[]> availableMoves = new ArrayList<int[]>();



        //3 will be for king and it will be implement it later. NOTE still have to check for edges
        //Player 1
        if(playerID == 1) {
            //Checks the left side
            if(col != 0 && row != 0 && _PieceLocations[row-1][col-1] == 0){
                availableMoves.add(new int[]{row-1, col-1});
            }
            else if(col != 0 && row != 0 && _PieceLocations[row-1][col-1] == 2) {
                if (col != 1 && row != 1 && _PieceLocations[row-2][col-2] == 0)
                    availableMoves.add(new int[]{row-2, col-2});
            }

            //Checks the right side
            if(col != 7 && row != 0 &&_PieceLocations[row-1][col+1] == 0){
                availableMoves.add(new int[]{row-1, col+1});
            }
            else if(col != 7 && row != 0 && _PieceLocations[row-1][col+1] == 2) {
                if (col != 6 && row != 0 &&_PieceLocations[row-2][col+2] == 0)
                    availableMoves.add(new int[]{row-2, col+2});
            }
        }
        //Player 2
        else {
            //Checks the left side
            if(col != 0 && row != 7 &&_PieceLocations[row+1][col-1] == 0){
                availableMoves.add(new int[]{row+1, col-1});
            }
            else if(col != 0 && row != 7 && _PieceLocations[row+1][col-1] == 1) {
                if (col != 1 && row != 6 &&_PieceLocations[row+2][col-2] == 0)
                    availableMoves.add(new int[]{row+2, col-2});
            }

            //Checks the right side
            if(col !=7 && row != 7 && _PieceLocations[row+1][col+1] == 0){
                availableMoves.add(new int[]{row+1, col+1});
            }
            else if(col !=7 && row != 7 && _PieceLocations[row+1][col+1] == 1) {
                if (col != 6 && row != 6 && _PieceLocations[row+2][col+2] == 0)
                    availableMoves.add(new int[]{row+2, col+2});
            }
        }

        return availableMoves;
    }

    //Make move and updates the pieces and the board
    public void makeMove(int[] moveLocations) {

        int tempData = _PieceLocations[moveLocations[0]][moveLocations[1]];
        int move;

        //Single jump
        //Only testing for player 1
        if (moveLocations[2] == (moveLocations[0]+1) || moveLocations[2] == (moveLocations[0]-1)) {

            System.out.print(Arrays.toString(moveLocations));

            //Update Board
            _PieceLocations[moveLocations[0]][moveLocations[1]] = 0;
            _PieceLocations[moveLocations[2]][moveLocations[3]] = tempData;

            //Update Piece
            for (Piece piece : pieces) {
                if(piece.sendID()[0] == moveLocations[0] && piece.sendID()[1] == moveLocations[1])
                    piece.updatePiece(moveLocations[2], moveLocations[3]);
            }
        }
        //Double jump
        else{
            move = getMove(moveLocations);

            //Updates Board
            _PieceLocations[moveLocations[0]][moveLocations[1]] = 0;
            _PieceLocations[moveLocations[2]][moveLocations[3]] = tempData;

            //Deletes piece
            boolean foundPiece = false;

            for (Piece piece : pieces){
                switch (move){
                    case 1 :
                        if (moveLocations[0]-1 == piece.sendID()[0] && moveLocations[1]-1 == piece.sendID()[1]) {
                            pieces.remove(piece);
                            _PieceLocations[moveLocations[0] - 1][moveLocations[1] - 1] = 0;
                            foundPiece = true;
                        }
                        break;
                    case 2:
                        if (moveLocations[0]-1 == piece.sendID()[0] && moveLocations[1]+1 == piece.sendID()[1]) {
                            pieces.remove(piece);
                            _PieceLocations[moveLocations[0] - 1][moveLocations[1] + 1] = 0;
                            foundPiece = true;
                        }
                        break;
                    case 3:
                        if (moveLocations[0]+1 == piece.sendID()[0] && moveLocations[1]-1 == piece.sendID()[1]) {
                            pieces.remove(piece);
                            _PieceLocations[moveLocations[0] + 1][moveLocations[1] - 1] = 0;
                            foundPiece = true;
                        }
                        break;
                    case 4:
                        if (moveLocations[0]+1 == piece.sendID()[0] && moveLocations[1]+1 == piece.sendID()[1]) {
                            pieces.remove(piece);
                            _PieceLocations[moveLocations[0] + 1][moveLocations[1] + 1] = 0;
                            foundPiece = true;
                        }
                        break;
                }

                if (foundPiece)
                    break;
            }

            //Update Piece
            for (Piece piece : pieces) {
                if(piece.sendID()[0] == moveLocations[0] && piece.sendID()[1] == moveLocations[1])
                    piece.updatePiece(moveLocations[2], moveLocations[3]);
            }


        }

    }

    //Determines what move is being made
    private int getMove(int[] moveLocations) {
        //Moves Player 1 Left = 1, Player 1 Right = 2, Player 2 Left = 3, Player 2 Right = 4
        if(moveLocations[0] > moveLocations[2]) {
            if(moveLocations[1] > moveLocations[3])
                return 1;
            else
                return 2;

        }
        else {
            if(moveLocations[1] > moveLocations[3])
                return 3;
            else
                return 4;
        }
    }

    //Just for testing. Prints the board
    public void printBoard(){
        for (int[] row : _PieceLocations){
            for (int col : row )
                System.out.print(col);
            System.out.print("\n");
        }
    }
}
