import java.util.Scanner;

public class Game {
    private Scanner scanner;
    private String difficulty;
    private Board board;
    private static Game game;

    public Game () {
        scanner = new Scanner(System.in);
        difficulty = "Normal";
        board = new Board(6, 5, difficulty, '.', 'X'); //bombs = 36, 19x11
    }
    public static void main (String[] args) {
        game = new Game();
        int selected = 0;
        do {
            game.menu();
            boolean invalidSelected = true;
            do {
                System.out.print("Input your selected menu = ");
                selected = game.input();
                switch (selected) {
                    case 1:
                        selected = game.play();
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
        } while (selected!=0);
    }
    private void menu () {
        System.out.println("--------------------Menu--------------------");
        System.out.println("1. Play game");
        System.out.println("2. Rule of game");
        System.out.println("3. Settings");
        System.out.println("0. Exit");
    }
    private int play () {
        board.showBoard(board.getBoard());
        do {
            int i,j;
            do {
                System.out.print("Please inspect = ");
                i = game.input();
                j = game.input();
            } while (i==-1 || j==-1);
            System.out.println("inspect "+i+","+j+" = "+board.inspect(i,j));
            board.showBoard(board.getBoardView());
            System.out.println();
        } while (board.getStatus_play());

        if (board.getStatus_win()) {
            System.out.println("You Win");
        } else if (board.getStatus_gameOver()) {
            System.out.println("Game Over, You Lose");
        }
        board.showBoard(board.getBoard());
        //return selected 0= exit game, else back to menu
        return 0;
    }
    private void rule() {
        System.out.println("----------------Rule of Game----------------");
        System.out.println("No special rule, just like normal Minesweaper");
        System.out.println("Thanks for ejoy it!");
        System.out.println("Created by me");
    }
    private void settings () {
        /*
        boardHeight = 19;
        boardWidth = 11;
        bombsEasy = (boardHeight*boardWidth)/7;
        bombsNormal = (boardHeight*boardWidth)/5;
        bombsHard = (boardHeight*boardWidth)/3;
        maxBombs = (boardHeight*boardWidth)/2;
        */
        System.out.println("----------------Rule of Game----------------");
        System.out.println("------------------Settings------------------");
        System.out.println("Current Settings:");
        System.out.println("Difficulty   = "+this.difficulty);
        System.out.println("Board Height = "+board.getBoardHeight());
        System.out.println("Board Width  = "+board.getBoardWidth());
        System.out.println("Bombs        = "+board.getNumberOfBombs());
        System.out.println("Bobms Symbol = "+"\" "+board.getBombSymbol()+" \"");
        System.out.println("Boards Symbol= "+"\" "+board.getBoardViewSymbol()+" \"");
        System.out.print("Edit settings (y/n)? : ");
        String selected = scanner.next();
        if (selected.equalsIgnoreCase("y") || selected.equalsIgnoreCase("yes")) {
            editSettings();
        }
    }
    private void editSettings () {
        //edit setting bla bla bla
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
