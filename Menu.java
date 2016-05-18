package SNAKE;

import javax.swing.JFrame;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Image;

class Menu extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private Image menuzdj;
	private JButton zakoncz;
	static JButton zacznij;
	static JButton wyniki;
	
	public Menu() {
																						//// OKNO MENU ////
																							/* KOMENTARZE ANALOGICZNIE JAK W SNAKE.java */
		super("SNAKE | Menu");
		setSize(500, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setLocation(new Point(240, 120));
		
		ImageIcon men = new ImageIcon(this.getClass().getResource("menu.jpg"));
		menuzdj = men.getImage();
		
		Insets insets = new Insets(1,1,1,1);
		
		zacznij = new JButton("Zacznij");
		zacznij.setMargin(insets);
		zacznij.setFocusable(false);
		zacznij.setBounds(407, 90, 80, 30);
		zacznij.addActionListener(new Zacznij());
		 
		wyniki = new JButton("Wyniki");
		wyniki.setMargin(insets);
		wyniki.setFocusable(false);
		wyniki.setBounds(407, 130, 80, 30);
		wyniki.addActionListener(new WynikiO());
		
		zakoncz = new JButton("Zakoñcz");
		zakoncz.setMargin(insets);
		zakoncz.setFocusable(false);
		zakoncz.setBounds(407, 210, 80, 30);
		zakoncz.addActionListener(new Zakoncz());
		 
		add(zacznij);
		add(wyniki);
		add(zakoncz);
		
		setVisible(true);

	}
		public void paint(Graphics g) {														///// LEWE ZDJECIE MENU Z ZASADAMI ////
		       super.paintComponents(g); 
	        g.drawImage(menuzdj, 5, 25, null);													// "narysowanie" zdjecia
    }
	
		
		private class Zacznij implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Snake();
				zacznij.setEnabled(false);
				wyniki.setEnabled(false);
				Widok.timer.start();
			}
		}
		
		private class WynikiO implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				new OknoWyniki();
			}
		}
		
		private class Zakoncz implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		}
		
																				//////////////////////////////////////// MAIN ////////////////////////////////////////
		
		
		public static void main(String[] args) {
			new Menu();																					//zaczynamy startem okna menu
		}
}
		

