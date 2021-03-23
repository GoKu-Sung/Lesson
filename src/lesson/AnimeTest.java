package lesson;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.awt.*;


	public class AnimeTest extends JPanel implements ActionListener {
		private Timer timer;
		private BufferedImage image;
		private final int START_X = 0;
		private final int START_Y = 0;
		private int x, y;
		private boolean checkX = true;
		private boolean checkY = true;
		
		public AnimeTest() {
			this.setBackground(Color.black);
			this.setDoubleBuffered(true);
			
			File file = new File("space.jpg");
			try {
				image = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			
			timer = new Timer(10, this);
			timer.start();
			
			x = START_X;
			y = START_Y;
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, x, y, this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
//			이미지의 x ,y 좌표를 변경
			if(x >= 400) {
				checkX = false;
			}else if (x <= 0) {
				checkX = true;
			}
			if(y >= 170) {
				checkY = false;
			}else if (y <= 0) {
				checkY = true;
			}
			
			if(checkX == true) {
				x += 1;
			}else if(checkX == false) {
				x -= 1;
			}
			if(checkY == true) {
				y += 1;
			}else if(checkY == false) {
				y -= 1;
			}
			repaint();
		}

	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new AnimeTest());
		frame.setTitle("애니메이션 테스트");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setVisible(true);
	}	
}
