/**
 * @author Qijing Yu
 *This is the class to launch the Game.
 */
package uk.ac.ncl.csc8005.team3.coreEngine;


import uk.ac.ncl.csc8005.team3.GUI.FileFolder;
import uk.ac.ncl.csc8005.team3.GUI.GameWindow;




public class MainStart {

	
		public static void main(String[] args) {
		//load the game from the default folder.
		FileFolder f = new FileFolder("resources/levelCollection");	
	        GameWindow w = new GameWindow(f);
	        w.start();
		}
}
