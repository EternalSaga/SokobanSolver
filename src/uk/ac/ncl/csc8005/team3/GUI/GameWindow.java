package uk.ac.ncl.csc8005.team3.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;



import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import uk.ac.ncl.csc8005.team3.block.Coordinate;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.BoardController;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;


import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;



public class GameWindow {

	private JFrame frame;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnReset;
	private JButton btnQuit;
	private JButton btnSolver;
	private JButton btnAbout;
	private JButton btnSave;
	private JButton btnImport;



	private JComboBox comboBox;
	private FileFolder f;
	private IOMethods io;
	private Board board;
	private BoardPanel boardPanel;
	private BoardController bc;
	

	
	
	/**
	 * Create the application.
	 */
	public GameWindow(FileFolder f) {
		this.f = f;
		io = new IOMethods();
		 board = io.loadBoardFromFile("resources/levelCollection/forTest.txt");
		boardPanel = new BoardPanel(board);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 *
	 */
	
	public void start(){
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(getPanel1(), BorderLayout.NORTH);
		frame.getContentPane().add(getPanel2(), BorderLayout.SOUTH);
		frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
		
		
		frame.addKeyListener(new KeyManager());
		frame.pack();
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.requestFocusInWindow();
		
	}
	
	
	public JPanel getPanel1(){
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		btnImport = new JButton("Import");
		btnImport.addActionListener(new ButtonListener());
		panel_1.add(btnImport);
		
		comboBox = new JComboBox();
		comboBox.setMaximumRowCount(256);
		ArrayList<String> list = f.getFileNames();
		for (String n: list){
			comboBox.addItem(n); 
		}	
		comboBox.setPreferredSize(new Dimension(200, 50));
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(new ButtonListener());
		


		panel_1.add(comboBox);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ButtonListener());
		panel_1.add(btnReset);
		
		
		
		btnSolver = new JButton("Solve the level");
		btnSolver.addActionListener(new ButtonListener());
		panel_1.add(btnSolver);
		
	/*	btnSave = new JButton("Save the level");
		btnSave.addActionListener(new ButtonListener());
		panel_1.add(btnSave);
		*/
		
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ButtonListener());
		panel_1.add(btnQuit);
		
		btnAbout = new JButton("About");
		btnAbout.addActionListener(new ButtonListener());
		panel_1.add(btnAbout);
		
