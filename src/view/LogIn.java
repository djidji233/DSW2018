package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.MainFrame;

public class LogIn extends JFrame {
	private static LogIn instance = null;
	private String user;
	private JTextField nameTf = new JTextField();
	private JPasswordField passTf = new JPasswordField();
	private JButton btn = new JButton("Confirm");

	private LogIn() {

	}

	public static LogIn getInstance() {
		if (instance == null) {
			instance = new LogIn();
			instance.initialise();
		}
		return instance;
	}

	private void initialise() {
		setTitle("Log In");
		setSize(400, 200);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		GridLayout gp = new GridLayout(3, 2, 10, 10);
		panel.setLayout(gp);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(new JLabel("username : "));
		panel.add(nameTf);
		panel.add(new JLabel("password : "));
		panel.add(passTf);
		panel.add(new JLabel("(admin,admin) ili (user,user)"));
		panel.add(btn);
		btn.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				Scanner sc;
				boolean korisnik = false;
				try {
					sc = new Scanner(new File("LogInPodaci"));
					while (sc.hasNextLine()) {
						String linija[] = sc.nextLine().split(",");
						if (nameTf.getText().equals(linija[0]) && passTf.getText().equals(linija[1])) {
							// ograniciti pristup useru... admin moze sve?
							korisnik = true;
							user = linija[0];
							MainFrame.getInstance().setUser(user);
							MainFrame.getInstance().setVisible(true);
							setVisible(false);
							dispose();
							break;
						}
					}
					if (korisnik == false) {
						JOptionPane.showMessageDialog(null,
								"User with this username or password doesn't exist", "Error",
								JOptionPane.ERROR_MESSAGE);
						nameTf.setText("");
						passTf.setText("");
					}
				} catch (FileNotFoundException e1) {
					System.out.println("Greska kod LogIna");
					e1.printStackTrace();
				}
			}
		});
		this.add(panel);
		this.getRootPane().setDefaultButton(btn);
	}
	public String getUser() {
		return user;
	}
	
}
