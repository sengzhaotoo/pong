package Assignment3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;

	int screenWidth = 250;
	int screenHeight = 200;

	int buttonWidth = 100;
	int buttonHeight = 40;
	int checkBoxWidth = 150;
	int checkBoxHeight = 40;

	static String userName;

	JFrame frame = new JFrame();

	JButton Play, Quit;
	ButtonGroup bg = new ButtonGroup();
	JRadioButton Level1, Level2, Level3;

	public MainMenu() {


		addButtons();
		addActions();

		getContentPane().setLayout(null);

		Play.setBounds((screenWidth - buttonWidth) / 2, 5, buttonWidth, buttonHeight); // Position the Play button
		Quit.setBounds((screenWidth - buttonWidth) / 2, 50, buttonWidth, buttonHeight); // Position the Quit button
		Level1.setBounds((screenWidth - buttonWidth) / 2, 85, checkBoxWidth, checkBoxHeight);
		Level2.setBounds((screenWidth - buttonWidth) / 2, 105, checkBoxWidth, checkBoxHeight);
		Level3.setBounds((screenWidth - buttonWidth) / 2, 125, checkBoxWidth, checkBoxHeight);

		//group radio buttons allowing only one button can be pressed
		bg.add(Level1);
		bg.add(Level2);
		bg.add(Level3);

		// Adding buttons to JFrame
		getContentPane().add(Play); 
		getContentPane().add(Quit); 
		add(Level1);
		add(Level2);
		add(Level3);

		// Framework for Main Menu  
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(screenWidth, screenHeight);
		setTitle("Pong Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	public void addButtons(){
		Play = new JButton("Play");
		Quit = new JButton("Quit");
		Level1 = new JRadioButton("Easy");
		Level2 = new JRadioButton("Intermediate");
		Level3 = new JRadioButton("Difficult");
	}

	//add actions to buttons
	private void addActions() {

		Play.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				dispose();
				Game game = new Game();

				if(Level1.isSelected()){
					Ball.speed = 4;
				} else if(Level2.isSelected()){
					Ball.speed = 7;
					Game.ai.speed = Ball.speed-1;
					Game.player.speed = Game.ai.speed;
				} else if (Level3.isSelected()){
					Ball.speed = 10;
					Game.ai.speed = Ball.speed-1;
					Game.player.speed = Game.ai.speed;
				} 

				if(!(Level1.isSelected() || Level2.isSelected() || Level3.isSelected())){
					JOptionPane.showMessageDialog(frame, "Please select a level");
					new MainMenu();
				} else {
					JOptionPane.showMessageDialog(frame, "RULES: First to get 10 points, wins!\n"
							+ "'W' for UP \n 'S' for DOWN \n ESC for QUIT");
					userName = JOptionPane.showInputDialog("Please key in your name");
					game.start();
				}

			}
		});
		
		Quit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				System.exit(0); 
			}
		});
	}


	public static void main(String[] args) {
		new MainMenu(); // Launch game
	}
}
