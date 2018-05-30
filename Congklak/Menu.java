import java.util.Scanner;
import java.util.Random;

public class Menu {

	private String player1Name;
	private String player2Name;
	private Player player1 = new Player();
	private Player player2 = new Player();
	private BoardCongklak congklak = new BoardCongklak(player1, player2);
	private Scanner input = new Scanner(System.in);
	private int selectedMenu;

	public void setPlayer1Name(String player1Name) {
		this.player1Name = player1Name;
	}
	public void setPlayer2Name(String player2Name) {
		this.player2Name = player2Name;
	}
	public String getPlayer1Name() {
		return player1Name;
	}
	public String getPlayer2Name() {
		return player2Name;
	}
	public Player getWinner() {
		if (player1.getOwned() > player2.getOwned()) {
			return player1;
		}
		return player2;
	}
	public boolean checkFinish() {
		if (congklak.getTotalVillage("a") == 0 || congklak.getTotalVillage("b") == 0) {
			return true; // masih error
		}
		return false;
	}

	public void showMainMenu() {
		System.out.println("===========================================================");
		System.out.println(" 			Main Menu");
		System.out.println("1. Play ");
		System.out.println("2. Rule of Game");
		System.out.println("0. exit");
		System.out.print("Select menu = ");
	}

	public void showPlayerForm () {
		System.out.println("===========================================================");
		System.out.print("Player 1 name = ");
		player1Name = input.next();
		player1.setPlayerName(player1Name);

		System.out.print("Player 2 name = ");
		player2Name = input.next();
		player2.setPlayerName(player2Name);

	}
	public void showCloseMessage() {
		System.out.println("===========================================================");
		System.out.println(" 		Bye");
		System.out.println("===========================================================");
	}

	public void setSelectedMenu() {
		//add try catch
		boolean is_correct = false;
		do {
			String num = input.next();
			try {
				selectedMenu = Integer.valueOf(num);
				if (selectedMenu<3 && selectedMenu>=0) {
					is_correct = true;
				}
				else {
					is_correct = false;
					System.out.print("Input isn't correct, please try again : ");
				}
			}
			catch (Exception e) {
				System.out.print("Input isn't correct, please try again : ");
				is_correct = false;
			}
		} while (!is_correct);
	}
	public int getSelectedMenu() {
		return selectedMenu;
	}

	public int setVillageIndex() {
		//add try catch
		int result = 0;
		boolean is_correct = false;
		do {
			String num = input.next();
			try {
				result = Integer.valueOf(num);
				if (result<0 || result>=6) {
					is_correct = false;
					System.out.print("Error out of bounds, please try again : ");
				}else {
					is_correct = true;
				}
			}
			catch (Exception e) {
				is_correct = false;
				System.out.print("Input isn't correct, please try again : ");
			}
		} while (!is_correct);
		return result;
	}

	public void showRuleOfGame() {
		System.out.println(" 			Rule Of Game");
		System.out.println("Congklak is a traditional game in Indonesia, this is played by two players");
		System.out.println("Actually it has many rule, but in this game we just use a rule that explain below");
		System.out.println("The rules:");
		System.out.println(" 1. Game start by a player that get first turn, in this application it will randomly");
		System.out.println(" 2. Player select which location to start by input index of location (village),\n index of location explain on the last paragraph");
		System.out.println(" 3. bla..bla..bla\n\n");
		System.out.println("index of location(village)");
		System.out.printf("       +====+====+====+====+====+====+\n");
		System.out.printf("       | a0 | a1 | a2 | a3 | a4 | a5 |\n");
		System.out.printf("#------#====+====+====+====+====+====@------@\n");
		System.out.printf("|  bS  |                             |  aS  |\n");
		System.out.printf("#------#====+====+====+====+====+====@------@\n");
		System.out.printf("       | b5 | b4 | b3 | b2 | b1 | b0 |\n");
		System.out.printf("       +====+====+====+====+====+====+\n");
		System.out.println("explanation :");
		System.out.println("	\"a\" mean player1 village, and then number afterwards is index of location(village) ");
		System.out.println("	\"b\" mean player2 village, and then number afterwards is index of location(village) ");
		System.out.println("	\"aS\" mean player1 score");
		System.out.println("	\"bS\" mean player2 score");

	}

	public int setFirstTurn() {
		int result;
		Random rand = new Random();;
		result = rand.nextInt(2) + 1;
		return result;
	}

	public boolean is_playAgain() {
		boolean notCorrect = false;
		boolean result = false;
		do {
			System.out.print("Play again [y/n] ? = ");
			String answer = input.next();
			answer = answer.toUpperCase();
			switch (answer) {
			case "Y":
				notCorrect = true;
				result = true;
				return true;
			case "N":
				result = false;
				return false;
			default:
				notCorrect = true;
			}
		} while (notCorrect);
		return result;
	}

	public boolean is_backToMenu() {
		boolean notCorrect = false;
		boolean result = false;
		do {
			System.out.print("Back to main menu [y/n] ? = ");
			String answer = input.next();
			answer = answer.toUpperCase();
			//System.out.println(answer);
			switch (answer) {
			case "Y":
				notCorrect = true;
				result = true;
				return true;
			case "N":
				result = false;
				return false;
			default:
				notCorrect = true;
			}
		} while (notCorrect);
		return result;
	}

	public void play() {
		int turn = setFirstTurn();
		boolean finish = false;
		do {
			if (turn == 1) {
				System.out.println("===========================================================\n");
				System.out.println(player1.getPlayerName() +", your turn!");
				congklak.showBoard();
				System.out.print("Input village index = ");
				int index = setVillageIndex();
				System.out.println();  //biar rapi
				congklak.setPlayer1Pointer(index);
				congklak.playPlayer1();
				if (checkFinish()) {
					System.out.println("game FINISH");
					break;

				}
				if (congklak.getPlayer1PointerCode().equals("s")) {
					System.out.println("===========================================================\n");
					System.out.println(player1.getPlayerName() +", your turn again!");
					congklak.showBoard();
					System.out.print("Input village index again = ");
					index = setVillageIndex();
					System.out.println();  //biar rapi
					congklak.setPlayer1Pointer(index);
					congklak.playPlayer1();
					if (checkFinish()) {
						System.out.println("game FINISH");
						break;

					}
				}
			}
			turn = 2;
			if (turn == 2) {
				System.out.println("===========================================================\n");
				System.out.println(player2.getPlayerName() +", your trun!");
				congklak.showBoard();
				System.out.print("Input village index = ");
				int index = setVillageIndex();
				System.out.println( );  //biar rapi
				congklak.setPlayer2Pointer(index);
				congklak.playPlayer2();
				if (checkFinish()) {
					System.out.println("game FINISH");
					break;

				}
				if (congklak.getPlayer2PointerCode().equals("s")) {
					System.out.println("===========================================================\n");
					System.out.println(player2.getPlayerName() +", your turn again!");
					congklak.showBoard();
					System.out.print("Input village index again = ");
					index = setVillageIndex();
					System.out.println();  //biar rapi
					congklak.setPlayer2Pointer(index);
					congklak.playPlayer2();
					if (checkFinish()) {
						System.out.println("game FINISH");
						break;
					}
				}
			}
			turn = 1;
		} while (!finish);
		Player winner = getWinner();
		System.out.println(winner.getPlayerName()+ " menang dengan skor "+ winner.getOwned());
	}

}
