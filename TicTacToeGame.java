//@author Guy-Vincent Jourdan, University of Ottawa

import java.util.Arrays;
public class TicTacToeGame
{
	private CellValue[] board;//Stores the game board
	private int level;//Stores the number of rounds played
	private GameState gameState;//Stores the current gamestate
	private int lines;//Stores the number of rows
	private int columns;//Stores the number of columns
	private int sizeWin;//Stores the amount of matching values required to win

	//Default constructor:
	public TicTacToeGame()
	{
		board = new CellValue[3*3];
		for (int a = 0; a < board.length; a++) {board[a] = CellValue.EMPTY;}
		level = 0;
		gameState = GameState.PLAYING;
		lines = 3;
		columns = 3;
		sizeWin = 3;
	}

	//Constructor specifying the size of the board:
	public TicTacToeGame(int lines, int columns)
	{
		board = new CellValue[lines * columns];
		for(int a = 0; a < board.length; a++) {board[a] = CellValue.EMPTY;}
		level = 0;
		gameState = GameState.PLAYING;
		this.lines = lines;
		this.columns = columns;
		sizeWin = 3;
	}

	//Constructor specifying the board size and sizeWin:
	public TicTacToeGame(int lines, int columns, int sizeWin)
	{
		board = new CellValue[lines * columns];
		for (int a = 0; a < board.length; a++) {board[a] = CellValue.EMPTY;}
		level = 0;
		gameState = GameState.PLAYING;
		this.lines = lines;
		this.columns = columns;
		this.sizeWin = sizeWin;
	}

	public int getLines() {return lines;}
	public int getColumns() {return columns;}
	public int getLevel() {return level;}
	public int getSizeWin() {return sizeWin;}
	public GameState getGameState() {return gameState;}

	//Determine next player:
	public CellValue nextCellValue()
	{
		if (level % 2 == 0) {CellValue val = CellValue.X; return val;}
		else {CellValue val = CellValue.O; return val;}
	}

	//Return value of cell at index:
	public CellValue valueAt(int i)
	{
		if(i < board.length) {return board[i];}
		else {System.out.println("Error message: index out of range");}
		return CellValue.EMPTY;
	}

	//Handles each move:
	public void play(int i)
	{
		if (i >= board.length || i <= -1) {System.out.println("Error message: index out of range");}
		else if (i < board.length && board[i] == CellValue.EMPTY && gameState == GameState.PLAYING)
		{
			if (nextCellValue() == CellValue.X)
			{
				board[i] = CellValue.X;
				setGameState(i);
				level++;
			}
			else if (nextCellValue() == CellValue.O)
			{
					board[i] = CellValue.O;
					setGameState(i);
					level++;
			}
		}
		else if (board[i] != CellValue.EMPTY) {System.out.println("Error message: non-empty cell under specified index, choose another cell");}
		else if (gameState == GameState.XWIN || gameState == GameState.OWIN)
		{
			if (nextCellValue() == CellValue.X)
			{
				board[i] = CellValue.X;
				System.out.println("Player X won the game, however the play() was recorded");
				level++;
			}
			else if (nextCellValue() == CellValue.O)
			{
				board[i] = CellValue.O;
				System.out.println("Player X won the game, however the play() was recorded");
				level++;
			}
		}
		else if (gameState == GameState.DRAW) {System.out.println("Game drawn");}
	}


