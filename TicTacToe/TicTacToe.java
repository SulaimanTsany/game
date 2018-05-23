
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
	static byte [][] table= new byte [3][3];
	static boolean [][] emptyTable= new boolean[3][3];
	static byte menuSelect;
	static boolean playAgain=false;
	static boolean exit= false;

	public static void menu(){

		System.out.println();
		System.out.println("__________________________Main Menu__________________________");
		System.out.println("1. Play Game");
		System.out.println("2. Rule of Game");
		System.out.println("3. Exit");
		try{
			Scanner inputUser= new Scanner(System.in);
			System.out.print("Input your choice = ");
			menuSelect= inputUser.nextByte();
			System.out.println("_____________________________________________________________");
		}
		catch (InputMismatchException e){
		}
	}

	public static void playGame(){
		boolean finish=false;
		do{
			resetGame();
			do{
				printBoard();
				playerInput((byte)1);
				if (checkWiner(table)==1){
					printBoard();
					System.out.println("The game Finish");
					System.out.println("=========== Player 1 WIN ===========");
					break;
				}else if(checkDraw()){
					printBoard();
					System.out.println("=========== Game Draw,  No Winner ===========");
					finish=true;
					break;
				}
				printBoard();
				playerInput((byte)2);
				if (checkWiner(table)==2){
					printBoard();
					System.out.println("The game Finish");
					System.out.println("=========== Player 2 WIN ===========");
					break;
				}else if(checkDraw()){
					printBoard();
					System.out.println("=========== Game Draw,  No Winner ===========");
					finish=true;
					break;
				}
				finish= checkWiner(table)==0 ? false: true;
			}while(!finish);
			askToPlayAgain();
		}while(playAgain);
	}

	public static boolean askToPlayAgain(){
		System.out.println("Do you want to play again?");
		boolean askAgain=false;
		do{
			System.out.print("Press \"Y\" to play again, or \"N\" to exit = ");
			Scanner userInput= new Scanner(System.in);
			String input= userInput.next();
			if (input.equalsIgnoreCase("y")){
				playAgain=true;
				askAgain=false;
			}else if (input.equalsIgnoreCase("n")){
				playAgain=false;
				askAgain=false;
			}else{
				System.out.println("# Incorrect input, try input again !");
				askAgain=true;
			}
		}while(askAgain);
		return playAgain;
	}

	public static void backToMainMenu(){
		Scanner inputChoice = new Scanner(System.in);
		String input;
		do{
			System.out.print("Press \"O\" to back to Main Menu, or \"X\" to exit the aplication =");
			input= inputChoice.next();
			if(input.equalsIgnoreCase("O")){
				exit=false;
				break;
			}else if(input.equalsIgnoreCase("x")){
				exit=true;;
				break;
			}else{
				System.out.println("# Incorrect input, try again!");
			}
		}while(!input.equalsIgnoreCase("x")&& !input.equalsIgnoreCase("o"));
	}

	public static void ruleOfGame(){
		System.out.println("___________________Rule of Game___________________");
		System.out.println("- It played by two players");
		System.out.println("- It plays on a board like a table 3X3");
		System.out.println("- The goal of the game is make three signs like a line by horizontal, vertikal, or diagonal");
		System.out.println("- Player's 1 sign by \"X\" , and player's 2 sign by \"O\"");
		System.out.println("- The player just select a row and colom that he wants to fill on his time ");
		System.out.println("- Number of colom and row are start from top-left by 1 to 3. Example of it :");
		System.out.println("  if player 1 select the row= 1 , and the coloms =3 , then the board be like it below");
		System.out.println("\t      |   |   | X | ");
		System.out.println("\t      |   |   |   | ");
		System.out.println("\t      |   |   |   | ");
		System.out.println("- The winner is the first player that make a line by his sign ");
		System.out.println("  if the board like this it means Player 2 wins");
		System.out.println("\t      | X | O | O | ");
		System.out.println("\t      | O | O | X | ");
		System.out.println("\t      | O | X | X | ");
		System.out.println("  Or like this, it means player 2 wins too");
		System.out.println("\t      | X | O | X | ");
		System.out.println("\t      | O | O | O | ");
		System.out.println("\t      | X | X | O | ");
		System.out.println("- The board just can fill once at the game");
		System.out.println("- If all board was filled and no player make a line with his sign, it means no player win or Draw");
		System.out.println("__________________________________________________");
	}

	public static void playerInput(byte player){
		boolean tryInput=false;
		byte row=0, colom=0;
		do{
			System.out.println("==> Player's "+player+" turn");
			try{
				System.out.println("Wich location what you want to fill ? ");
				Scanner inputData= new Scanner(System.in);
				System.out.print("Row    = ");
				row= inputData.nextByte();
				System.out.print("Coloms = ");
				colom= inputData.nextByte();
				if(emptyTable[row-1][colom-1]){
					emptyTable[row-1][colom-1]= false;
					table[row-1][colom-1]= player;
					tryInput=false;
				}else{
					System.out.println("This location have filled, try other location !");
					tryInput=true;
				}
			}
			catch(ArrayIndexOutOfBoundsException ex){
				System.out.println("# The row or colom is invali, try another value that allowed");
				System.out.println("# The row and colom just can fill with 1, 2, or 3 ");
				tryInput=true;
			}
			catch(InputMismatchException e){
				System.out.println("# Your input is not a number, try input corecctly");
			}
		}while(tryInput||((row<1||row>3)||(colom<1||colom>3)));
	}

	public static void printBoard(){
		System.out.println("Current Board");
		for(int row=0; row<3; row++){
			for(int space=1; space<=3; space++){
				System.out.print("  ");
				if(space==3){
					System.out.print("|");
				}
			}
			for (int colom=0; colom<3; colom++){
				if(table[row][colom]==1){
					System.out.print(" X ");
				}else if(table[row][colom]==2){
					System.out.print(" O ");
				}else{
					System.out.print("   ");
				}
				System.out.print("|");
			}System.out.println();
		}
		System.out.println();
	}

	public static int checkWiner(byte[][] table){
		int winner=0;
		if ((((table[0][0]==1)&&(table[0][1]==1)&&(table[0][2]==1)) ||
			 ((table[1][0]==1)&&(table[1][1]==1)&&(table[1][2]==1)) ||
			 ((table[2][0]==1)&&(table[2][1]==1)&&(table[2][2]==1)) ||
			 ((table[0][0]==1)&&(table[1][0]==1)&&(table[2][0]==1)) ||
			 ((table[0][1]==1)&&(table[1][1]==1)&&(table[2][1]==1)) ||
			 ((table[0][2]==1)&&(table[1][2]==1)&&(table[2][2]==1)) ||
			 ((table[0][0]==1)&&(table[1][1]==1)&&(table[2][2]==1)) ||
			 ((table[0][2]==1)&&(table[1][1]==1)&&(table[2][0]==1)) )) {
			winner=1;

		}else if((((table[0][0]==2)&&(table[0][1]==2)&&(table[0][2]==2)) ||
				  ((table[1][0]==2)&&(table[1][1]==2)&&(table[1][2]==2)) ||
				  ((table[2][0]==2)&&(table[2][1]==2)&&(table[2][2]==2)) ||
				  ((table[0][0]==2)&&(table[1][0]==2)&&(table[2][0]==2)) ||
				  ((table[0][1]==2)&&(table[1][1]==2)&&(table[2][1]==2)) ||
				  ((table[0][2]==2)&&(table[1][2]==2)&&(table[2][2]==2)) ||
				  ((table[0][0]==2)&&(table[1][1]==2)&&(table[2][2]==2)) ||
				  ((table[0][2]==2)&&(table[1][1]==2)&&(table[2][0]==2)) )) {
			winner=2;
		}
		return winner;
	}

	public static void resetGame(){
		for (int row=0; row<3; row++){
			for(int colom=0; colom<3; colom++){
				table[row][colom]=0;
				emptyTable[row][colom]=true;
			}
		}
	}

	public static boolean checkDraw(){
		boolean result=true;
		for (byte row=0; row<3; row++){
			for (byte colom=0; colom<3; colom++){
				if(emptyTable[row][colom]){
					result= false;
					return result;
				}
			}
		}
		return result;
	}

	public static void main(String[]args){
		do {
			menu();
			switch (menuSelect){
			case 1:
				playGame();
				backToMainMenu();
				break;
			case 2:
				ruleOfGame();
				backToMainMenu();
				break;
			case 3:
				exit=true;
				break;
			default:
				System.out.println("# Incorrect input, try again!");
				exit=false;
			}
		}while(!exit);
	}
}
