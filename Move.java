public class Move{
    private int colToPlayMoveIn;
    private BoardSlot moveColor;
    
    public Move(int colToPlayMoveIn){
        this.colToPlayMoveIn = colToPlayMoveIn;
    }

    public void setMoveColor(BoardSlot moveColor){
        this.moveColor = moveColor;
    }

    public int getColPosition(){
        return colToPlayMoveIn ;
    }
    
    public BoardSlot getMoveColor(){
        return moveColor;
    }
}