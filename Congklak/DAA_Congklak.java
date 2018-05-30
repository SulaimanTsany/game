
public class DAA_Congklak {

	public static void main(String[] args) {		
		
		boolean exitGame = false;
		Menu menu = new Menu();
		do {
			menu.showMainMenu();
			menu.setSelectedMenu();
			int selected = menu.getSelectedMenu();
			switch (selected) {
			case 1 :
				menu.showPlayerForm();
				menu.play();
				break;
			case 2 :
				menu.showRuleOfGame();
				if (menu.is_backToMenu()) {
					continue;
				} else {
					exitGame = true;
					menu.showCloseMessage();
				}
				break;
			case 0 : 
				exitGame = true;
				menu.showCloseMessage();
				break;
			}
		} while (!exitGame);
		
	}
}
