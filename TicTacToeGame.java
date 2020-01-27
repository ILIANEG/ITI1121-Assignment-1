/**
* The class <b>TicTacToeGame</b> is the
* class that implements the Tic Tac Toe Game.
* It contains the grid and tracks its progress.
* It automatically maintain the current state of
* the game as players are making moves.
*
* @author Guy-Vincent Jourdan, University of Ottawa
*/
public class TicTacToeGame {
	/**
	*The access to following instance variables should be changed, 
	*appropriate instance variables should become "private"
	*/
	
	/**
	*The board of the game, stored as a one dimension array.
	*/
	public CellValue[] board;
	/**
	*level records the number of rounds that have been
	*played so far. 
	*/
	public int level;
	/**
	* gameState records the current state of the game.
	*/
	public GameState gameState;
	/**
	* lines is the number of lines in the grid
	*/
	public int lines;
	/**
	* columns is the number of columns in the grid
	*/
	public int columns;
	/**
	* sizeWin is the number of cell of the same type
	* that must be aligned to win the game
	*/
	public int sizeWin;
	
	/**
	* default constructor, for a game of 3x3, which must
	* align 3 cells
	*/
	
	
	
	
	
	public TicTacToeGame(){
		board = new CellValue[3*3];
		level = 0;
		gameState = GameState.PLAYING;
		lines = 3;
		columns = 3;
		sizeWin = 3;
	}
	
	/**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game. 3 cells must
	* be aligned.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
  	*/
	public TicTacToeGame(int lines, int columns){
		board = new CellValue[lines * columns];
		level = 0;
		gameState = GameState.PLAYING;
		this.lines = lines;
		this.columns = columns;
		sizeWin = 3;
	}
	
	/**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game, as well as
	* the number of cells that must be aligned to win.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
    * @param sizeWin
    *  the number of cells that must be aligned to win.
  	*/
	public TicTacToeGame(int lines, int columns, int sizeWin){
		board = new CellValue[lines * columns];
		level = 0;
		gameState = GameState.PLAYING;
		this.lines = lines;
		this.columns = columns;
		this.sizeWin = sizeWin;
	}
	
	/**
	* getter for the variable lines
	* @return
	* the value of lines
	*/
	public int getLines(){
		return lines
	}

   	/**
	* getter for the variable columns
	* @return
	* the value of columns
	*/
	public int getColumns(){
		return columns;
	}

   	/**
	* getter for the variable level
	* @return
	* 	the value of level
	*/
	public int getLevel(){
		return level;

	}

  	/**
	* getter for the variable sizeWin
	* @return
	* 	the value of sizeWin
	*/
	public int getSizeWin(){
		return sizeWin;
	}

   	/**
	* getter for the variable gameState
	* @return
	* 	the value of gameState
	*/
	public GameState getGameState(){
		return gameState;
	}

   	/**
	* returns the cellValue that is expected next,
	* in other word, which played (X or O) should
	* play next.
	* This method does not modify the state of the
	* game.
	* @return
    * the value of the enum CellValue corresponding
   	* to the next expected value.
  	*/
	public CellValue nextCellValue(){
		if(level % 2 == 0){
			cellValue val = new cellValue.X;
			return val;
		}
		else{
			cellValue val = new cellValue.O;
			return val;
		}
	}

   	/**
	* returns the value  of the cell at
	* index i.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
   	* @param i
    * the index of the cell in the array board
    * @return
    *  the value at index i in the variable board.
  	*/
	public CellValue valueAt(int i) {
		if(i < board.length){
			return board[i];
		}
		else{
			System.out.println("Error message: index out of range");
		}
	}

   	/**
	* This method is called when the next move has been
	* decided by the next player. It receives the index
	* of the cell to play as parameter.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
	* If the chosen cell is not empty, an error message is
	* printed out. The behaviour is then unspecified
	* If the move is valide, the board is updated, as well
	* as the state of the game.
	* To faciliate testing, is is acceptable to keep playing
	* after a game is already won. If that is the case, the
	* a message should be printed out and the move recorded.
	* the  winner of the game is the player who won first
   	* @par
    * the index of the cell in the array board that has been
    * selected by the next player
  	*/
	public void play(int i) {
		if(i < board.length && board[i] == gameState.EMPTY && gameState != gameState.XWIN && gameState != gameState.OWIN){
			switch(nextCellValue()){
				case X:
					board[i] = CellValue.X;
				case O:
					board[i] = CellValue.O;
			}
		}
		else if(i >= board.length){
			System.out.println("Error message: index out of range")
		}
		else if(board[i] != cellValue.EMPTY){
			System.out.println("Error message: non-empty cell under specified index, choose another cell");
		}
		else if(gameState == gameState.XWIN || gameState == gameState.OWIN){
			switch(gameState){
				case XWIN:
					board[i] = CellValue.X
					System.out.println("Player X has won the game, however this turn has been recorded")
				case OWIN:
					board[i] = CellValue.O
					System.out.println("Player O has won the game, however this turn has been recorded")
			}
		}
	}


   	/**
	* A helper method which updates the gameState variable
	* correctly after the cell at index i was just set in
	* the method play(int i)
	* The method assumes that prior to setting the cell
	* at index i, the gameState variable was correctly set.
	* it also assumes that it is only called if the game was
	* not already finished when the cell at index i was played
	* (i.e. the game was playing). Therefore, it only needs to
	* check if playing at index i has concluded the game, and if
	* set the oucome correctly
	*
   	* @param i
    	*  the index of the cell in the array board that has just
    	* been set
  	*/


	private void setGameState(int i)
	{
		
	}



   /**
	* Returns a String representation of the game matching
	* the example provided in the assignment's description
	*
   	* @return
    *  String representation of the game
  	*/

	
	public String toString()
	{// This will print the whole board (and message at end). It must be run once every round
		String message, intermediate = "", boardgraphic = "", row = "";
		char[] array = new char[columns * lines];

		//Determine length of intermediate line:
		for (int i = 0; i < (4 * columns - 1); i++) {intermediate += "-";}

		//Determine message
		if (level % 2 == 1) {message = "O to play: ";}
		else {message = "X to play: ";}

		//Populate array
		for (int i = 0; i < columns * lines; i++)
		{
			if (board[i] == X) {array[i] = ' ';}
			else if (board[i] == O) {array[i] = 'O';}
			else {array[i] = ' ';}
		}

		//Draw board:
		for (int x = 0; x < lines; x++)
		{
			row = " " + array[x * columns];
			for (int y = 1; y < columns; y++)
			{
				row += " | " + array[x * columns + y];
			}
			if (x != lines - 1) {boardgraphic += row + "\n" + intermediate + "\n";}
			else {boardgraphic += row + "\n";}
		}

		return boardgraphic + "\n" + message;
	}
}
