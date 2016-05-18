package SNAKE;

import javax.swing.JFrame;
import java.awt.Point;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class Snake extends JFrame {
	 
		private static final long serialVersionUID = 1L;
		
	static JButton start;
	static JButton wyniki;
	static JButton menu;
	static JLabel wynik;
	private JButton zakoncz;
	private JLabel wynikTekst;
	 
	public Snake() {																//// OKNO GRY ////
	 
		super("Snake");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setLocation(new Point(240, 120));
		 
		Insets insets = new Insets(1,1,1,1);
																						//Przyciski
		start = new JButton("Start");
		start.setMargin(insets);
		start.setFocusable(false);
		start.setBounds(407, 90, 80, 30);
		start.addActionListener(new Start());
		 
		wyniki = new JButton("Wyniki");
		wyniki.setMargin(insets);
		wyniki.setFocusable(false);
		wyniki.setBounds(407, 130, 80, 30);
		wyniki.addActionListener(new WynikiO());
		 
		menu = new JButton("Menu");
		menu.setMargin(insets);
		menu.setFocusable(false);
		menu.setBounds(407, 170, 80, 30);
		menu.addActionListener(new MenuO());
		
		zakoncz = new JButton("Zakoñcz");
		zakoncz.setMargin(insets);
		zakoncz.setFocusable(false);
		zakoncz.setBounds(407, 210, 80, 30);
		zakoncz.addActionListener(new Zakoncz());
		 
		wynikTekst = new JLabel("Wynik:");												//tekst z wynikami naszymi biezacymi
		wynikTekst.setBounds(406, 30, 40, 30);
		 
		wynik = new JLabel("0");
		wynik.setBounds(450, 30, 42, 30);
		 
		Widok widok = new Widok();														//pole gry
		widok.setBounds(0, 0, 400, 372);
		 
		add(widok);
		add(start);
		add(menu);
		add(wyniki);
		add(wynikTekst);
		add(zakoncz);
		add(wynik);
		 
		setVisible(true);
	}
																				//// AKCJE PO NACISNIECIU PRZYCISKOW ////	
	private class MenuO implements ActionListener {										//zatrzymanie gry i otwarcie menu
		public void actionPerformed(ActionEvent e) {
			Widok.timer.stop();
			dispose();
			new Menu();
		}
	}
	
	private class Zakoncz implements ActionListener {									//zakonczenie gry
		public void actionPerformed(ActionEvent e) {
			Widok.timer.stop();
			dispose();
		}
	}
	 
	private class Start implements ActionListener {										//rozpoczecie gry i zablokowanie przyciskow
		public void actionPerformed(ActionEvent e) {
			start.setEnabled(false);
			wyniki.setEnabled(false);
			Widok.timer.start();
		}
	}
	 
	private class WynikiO implements ActionListener {									//Otwarcie okna ze zbiorczymi wynikami
		public void actionPerformed(ActionEvent e) {
			new OknoWyniki();
		}
	}
}