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
public class BoardPanel extends JPanel{

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4650266191907566738L;
	
	/**
	 * Creates a new level grid panel.
	 * It generates a graphic display of the given level
	 * @param level The current level
	 */
	public BoardPanel(Board board) {
		super();
		
		this.setLayout(new GridLayout(board.getHeight(),board.getWidth()));
		ImageIcon ico = null;
		for (int row = 0; row < board.getHeight(); row++) {
			for (int column = 0; column < board.getWidth(); column++) {
				switch (board.getBlockAttribute(column,row)) {
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
					case BOX:
						ico = new ImageIcon(BlockAttribute.BOX.getPath());
					case BOXONGOAL:
						ico = new ImageIcon(BlockAttribute.BOXONGOAL.getPath());
					case PLAYERONGOAL:
						ico = new ImageIcon(BlockAttribute.PLAYERONGOAL.getPath());
					default:
						break;
				}
				/**
				if(cod.equals(board.getPlayerPosition()))
					
				if(board.isBoxAt(pos)){
					if(board.getFixedMapElement(pos) == FixedMapElement.TARGET)
						ico = new ImageIcon(this.getClass().getResource("/ressources/boxOnTarget.jpg"));
					else
						ico = new ImageIcon(this.getClass().getResource("/ressources/box.jpg"));
				}
				*/
				JLabel levelElement = new JLabel(ico);
				this.add(levelElement);
			}
		}
	}

}
