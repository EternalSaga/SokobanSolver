package uk.ac.ncl.csc8005.team3.block;

public enum BlockAttribute {
	WALL("wall","resources/background/wall.jpg"),
	FLOOR("floor","resources/background/floor.jpg"),
	PLAYER("player","resources/background/player.jpg"),
	BOX("box","resources/background/box.jpg"),
	GOAL("goal","resources/background/goal.jpg"),
	BOXONGOAL("boxongoal","resources/background/boxongoal.jpg"),
	PLAYERONGOAL("playerongoal","resources/background/playerongoal.jpg");
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
