package uk.ac.ncl.csc8005.team3.block;

public enum BlockAttribute {
	WALL("wall","path1"),
	FLOOR("floor","path2"),
	PLAYER("player","path3"),
	BOX("box","path4"),
	GOAL("goal","path5"),
	BOXONGOAL("boxongoal","path6"),
	PLAYERONGOAL("playerongoal","path7");
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
