package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import app.MainFrame;
import tree.model.Parametar;
import tree.model.Project;

public class Tab extends JPanel {
	private Parametar parametar;
	private JPanel panel;
	private JTextField nameTf = new JTextField();
	private JTextField typeTf = new JTextField();
	private JComboBox<String> guiCbx = new JComboBox<>();
	private JTextField contentTf = new JTextField();
	private JButton btn = new JButton("Confirm");
	private JCheckBox desktopstct = new JCheckBox("Desktop shortcut");
	private JCheckBox pokretanjenakon = new JCheckBox("Pokretanje nakon instalacije");
	private JComboBox<String> looknfeel;

	public Tab(Parametar p) {
		parametar = p;
		nameTf.setText(p.getName());

		typeTf.setText(p.getType());

		// guiTf.setText(p.getGui());
		guiCbx.addItem("Radio button");
		guiCbx.addItem("Text field");
		guiCbx.addItem("Check box");
		guiCbx.addItem("Combo box");
		GridLayout gp = new GridLayout(6, 2, 10, 10);
		setLayout(gp);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		add(new JLabel("Name: "));
		add(nameTf);

		if (p.isCustom()) {
			nameTf.setEditable(false);
			typeTf.setEditable(false);
			add(new JLabel("Label: "));
			add(contentTf);
			add(guiCbx);
		} else {
			add(new JLabel("Type: "));
			add(typeTf);
			nameTf.setEditable(false);
			typeTf.setEditable(false);
			if (p.getType().equals("naziv")) {
				add(new JLabel("Naziv: "));
				add(contentTf);
			} else if (p.getType().equals("autor")) {
				add(new JLabel("Autor: "));
				add(contentTf);
			} else if (p.getType().equals("logo")) {
				add(new JLabel("Logo path: "));
				add(contentTf);
			} else if (p.getType().equals("uslovi koriscenja")) {
				add(new JLabel("Txt file path: "));
				add(contentTf);
			} else if (p.getType().equals("look and feel")) {
				looknfeel = new JComboBox<>();
				looknfeel.addItem("cross platform");
				looknfeel.addItem("motif");
				looknfeel.addItem("system");
				looknfeel.addItem("nimbus");
				add(looknfeel);
			} else if (p.getType().equals("desktop shortcut")) {
				add(desktopstct);
			} else if (p.getType().equals("pokretanje nakon instalacije")) {
				add(pokretanjenakon);
			}
		}

		add(btn);
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (p.isCustom()) {
					parametar.setName(nameTf.getText());
					//parametar.set(contentTf.getText());
					parametar.setGui((String) guiCbx.getSelectedItem());
				} else {
					if (p.getType().equals("naziv")) {
						parametar.setNaziv(contentTf.getText());
					} else if (p.getType().equals("autor")) {
						parametar.setAutor(contentTf.getText());
					} else if (p.getType().equals("logo")) {
						parametar.setLogoPath(contentTf.getText());
					} else if (p.getType().equals("uslovi koriscenja")) {
						parametar.setTermsOfUse(contentTf.getText());
					} else if (p.getType().equals("look and feel")) {
						parametar.setLookAndFeel((String)looknfeel.getSelectedItem());
					} else if (p.getType().equals("desktop shortcut")) {
						if (desktopstct.isSelected())
							parametar.setDesktopShortcut(true);
						else
							parametar.setDesktopShortcut(false);
					} else if (p.getType().equals("pokretanje nakon instalacije")) {
						if (pokretanjenakon.isSelected())
							parametar.setRunAfterInstalation(true);
						else
							parametar.setRunAfterInstalation(false);
					}
				}

			}
		});

	}

	public Parametar getParametar() {
		return parametar;
	}

	public void setParametar(Parametar project) {
		this.parametar = project;
	}

}
