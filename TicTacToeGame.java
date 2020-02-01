/**
* The class <b>TicTacToeGame</b> is the
* class that implements the Tic Tac Toe Game.
* It contains the grid and tracks its progress.
* It automatically maintain the current state of
* the game as players are making moves.
*
* @author Guy-Vincent Jourdan, University of Ottawa
*/
import java.util.Arrays;
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
		if(i < board.length && board[i] == CellValue.EMPTY && gameState == GameState.PLAYING && i > -1){
			if(nextCellValue() == CellValue.X){
				board[i] = CellValue.X;
				setGameState(i);
				level++;
			}
			else if(nextCellValue() == CellValue.O){
					board[i] = CellValue.O;
					setGameState(i);
					level++;
			}
		}
		else if(i >= board.length || i < 0){
			System.out.println("Error message: index out of range");
		}
		else if(board[i] != CellValue.EMPTY){
			System.out.println("Error message: non-empty cell under specified index, choose another cell");
		}
		else if(gameState == GameState.XWIN || gameState == GameState.OWIN){
			if(nextCellValue() == CellValue.X){
				board[i] = CellValue.X;
				System.out.println("Player X won the game, however the play was recorded");
				level++;
			}
			else if(nextCellValue() == CellValue.O){
				board[i] = CellValue.O;
				System.out.println("Player X won the game, however the play was recorded");
				level++;
			}
		}
		else if(gameState == GameState.DRAWN){
			System.out.println("Game drawn");
		}
//		System.out.println(Arrays.toString(board));
	System.out.println(gameState.toString());
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
		int columnIndex = (i) % columns;
		int rowIndex = (i) / columns * columns;
		int[] diagonalLeftIndex = {i / columns, i % columns};
		int[] diagonalRightIndex = {i / columns, i % columns};

		diagonalLeftIndex = findOrigin(diagonalLeftIndex, 'l');
		diagonalRightIndex = findOrigin(diagonalRightIndex, 'r');

		// Initialising column diagonal and line array
		CellValue[] vertical = new CellValue[lines];
		CellValue[] leftDiagonal = new CellValue[lines];
		CellValue[] rightDiagonal = new CellValue[lines];
		CellValue[] horizontal = new CellValue[columns];
		int indexCount = 0;
		//loop will populate array column, which will recieve column where i located
		for(int a = columnIndex; a < board.length; a = a + columns){
			vertical[indexCount] = board[a];
			indexCount++;
		}
		indexCount = 0;
		//loop will populate array line, which will recieve line in which i located
		for(int a = rowIndex; a < rowIndex + 3; a++){
			horizontal[indexCount] = board[a];
			indexCount++;
		}

		//Populate array of diagonal to the left if it was found
		indexCount = 0;
		int row = diagonalLeftIndex[0];
		int col = diagonalLeftIndex[1];
		while(row < lines && col < columns )
		{
			leftDiagonal[indexCount] = board[row * columns + col];
			row++;
			col++;
			indexCount++;
		}

		//Populate array of diagonal to the right if it was found
		indexCount = 0;
		row = diagonalRightIndex[0];
		col = diagonalRightIndex[1];
		while(row < lines && 0 <= col)
		{
			rightDiagonal[indexCount] = board[row * columns + col];
			row++;
			col--;
			indexCount++;
		}

		System.out.println(Arrays.toString(leftDiagonal));
		System.out.println(Arrays.toString(rightDiagonal));

		CellValue[][] toCheck = {vertical, horizontal, leftDiagonal, rightDiagonal};
		//Loop checks for winner if any
		for(int a = 0; a < toCheck.length && gameState == GameState.PLAYING; a++){
			checkWin(toCheck[a]);
		}
		//Loop checks if the game was DRAWN
		if(gameState == GameState.PLAYING){
			boolean drawn = true;
			int index = 0;
			while(drawn && index < board.length){
				if(board[index] == CellValue.EMPTY){
					drawn = false;
				}
				index++;
			}
			if(drawn){
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
	//Function checks if there is a winner in array of CellValues
	private void checkWin (CellValue[] in)
	{
		int winCount = 0;
		for(int i = 0; i < in.length - 1; i++)
		{
			if(in[i] == in[i + 1] && in[i] != CellValue.EMPTY && in[i] != null)
			{
				winCount++;
			}
			if(winCount == sizeWin - 1)
			{
				if(in[i] == CellValue.X)
				{
					gameState = GameState.XWIN;
				}
				else
				{
					gameState = GameState.OWIN;
				}
			}
		}
	}
	//function finds origin of diagonal or where the first point of diagonal is
	private int[] findOrigin(int[] rc, char lr)
	{
		if(lr == 'l')
		{
			int[] lrc = rc;
			while(true)
			{
				if((lrc[0] == 0 && lrc[1] == columns - 1) && (lrc[0] == lines - 1 && lrc[1] == 0))
				{
					lrc[0] = 0;
					lrc[1] = 0;
					return lrc;
				}
				else if(lrc[0] == 0 || lrc[1] == 0)
				{
					return lrc;
				}
				else
				{
					lrc[0] = rc[0] - 1;
					lrc[1] = rc[1] - 1;
				}
			}
		}
		if(lr == 'r')
		{
			int[] rrc = rc;
			while(true)
			{
				if((rrc[0] == 0 && rrc[1] == 0) || (rrc[0] == lines - 1 && rrc[1] == columns - 1))
				{
					rrc[0] = 0;
					rrc[1] = columns - 1;
					return rrc;
				}
				else if(rrc[0] == 0 || rrc[1] == columns - 1)
				{
					return rrc;
				}
				else
				{
					rrc[0] = rrc[0] - 1;
					rrc[1] = rrc[1] + 1;
				}
			}
		}
		return rc;
	}
}
