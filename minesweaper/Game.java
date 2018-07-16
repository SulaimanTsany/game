import java.util.Scanner;
import java.util.Random;

public class Game {
    private Scanner scanner;
    private static String difficulty;
    private static int boardWidth;
    private static int boardHeight;
    private static char bombSymbol;
    private static char boardSymbol;
    private static Board board;
    private static Game game;

    public Game () {
        scanner = new Scanner(System.in);
        difficulty = "normal";
        boardHeight = 6;
        boardWidth = 5;
        boardSymbol = '.';
        bombSymbol = 'X';
        board = new Board(boardHeight, boardWidth, difficulty, boardSymbol, bombSymbol); //bombs = 36, 19x11
    }
    public static void main (String[] args) {
        game = new Game();
        int selected = 0;
        boolean gameOver = false;
        do {
            game.menu();
            boolean invalidSelected = true;
            do {
                gameOver = false;
                System.out.print("Input your selected menu = ");
                selected = game.input();
                switch (selected) {
                    case 1:
                        gameOver = game.play();
                        invalidSelected = false;
                        break;
                    case 2:
                        game.rule();
                        invalidSelected = false;
                        break;
                    case 3:
                        game.settings();
                        invalidSelected = false;
                        break;
                    case 0:
                        invalidSelected = false;
                        break;
                    default:
                        System.out.println("Your selected menu is incorrect, please input again!");
                        invalidSelected = true;
                }
            } while (invalidSelected);
        } while (selected!=0 || gameOver);
    }
    private static void setBoardHeight (int newHeight) {
        boardHeight = newHeight;
    }
    private static void setBoardWidth (int newWidth) {
        boardWidth = newWidth;
    }
    private void menu () {
        System.out.println();
        System.out.println("--------------------Menu--------------------");
        System.out.println("1. Play game");
        System.out.println("2. Rule of game");
        System.out.println("3. Settings");
        System.out.println("0. Exit");
    }
    private boolean play () {
        //board.showBoard(board.getBoard());
        board = new Board(boardHeight, boardWidth, difficulty, boardSymbol, bombSymbol);
        System.out.println();
        System.out.println("----------------Game Start-------------------");
        board.showBoard(board.getBoardView());
        do {
            int i,j;
            System.out.print("Please inspect (Row+Space+Col) = ");
            i = game.input();
            j = game.input();
            if (i==-1 || j==-1 ) {
                return true;
            }
            System.out.println("inspected "+i+","+j+" = "+board.inspect(i,j));
            board.showBoard(board.getBoardView());
            System.out.println();
        } while (board.getStatus_play());

        if (board.getStatus_win()) {
            System.out.println("*----------YOU WIN----------");
        } else if (board.getStatus_gameOver()) {
            System.out.println("*----Game Over, YOU LOSE----");
        }
        board.showBoard(board.getBoard());
        //return selected 0= exit game, else back to menu
        return true;
    }
    private void rule() {
        System.out.println();
        System.out.println("----------------Rule of Game----------------");
        System.out.println("No special rule, just like normal Minesweaper");
        System.out.println("Thanks for ejoy it!");
        System.out.println("Created by me");
    }
    private void settings () {
        boolean exitSettings = false;
        do {
            System.out.println();
            System.out.println("------------------Settings------------------");
            System.out.println("Current Settings:");
            System.out.println("Difficulty   = "+this.difficulty);
            System.out.println("Board Height = "+this.boardHeight);
            System.out.println("Board Width  = "+this.boardWidth);
            System.out.println("Bombs        = "+board.getNumberOfBombs());
            System.out.println("Bobm Symbol = "+"\" "+this.bombSymbol+" \"");
            System.out.println("Board Symbol= "+"\" "+this.boardSymbol+" \"");
            System.out.print("Edit settings (y/n)? : ");
            String selected = scanner.next();
            if (selected.equalsIgnoreCase("y") || selected.equalsIgnoreCase("yes")) {
                editSettingsMenu();
                System.out.print("Do you want to apply (y/n)? : ");
                String is_save = scanner.next();
                if (is_save.equalsIgnoreCase("y") || is_save.equalsIgnoreCase("yes")) {
                    board = new Board(boardHeight, boardWidth, difficulty, boardSymbol, bombSymbol);
                }
            }
            else if (selected.equalsIgnoreCase("n") || selected.equalsIgnoreCase("no")) {
                exitSettings = true;
            }
        } while (!exitSettings);
    }
    private void editSettingsMenu () {
        System.out.println();
        System.out.println("---------------Edit Settings----------------");
        System.out.println("Menu settings:");
        System.out.println("1. Difficulty");
        System.out.println("2. Board size");
        System.out.println("3. Bomb symbol");
        System.out.println("4. Board symbol");
        System.out.println("0. Cancel");
        boolean invalidSelected = false;
        do {
            System.out.print("Please selected = ");
            int selected = input();
            switch (selected) {
                case 1:
                    edit_difficulty();
                    invalidSelected = false;
                    break;
                case 2:
                    edit_boardSize();
                    invalidSelected = false;
                    break;
                case 3:
                    edit_bombSymbol();
                    invalidSelected = false;
                    break;
                case 4:
                    edit_boardSymbol();
                    invalidSelected = false;
                    break;
                case 0:
                    invalidSelected = false;
                    break;
                default:
                    System.out.println("selected invalid, please reinput!");
                    invalidSelected = true;
            }
        } while (invalidSelected);
    }
    private void edit_difficulty () {
        System.out.println();
        System.out.println("Current Difficulty   = "+this.difficulty);
        System.out.println("Difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Normal");
        System.out.println("3. Hard");
        System.out.println("0. Cancel");
        boolean invalidSelected = false;
        do {
            System.out.print("Please selected difficulty = ");
            int selected = input();
            switch (selected) {
                case 1:
                    difficulty = "easy";
                    invalidSelected = false;
                    break;
                case 2:
                    difficulty = "normal";
                    invalidSelected = false;
                    break;
                case 3:
                    difficulty = "hard";
                    invalidSelected = false;
                    break;
                case 0:
                    invalidSelected = false;
                    break;
                default:
                    System.out.println("selected invalid, please reinput!");
                    invalidSelected = true;
            }
        } while (invalidSelected);
    }
    private void edit_boardSize () {
        System.out.println();
        System.out.println("Current Board Height = "+board.getBoardHeight());
        System.out.println("Current Board Width  = "+board.getBoardWidth());
        System.out.print("New height = ");
        int newHeight = input();
        boardHeight = newHeight;
        System.out.print("New width  = ");
        int newWidth = input();
        boardWidth = newWidth;
    }
    private void edit_bombSymbol () {
        System.out.println();
        System.out.println("Current Bomb Symbol= "+"\" "+board.getBombSymbol()+" \"");
        System.out.print("New Symbol = ");
        char newSymbol = scanner.next().charAt(0);
        bombSymbol = newSymbol;
    }
    private void edit_boardSymbol () {
        System.out.println();
        System.out.println("Current Board Symbol= "+"\" "+this.boardSymbol+" \"");
        System.out.print("New Symbol = ");
        char newSymbol = scanner.next().charAt(0);
        boardSymbol = newSymbol;
    }
    private int input () {
        boolean invalidInput = false;
        int input = -1;
        do {
            if (invalidInput) {
                System.out.print("Not a number, please reinput = ");
            }
            invalidInput = false;
            String i = scanner.next();
            if (i.equals("-1")) {
                return -1;
            }
            try {
                input = Integer.valueOf(i);
            } catch (Exception e) {
                invalidInput = true;
            }
        } while (invalidInput);
        return input;
    }
}

