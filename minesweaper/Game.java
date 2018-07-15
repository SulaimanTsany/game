import java.util.Scanner;

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
