class Connect4{
    private Board gameBoard;
    private int goNumber;
    
    public Connect4(){
        gameBoard = new Board();
        goNumber = 0;
        while(gameBoard.noWinner()){
            // get input
            Move moveToMake = new Move(5,0);
            moveToMake.setMoveColor(getCurrentPlayerColor());
            gameBoard.makeMove(moveToMake);
            gameBoard.displayBoard();
            break;
        }
    
    }
    
    private BoardSlot getCurrentPlayerColor(){
        if(goNumber % 2 == 0){
            return BoardSlot.RED;
        }else{
            return BoardSlot.YELLOW;
        }
    }
}