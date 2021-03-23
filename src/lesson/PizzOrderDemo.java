package lesson;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class PizzOrderDemo extends JFrame implements ActionListener	{
	private int sum, temp1, temp2, temp3;
	
	private JButton orderBtn, cancelBtn;
	private JPanel orderPanel;
	private JTextField priceField;
	
	JPanel welconePanel = new WelcomePanel();
	JPanel typePanel = new TypePanel();
	JPanel toppingPanel = new ToppingPanel();
	JPanel sizePanel = new SizePanel();
	
	public PizzOrderDemo() {
		this.setTitle("피자 주문");
		this.setSize(500, 200);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		orderBtn = new JButton("주문");
		orderBtn.addActionListener(this);
		
		cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(this);
		
		priceField = new JTextField();
		priceField.setEditable(false);
		priceField.setColumns(6);
		
		orderPanel = new JPanel();
		orderPanel.add(orderBtn);
		orderPanel.add(cancelBtn);
		orderPanel.add(priceField);
		
		this.add(welconePanel,BorderLayout.NORTH);
		this.add(orderPanel, BorderLayout.SOUTH);
		this.add(sizePanel, BorderLayout.EAST);
		this.add(typePanel, BorderLayout.WEST);
		this.add(toppingPanel, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == orderBtn) {
			sum = 5000 + (temp1 + temp2 + temp3)*1000;
			
			priceField.setText(String.valueOf(sum));
			System.out.println("temp1:"+temp1+", tmep2:"+temp2+", temp3:"+temp3);
		}else if (e.getSource() == cancelBtn) {
			temp1 = 0;
			temp2 = 0;
			temp3 = 0;
			sum = 0;
			priceField.setText(String.valueOf(sum));
		}
	}
	
	class WelcomePanel extends JPanel{
		private JLabel message;
		
		public WelcomePanel() {
			message = new JLabel("자바 피자에 오신것을 환영합니다.");
			this.add(message);
		}
	}
	
	class TypePanel extends JPanel implements ItemListener {
		private JRadioButton combo, potato, bulgogi;
		
		private ButtonGroup btnGroup;
		
		public TypePanel() {
			setLayout(new GridLayout(3, 1));
			combo = new JRadioButton("콤보", true);
			combo.addItemListener(this);
			potato = new JRadioButton("포테이토", true);
			potato.addItemListener(this);
			bulgogi = new JRadioButton("불고기", true);
			bulgogi.addItemListener(this);
			
			btnGroup = new ButtonGroup();
			btnGroup.add(combo);
			btnGroup.add(potato);
			btnGroup.add(bulgogi);
			
			this.setBorder(BorderFactory.createTitledBorder("종류"));
			
			this.add(combo);
			this.add(potato);
			this.add(bulgogi);
		}
		
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(combo.isSelected()) {
				temp1 = 0;
			}else if(potato.isSelected()) {
				temp1 = 1;
			}else if(bulgogi.isSelected()) {
				temp1 = 2;
			}
		}
	}
	
	class ToppingPanel extends JPanel implements ItemListener {
		private JRadioButton pepper, cheese, peperoni, bacon;
		
		private ButtonGroup btnGroup;
		
		public ToppingPanel() {
			setLayout(new GridLayout(4, 1));
			pepper = new JRadioButton("피망", true);
			pepper.addItemListener(this);
			cheese = new JRadioButton("치즈", true);
			cheese.addItemListener(this);
			peperoni = new JRadioButton("페페로니", true);
			peperoni.addItemListener(this);
			bacon = new JRadioButton("베이컨", true);
			bacon.addItemListener(this);
			
			btnGroup = new ButtonGroup();
			btnGroup.add(pepper);
			btnGroup.add(cheese);
			btnGroup.add(peperoni);
			btnGroup.add(bacon);
			
			this.setBorder(BorderFactory.createTitledBorder("추가토핑"));
			
			this.add(pepper);
			this.add(cheese);
			this.add(peperoni);
			this.add(bacon);
		}
		
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(pepper.isSelected()) {
				temp2 = 0;
			}else if(cheese.isSelected()) {
				temp2 = 1;
			}else if(peperoni.isSelected()) {
				temp2 = 2;
			}else if(bacon.isSelected()) {
				temp2 = 3;
			}
		}
	}
	
	class SizePanel extends JPanel implements ItemListener {
		private JRadioButton small, medium, large;
		
		private ButtonGroup btnGroup;
		
		public SizePanel() {
			setLayout(new GridLayout(3, 1));
			small = new JRadioButton("Small", true);
			small.addItemListener(this);
			medium = new JRadioButton("Medium", true);
			medium.addItemListener(this);
			large = new JRadioButton("Large", true);
			large.addItemListener(this);
			
			btnGroup = new ButtonGroup();
			btnGroup.add(small);
			btnGroup.add(medium);
			btnGroup.add(large);
			
			this.setBorder(BorderFactory.createTitledBorder("크기"));
			
			this.add(small);
			this.add(medium);
			this.add(large);
		}
		
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(small.isSelected()) {
				temp3 = 0;
			}else if(medium.isSelected()) {
				temp3 = 1;
			}else if(large.isSelected()) {
				temp3 = 2;
			}
		}
	}
	
	public static void main(String[] args) {
		new PizzOrderDemo();
	}
}
