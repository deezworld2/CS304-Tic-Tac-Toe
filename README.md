# Tic Tac Toe

This project was developed as an assignment for my Software Engineering class at my university. This was a solo project, and all source code used in the project is either supplied by my university or written by myself.

## Introduction
The idea of this assignment/project was to create a simple game of Tic-Tac-Toe, complete with a Graphical User Interface (GUI). The program would allow a maximum of two human users to play against eachother, and the human users would have the ability to choose the size of the board. A winner is found when all spaces within the same row, column, or diagonal are filled with the same letter (X or O), otherwise there is a tie.

## Documentation
The entirety of the source code for the project is written in Java. All of the classes used in the project are documented as follows:
  * ### __TicTacToe.java__
   		
     This is the driver class for the project, where the main game-loop is located and all of the class methods are exercised. The class has a single member variable ```DEFAULT_WIDTH = 3``` to hold the default board size as an _integer_. The following lines of code are included in the ```main``` method to allow users to pass a value as an _Integer_ to the method to be used for the board size.
     ```
    int width = DEFAULT_WIDTH;
    
    if(args.length >= 1) {

            try {
                width = Integer.parseInt(args[0]);
            }
            catch(NumberFormatException e) {}

     }
      ```
    The game and its GUI are created and initialized in the ```main``` method with the following lines of code:
    ```
    TicTacToeModel model = new TicTacToeModel(width); //create instance of the game
    TicTacToeView view = new TicTacToeView(model); //create GUI of game instance
    ```
    The main loop contains simple statements that set the conditions for the view-window of the GUI. The window is created using a ```JFrame``` object imported from the _javax.swing.JFrame_ library. The window size is set to 800 x 800 with the condition to exit when it is closed.
    ```
    JFrame frame = new JFrame("Tic-Tac-Toe");

    frame.setSize(800,800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ```
     The window ```frame``` is set to hold the GUI ```view``` using the ```JFrame```'s ```add()``` method.
     ```
     frame.getContentPane().add(view.container);
     frame.pack();
     frame.setVisible(true);
     ```
  
  * ### TicTacToeModel.java
     The ```TicTacToeModel``` class holds all of the base game code, including code for move-handling (checking if moves are valid and inputting the moves on the board), turn-tracking (keeping track of which player's turn it is), and win-checking. This class is the foundation of the project, and all code for the GUI is based on the code in this class.
     #### Structures and Variables
     * ```public Mark[][] grid;``` holds the contents of the game board
     * ```private boolean xTurn;``` keeps track of which player's turn it is
     * ```private int width;``` holds the board size