		return panel_1;
	}
	
	public JPanel getPanel2(){
		JPanel panel_2 = new JPanel();
		JPanel panelIn1 = new JPanel();
		panelIn1.setLayout(new BorderLayout());
		
		
		btnUp = new JButton("Up");
		
		btnUp.addActionListener(new ButtonListener());
		panelIn1.add(btnUp,BorderLayout.NORTH);
		
		btnDown = new JButton("Down");
		btnDown.addActionListener(new ButtonListener());
		panelIn1.add(btnDown,BorderLayout.SOUTH);
		
		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ButtonListener());
		panelIn1.add(btnLeft,BorderLayout.WEST);
		
		btnRight = new JButton("Right");
		btnRight.addActionListener(new ButtonListener());
		
		panelIn1.add(btnRight,BorderLayout.EAST);
		panel_2.add(panelIn1);
		
		
		JPanel panelIn2 = new JPanel();
		panelIn2.setLayout(new BorderLayout());
		
		
		JLabel label = new JLabel("<html>How to play <br>1.Move arrow keys on your keyboard<br> 2.Click up,down,left,right buttons</html>");
		
		
		panelIn2.add(label,BorderLayout.CENTER);
		panel_2.add(panelIn2);
		
		return panel_2;
	}
	
	
	
	
	
	public class ButtonListener implements ActionListener{
		
		
		
		private Solver solver;
		private int direction;
		private Coordinate player ,nextPlayer,nextBox;
		private String msg;
		
		
								
		 public void actionPerformed(ActionEvent buttonPressed) {
			
			 player = board.getPlayerPosition();
			 
			 bc = new BoardController(board);
			
			 
			 if(buttonPressed.getSource() == comboBox){
				 
						frame.getContentPane().remove(boardPanel);
						msg = (String)comboBox.getSelectedItem();
						 board = io.loadBoardFromFile(f.getFiles().get(msg).getPath());
						boardPanel = new BoardPanel(board);
						frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
						
						frame.pack();
						frame.setVisible(true);
						frame.setFocusable(true);
						frame.requestFocusInWindow();
			 }
			 else if (buttonPressed.getSource()== btnImport){
					
				JFileChooser fc = new JFileChooser();	
				if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					frame.getContentPane().remove(boardPanel);
					board = io.loadBoardFromFile(file.getPath());
					boardPanel = new BoardPanel(board);
					frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
					f.putInFile(file.getName(), file);
					comboBox.addItem(file.getName());
					comboBox.setSelectedItem(file.getName());
					frame.pack();
					frame.setVisible(true);
					frame.setFocusable(true);
					frame.requestFocusInWindow();
					
				}
				 
			 }
			
			 else if(buttonPressed.getSource() == btnQuit){
				
				 JDialog.setDefaultLookAndFeelDecorated(true);
					int response = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.YES_OPTION)
						System.exit(0);
			 }else if (buttonPressed.getSource()== btnSolver){
				
				 
				  solver = new Solver(board);
			   	solver.start();
				frame.setFocusable(true);
				frame.requestFocusInWindow();
			 
		 }else if (buttonPressed.getSource()== btnAbout){
				
			
			 About a = new About();
			 a.start();
			 frame.setFocusable(true);
				frame.requestFocusInWindow();
		 }
		/* else if (buttonPressed.getSource()== btnSave){
				
				
			 msg = (String)comboBox.getSelectedItem();
				String path = f.getFiles().get(msg).getPath();
				io.saveBoardToFile(path, bc.getThisBoard());
				
	 
		 }
			*/ 
			 
			else if(buttonPressed.getSource() == btnReset){
				
				
				
				frame.getContentPane().remove(boardPanel);
				msg = (String)comboBox.getSelectedItem();
				 board = io.loadBoardFromFile(f.getFiles().get(msg).getPath());
				boardPanel = new BoardPanel(board);
				frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
				
				frame.pack();
				frame.setVisible(true);
				frame.setFocusable(true);
				frame.requestFocusInWindow();
	 
			 }
			else if (buttonPressed.getSource() == btnLeft){
				
				frame.getContentPane().remove(boardPanel);
				
				
			    direction = 3;
				nextPlayer = new Coordinate(player.getX()- 1, player.getY());
				nextBox = new Coordinate(player.getX()- 2, player.getY());
				bc.setDirection(3);
				bc.setNextCoordinate(nextPlayer, nextBox);
				bc.checkPlayerWallCollision();
				
				boardPanel = new BoardPanel(bc.getThisBoard());
				frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
				
				frame.pack();
				frame.setVisible(true);
				frame.setFocusable(true);
				frame.requestFocusInWindow();
				
			}
	         else if (buttonPressed.getSource() == btnRight){
	        	frame.getContentPane().remove(boardPanel);
				
					nextPlayer = new Coordinate(player.getX() + 1, player.getY());
					nextBox = new Coordinate(player.getX() + 2, player.getY());
					bc.setDirection(4);
					bc.setNextCoordinate(nextPlayer, nextBox);
					bc.checkPlayerWallCollision();
					
					boardPanel = new BoardPanel(bc.getThisBoard());
				
					frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
					
					frame.pack();
					frame.setVisible(true);
					frame.setFocusable(true);
					frame.requestFocusInWindow();
				}
	            else if (buttonPressed.getSource() == btnUp){
	            	
	            	frame.getContentPane().remove(boardPanel);
	            	
	            	
					nextPlayer = new Coordinate(player.getX() , player.getY()-1);
					nextBox = new Coordinate(player.getX() , player.getY()-2);
					bc.setDirection(1);
					bc.setNextCoordinate(nextPlayer, nextBox);
					bc.checkPlayerWallCollision();
					
					
					boardPanel = new BoardPanel(bc.getThisBoard());
					frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
					
					frame.pack();
					frame.setVisible(true);
					frame.setFocusable(true);
					frame.requestFocusInWindow();
					}
			
	            else if (buttonPressed.getSource() == btnDown){
	            	
	            	frame.getContentPane().remove(boardPanel);
	            	
	            	
					nextPlayer = new Coordinate(player.getX() , player.getY()+1);
					nextBox = new Coordinate(player.getX() , player.getY()+2);
					bc.setDirection(2);
					bc.setNextCoordinate(nextPlayer, nextBox);
					bc.checkPlayerWallCollision();
					
					boardPanel = new BoardPanel(board);
					boardPanel = new BoardPanel(bc.getThisBoard());
					frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
					
					frame.pack();
					frame.setVisible(true);
					frame.setFocusable(true);
					frame.requestFocusInWindow();	            }
	        } 
		 
	}
	
	
	public class KeyManager implements KeyListener {
		
		
		
		@Override
		public void keyPressed(KeyEvent e) {
			frame.getContentPane().remove(boardPanel);
			 bc = new BoardController(board);
			bc.getInput(e);			
			boardPanel = new BoardPanel(bc.getThisBoard());
			frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
			frame.pack();
			if (bc.checkSuccess() == true){
				LevelComplete lc = new LevelComplete(f);		
				lc.initialize();		
			}	
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
}

