package Assignment3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {

	int x; // x-coordinate
	int y; // y -coordinate
	int size = 16;
	public static int speed = 4;

	int vx; // Increment on ball's x-coordinate
	int vy; // Increment on ball's y-coordinate

	Rectangle boundingBox;

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;

		vx = speed;
		vy = speed;

		// set bounding area for the ball
		boundingBox = new Rectangle(x, y, size, size);
		boundingBox.setBounds(this.x, this.y, this.size, this.size);
	}

	public void tick(Game game) {
		boundingBox.setBounds(x, y, size, size);

		// Check boundaries

		if (x <= 0) {
			Game.p2Score++; //AI player scores 1 point
			vx = speed;
		} else if (x + size >= game.getWidth()) {
			Game.p1Score++; //player 1 scores 1 point
			vx = -speed;
		}
		if (y <= 15) {
			vy = speed;
		} else if (y + size >= game.getHeight()) {
			vy = -speed;
		}

		x += vx;
		y += vy;

		// passing game into collision method, so that the ball would bounce away
		collision(game);
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, size, size);
	}

	private void collision(Game game) {
		if (boundingBox.intersects(Game.player.boundingBox)) {
			vx = speed; // player paddle on left, so ball bounces towards right
			// side
		} else if (boundingBox.intersects(Game.ai.boundingBox)) {
			vx = -speed; // AI paddle on left, so ball bounces towards left side
		}
	}
}
