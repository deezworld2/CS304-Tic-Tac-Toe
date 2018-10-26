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
     * ```public enum Mark``` holds an enumeration of strings to serve as game-pieces. It holds three different enumerations: ```X("X")```, ```O("O")```, and ```EMPTY("")```.
     * ```public enum Result``` holds an ```enum``` of strings to be used when printing the final game result. ```Result``` has four enumerations: ```X("X)```, ```O("O")```, ```TIE("Tie")```, and ```NONE("None")```.
     #### Methods
     * ```public TicTacToeModel(int width)``` is a constructor that accepts an integer value to load into the ```width``` member variable. The constructor also sets ```xTurn``` to ```true```, meaning that "X" will always go first. The constructor also initializes the gameboard ```grid``` to be an array of ```Mark``` enumerations of size ```[width][width]```. The constructor then fills each cell of the gameboard with ```EMPTY``` ```Mark```'s to simulate an empty gameboard.
     * ```public boolean isGridBlank()``` scans the board for cells with game-pieces. If no game-pieces are found, then the board is empty and method returns ```true```.
     * ```public boolean makeMark(int row, int col)``` checks to see if it is X's turn; if ```true```, then the method checks to see if the cell at ```grid[row][col]``` is a valid move (exists in the array and is not occupied by an 'X' or an 'O') using ```isValidSquare()``` and ```isSquareMarked()```. If the move is valid, the method will place either an 'X' or an 'O' in the cell depending on the value of ```xTurn```. The method will return ```true``` if it was able to successfully place a mark in ```grid[row][col]``` and ```false``` otherwise.
     * ```public boolean isValidSquare(int row, int col)``` checks to see if the cell ```grid[row][col]``` exists in the gameboard ```grid[width][width]```. If it does, it returns ```true```, otherwise it returns false.
     * ```public boolean isSquareMarked(int row, int col)``` checks to see if the cell ```grid[row][col]``` contains any game-pieces. If it does, it will return ```true```, otherwise it will return false.
     * ```public Mark getMark(int row, int col)``` returns the game-piece at ```grid[row][col]```.
     * ```public Result getResult()``` returns the final result of the game. Uses ```isMarkWin()``` and ```isTie()``` to determine the result, and then returns the respective ```Result```.
     * ```public boolean isMarkWin(Mark mark)``` scans the board to see if ```mark``` won the game. It holds a ```boolean``` ```xWon```, set to ```true``` at the start, to hold the result of the method. The method has two sets of nested ```for``` loops to scan the board row-by-row and column-by-column for cells that contain ```mark```. If a value that is not ```mark``` is found during a check, ```xWon``` is set to false, meaning that ```mark``` did not win the game. There is an additional ```for``` loop that indexes the gameboard using ```(width - 1) - x``` where `x` is the loop variable in order to scan diagonals for ```mark```. The failing condition of the check is the same as the other two loops in the method. The method returns the value of ```xWon```.
     * ```private boolean isTie()``` checks the gameboard for a tie. First, it checks to see if the board is empty. If the board is empty, it will return ```false```. If the board is not empty, it will check to see if either player won using a pair of ```isMarkWin()``` methods; if either method returns ```true```, ```isTie()``` will return ```false```, otherwise it will return ```true```.
     * ```public boolean isGameover()``` checks to see if the game is over by checking to see if the value of ```getResult()``` is ```Result.NONE```. If the two values are equal, it will return ```true```. Otherwise, it will return ```false```.
     * ```public boolean isXTurn()``` checks to see if it is X's turn.
     * ```public int getWidth()``` returns the board size.
  * ### TicTacToeView.java
     The ```TicTacToeView``` class holds all of the coding for the GUI. This class uses assets from the _java.awt_ and the _javax.swing_ libraries. __NOTE:__ The ```TicTacToeView``` class ```extends JPanel``` and ```implements ActionListener```.
     #### Structures and Variables
     * ```private TicTacToeModel model``` holds the game instance to be used in the GUI.
     * ```public JButton[][] grid``` holds an array of buttons to be used as the gameboard.
     * ```public JPanel panel```, ```JLabel resultLabel```, and ```JPanel container``` serve as containers to hold and display different parts of the game.
     #### Methods
     * ```public TicTacToeView(TicTacToeModel model)``` constructor that sets up the layout and composition of content to be displayed in the GUI. It sets the layout of ```panel``` to a ```GridLayout``` of size ```model.getWidth()``` to hold the gameboard. ```resultLabel``` is set to display the game's winner. ```container``` is given a ```BoxLayout``` to serve as the display area for the gameboard ```panel``` and the game's result ```resultLabel```. ```grid``` is set to size ```[model.getWidth()][model.getWidth()]``` to serve as the gameboard. The constructor then uses nested ```for``` loops to go through each cell in ```grid``` and does the following for each: initializes a ```JButton```, adds an ```ActionListener```, sets row-column-square labels, sets the button size, and sets the button's precise location in the panel. The gameboard is then added to its respective display panel using ```panel.add(grid[row][col])```. The rest of the constructor simply sets the size and visibility of ```container``` and adds the gameboard and the game result to ```container``` to be displayed.
     * ```public void showNextMovePrompt()``` prompts users to input their moves on the gameboard.
     * ```public void showInputError()``` gives users an error if they input a value that is less than zero or is not in the accepted range.
     * ```public void showResult(String r)``` outputs the result of the game.
     * ```public void actionPerformed(ActionEvent e)``` contains code for receiveing and handling user input in the GUI. It detects button clicks using ```getSource()``` and retrieves the indexes of the buttons and stores them in two integer variables: ```row``` and ```col```. The method checks to see if the game is over using ```model.isGameOver()```. If the game isnt over, the method makes sure the button pressed corresponds to a valid move on the gameboard (```model.isValidSquare() == true``` and ```model.isSquareMarked() == false```) and if it does, it sets that button to display the gamepiece of the player that pressed the button (determined by ```model.isXTurn()```.
