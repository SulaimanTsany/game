import java.util.Random;

public class Board {
    protected char[][] board;
    protected char[][] boardView;
    protected boolean[][] board_isOpened;
	protected char bombSymbol;
    protected char boardViewSymbol;
    protected int height;
    protected int width;
    protected int numberOfbombs;
    protected boolean status_gameOver;

    public Board () {
		bombSymbol = '*';
        status_gameOver = false;
    }
    public Board(int height, int width, int bombs, char boardView, char bombSymbol) {
        this.height = height;
        this.width = width;
        setBoardViewSymbol('.');
        this.board = new char[this.height][this.width];
        this.boardView = new char[this.height][this.width];
        this.board_isOpened = new boolean[this.height][this.width];
        setBoardViewSymbol(boardView);
        setBombSymbol(bombSymbol);
        status_gameOver = false;
        for (int i=0; i<this.height; i++) {
            for (int j=0; j<this.width; j++) {
                this.board[i][j] = ' ';
                this.boardView[i][j] = boardViewSymbol;
                this.board_isOpened[i][j] = false;
            }
        }
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
    public void makeBoard() {
        board = new char[this.height][this.width];
        boardView = new char[this.height][this.width];
        board_isOpened = new boolean[this.height][this.width];
        setBoardViewSymbol('.');
        for (int i=0; i<this.height; i++) {
            for (int j=0; j<this.width; j++) {
                board[i][j] = ' ';
                boardView[i][j] = boardViewSymbol;
                board_isOpened[i][j] = false;
            }
        }
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setNumberOfBombs (int numberOfbombs) {
        this.numberOfbombs = numberOfbombs;
    }
    public int getNumberOfbombs () {
        return numberOfbombs;
    }
    public void generateBoard() {
        generateBombs();
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if (board[i][j] != bombSymbol) {
                    board[i][j] = countbombs(i,j);
                }
            }
        }
    }
    public void showBoard(char[][] theBoard) {
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
                }
            } else if (board[i][j] == ' ') {
                System.out.println("Recursive on "+i+","+j);
                inspectRecursive(i,j);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    private void inspectRecursive (int i, int j) {
        try {
            if (board[i][j] == ' ' && !board_isOpened[i][j]) {
                boardView[i][j] = board[i][j];
                board_isOpened[i][j] = true;
                //showBoard(boardView);
                inspectRecursive(i-1, j);
                inspectRecursive(i+1, j);
                inspectRecursive(i, j-1);
                inspectRecursive(i, j+1);
            } else {
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
        int bombs = this.numberOfbombs;
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
