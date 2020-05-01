package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import app.MainFrame;

public class StatusBar extends JPanel {
	public StatusBar() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		Border b = BorderFactory.createLoweredBevelBorder();
		p1.setBorder(b); p2.setBorder(b); p3.setBorder(b); p4.setBorder(b);
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		//String d = sdf.format(date);
		p1.add(new JLabel("Datum i vreme: (" + sdf.format(date) + ")"));
		p2.add(new JLabel("Korisnik: " + MainFrame.getInstance().getUser()));
		p3.add(new JLabel("Akcija: < Naziv komandne akcije >"));
		p3.setBackground(Color.ORANGE);
		p4.add(new JLabel("Status: < Ready >"));
		p4.setBackground(Color.YELLOW);
		
		add(p1); add(p2); add(p3); add(p4);
	}
}
