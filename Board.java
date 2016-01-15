import java.util.*;

public class Board{
    
    private BoardSlot[][] boardRepresentation;
    private int rowLength;
    private int columnLength;
    
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
        for(int i = 0; i < columnLength; i++){
            for(int j = 0; j < rowLength; j++){
                printBoardSlot(i, j);
            }
            System.out.println("|");
        }
    }
    
    public void printBoardSlot(int row, int column){
        System.out.print("|" + boardRepresentation[row][column]);
    }
    
    // check move
    // make move
    // winning move
    // change how playing a move works
    
    
    public boolean noWinner(){
        return true;
    }
    
    public void makeMove(Move moveToPlay){
        if(validMove(moveToPlay)){
            boardRepresentation[moveToPlay.getColPosition()][findFirstEmptySlot(moveToPlay)] = moveToPlay.getMoveColor();
        }
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
        return (colPos >= 0 && colPos < columnLength);
    }
    
    private boolean moveIsEmpty(Move moveToValidate){
        int topSlotInBoard = rowLength - 1;
        return boardRepresentation[moveToValidate.getColPosition()][topSlotInBoard] == BoardSlot.EMPTY;
    }
    
    private int findFirstEmptySlot(Move moveToFindEmptySlotFor){
        boolean spaceFound = false;
        int currentRowSlot = rowLength - 1;
        while(!spaceFound){
            if(boardRepresentation[moveToValidate.getColPosition()][currentRowSlot] != BoardSlot.EMPTY){
                spaceFound = true;  
            } else { 
                currentRowSlot--;
            }
        }
        return currentRowSlot++;
    }

}