	//Checks if the game had been concluded:
	private void setGameState(int i)
	{
		int columnIndex = (i) % columns;
		int rowIndex = (i) / columns * columns;
		int[] diagonalLeftIndex = {i / columns, i % columns};
		int[] diagonalRightIndex = {i / columns, i % columns};

		diagonalLeftIndex = findOrigin(diagonalLeftIndex, 'l');
		diagonalRightIndex = findOrigin(diagonalRightIndex, 'r');
		int[] columnElement = {0,columnIndex};
		int[] rowElement = {i/columns, 0};

		//Initialize internal arrays:
		CellValue[] vertical = new CellValue[lines];
		CellValue[] leftDiagonal = new CellValue[lines];
		CellValue[] rightDiagonal = new CellValue[lines];
		CellValue[] horizontal = new CellValue[columns];

		//Populate vertical[] at index:
		int indexCount = 0;
		int row = columnElement[0];
		int col = columnElement[1];
		while (row < lines)
		{
			vertical[indexCount] = board[row * columns + col];
			indexCount++;
			row++;
		}

		//Populate horizontal[] at index:
		indexCount = 0;
		row = rowElement[0];
		col = rowElement[1];
		while (col < columns)
		{
			horizontal[indexCount] = board[row * columns + col];
			indexCount++;
			col++;
		}

		//Populate array of diagonal to the left:
		indexCount = 0;
		row = diagonalLeftIndex[0];
		col = diagonalLeftIndex[1];
		while(row < lines && col < columns )
		{
			leftDiagonal[indexCount] = board[row * columns + col];
			row++;
			col++;
			indexCount++;
		}

		//Populate array of diagonal to the right:
		indexCount = 0;
		row = diagonalRightIndex[0];
		col = diagonalRightIndex[1];
		while(row < lines && 0 <= columns)
		{
			rightDiagonal[indexCount] = board[row * columns + col];
			row++;
			col--;
			indexCount++;
		}
		CellValue[][] toCheck = {vertical, horizontal, leftDiagonal, rightDiagonal};

		//Check for winner:
		for (int a = 0; a < toCheck.length && gameState == GameState.PLAYING; a++)
		{
			checkWin(toCheck[a]);
		}

		//Check if the board was displayed:
		if(gameState == GameState.PLAYING)
		{
			boolean drawn = true;
			int index = 0;
			while(drawn && index < board.length)
			{
				if(board[index] == CellValue.EMPTY) {drawn = false;}
				index++;
			}
			if (drawn) {gameState = GameState.DRAW;}
		}
	}

	//This will print the whole board (and message at end):
	public String toString()
	{
		String message, intermediate = "", boardgraphic = "", row = "";
		char[] array = new char[columns * lines];

		//Determine length of intermediate line:
		for (int i = 0; i < (4 * columns - 1); i++) {intermediate += "-";}

		//Determine message:
		if (gameState == GameState.XWIN) {message = "Result: XWIN";}
		else if (gameState == GameState.OWIN) {message = "Result: OWIN";}
		else if (level % 2 == 1) {message = "O to play: ";}
		else {message = "X to play: ";}

		//Populate array:
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
			for (int y = 1; y < columns; y++) {row += " | " + array[x * columns + y];}
			if (x != lines - 1) {boardgraphic += row + "\n" + intermediate + "\n";}
			else {boardgraphic += row + "\n";}
		}
		return boardgraphic + "\n" + message;
	}

	//Algorithm to check for winner:
	private void checkWin (CellValue[] in)
	{
		int winCount = 0;
		for(int i = 0; i < in.length - 1; i++)
		{
			if(in[i] == in[i + 1] && in[i] != CellValue.EMPTY && in[i] != null) {winCount++;}
			else {winCount = 0;}

			if(winCount == sizeWin - 1)
			{
				if(in[i] == CellValue.X) {gameState = GameState.XWIN;}
				else {gameState = GameState.OWIN;}
			}
		}
	}

	//Locate origin of diagonal:
	private int[] findOrigin(int[] rc, char lr)
	{
		if (lr == 'l')
		{
			int[] lrc = rc;
			while (true)
			{
				if ((lrc[0] == 0 && lrc[1] == columns - 1) || (lrc[0] == lines - 1 && lrc[1] == 0))
				{
					lrc[0] = 0;
					lrc[1] = 0;
					return lrc;
				}
				else if (lrc[0] == 0 || lrc[1] == 0) {return lrc;}
				else
				{
					lrc[0] = rc[0] - 1;
					lrc[1] = rc[1] - 1;
				}
			}
		}
		if (lr == 'r')
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
				else if (rrc[0] == 0 || rrc[1] == columns - 1) {return rrc;}
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
