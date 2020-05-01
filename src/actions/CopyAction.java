package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import app.MainFrame;
import tree.model.Parametar;
import tree.model.ParametarElementsSelection;

public class CopyAction extends AbstractGlavnaAction {

	CopyAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		//putValue(SMALL_ICON, loadIcon("icons/copy.png"));
		putValue(NAME, "Copy");
		putValue(SHORT_DESCRIPTION, "Copy");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (MainFrame.getInstance().getTree().getSelectionPaths() != null) {
			TreePath[] selektovani = MainFrame.getInstance().getTree().getSelectionPaths();
			ArrayList<Parametar> p = new ArrayList<>();
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
			System.out.println("Kopirao: " + p);
			ParametarElementsSelection contents = new ParametarElementsSelection(p);
			MainFrame.getInstance().getClipboard().setContents(contents, MainFrame.getInstance());
		} else {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "Moras da selektujes nesto da bi kopirao", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
