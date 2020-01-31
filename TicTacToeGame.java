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
		board = new CellValue[3 * 3];
		for(int a = 0; a < board.length; a++){
			board[a] = CellValue.EMPTY;
		}
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
		for(int a = 0; a < board.length; a++){
			board[a] = CellValue.EMPTY;
		}
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
		for(int a = 0; a < board.length; a++){
			board[a] = CellValue.EMPTY;
		}
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
		return lines;
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
			CellValue val = CellValue.X;
			return val;
		}
		else{
			CellValue val = CellValue.O;
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
		return CellValue.EMPTY;
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
		i--;
		if(i < board.length && board[i] == CellValue.EMPTY && gameState == GameState.PLAYING){
			System.out.println(nextCellValue().name());
			if(nextCellValue() == CellValue.X){
				board[i] = CellValue.X;
				setGameState(i);
			}
			if(nextCellValue() == CellValue.O){
					board[i] = CellValue.O;
					setGameState(i);
			}
		}
		else if(i >= board.length){
			System.out.println("Error message: index out of range");
		}
		else if(board[i] != CellValue.EMPTY){
			System.out.println("Error message: non-empty cell under specified index, choose another cell");
		}
		else if(gameState == GameState.XWIN || gameState == GameState.OWIN){
			if(nextCellValue() == CellValue.X){
				board[i] = CellValue.X;
				System.out.println("Player X has won the game, however this turn has been recorded");
			}
			if(nextCellValue() == CellValue.O){
				board[i] = CellValue.O;
				System.out.println("Player O has won the game, however this turn has been recorded");
			}
		}else{
			System.out.println("Invalid input");
		}
		level++;
		System.out.println(gameState.name());
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

	private void setGameState(int i){
		i--;
		int columnIndex = (i) % columns;
		int rowIndex = (i) / columns * columns;
		boolean leftDiagFound = false;
		boolean rightDiagFound = false;
		int diagLeftIndex = i;
		int diagRightIndex = i;
		while(! leftDiagFound){
			if(diagLeftIndex - columns - 1 >= diagLeftIndex / columns * columns - columns){
				diagLeftIndex = diagLeftIndex - columns - 1;
			}else{
				leftDiagFound = false;
			}
		}
		while(! rightDiagFound){
			if(diagRightIndex - columns + 1 < diagRightIndex / columns * columns){
				diagRightIndex = diagRightIndex - columns + 1;
			}else{
				leftDiagFound = false;
			}
		}
		// Initialising column diagonal and line array
		CellValue[] column = new CellValue[lines];
		CellValue[] leftDiagonal = new CellValue[lines];
		CellValue[] rightDiagonal = new CellValue[lines];
		CellValue[] line = new CellValue[columns];
		int indexCount = 0;
		//loop will populate array column, which will recieve column where i located
		for(int a = columnIndex; a < board.length; a = a + columns){
			column[indexCount] = board[a];
			indexCount++;
		}
		indexCount = 0;
		//loop will populate array line, which will recieve line in which i located
		for(int a = rowIndex; a < rowIndex + 4; a++){
			line[indexCount] = board[a];
			indexCount++;
		}
		indexCount = 0;
		//loop will populate Left diagonal
		for(int a = diagLeftIndex; a < lines * columns; a = a + columns + 1){
			leftDiagonal[indexCount] = board[a];
			indexCount++;
		}
		indexCount = 0;
		//loop will populate Right leftDiagonal
		for(int a = diagLeftIndex; a < lines * columns; a = a + columns - 1){
			rightDiagonal[indexCount] = board[a];
			indexCount++;
		}
		CellValue[][] toCheck = {column, line, leftDiagonal, rightDiagonal};
		//Loop checks for winner if any
		for(int a = 0; a < toCheck.length && gameState == GameState.PLAYING; a++){
			checkWin(toCheck[a]);
		}
		//Loop checks if the game was DRAWN
		if(gameState == GameState.PLAYING){
			boolean drawn = true;
			int index = 0;
			while(! empty){
				if(board[index] == CellValue.EMPTY){
					empty = false;
				}
				index++;
			}
			if(! drawn){
				gameState = GameState.DRAWN;
			}
		}
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
			if (board[i] == CellValue.X) {array[i] = 'X';}
			else if (board[i] == CellValue.O) {array[i] = 'O';}
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

	private void checkWin (CellValue[] in)
	{
		int X = 0;
		int O = 0;
		for (int i = 0; i < in.length - 1; i++)
		{
			//Check if X won:
			if (in[i] == CellValue.X && in[i] == in[i++])
			{
				X++;
				if (X >= sizeWin)
				{
					gameState = GameState.XWIN;
					System.out.println("X wins");
					return;
				}
			}
			else {X = 0;}

			//Check if O won:
			if (in[i] == CellValue.O && in[i] == in[i++])
			{
				O++;
				if (O >= sizeWin) {gameState = GameState.OWIN;
				System.out.println("X wins");}
			}
			else {O = 0;}
		}
		gameState = GameState.PLAYING;
		return;
	}
}
