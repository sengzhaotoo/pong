 package Assignment3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AIPaddle {
	int x; //x-coordinate for paddle
	int y; //y-coordinate for paddle
	static int width = 20; //width of the paddle
	static int height = 50; //height of the paddle 

	int speed = 3; //speed of the paddle moving up and down  
	
	//paddle going up or down conditions
	boolean goingUp = false;
	boolean goingDown = false;

	Rectangle boundingBox;
	
	public AIPaddle(int x, int y){
		this.x = x;
		this.y = y;
		
		//create a rectangular box that fits the paddle so that the ball would bounce away
		boundingBox = new Rectangle(x, y , width, height);
		boundingBox.setBounds(x, y, width, height);
	}

	public void tick(Game game){
		boundingBox.setBounds(x, y, width, height);
		
		if(Game.ball.y < y && y > 20){
			y -= speed;
		} else if(Game.ball.y > y && y + height <= game.getHeight()){
			y += speed;
		}
	}

	public void render(Graphics g){
		//rendering the white block 
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}
}
