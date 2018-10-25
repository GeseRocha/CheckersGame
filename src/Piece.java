import javax.swing.*;
import java.awt.*;



import java.lang.*;

public class Piece {

    private String _color;
    private int[] piece_id = new int[4];

    public Piece( int row , int col, int player , int pieceID, String color1 , String color2)
    {
        //Graphics2D g;
        piece_id[0] = row;
        piece_id[1] = col;
        piece_id[2] = player;
        piece_id[3] = pieceID;

        setColor(color1 , color2);
    }

    public void setColor(String color1 , String color2)
    {
        if (piece_id[2] == 1 )
            _color = color1;
        else
            _color = color2;
    }

    public void movePiece()
    {

    }

    public boolean isKing(int []player_id)
    {
        if (player_id[2] == 2)
            return (player_id[0] == 0);
        else
            return (player_id[0] == 7);
    }

    //Returns the whole ID
    public int[] sendID()
    {
        return piece_id;
    }

    //Return the numbering ID
    public int sendPieceID()
    {
        return piece_id[3];
    }

    //Updates Piece ID
    public void updatePiece(int row, int col) {
        piece_id[0] = row;
        piece_id[1] = col;
    }





}