package uk.ac.ncl.csc8005.team3.block;

public enum BlockAttribute {
	WALL("wall","resources/background/wall.PNG"),
	FLOOR("floor","resources/background/floor.PNG"),
	PLAYER("player","resources/background/player.PNG"),
	BOX("box","resources/background/box.PNG"),
	GOAL("goal","resources/background/goal.PNG"),
	BOXONGOAL("boxongoal","resources/background/boxongoal.png"),
	PLAYERONGOAL("playerongoal","resources/background/playerongoal.png");
	private String attribute;
	private String path;
	private BlockAttribute(String attribute, String path){
		this.attribute = attribute;
		this.path = path;
	}
	//We don't need the setter method.
	public String getAttribute() {
		return attribute;
	}
	public String getPath() {
		return path;
	}
}
