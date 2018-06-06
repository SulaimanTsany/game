import java.util.Scanner;

public class Play {
    public static void main (String[] args) {
        Board main = new Board(19, 11, 15, '.', 'X'); //bombs = 36
        //main.setHeight(19);
        //main.setWidth(11);
        //main.makeBoard();
        //main.setNumberOfBombs(36);
        //main.generateBoard();
        //main.showBoard(main.getBoard());
        //main.showBoard(main.getBoardView());
        System.out.println("inspect 5,5 = "+main.inspect(5,5));
        //main.showBoard(main.getBoardView());
        System.out.println("inspect 10,5 = "+ main.inspect(10,5));
        //main.showBoard(main.getBoardView());
        System.out.println("inspect 1,1 = "+main.inspect(1,1));
        //main.showBoard(main.getBoardView());

        System.out.println("selesai = " + main.getStatus_gameOver());
        main.showBoard(main.getBoardView());
        main.showBoard(main.getBoard());
    }


}
