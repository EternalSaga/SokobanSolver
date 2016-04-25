package uk.ac.ncl.csc8005.team3.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.net.ssl.KeyManager;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.BoardController;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GameWindow {

	private JFrame frame;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnReset;
	private JButton btnQuit;
	private JButton btnSolver;



	private JComboBox comboBox;
	private FileFolder f;
	private IOMethods io;
	
	private Board board;
	private BoardPanel boardPanel;
	
	/**
	 * Create the application.
	 */
	public GameWindow(FileFolder f) {
		this.f = f;
		io = new IOMethods();
		board =io.loadBoardFromFile("res/levelCollection/level.txt");
		boardPanel =new BoardPanel(board);
		
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
		//frame.setBounds(100, 100, 1038, 685);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(getPanel1(), BorderLayout.NORTH);
		frame.getContentPane().add(getPanel2(), BorderLayout.SOUTH);
		frame.getContentPane().add(getPanel3(), BorderLayout.CENTER);

		
	}
	
	
	public JPanel getPanel1(){
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ButtonListener());
		panel_1.add(btnReset);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ButtonListener());
		panel_1.add(btnQuit);
		
		JButton btnSolver = new JButton("Solve the level");
		btnSolver.addActionListener(new ButtonListener());
		panel_1.add(btnSolver);
		
		return panel_1;
	}
	
	public JPanel getPanel2(){
		JPanel panel_2 = new JPanel();
		JPanel panelIn = new JPanel();
		panelIn.setLayout(new BorderLayout());
		JButton btnUp = new JButton("Up");
		
		btnUp.addActionListener(new ButtonListener());
		panelIn.add(btnUp,BorderLayout.NORTH);
		
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ButtonListener());
		panelIn.add(btnDown,BorderLayout.SOUTH);
		
		JButton btnLeft = new JButton("Left");
		//btnUp.setPreferredSize(new Dimension(200,20));
		btnLeft.addActionListener(new ButtonListener());
		panelIn.add(btnLeft,BorderLayout.WEST);
		
		JButton btnRight = new JButton("Right");
		btnRight.addActionListener(new ButtonListener());
		
		panelIn.add(btnRight,BorderLayout.EAST);
		panel_2.add(panelIn);
		return panel_2;
	}
	
	
	
	
	
	
	
	public JPanel getPanel3(){
		
		JPanel panel_3 = new JPanel();
		panel_3.add(boardPanel);
		return panel_3;
		
	}
	
	
	
	public class ButtonListener implements ActionListener{
		
		private KeyManager k;
		private BoardController bc;
		private Solver solver;
		
		 public void actionPerformed(ActionEvent buttonPressed) {
			 if(buttonPressed.getSource() == comboBox){
				 JComboBox c = (JComboBox)buttonPressed.getSource();
					String msg = (String)c.getSelectedItem();
						board = io.loadBoardFromFile(f.getFiles().get(msg).getPath());
						boardPanel = new BoardPanel(board);
						
			 }
			 else if(buttonPressed.getSource() == btnReset){
				 //reset
			 }
			 else if(buttonPressed.getSource() == btnQuit){
				 JDialog.setDefaultLookAndFeelDecorated(true);
					int response = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.YES_OPTION)
						System.exit(0);
			 }else if (buttonPressed.getSource()== btnSolver){
				solver = new Solver();
				solver.start();
				
			 }
			/* else if (buttonPressed.getSource() == btnLeft){
	              
				 bc.getInput(k.left);
	            else if (buttonPressed.getSource() == btnRight)
	               bc.getInput(k.right);
	            else if (buttonPressed.getSource() == btnUp)
	            	  bc.getInput(k.up);
	            else if (buttonPressed.getSource() == btnDown)
	            	  bc.getInput(k.down);
	        } */
	}
}
