# Tic Tac Toe

This project was developed as an assignment for my Software Engineering class at my university. This was a solo project, and all source code used in the project is either supplied by my university or written by myself.

## Introduction
The idea of this assignment/project was to create a simple game of Tic-Tac-Toe, complete with a Graphical User Interface (GUI). The program would allow a maximum of two human users to play against eachother, and the human users would have the ability to choose the size of the board. A winner is found when all spaces within the same row, column, or diagonal are filled with the same letter (X or O), otherwise there is a tie.

## Documentation
The entirety of the source code for the project is written in Java. All of the classes used in the project are documented as follows:
  * ### __TicTacToe.java__
   		
     This is the driver class for the project, where the main game-loop is located and all of the class methods are exercised. The class has a single member variable ```DEFAULT_WIDTH = 3``` to hold the default board size as an _integer_. In the event that the user wants to use a different board size, the following lines of code are included in the ```main``` method to allow them to pass a board size as an _Integer_ parameter to the method to be used for the board size.
     ```
    int width = DEFAULT_WIDTH;
    
    if(args.length >= 1) {

            try {
                width = Integer.parseInt(args[0]);
            }
            catch(NumberFormatException e) {}

     }
      ```
