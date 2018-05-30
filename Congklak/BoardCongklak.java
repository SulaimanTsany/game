
public class BoardCongklak {
	private Player player1;
	private Player player2;
	private int pointer; //start from 0
	private int player1LastPointer;
	private int player2LastPointer;
	private String player1PointerCode;
	private String player2PointerCode;
	private boolean finish;
	private int[] villageA = new int [6];
	private int[] villageB = new int [6];

	public BoardCongklak (Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		finish = false;
		for (int i = 0; i<6; i++) {
			villageA[i] = 7;
			villageB[i] = 7;
		}
	}

	public int getPlayer1LastPointer() {
		return player1LastPointer;
	}
	public int getPlayer2LastPointer() {
		return player2LastPointer;
	}
	public String getPlayer1PointerCode() {
		return player1PointerCode;
	}
	public String getPlayer2PointerCode() {
		return player2PointerCode;
	}
	public void setPlayer1Pointer(int player1LastPointer) {
		player1PointerCode = "a";
		this.player1LastPointer = player1LastPointer;
	}
	public void setPlayer2Pointer(int player2LastPointer) {
		player2PointerCode = "b";
		this.player2LastPointer = player2LastPointer;
	}
	public void setPointer(int pointer) {
		this.pointer = pointer;
	}

	public void setVillageAmount(String villageCode, int villageIndex, int newNum) { // untuk trial
		if (villageCode.equals("a")) {
			villageA[villageIndex] = newNum;
		}
		else if (villageCode.equals("b")) {
			villageB[villageIndex] = newNum;
		}
	}

	public int getVillageAmount (String villageCode , int villageIndex) {
		int result = 0;
		if (villageCode.equals("a")) {
			result = villageA[villageIndex];
		}
		else if (villageCode.equals("b")) {
			result = villageB[villageIndex];
		}
		return result;
	}

	public int getTotalVillage(String villageCode) {
		int result = 0;
		if (villageCode.equals("a")) {
			for (int i=0; i<6; i++) {
				result += villageA[i];
			}
		}
		else if (villageCode.equals("b")) {
			for (int i=0; i<6; i++) {
				result += villageB[i];
			}
		}
		return result;
	}

	public void showPlayer1Owened() {
		System.out.println("\n"+player1.getOwned());
	}

	public void playPlayer1() {
		boolean repeat = true;
		boolean stop = true;
		do {
			if (getVillageAmount(getPlayer1PointerCode(), getPlayer1LastPointer()) != 0) {
				player1Return(getPlayer1PointerCode(), getPlayer1LastPointer());
				showBoard();
				System.out.println("        Habis di " + getPlayer1LastPointer()+ getPlayer1PointerCode() +"\n");
			}
			else {
				repeat = false;
			}
			if (getVillageAmount(getPlayer1PointerCode(), getPlayer1LastPointer()) == 1) {
				repeat = false;
				if (getPlayer1PointerCode().equals("a")) {
					System.out.println("Shoot from "+getPlayer1LastPointer()+getPlayer1PointerCode());
					player1Shoot(getPlayer1LastPointer());
					showBoard();
				}
				else if (getPlayer1PointerCode().equals("b")) {
					System.out.println("Habis di wilayah lawan, giliran "+ player1.getPlayerName() +" selesai");
				}

			}
		} while ( repeat );
	}

	public void playPlayer2() {
		boolean repeat = true;
		boolean stop = true;
		do {
			if (getVillageAmount(getPlayer2PointerCode(), getPlayer2LastPointer()) != 0) {
				player2Return(getPlayer2PointerCode(), getPlayer2LastPointer());
				showBoard();
				System.out.println("        Habis di " + getPlayer2LastPointer()+ getPlayer2PointerCode() +"\n");
			}
			else {
				repeat = false;
			}
			if (getVillageAmount(getPlayer2PointerCode(), getPlayer2LastPointer()) == 1) {
				repeat = false;
				if (getPlayer2PointerCode().equals("b")) {
					System.out.println("Shoot from "+getPlayer2LastPointer()+getPlayer2PointerCode());
					player2Shoot(getPlayer2LastPointer());
					showBoard();
				}
				else if (getPlayer2PointerCode().equals("a")) {
					System.out.println("Habis di wilayah lawan, giliran "+ player2.getPlayerName() +" selesai");
				}
			}
		} while ( repeat );
	}

	public void player1Return (String villageCode, int lastPointer) {
		player1PointerCode = villageCode;
		int pointer = lastPointer;
		if (villageCode.equals("a")) {
			villageA[pointer] = player1.setHold(villageA[pointer] );
		}
		else if (villageCode.equals("b")) {
			villageB[pointer] = player1.setHold(villageB[pointer] );
		}
		boolean is_return = true;
		boolean is_first = true;
		boolean is_next = true;
		do {
			if (is_first) {
				is_first = false;
				is_next = false;
			}
			else {
				player1PointerCode = "a";
			}
			while ( (pointer < 6) && (player1.getHold()!=0) && player1PointerCode.equals("a")) {
				//rotate on own village
				if (pointer == 5) {  			//If the last of village add hold to Owned
					if (player1.getHold() != 0) {
						player1.putHold();
						player1.addOwned();
						player1PointerCode = "s";
						is_next = true;
					}
					if (player1.getHold() == 0) {
						is_return = false;
						is_next = false;
						break;
					}
				}
				else {
					player1LastPointer = pointer+1;
					player1PointerCode = "a";
					villageA[pointer+1] ++;

					player1.putHold();
					if (player1.getHold() == 0) {
						is_return = false;
						is_next = false;
						break;
					}
				}
				pointer++;
			}
			if (is_next) {
				pointer = -1;
				player1PointerCode = "b";
			}
			//rotate on oppennent village
			while ( (pointer < 5) && (player1.getHold()!=0) && player1PointerCode.equals("b")) {
				//rotate on own village
				player1LastPointer = pointer+1;
				player1PointerCode = "b";
				villageB[pointer+1] ++;
				player1.putHold();
				if (player1.getHold() == 0) {
					is_return = false;
					break;
				}
				pointer++;
			}
			pointer = -1;
		} while (is_return);
	}

