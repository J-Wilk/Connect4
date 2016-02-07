import java.util.*;

public class Board{
    
    private BoardSlot[][] boardRepresentation;
    private int noOfRows;
    private int noOfCols;
    private int lastMoveColumn;
    private int lastMoveRow;
    private int goNumber;
    private BoardSlot lastMoveColor;
    
    public Board(){
        noOfRows = 6;
        noOfCols = 7;
        goNumber = 0;
        boardRepresentation = new BoardSlot[noOfRows][noOfCols];
        initalizeGameBoardToEmpty();
    }
    
    public void initalizeGameBoardToEmpty(){
        for(int i = 0; i < noOfRows; i++){
            for(int j = 0; j < noOfCols; j++){
                boardRepresentation[i][j] = BoardSlot.EMPTY;
            }
        }
    }
    
    public void displayBoard(){
        printNumbers();
        for(int i = 0; i < noOfRows; i++){
            for(int j = 0; j < noOfCols; j++){
                printBoardSlot(i, j);
            }
            System.out.println("|");
        }
        System.out.println();
    }
    
    private void printNumbers(){
        for(int i = 1; i < noOfCols + 1; i++){
            System.out.print(" " + i);
        }
        System.out.println();
    }
    
    public void printBoardSlot(int row, int column){
        System.out.print("|" + boardRepresentation[row][column]);
    }
    
    public boolean noWinner(){
        if(horizontalWin() || verticleWin() || diagonalWin()){
            return false;
        }
        return true;
    }
    
    private boolean horizontalWin(){
        int count = 0;
        int position = lastMoveColumn;
        while(inColumnBoundary(position)){
            if(boardRepresentation[lastMoveRow][position] == lastMoveColor)
            {
                count++;
            } else {
                break;
            }
            position++;
        }
        position = lastMoveColumn - 1;
        while(inColumnBoundary(position)){
            if(boardRepresentation[lastMoveRow][position] == lastMoveColor)
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
        int position = lastMoveRow;
        while(inRowBoundary(position)){
            if(boardRepresentation[position][lastMoveColumn] == lastMoveColor){
                count++;
            } else {
                break;
            }
            position++;
        }
        System.out.println("count: " + count);
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
            if(boardRepresentation[lastMoveRowPosition][lastMoveColPosition] == lastMoveColor){
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
            if(boardRepresentation[lastMoveRowPosition][lastMoveColPosition] == lastMoveColor){
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
            if(boardRepresentation[lastMoveRowPosition][lastMoveColPosition] == lastMoveColor){
                count++;
            } else {
                break;
            }
            lastMoveRowPosition--;
            lastMoveColPosition++;
        }
        lastMoveRowPosition = lastMoveRow + 1;
        lastMoveColPosition = lastMoveColumn - 1;
        while(inRowBoundary(lastMoveRowPosition) && inColumnBoundary(lastMoveColPosition)){
            if(boardRepresentation[lastMoveRowPosition][lastMoveColPosition] == lastMoveColor){
                count++;
            } else {
                break;
            }
            lastMoveRowPosition++;
            lastMoveColPosition--;
        }
        return count >= 4;
    }
    
    private boolean inColumnBoundary(int columnPosition){
        return columnPosition > -1 && columnPosition < noOfCols;
    }
    
    private boolean inRowBoundary(int rowPosition){
        return rowPosition > -1 && rowPosition < noOfRows;
    }
    
    public void makeMove(Move moveToPlay){
        if(validMove(moveToPlay)){
            int firstEmptySlotInColumn = findFirstEmptySlot(moveToPlay);
            int column = moveToPlay.getColPosition();
            boardRepresentation[firstEmptySlotInColumn][column] = moveToPlay.getMoveColor();
            assignPositionToLastMoveFields(firstEmptySlotInColumn, column);
            assignColorToLastMoveField(moveToPlay.getMoveColor());
            goNumber++;
        }
    }
    
    private void assignPositionToLastMoveFields(int row, int column){
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
        return colPos >= 0 && colPos < noOfCols;
    }
    
    private boolean moveIsEmpty(Move moveToValidate){
        int topSlotInBoard = 0;
        return boardRepresentation[topSlotInBoard][moveToValidate.getColPosition()] == BoardSlot.EMPTY;
    }
    
    private int findFirstEmptySlot(Move moveToFindEmptySlotFor){
        boolean spaceFound = false;
        int currentRowSlot = noOfRows - 1;
        while(!spaceFound && currentRowSlot > 0){
            if(boardRepresentation[currentRowSlot][moveToFindEmptySlotFor.getColPosition()] == BoardSlot.EMPTY){
                spaceFound = true;  
            } else { 
                currentRowSlot--;
            }
        }
        return currentRowSlot;
    }
    
    public String getBoardSlot(int row, int column){
        return boardRepresentation[column][row].toString();
    }
    
    public int getGoNumber(){
        return goNumber;
    }
    
    public void setGoNumber(int newGoNumber){
        goNumber = newGoNumber;
    }

}