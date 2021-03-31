package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PongGame extends JPanel implements KeyListener {
	private Ball ball;
	private Racquet racquet1;
	private Racquet racquet2;
	private Score score;
	
	public PongGame() {
		ball = new Ball(this, Color.white);
		this.setBackground(Color.black);
		racquet1 = new Racquet(this, 0, 150, Color.blue, 1);
		racquet2 = new Racquet(this, 965, 150, Color.yellow, 2);
		score = new Score(1000, 600);
		this.setFocusable(true); //우선적으로 키 이벤트를 받음
		this.addKeyListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		ball.draw(g2d);
		racquet1.draw(g2d);
		racquet2.draw(g2d);
		score.draw(g2d);
	}
	
	private void move() {
		ball.move();
		racquet1.move();
		racquet2.move();
		score.checkCollision();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		racquet1.keyPressed(e);
		racquet2.keyPressed(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		racquet1.keyReleased(e);
		racquet2.keyReleased(e);		
	}
	
	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	class Ball {
		private static final int RADIUS = 20;
		private int x = 480, y= 280, xSpeed, ySpeed;
		private PongGame game;
		private Color color;
		
		public Ball(PongGame game, Color color) {
		this.game = game;
		this.color = color;
		}
		
		public void draw(Graphics g) {
			g.setColor(color);
			g.fillOval(x, y, 2 * RADIUS, 2 * RADIUS);
		}
		
		void move() {
			if(x + xSpeed < 0)
				xSpeed = 1;
			if(x + xSpeed > game.getWidth() - 2 * RADIUS)
				xSpeed = -1;
			if(y + ySpeed < 0)
				ySpeed = 1;
			if(y + ySpeed > game.getHeight() - 2 * RADIUS)
				ySpeed = -1;
			if(collision()) {
				if(xSpeed < 0) {
					xSpeed = -xSpeed + 1;
				}else {
					xSpeed = -xSpeed - 1;
				}
			}
			x += xSpeed;
			y += ySpeed;
		}
		public Rectangle getBounds() {
			return new Rectangle(x, y, 2 * RADIUS, 2 * RADIUS);
		}
		
		public boolean collision() {
			return game.racquet1.getBounds().intersects(getBounds()) || game.racquet2.getBounds().intersects(getBounds());
		}
	}
	
	class Racquet {
		private static final int WIDTH = 20;
		private static final int HEIGHT = 80;
		private int x=0, y=0;
		private int xSpeed = 0;
		private int ySpeed = 0;
		private PongGame game;
		private Color color;
		private int index;
		
		public Racquet(PongGame game, int x, int y, Color color, int index) {
			this.game = game;
			this.x = x;
			this.y = y;
			this.color = color;
			this.index = index;
		}
		
		public Rectangle getBounds() {
			return new Rectangle(x, y, WIDTH, HEIGHT);
		}

		public void draw(Graphics g) {
			g.setColor(color);
			g.fillRect(x, y, WIDTH, HEIGHT);
		}
		
		
		public void keyPressed(KeyEvent e) {
			if(index == 1) {
			if (e.getKeyCode() == KeyEvent.VK_A)
				ySpeed = -3;
			else if (e.getKeyCode() == KeyEvent.VK_Z)
				ySpeed = 3;
			}else if(index == 2) {
			if (e.getKeyCode() == KeyEvent.VK_UP)
				ySpeed = -3;
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
				ySpeed = 3;
			}
		}
		
		public void keyReleased(KeyEvent e) {
			ySpeed = 0;
		}
		
		public void move()	{
			if(y + ySpeed > 0 && y + ySpeed < game.getHeight() - HEIGHT)
				y += ySpeed;
		}		
	}
	
	class Score {
		private int GAME_WIDTH;
		private int GAME_HEIGHT;		
		private int scoreR = 0, scoreL = 0;
		
		public Score(int width, int height) {
			GAME_WIDTH = width;
			GAME_HEIGHT = height;
		}
		
		public void checkCollision() {
			if(getBall().x + getBall().xSpeed < 0) {
				scoreR++;
				getBall().x = 480;
				getBall().xSpeed = 1;
			}
			else if(getBall().x + getBall().xSpeed > GAME_WIDTH-14 - 2 * getBall().RADIUS) {
				scoreL++;
				getBall().x = 480;
				getBall().xSpeed = 1;
			}
		}
		
		 public void draw(Graphics g){
			g.setColor(Color.white);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
			
			g.drawLine(GAME_WIDTH/2 , 0, GAME_WIDTH/2, GAME_HEIGHT);
			
			g.drawString(""+scoreL, GAME_WIDTH/2 - 85, 50);
			g.drawString(""+scoreR, GAME_WIDTH/2 + 20, 50);
		 }
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		PongGame game = new PongGame();
		frame.add(game);
		while (true) {
			game.move();
			game.repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
