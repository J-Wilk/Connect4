import java.util.*;

public class Board{
    
    private BoardSlot[][] boardRepresentation;
    private int rowLength;
    private int columnLength;
    private int lastMoveColumn;
    private int lastMoveRow;
    private BoardSlot lastMoveColor;
    
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
        int count = 0;
        int position = lastMoveRow;
        while(inColumnBoundary(position)){
            if(boardRepresentation[lastMoveColumn][position] == lastMoveColor)
            {
                count++;
            } else {
                break;
            }
            position++;
        }
        position = lastMoveRow - 1;
        while(inColumnBoundary(position)){
            if(boardRepresentation[lastMoveColumn][position] == lastMoveColor)
            {
                count++;
            } else {
                break;
            }
            position--;
        }
        return count >= 4;
    }
    
    private boolean verticleWin(){
        int count = 0;
        int position = lastMoveColumn;
        while(inRowBoundary(position)){
            if(boardRepresentation[position][lastMoveRow] == lastMoveColor){
                count++;
            } else {
                break;
            }
            position++;
        }
        return count >= 4;
    }
    
    private boolean diagonalWin(){
        return checkForLeftRightDiagnolWin() || checkForRightLeftDiagnolWin();
    }
    
    private boolean checkForLeftRightDiagnolWin(){
        int count = 0;
        int lastMoveRowPosition = lastMoveRow;
        int lastMoveColPosition = lastMoveColumn;
        while(inRowBoundary(lastMoveRowPosition) && inColumnBoundary(lastMoveColPosition)){
            if(boardRepresentation[lastMoveColPosition][lastMoveRowPosition] == lastMoveColor){
                count++;
            } else {
                break;
            }
            lastMoveRowPosition--;
            lastMoveColPosition--;
        }
        lastMoveRowPosition = lastMoveRow + 1;
        lastMoveColPosition = lastMoveColumn + 1;
        while(inRowBoundary(lastMoveRowPosition) && inColumnBoundary(lastMoveColPosition)){
            if(boardRepresentation[lastMoveColPosition][lastMoveRowPosition] == lastMoveColor){
                count++;
            } else {
                break;
            }
            lastMoveRowPosition++;
            lastMoveColPosition++;
        } 
        return count >= 4;
    }
    
    private boolean checkForRightLeftDiagnolWin(){
        int count = 0;
        int lastMoveRowPosition = lastMoveRow;
        int lastMoveColPosition = lastMoveColumn;
        while(inRowBoundary(lastMoveRowPosition) && inColumnBoundary(lastMoveColPosition)){
            if(boardRepresentation[lastMoveColPosition][lastMoveRowPosition] == lastMoveColor){
                count++;
            } else {
                break;
            }
            lastMoveRowPosition--;
            lastMoveColPosition++;
        }
        lastMoveRowPosition = lastMoveRow + 1;
        lastMoveColPosition = lastMoveColumn + 1;
        while(inRowBoundary(lastMoveRowPosition) && inColumnBoundary(lastMoveColPosition)){
            if(boardRepresentation[lastMoveColPosition][lastMoveRowPosition] == lastMoveColor){
                count++;
            } else {
                break;
            }
            lastMoveRowPosition--;
            lastMoveColPosition++;
        } 
        return count >= 4;
    }
    
    private boolean inColumnBoundary(int columnPosition){
        return columnPosition > -1 && columnPosition < rowLength-1;
    }
    
    private boolean inRowBoundary(int rowPosition){
        return rowPosition > -1 && rowPosition < columnLength;
    }
    
    public void makeMove(Move moveToPlay){
        if(validMove(moveToPlay)){
            int firstEmptySlotInColumn = findFirstEmptySlot(moveToPlay);
            int column = moveToPlay.getColPosition();
            boardRepresentation[firstEmptySlotInColumn][column] = moveToPlay.getMoveColor();
            assignPositionToLastMoveFields(firstEmptySlotInColumn, column);
            assignColorToLastMoveField(moveToPlay.getMoveColor());
        }
    }
    
    private void assignPositionToLastMoveFields(int column, int row){
        lastMoveColumn = column;
        lastMoveRow = row;
    }
    
    private void assignColorToLastMoveField(BoardSlot lastMoveColor){
        this.lastMoveColor = lastMoveColor;
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
        return colPos >= 0 && colPos < rowLength;
    }
    
    private boolean moveIsEmpty(Move moveToValidate){
        int topSlotInBoard = 0;
        return boardRepresentation[topSlotInBoard][moveToValidate.getColPosition()] == BoardSlot.EMPTY;
    }
    
    private int findFirstEmptySlot(Move moveToFindEmptySlotFor){
        boolean spaceFound = false;
        int currentRowSlot = columnLength - 1;
        while(!spaceFound && currentRowSlot > 0){
            if(boardRepresentation[currentRowSlot][moveToFindEmptySlotFor.getColPosition()] == BoardSlot.EMPTY){
                spaceFound = true;  
            } else { 
                currentRowSlot--;
            }
        }
        return currentRowSlot;
    }

}