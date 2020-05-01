package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;

import app.MainFrame;
import command.AddCommand;
import events.Lisnr;
import tree.model.Modul;
import tree.model.Parametar;
import tree.model.Proizvod;
import tree.model.Project;
import tree.model.SoftverskaKompanija;
import view.CustomParametarMaker;
import view.DefaultParametarMaker;

public class NewAction extends AbstractGlavnaAction {
	private int brS = 1;
	private int brPr = 1;
	private int brM = 1;
	private int brPar = 1;

	public NewAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("icons/new.png"));
		putValue(NAME, "New");
		putValue(SHORT_DESCRIPTION, "New");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = MainFrame.getInstance().getTree().getLastSelectedPathComponent();
		if (o == null && MainFrame.getInstance().getTreeModel().getRoot() == null) {
			Project novi = new Project();
			novi.setName("Workspace");
			MainFrame.getInstance().getTreeModel().addProject(novi);
			MainFrame.getInstance().getCommandManager().addCommand(new AddCommand(novi));
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
		} else if (o instanceof Project) {
			SoftverskaKompanija novi = new SoftverskaKompanija();
			novi.setName("Softverska kompanija " + brS++);
			Project parent = (Project) MainFrame.getInstance().getTreeModel().getRoot(); // o
			parent.add(novi);
			MainFrame.getInstance().getCommandManager().addCommand(new AddCommand(novi));
			MainFrame.getInstance().getTreeModel().nodeStructureChanged((Project) o);
		} else if (o instanceof SoftverskaKompanija) {
			Proizvod novi = new Proizvod();
			novi.setName("Proizvod" + brPr++);
			SoftverskaKompanija parent = (SoftverskaKompanija) o;
			parent.add(novi);
			MainFrame.getInstance().getCommandManager().addCommand(new AddCommand(novi));
			MainFrame.getInstance().getTreeModel().nodeStructureChanged((SoftverskaKompanija) o);
		} else if (o instanceof Proizvod) {
			String[] options = new String[] { "Modul", "Default Parametar", "Custom Parametar" };
			int response = JOptionPane.showOptionDialog(null, "Izaberite sta zelite da dodate", "Choose",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, o);
			if (response == 0) {
				Modul novi = new Modul();
				novi.setName("Modul " + brM++);
				Proizvod parent = (Proizvod) o;
				parent.add(novi);
				MainFrame.getInstance().getCommandManager().addCommand(new AddCommand(novi));
			} else if (response == 1) {
				Parametar novi = new Parametar();
				//novi.addListener(MainFrame.getInstance().getDesnoDole());
				DefaultParametarMaker dpm = new DefaultParametarMaker(novi, o);
				dpm.setVisible(true);
			} else if (response == 2) {
				Parametar novi = new Parametar();
				//novi.addListener(MainFrame.getInstance().getDesnoDole());
				CustomParametarMaker cpm = new CustomParametarMaker(novi, o);
				cpm.setVisible(true);
			}
			MainFrame.getInstance().getTreeModel().nodeStructureChanged((Proizvod) o);
		} else if (o instanceof Modul) {
			String[] options = new String[] {"Default Parametar", "Custom Parametar" };
			int response = JOptionPane.showOptionDialog(null, "Izaberite sta zelite da dodate", "Choose",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, o);
			if (response == 0) {
				Parametar novi = new Parametar();
				//novi.addListener(MainFrame.getInstance().getDesnoDole());
				DefaultParametarMaker dpm = new DefaultParametarMaker(novi, o);
				dpm.setVisible(true);
			} else if (response == 1) {
				Parametar novi = new Parametar();
				//novi.addListener(MainFrame.getInstance().getDesnoDole());
				CustomParametarMaker cpm = new CustomParametarMaker(novi, o);
				cpm.setVisible(true);
			}
			MainFrame.getInstance().getTreeModel().nodeStructureChanged((Modul) o);
		} else if (o instanceof Parametar) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "Parametar je leaf", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
