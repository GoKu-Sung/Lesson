package lesson;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D.Float;
import java.util.ArrayList;

import javax.swing.*;

public class MoreShapes extends JFrame {
	public MoreShapes() {
		setSize(600, 130);
		setTitle("Java 2D Shapes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new MyPanel();
		add(panel);
		setVisible(true);
	}

	
	class MyPanel extends JPanel{
		ArrayList<Shape> shapeArray = new ArrayList<Shape>();
		
		public MyPanel() {
			Shape s;
			
			s = new Rectangle2D.Float(10, 10, 60, 60);
			shapeArray.add(s);
			
			s = new RoundRectangle2D.Float(110, 10, 60, 60, 20, 20);
			shapeArray.add(s);
			
			s = new Ellipse2D.Float(210, 10 , 60, 60);
			shapeArray.add(s);
			
			s = new Arc2D.Float(310, 10, 60, 60, 90, 90, Arc2D.OPEN);
			shapeArray.add(s);
			
			s = new Arc2D.Float(410, 10, 60, 60, 0, 180, Arc2D.CHORD);
			shapeArray.add(s);
			
			s = new Arc2D.Float(510, 10, 60, 60, 45, 90, Arc2D.PIE);
			shapeArray.add(s);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
//			g2.setColor(Color.black);
//			g2.fill(new Rectangle2D.Float(10, 10, 60, 60)); 일반 채우기
			
			GradientPaint gp = new GradientPaint(0, 10, Color.white, 0, 70, Color.red);
			
			for(int i = 0; i<shapeArray.size();i++) {		
				g2.setPaint(gp);
				g2.fill(shapeArray.get(i));
			}//그라데이션 채우기
			
			g2.setStroke(new BasicStroke(3));
			for (Shape s : shapeArray)
				g2.draw(s);
		}
	}
	public static void main(String[] args) {
		new MoreShapes();

	}

}
