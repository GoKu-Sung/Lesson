package lesson;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PingPongGame extends JPanel implements KeyListener {
	private Ball ball;
	private Racquet racquet1;
	private Racquet racquet2;
	private int countR = 0, countL = 0;
	
	public PingPongGame() {
		ball = new Ball(this, Color.red);
		this.setBackground(Color.green);
		racquet1 = new Racquet(this, 10, 150, Color.blue, 1);
		racquet2 = new Racquet(this, 560, 150, Color.yellow, 2);
		this.setFocusable(true); //쓴 이유 알아보기 
		this.addKeyListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		ball.draw(g2d);
		racquet1.draw(g2d);
		racquet2.draw(g2d);
	}
	
	private void move() {
		ball.move();
		racquet1.move();
		racquet2.move();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
	
	class Ball {
		private static final int RADIUS = 20;
		private int x =0, y= 0, xSpeed = 1, ySpeed = 1;
		private PingPongGame game;
		private Color color;
		
		public Ball(PingPongGame game, Color color) {
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
				xSpeed = -xSpeed;
			}
				x += xSpeed;
				y += ySpeed;
			
		}
		
		public Rectangle getBounds() {
			return new Rectangle(x, y, 2 * RADIUS, 2 * RADIUS);
		}
		
		private boolean collision() {
			return game.racquet1.getBounds().intersects(getBounds()) || game.racquet2.getBounds().intersects(getBounds());
		}
	}
	
	class Racquet {
		private static final int WIDTH = 10;
		private static final int HEIGHT = 80;
		private int x=0, y=0;
		private int xSpeed = 0;
		private int ySpeed = 0;
		private PingPongGame game;
		private Color color;
		private int index;
		
		public Racquet(PingPongGame game, int x, int y, Color color, int index) {
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
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		PingPongGame game = new PingPongGame();
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
