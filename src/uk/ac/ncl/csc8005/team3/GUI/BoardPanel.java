/**
 * BoardPanel class creates JPanel to display game map. 
 * @author:Ruizhe Liu
 */ 
package uk.ac.ncl.csc8005.team3.GUI;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.ac.ncl.csc8005.team3.block.BlockAttribute;
import uk.ac.ncl.csc8005.team3.block.Coordinate;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;

/**
 * Panel containing the level's map representation
 */
public class BoardPanel extends JPanel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4650266191907566738L;

	/**
	 * Creates a new level grid panel. It generates a graphic display of the
	 * given level
	 * 
	 * @param level
	 * The current level
	 */
	public BoardPanel(Board board) {
		super();

		this.setLayout(new GridLayout(board.getHeight(), board.getWidth()));
		ImageIcon ico = null;
		for (int row = 0; row < board.getHeight(); row++) {
			for (int column = 0; column < board.getWidth(); column++) {

				if (!((board.getBlockAttribute(column, row) == null))) {

					switch (board.getBlockAttribute(column, row)) {
					case FLOOR:
						ico = new ImageIcon(BlockAttribute.FLOOR.getPath());
						break;
					case WALL:
						ico = new ImageIcon(BlockAttribute.WALL.getPath());
						break;
					case GOAL:
						ico = new ImageIcon(BlockAttribute.GOAL.getPath());
						break;
					case PLAYER:
						ico = new ImageIcon(BlockAttribute.PLAYER.getPath());
						break;
					case BOX:
						ico = new ImageIcon(BlockAttribute.BOX.getPath());
						break;
					case BOXONGOAL:
						ico = new ImageIcon(BlockAttribute.BOXONGOAL.getPath());
						break;
					case PLAYERONGOAL:
						ico = new ImageIcon(BlockAttribute.PLAYERONGOAL.getPath());
						break;
					}
				} else if(board.getBlockAttribute(column, row) == null){
					ico = new ImageIcon("resources/background/blanket.PNG");
				}
				Image image = ico.getImage(); // transform it 
				Image newimg = image.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				ImageIcon ico1 = new ImageIcon(newimg); 
				JLabel levelElement = new JLabel(ico1);
				this.add(levelElement);
			}
		}
	}
}
