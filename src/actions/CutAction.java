package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import app.MainFrame;
import command.CutCommand;
import tree.model.Modul;
import tree.model.Parametar;
import tree.model.ParametarElementsSelection;
import tree.model.Proizvod;

public class CutAction extends AbstractGlavnaAction {

	CutAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		//putValue(SMALL_ICON, loadIcon("icons/cut.png"));
		putValue(NAME, "Cut");
		putValue(SHORT_DESCRIPTION, "Cut");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// copy
		ArrayList<Parametar> p = new ArrayList<>();
		List<Integer> lista = new ArrayList<>();
		TreePath[] selektovani = null;
		if (MainFrame.getInstance().getTree().getSelectionPaths() != null) {
			selektovani = MainFrame.getInstance().getTree().getSelectionPaths();
			for (int i = 0; i < selektovani.length; i++) {
				Object o = selektovani[i].getLastPathComponent();
				if (o instanceof Parametar) {
					Parametar p1 = (Parametar) o;
					Parametar p2 = new Parametar(p1);
					p.add(p2);
				} else {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Samo parametri mogu da se kopiraju",
							"Error", JOptionPane.ERROR_MESSAGE);
					System.out.println("Greska u CopyAction - selektovano je nesto sto nije Parametar");
					return;
				}
			}
			ParametarElementsSelection contents = new ParametarElementsSelection(p);
			MainFrame.getInstance().getClipboard().setContents(contents, MainFrame.getInstance());
		} else {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "Moras da selektujes nesto da bi kopirao", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		System.out.println("uspeo da kopiram: " + p + "; sledi delete deo CutActiona");
		// delete
		for (int i = 0; i < selektovani.length; i++) {
			Object o = selektovani[i].getLastPathComponent();
			Parametar pom = null;
			if (o instanceof Parametar)
				pom = (Parametar) o;
			
			if (pom.getParent() == null)
				System.out.println("GRESKA!!! parent je null");
			//PROBLEM
			Object obj = pom.getParent();
			if (obj instanceof Modul) {
				Modul m = (Modul) obj;
				lista.add(m.getIndex(pom));
				m.remove(pom);
			} else if (obj instanceof Proizvod) {
				Proizvod proizvod = (Proizvod) obj;
				lista.add(proizvod.getIndex(pom));
				proizvod.remove(pom);
			}
			//lista.add(pom.getParent().getIndex(pom));
			//pom.removeFromParent(); 
			//baci onaj ludi null exception ali obrise sve lepo i moze da se pasteuje na drugo mesto
		}
		MainFrame.getInstance().getCommandManager().addCommand(new CutCommand(p, lista));
		MainFrame.getInstance().getTreeModel().nodeStructureChanged((TreeNode) MainFrame.getInstance().getTreeModel().getRoot());
		//SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree()); // opet ovo baca null
	}
}
