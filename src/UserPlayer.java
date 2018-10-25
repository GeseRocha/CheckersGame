public class UserPlayer implements Player{

    private int _playerID;
    private int _playerType = 0;

    public UserPlayer(int playerID){_playerID = playerID; }


    public int makeMove()
    {
        //Who's turn is next
        int nextTurn;

        GWindow.getInstance().getLocations(0, _playerID);

        if(_playerID == 1)
            nextTurn = 2;
        else
            nextTurn = 1;

        return nextTurn;
    }
}
