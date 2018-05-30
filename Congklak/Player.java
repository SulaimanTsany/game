
public class Player {

	private String playerName;
	private int owned;
	private int hold;
    private boolean lastHold;

	public Player () {
		owned = 0;
		lastHold = false;
	}
	public Player (String playerName) {
		this.playerName = playerName;
		owned = 0;
		lastHold = false;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerName() {
		return playerName;
	}
	public int setHold(int selected) {
		hold = selected;
		return 0; //get all, then set empty
	}
	public int getHold() {
		return hold;
	}
	public void putHold() {
		hold--;
	}
	public void addOwned() {
		owned ++;
	}
	public void addOwned(int num) {
		owned += num;
	}
	public int getOwned() {
		return owned;
	}
	public boolean is_lastHold() {
		if (hold==1) {
			return true;
		}
		return false;
	}
}
