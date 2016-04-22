package sokoban_new;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class GameWindow {

	private JFrame frame;
	private BoardPanel BoardPanel;
	private JButton btnReset;
	private JButton btnQuit;
	
	private Board board;
	private BoardPanel boardPanel;
	private JSplitPane secondPane;
	
	
	/**
	 * Create the application.
	 */
	public GameWindow() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	
	private void initialize() {
		
	
		
		setFrame(new JFrame());
		getFrame().setBounds(0, 0, 1000, 655);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		
		
		
		
		// quit button
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			JDialog.setDefaultLookAndFeelDecorated(true);
			int response = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION)
				System.exit(0);
			
			}
		});
		btnQuit.setBounds(469, 16, 115, 29);
		getFrame().getContentPane().add(btnQuit);
		
		// reset button
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				
			}
		});
		btnReset.setBounds(349, 16, 115, 29);
		getFrame().getContentPane().add(btnReset);
		
		// up button
		JButton btnUp = new JButton("up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	KeyManager.up;
				
			}
		});
		btnUp.setBounds(420, 486, 79, 29);
		getFrame().getContentPane().add(btnUp);
		
		JButton button_2 = new JButton("down");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button_2.setBounds(420, 552, 79, 31);
		getFrame().getContentPane().add(button_2);
		
		JButton button_1 = new JButton("left");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(334, 519, 79, 29);
		getFrame().getContentPane().add(button_1);
		
		JButton button_3 = new JButton("right");
		button_3.setBounds(505, 519, 79, 29);
		getFrame().getContentPane().add(button_3);
		
		JLabel lblSteps = new JLabel("steps");
		lblSteps.setBounds(280, 20, 36, 20);
		getFrame().getContentPane().add(lblSteps);
		
		
		// combobox
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
			}
		});
		comboBox.setBounds(25, 16, 115, 27);
		getFrame().getContentPane().add(comboBox);
		
		
		// choose button
		JButton btnNewButton = new JButton("Choose");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(155, 16, 96, 29);
		getFrame().getContentPane().add(btnNewButton);
		
		
		JSplitPane firstPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		firstPane.setBounds(0,0,978,65);
		getFrame().getContentPane().add(firstPane);
		
		
		JSplitPane secondPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		secondPane.setBounds(0,65,978,405);
		
		boardPanel = new BoardPanel(board);
		secondPane.setTopComponent(boardPanel);
		//secondPane.add(BoardPanel);
		getFrame().getContentPane().add(secondPane);
		
		JSplitPane thirdPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		thirdPane.setBounds(0,470,978,130);
		getFrame().getContentPane().add(thirdPane);
		
		
	}
	

		
/*
		@Override
		public int askLevelToPlay() {
			return this.selectedLevel;
		}

		@Override
		public void displayMessage(String msg) {
			this.labelMessage.setText(msg);
		}

		@Override
		public void displayLevel(Level level) {
			this.levelGridPanel = new BoardPanel(level);
			this.secondSplitPane.setTopComponent(this.levelGridPanel);
			this.pack();
		}

		@Override
		public void displayStartingMessage() {
			// Nothing to do
		}

		@Override
		public void keyTyped(KeyEvent event) {
			switch(event.getKeyChar()){
				case 'z':
					this.chosenDirection = Direction.UP;
					this.isDirectionChosen = true;
					break;
				case 'q':
					this.chosenDirection = Direction.LEFT;
					this.isDirectionChosen = true;
					break;
				case 's':
					this.chosenDirection = Direction.DOWN;
					this.isDirectionChosen = true;
					break;
				case 'd':
					this.chosenDirection = Direction.RIGHT;
					this.isDirectionChosen = true;
					break;
				default:
					break;
			}
		}
*/
		public void keyPressed(KeyEvent event) {
			// Nothing to do
		}

		public void keyReleased(KeyEvent event) {
			// Nothing to do
		}

		public JFrame getFrame() {
			return frame;
		}

		public void setFrame(JFrame frame) {
			this.frame = frame;
		}
	
}
