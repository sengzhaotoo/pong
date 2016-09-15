package Assignment3;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InputHandler implements KeyListener {

	JFrame frame = new JFrame();

	public InputHandler(Game game){
		//adds the handler to the frame and whenever the screen is selected, it'll listen for keys
		game.addKeyListener(this);
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		// if 'W' is pressed
		if (keyCode == KeyEvent.VK_W) {
			Game.player.goingUp = true;

		}
		// if 'S' is pressed
		if (keyCode == KeyEvent.VK_S) {
			Game.player.goingDown = true;
		}

		// if 'ESC' is pressed
		if (keyCode == KeyEvent.VK_ESCAPE){
			JOptionPane.showMessageDialog(frame, "'ESC' pressed. Game exiting...");
			Game.stop();
		}
	}

	public void keyReleased(KeyEvent e) {	
		int keyCode = e.getKeyCode();

		// if 'W' is pressed and released
		if (keyCode == KeyEvent.VK_W) {
			Game.player.goingUp = false;
		}
		// if 'S' is pressed and released 
		if (keyCode == KeyEvent.VK_S) {
			Game.player.goingDown = false;
		}

	}

	public void keyTyped(KeyEvent e) {

	}

}
