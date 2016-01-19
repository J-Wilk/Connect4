import java.util.*;

public class Board{
    
    private BoardSlot[][] boardRepresentation;
    private int rowLength;
    private int columnLength;
    private int lastMoveColumn;
    private int lastMoveRow;
    
    public Board(){
        rowLength = 7;
        columnLength = 6;
        boardRepresentation = new BoardSlot[columnLength][rowLength];
        initalizeGameBoardToEmpty();
    }
    
    private void initalizeGameBoardToEmpty(){
        for(int i = 0; i < columnLength; i++){
            for(int j = 0; j < rowLength; j++){
                boardRepresentation[i][j] = BoardSlot.EMPTY;
            }
        }
    }
    
    public void displayBoard(){
        printNumbers();
        for(int i = 0; i < columnLength; i++){
            for(int j = 0; j < rowLength; j++){
                printBoardSlot(i, j);
            }
            System.out.println("|");
        }
        System.out.println();
    }
    
    private void printNumbers(){
        for(int i = 1; i < rowLength + 1; i++){
            System.out.print(" " + i);
        }
        System.out.println();
    }
    
    public void printBoardSlot(int row, int column){
        System.out.print("|" + boardRepresentation[row][column]);
    }
    
    // winning move
    
    public boolean noWinner(){
        if(horizontalWin() || verticleWin() || diagonalWin()){
            return false;
        }
        return true;
    }
    
    private boolean horizontalWin(){
        return false;
    }
    
    private boolean verticleWin(){
        return false;
    }
    
    private boolean diagonalWin(){
        return false;
    }
    
    public void makeMove(Move moveToPlay){
        if(validMove(moveToPlay)){
            int firstEmptySlotInColumn = findFirstEmptySlot(moveToPlay)
            int column = moveToPlay.getColPosition()
            boardRepresentation[firstEmptySlotInColumn][column] = moveToPlay.getMoveColor();
            assignPositionToLastMoveFields(firstEmptySlotInColumn, colum);
        }
    }
    
    private void assignPositionToLastMoveFileds(int column, int row){
        lastMoveColumn = column;
        lastMoveRow = row;
    }
    
    private boolean validMove(Move moveToValidate){
        if(moveInBoard(moveToValidate)){
            if(moveIsEmpty(moveToValidate)){
                return true;
            }
        }
        return false;    
    }
    
    private boolean moveInBoard(Move moveToValidate){
        int colPos = moveToValidate.getColPosition(); 
        return colPos >= 0 && colPos < rowLength + 1;
    }
    
    private boolean moveIsEmpty(Move moveToValidate){
        int topSlotInBoard = 0;
        System.out.println(topSlotInBoard + " " + moveToValidate.getColPosition());
        return boardRepresentation[topSlotInBoard][moveToValidate.getColPosition()] == BoardSlot.EMPTY;
    }
    
    private int findFirstEmptySlot(Move moveToFindEmptySlotFor){
        boolean spaceFound = false;
        int currentRowSlot = columnLength - 1;
        while(!spaceFound && currentRowSlot > 0){
            if(boardRepresentation[currentRowSlot][moveToFindEmptySlotFor.getColPosition()] == BoardSlot.EMPTY){
                System.out.println("In");
                spaceFound = true;  
            } else { 
                currentRowSlot--;
            }
        }
        return currentRowSlot;
    }

}