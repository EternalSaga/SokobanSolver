package uk.ac.ncl.csc8005.team3.coreEngine;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * taken from https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src/blob/master/Episode%2014/TileGame/src/dev/codenmore/tilegame/input/KeyManager.java
 * 
 * @author Kate
 *
 */

public class KeyManager implements KeyListener {
	
	private boolean[] keys;
	public boolean up, down, left, right;
	
	public KeyManager() {
		keys = new boolean[256]; //i don't know why 256
	}
	
	public void tick() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		System.out.println("pressed"); //for testing
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
