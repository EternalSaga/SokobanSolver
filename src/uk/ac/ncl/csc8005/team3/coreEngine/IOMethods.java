package uk.ac.ncl.csc8005.team3.coreEngine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * IO class is resposible for saving and loading a board object.
 * @author Lingfei Tian
 */
public class IOMethods {
	
	/* It may be better to put these into Board class */
	public static final int MAX_ROWS = 50;
	public static final int MAX_COLS = 50;
	
	/*
	 * This method will load a text file which contains valid Map data.
	 * 
	 * @param  : Full file name include the path.
	 * 
	 * @return : The Board object represent Map data store in the input file. 
	 * 			 return null if load map failed, check log to know the exception details.
	 */
	public Board loadBoardFromFile(String fileFullname) {
		
	Board board = new Board();
		
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileFullname));
			String s;
			int rows = 0;
			while ( (s = in.readLine()) != null) {
				rows++;
				if (rows > MAX_ROWS) {
					break;
				}
				
				s = checkAndFormatForMapRow(s);
				if (s == null) {
					throw new Exception("The Map file " + fileFullname + 
							" contains invalid characters at line " + rows + ".");
				}
				if (s.length() != 0) {
					board.addRow(s);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if(in != null) {
					in.close();
				}	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		board.setCell();
		return board;
	}
	
	/**
	 * This method will check If the String is valid. If it's valid, will format it.
	 * 
	 * @param  : one row string of Map
	 * 
	 * @return : one row string of Map which had been formated. 
	 * 			 return null if the input String is invalid.
	 */
	private String checkAndFormatForMapRow(String row) {
		
		//row = row.trim();
		
		if (row.length() > MAX_COLS) {
			row = row.substring(0, MAX_COLS);
		}
		
		for (int i=0; i<row.length(); i++) {
			if(!checkCharacter(row.charAt(i))) {
				return null;
			}
		}
		
		return row;
	}
	
	/*
	 * This method will check If the character is valid.
	 * 
	 * @param  : the character will be check.
	 * 
	 * @return : true if input char is one of the invalid characters.
	 *           false if input char is not one of the invalid characters.
	 */
	private boolean checkCharacter(char ch) {
		boolean ret;
		switch(ch) {
			case '#':
				ret = true;
				break;
			case ' ':
				ret = true;
				break;
			case '-':
				ret = true;
				break;
			case '_':
				ret = true;
				break;
			case '@':
				ret = true;
				break;
			case '$':
				ret = true;
				break;
			case '.':
				ret = true;
				break;
			case '*':
				ret = true;
				break;
			case '+':
				ret = true;
				break;
			default:
				ret = false;
				break;
		}
		return ret;
	}
	
	/*
	 * This method will save a board object to a file.
	 * 
	 * @param  : Full file name include the path.
	 * @param  : Board object will be saved to file.
	 * 
	 * @return : true if successful saved.
	 *           false if failed to save.
	 */
	public boolean saveBoardToFile(String fileFullname, Board board) {
		
		boolean ret = false;
		PrintWriter out = null;
		try {
			out = new PrintWriter(fileFullname);
			int numOfRow = board.getHeight();
			for (int i=0; i<numOfRow; i++) {
				String row = board.getRow(i);
				out.println(row);
			}
			ret = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				out.close();
			}	
		}
		return ret;
	}
}