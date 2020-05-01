package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.MainFrame;
import command.AddCommand;
import tree.model.Modul;
import tree.model.Parametar;
import tree.model.Proizvod;

public class CustomParametarMaker extends JFrame {
	private JTextField nameTf = new JTextField();
	private JTextField typeTf = new JTextField();
	private JTextField labelTf = new JTextField();
	private JTextField guiTf = new JTextField();
	private JTextField contentTf = new JTextField();
	private JButton btn = new JButton("Create");
	
	public CustomParametarMaker(Parametar p, Object o) {
		setTitle("Custom Parametar");
		setSize(400, 300);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		GridLayout gp = new GridLayout(6, 2, 10, 10);
		panel.setLayout(gp);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(new JLabel("Name: "));
		panel.add(nameTf);
		panel.add(new JLabel("Type: "));
		panel.add(typeTf);
		panel.add(new JLabel("Label: "));
		panel.add(labelTf);
		panel.add(new JLabel("Gui: "));
		panel.add(guiTf);
		panel.add(new JLabel("Content: "));
		panel.add(contentTf);
		panel.add(new JLabel(""));
		panel.add(btn);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setName(nameTf.getText());
				p.setType(typeTf.getText());
				p.setGui(guiTf.getText());
				p.setCustom(true);
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
