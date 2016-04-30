package uk.ac.ncl.csc8005.team3.coreEngine;

public interface BoardInterface {
  
  public void addToMap(Coordinate thisCoordinate, BlockAttribute thisBlockAttribute);
  
  public Coordinate getPlayerPosition();
  
  public int getNumberOfGoals();
  
  public void clearMap();
  
  public BlockAttribute getBlockAttribute(int x, int y);
  
  public void addRow(String row);
  
  public int getWidth();
  
  public int getHeight();
  
  public BlockAttribute setEnum(char ch);
  
	public void setCell();
	
	public String getRow(int i);
	
	public Map<Coordinate, BlockAttribute> getTreeMap();

}