	public void player2Return (String villageCode, int lastPointer) {
		player2PointerCode = villageCode;
		int pointer = lastPointer;
		if (villageCode.equals("a")) {
			villageA[pointer] = player2.setHold(villageA[pointer] );
		}
		else if (villageCode.equals("b")) {
			villageB[pointer] = player2.setHold(villageB[pointer] );
		}
		boolean is_return = true;
		boolean is_first = true;
		boolean is_next = true;
		do {
			if (is_first) {
				is_first = false;
				is_next = false;
			}
			else {
				player2PointerCode = "b";
			}
			while ( (pointer < 6) && (player2.getHold()!=0) && player2PointerCode.equals("b")) {
				//rotate on own village
				if (pointer == 5) {  			//If the last of village add hold to Owned
					if (player2.getHold() != 0) {
						player2.putHold();
						player2.addOwned();
						player2PointerCode = "s";
						is_next = true;
					}
					if (player2.getHold() == 0) {
						is_return = false;
						is_next = false;
						break;
					}
				}
				else {
					player2LastPointer = pointer+1;
					player2PointerCode = "b";
					villageB[pointer+1] ++;
					player2.putHold();
					if (player2.getHold() == 0) {
						is_return = false;
						is_next = false;
						break;
					}
				}
				pointer++;
			}
			if (is_next) {
				pointer = -1;
				player2PointerCode = "a";
			}

			while ( (pointer < 5) && (player2.getHold()!=0) && player2PointerCode.equals("a")) {
				//rotate on own village
				player2LastPointer = pointer+1;
				player2PointerCode = "a";
				villageA[pointer+1] ++;
				player2.putHold();
				if (player2.getHold() == 0) {
					is_return = false;
					break;
				}
				pointer++;
			}
			pointer = -1;
		} while (is_return);
	}

	//put it before player1.putHold()
	public boolean is_player1_shoot(int pointer, int hold) { //masih ngaco
		if (villageA[pointer]==0 && hold==1) {
			return true;
		}
		return false;
	}

	//put it before player1.putHold()
	public boolean is_player2_shoot(int pointer, int hold) {
		if (villageB[pointer]==0 && hold==1) {
			return true;
		}
	    return false;
	}

	//unsafe
	public void player1Shoot(int pointer) {
		switch (pointer) {
		case 0 :
			player1.addOwned(villageB[5]);
			villageB[5] = 0;
			break;
		case 1 :
			player1.addOwned(villageB[4]);
			villageB[4] = 0;
			break;
		case 2 :
			player1.addOwned(villageB[3]);
			villageB[3] = 0;
			break;
		case 3 :
			player1.addOwned(villageB[2]);
			villageB[2] = 0;
			break;
		case 4 :
			player1.addOwned(villageB[1]);
			villageB[1] = 0;
			break;
		case 5 :
			player1.addOwned(villageB[0]);
			villageB[0] = 0;
			break;
		}
	}

	//unsafe
	public void player2Shoot(int pointer) {
		switch (pointer) {
		case 0 :
			player2.addOwned(villageA[5]);
			villageA[5] = 0;
			break;
		case 1 :
			player2.addOwned(villageA[4]);
			villageA[4] = 0;
			break;
		case 2 :
			player2.addOwned(villageA[3]);
			villageA[3] = 0;
			break;
		case 3 :
			player2.addOwned(villageA[2]);
			villageA[2] = 0;
			break;
		case 4 :
			player2.addOwned(villageA[1]);
			villageA[1] = 0;
			break;
		case 5 :
			player2.addOwned(villageA[0]);
			villageA[0] = 0;
			break;
		}
	}

	//to parse a number to 2 digits of String
	public String parss(int num) {
		String result = "";
		if (num <10) {
			result = "0"+ String.valueOf(num);
		}
		if (num >=10) {
			result = String.valueOf(num);
		}
		return result;
	}

	public void showBoard() {
		System.out.printf("       +====+====+====+====+====+====+\n");
		System.out.printf("       | %s | %s | %s | %s | %s | %s |\n", parss(villageA[0]), parss(villageA[1]), parss(villageA[2]), parss(villageA[3]), parss(villageA[4]), parss(villageA[5]) );
		System.out.printf("#------#====+====+====+====+====+====@------@\n");
		System.out.printf("|  %s  |                             |  %s  |\n", parss(player2.getOwned()), parss(player1.getOwned()) );
		System.out.printf("#------#====+====+====+====+====+====@------@\n");
		System.out.printf("       | %s | %s | %s | %s | %s | %s |\n", parss(villageB[5]), parss(villageB[4]), parss(villageB[3]), parss(villageB[2]), parss(villageB[1]), parss(villageB[0]) );
		System.out.printf("       +====+====+====+====+====+====+\n");
	}
}
