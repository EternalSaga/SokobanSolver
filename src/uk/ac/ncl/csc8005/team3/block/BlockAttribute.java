package uk.ac.ncl.csc8005.team3.block;

public enum BlockAttribute {
	WALL("wall","resources/wall.jpg"),
	FLOOR("floor","resources/floor.jpg"),
	PLAYER("player","resources/character.gif"),
	BOX("box","resources/box.jpg"),
	GOAL("goal","resources/target.png"),
	BOXONGOAL("boxongoal","resources/boxOnTarget.jpg"),
	PLAYERONGOAL("playerongoal","character.jpg");
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
