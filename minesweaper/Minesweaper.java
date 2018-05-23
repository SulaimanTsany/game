import java.util.Random;

public class Minesweaper {
    protected char[][] board;
	protected char bomSymbol;
    protected int height;
    protected int width;
    protected int numberOfBoms;

    public Minesweaper () {
		bomSymbol = '*';
    }
    public Minesweaper(int height, int width) {
        board = new char[height][width];
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                board[i][j] = ' ';
            }
        }
		bomSymbol = '*';
    }
    public void makeBoard() {
        board = new char[this.height][this.width];
        for (int i=0; i<this.height; i++) {
            for (int j=0; j<this.width; j++) {
                board[i][j] = ' ';
            }
        }
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setNumberOfBoms (int numberOfBoms) {
        this.numberOfBoms = numberOfBoms;
    }
    public int getNumberOfBoms () {
        return numberOfBoms;
    }
    public char countBoms (int i, int j) {
        int result =0;
        //hitung semua bom disekitar
		if (i == 0) {
			// if top
			if (j == 0) {
				if (board[i][j+1] == bomSymbol) {
					result++;
				}if (board[i+1][j] == bomSymbol) {
					result++;
				}if (board[i+1][j+1] == bomSymbol) {
					result++;
				}
			}
			else if (j == width-1) {
				if (board[i][j-1] == bomSymbol) {
					result++;
				}if (board[i+1][j] == bomSymbol) {
					result++;
				}if (board[i+1][j-1] == bomSymbol) {
					result++;
				}
			}
			else {
				if (board[i][j+1] == bomSymbol) {
					result++;
				}if (board[i][j-1] == bomSymbol) {
					result++;
				}if (board[i+1][j] == bomSymbol) {
					result++;
				}if (board[i+1][j+1] == bomSymbol) {
					result++;
				}if (board[i+1][j-1] == bomSymbol) {
					result++;
				}
			}
		}
		else if (i == height-1) {
			//if bottom
			if (j == 0) {
				if (board[i][j+1] == bomSymbol) {
					result++;
				}if (board[i-1][j] == bomSymbol) {
					result++;
				}if (board[i-1][j+1] == bomSymbol) {
					result++;
				}
			}
			else if (j == width-1) {
				if (board[i][j-1] == bomSymbol) {
					result++;
				}if (board[i-1][j] == bomSymbol) {
					result++;
				}if (board[i-1][j-1] == bomSymbol) {
					result++;
				}
			}
			else {
				if (board[i][j+1] == bomSymbol) {
					result++;
				}if (board[i][j-1] == bomSymbol) {
					result++;
				}if (board[i-1][j] == bomSymbol) {
					result++;
				}if (board[i-1][j+1] == bomSymbol) {
					result++;
				}if (board[i-1][j-1] == bomSymbol) {
					result++;
				}
			}
		}
		else {
			if (j == 0) {
                if (board[i][j+1] == bomSymbol) {
					result++;
				}if (board[i-1][j] == bomSymbol) {
					result++;
				}if (board[i-1][j+1] == bomSymbol) {
					result++;
				}if (board[i+1][j] == bomSymbol) {
					result++;
				}if (board[i+1][j+1] == bomSymbol) {
					result++;
				}
			}
			else if (j == width-1) {
                if (board[i][j-1] == bomSymbol) {
					result++;
				}if (board[i-1][j] == bomSymbol) {
					result++;
				}if (board[i-1][j-1] == bomSymbol) {
					result++;
				}if (board[i+1][j] == bomSymbol) {
					result++;
				}if (board[i+1][j-1] == bomSymbol) {
					result++;
				}
			}
			else {
                if (board[i-1][j-1] == bomSymbol) {
					result++;
				}if (board[i-1][j] == bomSymbol) {
					result++;
				}if (board[i-1][j+1] == bomSymbol) {
					result++;
				}if (board[i][j-1] == bomSymbol) {
					result++;
				}if (board[i][j+1] == bomSymbol) {
					result++;
				}if (board[i+1][j-1] == bomSymbol) {
					result++;
				}if (board[i+1][j] == bomSymbol) {
					result++;
				}if (board[i+1][j+1] == bomSymbol) {
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
    public void generateBoms() {
        Random random = new Random();
        int boms = this.numberOfBoms;
        int line = 0;
        while (boms > 0) {
            for (int i=0; i<width; i++) {
                int rd = random.nextInt(6)+0;
                if (rd==0 && board[line][i] != '*') {
                    board[line][i] = bomSymbol;
                    boms--;
                }
            }
            line++;
            //to reset pointer line
            if (line >= this.height) {
                line = 0;
            }
        }
    }
    public void generateBoard() {
        generateBoms();
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if (board[i][j] != bomSymbol) {
                    board[i][j] = countBoms(i,j);
                }
            }
        }
    }
    public String convertNum (int number) {
        String result = "";
        result = String.valueOf(number);
        if (number<10) {
            result+= " ";
        }
        return result;
    }
    public void showBoard() {
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

    public static void main (String[] args) {
        Minesweaper main = new Minesweaper();
        main.setHeight(19);
        main.setWidth(11);
        main.makeBoard();
        main.setNumberOfBoms(36);
        main.generateBoard();
        //System.out.println(main.countBoms(0,0));
        main.showBoard();
        System.out.println("selesai");
    }

}
