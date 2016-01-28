import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Connect4GUI{
    
    private JFrame mainWindow;
    private Board gameBoard; 

    public Connect4GUI(Board gameBoard){
        this.gameBoard = gameBoard;
        String title  = "Connect 4 Game";
        mainWindow = new JFrame(title);
        Container contentPane = mainWindow.getContentPane();
        contentPane.add(createMainPanel());
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
    
    private JPanel createMainPanel(){
        JPanel mainGamePanel = new JPanel(new GridLayout(7, 6));
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 6; j++){
                JButton button = new JButton(gameBoard.getBoardSlot(i, j));
                mainGamePanel.add(button);
            }
        }
        return mainGamePanel;   
    }

}