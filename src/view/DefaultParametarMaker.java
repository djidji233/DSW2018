package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.MainFrame;
import command.AddCommand;
import tree.model.Modul;
import tree.model.Parametar;
import tree.model.Proizvod;


public class DefaultParametarMaker extends JFrame {
	private String[] ponudjeni = {"naziv","autor","logo","uslovi koriscenja", "look and feel", "desktop shortcut", "pokretanje nakon instalacije"};
	private String chosen="";
	private JComboBox<String> cb;
	private JButton btn;

	public DefaultParametarMaker(Parametar p,Object o) {
		setTitle("Default Parametar");
		setSize(400, 150);
		setLocationRelativeTo(null);
		
		cb = new JComboBox(ponudjeni);
		btn = new JButton("Choose");
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(10,10));
		panel.add(new JLabel("Izaberite neki od predefinisanih parametara"), BorderLayout.NORTH);
		panel.add(cb, BorderLayout.CENTER);
		panel.add(btn, BorderLayout.SOUTH);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chosen = (String) cb.getSelectedItem();
				if(chosen.equals(""))
					System.out.println("greska kod DefaultParametarMaker");
				p.setType(chosen);
				p.setCustom(false);
				p.setName("Parametar "+p.getType());
				if(o instanceof Proizvod) {
					Proizvod parent = (Proizvod) o;
					parent.add(p);
					MainFrame.getInstance().getCommandManager().addCommand(new AddCommand(p));
					MainFrame.getInstance().getTreeModel().nodeStructureChanged((Proizvod) o);
				} else if( o instanceof Modul) {
					Modul parent = (Modul) o;
					parent.add(p);
					MainFrame.getInstance().getCommandManager().addCommand(new AddCommand(p));
					MainFrame.getInstance().getTreeModel().nodeStructureChanged((Modul) o);
				}
				System.out.println("Dodao parametar: " + p.getName() + ", tipa: " + p.getType());
				setVisible(false);
				dispose();
			}
		});
		this.add(panel);
	}
}
