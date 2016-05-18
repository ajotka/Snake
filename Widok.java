package SNAKE;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.Timer;
 
class Widok extends JPanel implements ActionListener {
	 
	private static final long serialVersionUID = 1L;
	
	private Image head;
	private Image headright;
	private Image headup;
	private Image headdown;
	private Image dot;
	private Image apple;
	private Image pear;
	private Image banda;
	private int apple_x;
	private int apple_y;
	private int pear_x;
	private int pear_y;
	private int tmp = 0;
	private final int WYSOKOSC = 350;
	private final int SZEROKOSC = 380;
	private final int DELAY = 350;
	private boolean left = true;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private ArrayList<Integer> x,y;
	static int dots;
	static int wynik = 0;
	static Timer timer;
	static boolean status = true;
	 
	public Widok() {
	 
		addKeyListener(new KAdapter());
		 																					//// GRAFIKA /////
		setBackground(Color.black);
		 
		ImageIcon ih = new ImageIcon(this.getClass().getResource("head.jpg"));
		head = ih.getImage();
		ImageIcon ihr = new ImageIcon(this.getClass().getResource("headright.jpg"));
		headright = ihr.getImage();
		ImageIcon ihu = new ImageIcon(this.getClass().getResource("headleftup.jpg"));
		headup = ihu.getImage();
		ImageIcon ihd = new ImageIcon(this.getClass().getResource("headleftdown.jpg"));
		headdown = ihd.getImage();
		 
		ImageIcon ib = new ImageIcon(this.getClass().getResource("body.jpg"));
		dot = ib.getImage();
		 
		ImageIcon ia = new ImageIcon(this.getClass().getResource("apple.jpg"));
		apple = ia.getImage();
		
		ImageIcon ip = new ImageIcon(this.getClass().getResource("pear.jpg"));
		pear = ip.getImage();
		 
		ImageIcon ic = new ImageIcon(this.getClass().getResource("banda.jpg"));
		banda = ic.getImage();
		
		setFocusable(true);
		initGame();																					//rozpoczecie gry - start ruchu
	}
	 
	public void initGame() {																//// INICJACJA GRY ////
		x = new ArrayList<Integer>();																//wspolrzedne kazdego kawalka ciala
		y = new ArrayList<Integer>();
		 
		dots = 3;																					//ilosc punktow ciala na starcie | 0 = glowa
		 
		for (int i = 0; i < dots; i++) {
			x.add(180 + i*10);																		//dodanie polozen tego ciala
			y.add(180);
		}
		 
		locateApple();
		locatePear();
		 
		timer = new Timer(DELAY, this);																//timer (pozniej: przyspieszenie)
	}
	 
	public void paint(Graphics g) {															//// GRAFICZNE PRZEDSTAWIENIE RUCHU I ELEMENTOW ////
		super.paint(g);
		
		 
		if (status) {
			g.drawImage(apple, apple_x, apple_y, this);												//dodawanie jablka
			
			if (((wynik % 50) == 0) && (wynik != 0)){												//dodawanie gruszki co 50 pkt
				g.drawImage(pear, pear_x, pear_y, this);
			}

			for (int i = 0; i < dots; i++) {
				if (i == 0) {																		//obracanie glowki razem z klawiszami
					if ((right) && (!left))
						g.drawImage(headright, x.get(i), y.get(i), this);
					else
						if ((up) && (!down))
							g.drawImage(headup, x.get(i), y.get(i), this);
						else
							if ((down) && (!up))
								g.drawImage(headdown, x.get(i), y.get(i), this);
							else
								g.drawImage(head, x.get(i), y.get(i), this);
				}
				else {
					g.drawImage(dot, x.get(i), y.get(i), this);										//rysowanie snake'a
				}
			}
		}
		 					//390 bo taka szerokosc pola
		for (int i = 0; i <= 390; i += 10) {														//rysowanie bandy poziomej
			g.drawImage(banda, i, 0, this);
			g.drawImage(banda, i, 360, this);
		}
		for (int i = 10; i < 360; i += 10) {														//rysowanie bandy pionowej
			g.drawImage(banda, 0, i, this);
			g.drawImage(banda, 390, i, this);
		}
	}
	 
	public void checkCollision() {																//// SPRAWDZANIE KOLIZJI ////
	 
		for(int i=1; i<dots; i++){																	//zjadanie swojego ogona
			if((x.get(i).equals(x.get(0))) && (y.get(i).equals(y.get(0)))) {						//czy dwa kawalki nie maja tych samych wspolrzednych
				status = false;
				break;
			}
		}
		 
		if (x.get(0) > SZEROKOSC)																	//uderzenie w bande
		status = false;
		 
		if (x.get(0) < 10)
		status = false;
		 
		if (y.get(0) > WYSOKOSC)
		status = false;
		 
		if (y.get(0) < 10)
		status = false;
	}
	
