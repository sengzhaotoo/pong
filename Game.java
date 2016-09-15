/* Name (Student ID) : Too Seng Zhao (12085403)
 * Due Date : 2nd December
 * Subject : CSCP2014
 * Assignment Number: 1- Revision on Basic Programming Structures
 * Basic Description: The game runs with 3 different levels, where involves 
 * 					  Player 1 and Player AI. The goal of the game is that the ball
 * 					  cannot intersects with the wall, user must use paddle to block the
 * 					  ball. Either player that reaches 10 points, wins! 
 * */



package Assignment3;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends Canvas implements Runnable {
	public static PlayerPaddle player;
	public static AIPaddle ai;
	public static Ball ball;
	InputHandler IH;

	JFrame frame; // Window of the game

	public final int WIDTH = 600; // Width of the game
	public final int HEIGHT = WIDTH * 9 / 16; // Height of the game
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
	public final String TITLE = "Pong";

	public static int p1Score, p2Score; //Players' scores

	BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB); // buffers the background image

	static boolean gameRunning = false;

	public void run() {
		while (gameRunning) {
			tick(); // updates the game coordinates etc
			render(); // renders the game

			try {
				Thread.sleep(7); // suspends current thread for a specific amount of time.
			} catch (Exception e) {
				e.toString();
			}
		}
	}

	public synchronized void start() { // starts the game
		gameRunning = true;
		new Thread(this).start();
	}

	public synchronized static void stop() { // ends the game
		gameRunning = false;
		System.exit(0);
	}

	public Game() {
		frame = new JFrame();

		setMinimumSize(gameSize);
		setPreferredSize(gameSize);
		setMaximumSize(gameSize);

		//framework for the game 
		frame.add(this, BorderLayout.CENTER); // adds whichever drawings into class Game and Canvas
		frame.pack(); // packs everything into JFrame

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);

		IH = new InputHandler(this);

		player = new PlayerPaddle(10, 60); // Player paddle appears at left hand side
		ai = new AIPaddle(570, 60); // AI Paddle appears at right hand side
		ball = new Ball(getWidth() / 2, getHeight() / 2); // ball appears at middle
	}

	public void tick() { // method for updates
		player.tick(this);
		ai.tick(this);
		ball.tick(this);

		if(p1Score == 10 || p2Score == 10){
			gameRunning = false;
			JOptionPane.showMessageDialog(frame, "GAME OVER!");
			if(p1Score == 10)
				JOptionPane.showMessageDialog(frame, "Player 1 : " + MainMenu.userName + " wins");
			else
				JOptionPane.showMessageDialog(frame, "Player 2 : AI wins");
			try{
				File scoreFile = new File("Scores.txt");
				if (scoreFile.exists()) {
					scoreFile.delete();
					System.out.println("File already exists!");
				}
				PrintWriter pen = new PrintWriter(scoreFile);
				pen.println("Player 1 name: " + MainMenu.userName);
				pen.println("Player 1 score: " + Game.p1Score);
				pen.println("Player 2 name: AI");
				pen.println("Player 2 score:" + Game.p2Score);
				pen.close();
			} catch (FileNotFoundException e) {
				e.toString();
			}
			System.exit(0);
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		//displaying scores for player 1 and AI player 
		g.setColor(Color.WHITE);
		g.drawString("Player 1: " + p1Score, 10, 15);
		g.drawString("Player 2: " + p2Score, 525, 15);

		player.render(g); // draws player
		ai.render(g); // draws AI
		ball.render(g); // draws ball

		g.dispose(); // disposes graphics
		bs.show(); // show the strategy
	}

}
