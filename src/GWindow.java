import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GWindow {

    private static GWindow gameWindow = null;

   // private int[] _moveLocations = new int[4];

    private GWindow(){
    }

    public static GWindow getInstance() {
        if (gameWindow == null)
            gameWindow = new GWindow();

        return gameWindow;
    }

    //Checks what type of player is asking. If 0 then User Type else AI Type
    public void getLocations(int playerType, int playerID){

        if (playerType == 0)
            userGetAvailablePieces(playerID);
        else
            aiGetLocations();

    }

    public void userGetAvailablePieces(int playerID){
        do {

            //Determines weather the player can jump, not implemented yet;
            boolean jump;

            //just for testing, in real implementation it will use the GUI to get locations
            int[] moveLocations = new int[4]; // [current row][current col][desired row][desire col]

            //get available locations
            ArrayList<int[]> availablePieces = new ArrayList<int[]>();


            for (Piece piece : Board.getInstance().getAvailablePieces(playerID)) {
                availablePieces.add(new int[]{piece.sendID()[0], piece.sendID()[1]});
            }

            //Show available pieces. In real implementation this will highlight and make all available pieces clickable, for
            //now it will show locations of available pieces in console

            //Only for testing
            Board.getInstance().printBoard();

            System.out.println("\nChoose from available pieces: ");

            for (int[] pieceLocations : availablePieces) {
                System.out.println(Arrays.toString(pieceLocations));
            }


            Scanner in = new Scanner(System.in);

            System.out.println("\nEnter piece row: ");
            moveLocations[0] = in.nextInt();
            System.out.println("Enter piece col: ");
            moveLocations[1] = in.nextInt();


            //Get Available moves
            ArrayList<int[]> availableMoves = new ArrayList<int[]>();

            for (int[] moves : Board.getInstance().getAllAvailableMoves(moveLocations[0], moveLocations[1], playerID)) {
                availableMoves.add(moves);
            }


            for (int[] availableLocation : availableMoves) {
                System.out.println(Arrays.toString(availableLocation));
            }

            System.out.println("\nEnter piece row: ");
            moveLocations[2] = in.nextInt();
            System.out.println("Enter piece col: ");
            moveLocations[3] = in.nextInt();

            Board.getInstance().makeMove(moveLocations);

            System.out.print("Move Made\n");
            Board.getInstance().printBoard();



        }while(false);




    }



    public void aiGetLocations(){

    }

}
