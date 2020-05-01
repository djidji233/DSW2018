package actions;

import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import app.MainFrame;
import command.PasteCommand;
import tree.model.Modul;
import tree.model.Parametar;
import tree.model.ParametarElementsSelection;
import tree.model.Proizvod;

public class PasteAction extends AbstractGlavnaAction {
	PasteAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		// putValue(SMALL_ICON, loadIcon("icons/paste.png"));
		putValue(NAME, "Paste");
		putValue(SHORT_DESCRIPTION, "Paste");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Transferable clipboardContent = MainFrame.getInstance().getClipboard().getContents(MainFrame.getInstance());

		if ((clipboardContent != null)
				&& (clipboardContent.isDataFlavorSupported(ParametarElementsSelection.elementFlavor))) {
			Object obj = MainFrame.getInstance().getTree().getLastSelectedPathComponent();
			if (obj == null) {
				JOptionPane.showMessageDialog(MainFrame.getInstance(),
						"Moras selektovati na koji cvor zelis da paste-ujes", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			List<Parametar> listParametar = new ArrayList<>();
			try {
				ArrayList<Parametar> elements = (ArrayList<Parametar>) clipboardContent
						.getTransferData(ParametarElementsSelection.elementFlavor);
				if (obj instanceof Proizvod) {
					Proizvod p = (Proizvod) obj;
					for (int i = 0; i < elements.size(); i++) {
						Parametar param = elements.get(i);
						p.add(param);
						param.setParent(p);
						listParametar.add(param);
						System.out.println("uspeo paste na Proizvod");
					}
					MainFrame.getInstance().getTreeModel().nodeStructureChanged((Proizvod) obj);
				} else if (obj instanceof Modul) {
					Modul m = (Modul) obj;
					for (int i = 0; i < elements.size(); i++) {
						Parametar param = elements.get(i);
						m.add(param);
						param.setParent(m);
						listParametar.add(param);
						System.out.println("uspeo paste na Modul");
					}
					MainFrame.getInstance().getTreeModel().nodeStructureChanged((Modul) obj);
				} else {
					JOptionPane.showMessageDialog(MainFrame.getInstance(),
							"Ne moze da se paste-uje na izabrani tip cvora", "Error", JOptionPane.ERROR_MESSAGE);
				}
				MainFrame.getInstance().getCommandManager().addCommand(new PasteCommand(listParametar));
				// SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree()); opet
				// ovo zeza
			} catch (Exception e2) {
				System.out.println("exception u PastAction");
			}
		}
	}

}
