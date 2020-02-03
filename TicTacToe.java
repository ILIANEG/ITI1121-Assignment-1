
import java.io.Console;

public class TicTacToe
{
	public static void main(String[] args)
	{
		StudentInfo.display();
		Console console = System.console();
		TicTacToeGame game;
		int lines, columns, win;
		lines = 3;
		columns = 3;
		win = 3;

		//Test preconditions:
		if (args.length >= 2)
		{
			lines = Integer.parseInt(args[0]);
			if (lines < 2)
			{
				System.out.println("Invalid argument, using default...");
				lines = 3;
			}
			columns = Integer.parseInt(args[1]);
			if (columns < 2)
			{
				System.out.println("Invalid argument, using default...");
				columns = 3;
			}
		}
		if (args.length >= 3)
		{
			win = Integer.parseInt(args[2]);
			if (win < 2)
			{
				System.out.println("Invalid argument, using default...");
				win = 3;
			}
		}
		if (args.length > 3) {System.out.println("Too many arguments. Only the first 3 are used.");}

		//Initialize game:
		game = new TicTacToeGame(lines, columns, win);
		//Game will contiunue until the game will not be drawn
		while (game.getGameState() == GameState.PLAYING)
		{
			System.out.print(game.toString());
			console = System.console();
			String input = console.readLine();
			//offsetting the input;
			int i = Integer.parseInt(input) - 1;
			game.play(i);
		}
		System.out.println(game.toString());
	}
}
