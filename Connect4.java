import java.util.Scanner;

class Connect4{
    private Board gameBoard;
    private int goNumber;
    
    public Connect4(){
        gameBoard = new Board();
        goNumber = 0;
        Scanner reader = new Scanner(System.in);
        while(gameBoard.noWinner()){
            gameBoard.displayBoard();
            System.out.println("Enter the column you would like to play your go in: ");
            int movePosition = reader.nextInt();
            Move moveToMake = new Move(movePosition);
            moveToMake.setMoveColor(getCurrentPlayerColor());
            gameBoard.makeMove(moveToMake);
            goNumber++;
        }
        gameBoard.displayBoard();
        printWinnerMessage();
    
    }
    
    private BoardSlot getCurrentPlayerColor(){
        if(goNumber % 2 == 0){
            return BoardSlot.RED;
        }else{
            return BoardSlot.YELLOW;
        }
    }
    
    private void printWinnerMessage(){
        String winningPlayersColor;
        if(getCurrentPlayerColor() == BoardSlot.RED){
            winningPlayersColor = "yellow";    
        } else {
            winningPlayersColor = "red";
        }
        System.out.println("Congratulations " + winningPlayersColor + " player, you won!");
    }
}