package SNAKE;

import javax.swing.JDialog;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Point;
import java.util.StringTokenizer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class OknoWyniki extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea pole;
	private JButton ok;
	private String tekst = "";
	
	public OknoWyniki() {
																						//wyglad okienka wynikow
		setTitle("Wyniki");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setSize(280, 260);
		setResizable(false);
		setModal(true);
		setLocation(new Point(310, 200));
		
		pole = new JTextArea();
		pole.setBounds(70, 15, 160, 170);
		pole.setEditable(false);
		pole.setBackground(new Color(238,238,238));
		pole.setFont(new Font("Verdana", Font.BOLD, 12));
		
		ok = new JButton("OK");
		ok.setBounds(115, 190, 50, 30);
		ok.setFocusable(false);
		ok.setMargin(new Insets(1,1,1,1));
		ok.addActionListener(new Close());
		
		wyswietlWyniki();
		
		add(pole);
		add(ok);
		
		setVisible(true);
	}
	
	public void wyswietlWyniki() {
		
		try {
																						//odczytanie z pliku
			FileReader fr = new FileReader("plik.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String tmp = "";
			int i = 1;
			while ((tmp = br.readLine()) != null) {										//parsowanie tekstu
				StringTokenizer st = new StringTokenizer(tmp);
				tekst += String.valueOf(i) + ". ";
				
				while (st.hasMoreTokens()) {
					String temp = st.nextToken();
					if (temp.equals(";"))
						tekst += " -  " + st.nextToken() + "\n";
					else
						tekst += temp + " ";
				}
				++i;
				
				pole.setText(tekst);													//wyswietlenie wynikow w okienku
			}
			
			br.close();
		}	
		catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private class Close implements ActionListener {										//zamkniecie okna wynikow
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}