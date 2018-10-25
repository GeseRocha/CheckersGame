
public class Main {

    public static void main(String[] args) {



        UserPlayer player1 = new UserPlayer(1);
        UserPlayer player2 = new UserPlayer(2);

        Board.getInstance();

        Board.getInstance().startGame(player1, player2);

    }




}