	public void move() {																		//// RUCH POSZCZEGÓLNYCH ELEMENTÓW SNAKE'a ////
	 
		for (int i = dots-1; i > 0; i--) {															
				
				if ((wynik >= 50) && (wynik < 100)){												//co 50 pkt
					timer.setDelay(200);															//zmiana czasu wykonywania ruchu
				}
				if ((wynik >= 100) && (wynik < 150)){
					timer.setDelay(150);
				}
				if ((wynik >= 150) && (wynik < 200)){
					timer.setDelay(100);
				}
				if ((wynik >= 200) && (wynik < 250)){
					timer.setDelay(80);
				}
				if ((wynik >= 250) && (wynik < 300)){
					timer.setDelay(60);
				}
				if ((wynik >= 3000) && (wynik < 350)){
					timer.setDelay(40);
				}
			
			x.set(i, x.get(i-1));																	//przesuwanie ciala	
			y.set(i, y.get(i-1));
		}
		 
		if (left) {																					//przesuwanie glowy
			tmp = x.get(0);																			//pobiera po³o¿enie
			x.set(0, tmp-10);																		//przesuwa o 10px
		}
		if (right) {
			tmp = x.get(0);
			x.set(0, tmp+10);
		}
		if (up) {
			tmp = y.get(0);
			y.set(0, tmp-10);
		}
		if (down) {
			tmp = y.get(0);
			y.set(0, tmp+10);
		}
	 
	}
	 
	public void locateApple() {																	//// USTAWIENIE JABLKA ////
		boolean bl = true;
		while (bl) {
			int r = (int) (Math.random()*38+1);														//randomowa pozycja
			apple_x = r*10;
			r = (int) (Math.random()*35+1);															//generuje liczby od 1 do 35
			apple_y = r*10;																			// 10 od bandy | do bandy 350
			 
			if ((x.contains(apple_x)) && (y.contains(apple_y)))										//sprawdza czy jablko nie bedzie na wezu
				continue;
			else
				bl = false;
		}
	}
	 
	public void checkApple() {																	//// ZJEDZENIE JABLKA ////
		if ((x.get(0) == apple_x) && (y.get(0) == apple_y)) {									
			++dots;																					// 1 jablko = 1 ogon
			wynik += 10;																			// 1 jablko = 10 pkt
			Snake.wynik.setText(String.valueOf(wynik));
			 
			x.add(x.get(1));																		//pobiera wspolrzedne
			y.add(y.get(1));
			 
			locateApple();																			//wstawia nowe jablko
		}
	}
	
	public void locatePear() {
		boolean pl = true;
		while (pl) {
			int r = (int) (Math.random()*38+1);
			pear_x = r*10;
			r = (int) (Math.random()*35+1);
			pear_y = r*10;
			 
			if ((x.contains(pear_x)) && (y.contains(pear_y)))
				continue;
			else
				pl = false;
		}
	}
	
	public void checkPear() {
		if ((x.get(0) == pear_x) && (y.get(0) == pear_y)) {
			++dots;
			wynik += 20;
			Snake.wynik.setText(String.valueOf(wynik));
			 
			x.add(x.get(1));
			y.add(y.get(1));
			 
			locatePear();
		}
	}
	 
	public void actionPerformed(ActionEvent e) {												//// AKCJE PODCZAS GRY I PODCZAS ZAKOÑCZONEJ GRY
		if (status) {																				//jesli gra wlaczona
			checkApple();
			checkPear();
			checkCollision();
			move();
			repaint();
		}
		else {																						//przy kolizji lub koncu gry
			timer.stop();																			//zatrzymanie i wyzerowanie do stanu poczatkowego
			x.clear();
			y.clear();
			dots = 3;
			 
			for (int i = 0; i < dots; i++) {
				x.add(180 + i*10);
				y.add(180);
			}
		 
			status = true;
			left = true;
			right = false;
			up = false;
			down = false;
			 
			Snake.start.setEnabled(true);
			Snake.wyniki.setEnabled(true);
			 
			repaint();
			 
			new KoniecGry();
		}
	}
	 
	private class KAdapter extends KeyAdapter {													//// STEROWANIE KLAWIATURA ////
	 
		public void keyPressed(KeyEvent e) {														//nacisniecie klawisza
			 
			int key = e.getKeyCode();																//pobranie ktory klawisz
			 
			if ((key == KeyEvent.VK_LEFT) && (!right)) {											//zdefiniowanie w ktora strone
				left = true;
				up = false;
				down = false;
			}
			 
			if ((key == KeyEvent.VK_RIGHT) && (!left)) {
				right = true;
				up = false;
				down = false;
				
			}
			 
			if ((key == KeyEvent.VK_UP) && (!down)) {
				up = true;
				right = false;
				left = false;
			}
			 
			if ((key == KeyEvent.VK_DOWN) && (!up)) {
				down = true;
				right = false;
				left = false;
			}
		}
	}
}