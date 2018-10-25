package edu.jsu.mcis;


import java.awt.*;
import java.awt.event.*;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class TicTacToeView extends JPanel implements ActionListener {

    private TicTacToeModel model;
    public JButton[][] grid;
    public JPanel panel;
    public JLabel resultLabel;
    public JPanel container;
    /* CONSTRUCTOR */

    public TicTacToeView(TicTacToeModel model) {

        this.model = model;

        panel = new JPanel();
        panel.setLayout(new GridLayout(model.getWidth(),model.getWidth()));
        resultLabel = new JLabel();
        resultLabel.setName("ResultLabel");
        resultLabel.setText("Winner: ");
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        grid = new JButton[3][3];

        for(int row=0; row < 3; row++){
            for(int col =0; col < 3; col++){
                grid[row][col] = new JButton();
                grid[row][col].addActionListener(this);
                grid[row][col].putClientProperty("row", row);
                grid[row][col].putClientProperty("col", col);
                grid[row][col].setPreferredSize(new Dimension(64,64));
                grid[row][col].setLocation((row*20)+20,(col*20)+20);
                grid[row][col].setName("Square" + row + col);
                grid[row][col].setText("-");

                panel.add(grid[row][col]);

            }
        }
        setSize(600,600);
        setVisible(true);
        container.add(panel);
        container.add(resultLabel);
    }

    public void viewModel() {

        /* Print the board to the console (see examples) */

        /* INSERT YOUR CODE HERE */
        if (model.getWidth() == 3 && model.isGridBlank()){
          System.out.print("\n  012\n\n0 ---\n1 ---\n2 ---\n\n\n\n");
        }else{
          System.out.print("\n");
    		  System.out.print("  ");

    		  for( int i = 0; i < model.getWidth(); i++ ){
    			  System.out.print(i);
    		  }

    		  System.out.print("\n");
    		  System.out.print("\n");

    		  for( int row = 0; row < model.getWidth(); row++ ){
    			   System.out.print(row + " ");
    			   for( int col = 0; col < model.getWidth(); col++ ){
               System.out.print(model.getMark(row, col));
    			   }

            System.out.print("\n");
    		  }

          System.out.print("\n\n\n");
         }
    }

    public void showNextMovePrompt() {

        /* Display a prompt for the player's next move (see examples) */

        /* INSERT YOUR CODE HERE */
        if (model.isXTurn()){
          System.out.println("Player 1 (X) Move:");
          System.out.print("Enter the row and column numbers, separated by a space: ");
        }else{
          System.out.println("Player 2 (O) Move:");
          System.out.print("Enter the row and column numbers, separated by a space: ");
        }
    }

    public void showInputError() {

        /* Display an error if input is invalid (see examples) */

        /* INSERT YOUR CODE HERE */
        System.out.println("ERROR: Input must be a positive number between 0 and the width of the board. Moves on spaces that are not empty are not allowed.");
    }

    public void showResult(String r) {

        /* Display final winner */

        System.out.println(r + "!");

    }

    @Override

    public void actionPerformed(ActionEvent e){

    JButton butn = (JButton) e.getSource();
    String select = butn.getName();
    int row = (int) (select.charAt(6)) - 48 ;
    int col = (int) (select.charAt(7)) - 48;

    if (!model.isGameover()){
        if (model.isValidSquare(row,col) && !model.isSquareMarked(row,col)){
            if(model.isXTurn()){
                butn.setText("X");
                model.makeMark(row,col);
            }else{
                butn.setText("O");
                model.makeMark(row,col);
                }
            }
        }

    if(model.isGameover()){
        if(null != model.getResult())
            switch (model.getResult().toString()) {
                case "X":
                    resultLabel.setText("X");
                    break;

                case "O":
                    resultLabel.setText("O");
                    break;
                default:
                    resultLabel.setText("TIE");
                    break;
            }
        }
    }
}
