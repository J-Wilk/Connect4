import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Connect4GUI{
    
    private JFrame mainWindow;
    private Board gameBoard; 
    private JPanel mainGamePanel;
    private JButton[][] buttonArray;

    public Connect4GUI(Board gameBoard){
        this.gameBoard = gameBoard;
        String title  = "Connect 4 Game";
        mainWindow = new JFrame(title);
        Container contentPane = mainWindow.getContentPane();
        buttonArray = new JButton[7][6];
        contentPane.add(createMainPanel());
        
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
    
    private JPanel createMainPanel(){
        mainGamePanel = new JPanel(new GridLayout(6, 7));
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                JButton button = new JButton(gameBoard.getBoardSlot(j, i));
                button.addActionListener(new ButtonListener(j, i));
                buttonArray[j][i] = button;
                mainGamePanel.add(button);
            }
        } 
        return mainGamePanel;   
    }
        
    class ButtonListener implements ActionListener {
        private int col;
        private int row;
        
        public ButtonListener(int col, int row){
            this.col = col;
            this.row = row;
        }
        
        public void actionPerformed(ActionEvent event){
            Move newMove = new Move(col + 1);
            newMove.setMoveColor(BoardSlot.RED);
            gameBoard.makeMove(newMove);
            gameBoard.displayBoard();
            buttonArray[col][row].setText(gameBoard.getBoardSlot(col, row));
            if(!gameBoard.noWinner()){
                JOptionPane.showMessageDialog(null, "Winner winner chicken dinner");
                gameBoard.initalizeGameBoardToEmpty();
                mainWindow.remove(mainGamePanel);
                mainWindow.add(createMainPanel());
                mainWindow.pack();
            } 
            mainGamePanel.revalidate();
            mainGamePanel.repaint();    
        }
    } 

}