class Board {
    private char[][] board;
    private char[][] boardView;
    private boolean[][] board_isOpened;
	private char bombSymbol;
    private char boardViewSymbol;
    private int height;
    private int width;
    private int numberOfBombs;
    private String difficulty;
    private boolean status_gameOver;
    private boolean status_win;
    private boolean status_play;

    public Board () { //useless
		bombSymbol = '*';
        status_gameOver = false;
        status_win = false;
        status_play = true;
    }
    public Board (int height, int width, String difficulty, char boardView, char bombSymbol) {
        this.height = height;
        this.width = width;
        int bombs = 0;
        switch (difficulty) {
            case "normal":
                bombs = (height*width)/5;
                break;
            case "easy":
                bombs = (height*width)/7;
                break;
            case "hard":
                bombs = (height*width)/3;
                break;
        }
        numberOfBombs = bombs;
        setBoardViewSymbol(boardView);
        setBombSymbol(bombSymbol);
        status_gameOver = false;
        status_win = false;
        status_play = true;
        makeBoard();
        setNumberOfBombs(bombs);
        generateBoard();
    }
    public char[][] getBoard () {
        return this.board;
    }
    public char[][] getBoardView () {
        return this.boardView;
    }
    public void setBoardViewSymbol (char symbol) {
        this.boardViewSymbol = symbol;
    }
    public void setBombSymbol (char symbol) {
        this.bombSymbol = symbol;
    }
    public void makeBoard () {
        board = new char[this.height][this.width];
        boardView = new char[this.height][this.width];
        board_isOpened = new boolean[this.height][this.width];
        for (int i=0; i<this.height; i++) {
            for (int j=0; j<this.width; j++) {
                board[i][j] = ' ';
                boardView[i][j] = boardViewSymbol;
                board_isOpened[i][j] = false;
            }
        }
    }
    public void setHeight (int height) {
        this.height = height;
    }
    public void setWidth (int width) {
        this.width = width;
    }
    public void setNumberOfBombs (int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }
    public int getNumberOfBombs () {
        return numberOfBombs;
    }
    public int getBoardHeight () {
        return this.height;
    }
    public int getBoardWidth () {
        return this.width;
    }
    public char getBombSymbol () {
        return this.bombSymbol;
    }
    public char getBoardViewSymbol () {
        return this.boardViewSymbol;
    }
    public void generateBoard () {
        generateBombs();
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if (board[i][j] != bombSymbol) {
                    board[i][j] = countbombs(i,j);
                }
            }
        }
    }
    public void showBoard (char[][] theBoard) {
        char[][] board = theBoard;
        for (int i=0; i<board.length; i++) {
            //first horizontal line
            if (i==0) {
                System.out.printf("     ");
                //print index of columns
                for (int k=0; k<board[0].length; k++) {
                    System.out.printf("  %s", convertNum(k));
                }
                System.out.println();
                System.out.printf("     ");
                for (int j=0; j<board[0].length; j++) {
                    System.out.print("+---");
                }
                System.out.println("+");
            }
            //print index of rows
            System.out.printf("  %s ", convertNum(i));
            for (int j=0; j<board[0].length; j++) {
                System.out.printf("| %s ", board[i][j]);
                if (j==(board[0].length-1)) {
                    System.out.println("|");
                }
            }
            System.out.printf("     ");
            //horizontal line
            for (int k=0; k<board[0].length; k++) {
                System.out.print("+---");
            }
            System.out.println("+");
        }
    }
    public boolean getStatus_gameOver () {
        return this.status_gameOver;
    }
    public boolean getStatus_win () {
        return this.status_win;
    }
    public boolean getStatus_play () {
        return this.status_play;
    }
    public boolean inspect (int i, int j) {
        try {
            if (board_isOpened[i][j]) {
                throw new Exception();
            }
            if (board[i][j] != ' ') {
                boardView[i][j] = board[i][j];
                board_isOpened[i][j] = true;
                if (board[i][j] == bombSymbol) {
                    status_gameOver = true;
                    status_play = false;
                }
            } else if (board[i][j] == ' ') {
                //System.out.println("Recursive on "+i+","+j);
                inspectRecursive(i,j);
            }
        } catch (Exception e) {
            return false;
        }
        is_win();
        return true;
    }
    public boolean is_win () {
        for (int i=0; i<this.height; i++) {
            for (int j=0; j<this.width; j++) {
                if (board[i][j]!=bombSymbol && !board_isOpened[i][j]) {
                    return false;
                }
            }
        }
        status_play = false;
        status_win = true;
        return true;
    }

    private void inspectRecursive (int i, int j) {
        try {
            boardView[i][j] = board[i][j];
            if (board[i][j] == ' ' && !board_isOpened[i][j]) {
                //
                board_isOpened[i][j] = true;
                inspectRecursive(i-1, j-1);
                inspectRecursive(i-1, j);
                inspectRecursive(i-1, j+1);
                //
                inspectRecursive(i+1, j-1);
                inspectRecursive(i+1, j);
                inspectRecursive(i+1, j+1);
                //
                inspectRecursive(i, j-1);
                inspectRecursive(i, j+1);
            } else {
                board_isOpened[i][j] = true;
                return;
            }
        } catch (Exception e) {
            return;
        }
    }
    private String convertNum (int number) {
        String result = "";
        result = String.valueOf(number);
        if (number<10) {
            result+= " ";
        }
        return result;
    }
    private char countbombs (int i, int j) {
        int result =0;
        //hitung semua bom disekitar
		if (i == 0) {
			// if top
			if (j == 0) {
				if (board[i][j+1] == bombSymbol) {
					result++;
				}if (board[i+1][j] == bombSymbol) {
					result++;
				}if (board[i+1][j+1] == bombSymbol) {
					result++;
				}
			}
			else if (j == width-1) {
				if (board[i][j-1] == bombSymbol) {
					result++;
				}if (board[i+1][j] == bombSymbol) {
					result++;
				}if (board[i+1][j-1] == bombSymbol) {
					result++;
				}
			}
			else {
				if (board[i][j+1] == bombSymbol) {
					result++;
				}if (board[i][j-1] == bombSymbol) {
					result++;
				}if (board[i+1][j] == bombSymbol) {
					result++;
				}if (board[i+1][j+1] == bombSymbol) {
					result++;
				}if (board[i+1][j-1] == bombSymbol) {
					result++;
				}
			}
		}
		else if (i == height-1) {
			//if bottom
			if (j == 0) {
				if (board[i][j+1] == bombSymbol) {
					result++;
				}if (board[i-1][j] == bombSymbol) {
					result++;
				}if (board[i-1][j+1] == bombSymbol) {
					result++;
				}
			}
			else if (j == width-1) {
				if (board[i][j-1] == bombSymbol) {
					result++;
				}if (board[i-1][j] == bombSymbol) {
					result++;
				}if (board[i-1][j-1] == bombSymbol) {
					result++;
				}
			}
			else {
				if (board[i][j+1] == bombSymbol) {
					result++;
				}if (board[i][j-1] == bombSymbol) {
					result++;
				}if (board[i-1][j] == bombSymbol) {
					result++;
				}if (board[i-1][j+1] == bombSymbol) {
					result++;
				}if (board[i-1][j-1] == bombSymbol) {
					result++;
				}
			}
		}
		else {
			if (j == 0) {
                if (board[i][j+1] == bombSymbol) {
					result++;
				}if (board[i-1][j] == bombSymbol) {
					result++;
				}if (board[i-1][j+1] == bombSymbol) {
					result++;
				}if (board[i+1][j] == bombSymbol) {
					result++;
				}if (board[i+1][j+1] == bombSymbol) {
					result++;
				}
			}
			else if (j == width-1) {
                if (board[i][j-1] == bombSymbol) {
					result++;
				}if (board[i-1][j] == bombSymbol) {
					result++;
				}if (board[i-1][j-1] == bombSymbol) {
					result++;
				}if (board[i+1][j] == bombSymbol) {
					result++;
				}if (board[i+1][j-1] == bombSymbol) {
					result++;
				}
			}
			else {
                if (board[i-1][j-1] == bombSymbol) {
					result++;
				}if (board[i-1][j] == bombSymbol) {
					result++;
				}if (board[i-1][j+1] == bombSymbol) {
					result++;
				}if (board[i][j-1] == bombSymbol) {
					result++;
				}if (board[i][j+1] == bombSymbol) {
					result++;
				}if (board[i+1][j-1] == bombSymbol) {
					result++;
				}if (board[i+1][j] == bombSymbol) {
					result++;
				}if (board[i+1][j+1] == bombSymbol) {
					result++;
				}
			}
		}

		if (result != 0) {
			char n = String.valueOf(result).charAt(0);
			return n;
		}
        return ' ';
    }
    private void generateBombs() {
        Random random = new Random();
        int bombs = this.numberOfBombs;
        int line = 0;
        while (bombs > 0) {
            for (int i=0; i<width; i++) {
                int rd = random.nextInt(6)+0;
                if (rd==0 && board[line][i] != '*') {
                    board[line][i] = bombSymbol;
                    bombs--;
                }
            }
            line++;
            //to reset pointer line
            if (line >= this.height) {
                line = 0;
            }
        }
    }
}
