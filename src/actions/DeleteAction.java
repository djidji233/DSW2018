package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import app.MainFrame;
import command.DeleteCommand;
import tree.model.Cvor;
import tree.model.Modul;
import tree.model.Parametar;
import tree.model.Proizvod;
import tree.model.Project;
import tree.model.SoftverskaKompanija;
import tree.model.TreeModel;

public class DeleteAction extends AbstractGlavnaAction {
	public DeleteAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("icons/delete.png"));
		putValue(NAME, "Delete");
		putValue(SHORT_DESCRIPTION, "Delete");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// opet i radi i ne radi, tj kad "ne radi" obrise ali  mu
		// opet baci null i swingUtilities.updateTreeUI, a i nodeStructureChanged... ?!?!
		Object o = MainFrame.getInstance().getTree().getLastSelectedPathComponent();
		if (o == null) {
			int dialog = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
					"You are about to delete the whole tree. Continue?");
			if (dialog == JOptionPane.YES_OPTION) {
				((TreeModel) MainFrame.getInstance().getTree().getModel()).delete();
			}
		}
		if (o instanceof Project) {
			Project p = (Project) o;
			if (p.getChildCount() != 0) {
				int dialog = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
						"This is the root of the JTree. It has " + p.getChildCount()
								+ " children. You will delete everything. Continue?");
				if (dialog == JOptionPane.YES_OPTION) {
					MainFrame.getInstance().getCommandManager().addCommand(new DeleteCommand(p, 0));
					((TreeModel) MainFrame.getInstance().getTree().getModel()).delete();
					MainFrame.getInstance().getDesnoGore().getTabPane().removeAll();
					return;
				}
			} else {
				int dialog = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
						"This is the root of the JTree. It has no children. You will delete everything. Continue?");
				if (dialog == JOptionPane.YES_OPTION) {
					MainFrame.getInstance().getCommandManager().addCommand(new DeleteCommand(p, 0));
					((TreeModel) MainFrame.getInstance().getTree().getModel()).delete();
					MainFrame.getInstance().getDesnoGore().getTabPane().removeAll();
					return;
				}
			}
		} else if (o instanceof SoftverskaKompanija) {
			SoftverskaKompanija s = (SoftverskaKompanija) o;
			Project parent = (Project) s.getParent();
			if (s.getChildCount() != 0) {
				int dialog = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
						"This node has " + s.getChildCount() + " children. You will delete them. Continue?");
				if (dialog == JOptionPane.YES_OPTION) {
					MainFrame.getInstance().getCommandManager().addCommand(new DeleteCommand(s, parent.getIndex(s)));
					parent.remove(s);
					MainFrame.getInstance().getTreeModel().nodeStructureChanged(parent);
				}
			} else {
				// leaf je pa samo remove
				MainFrame.getInstance().getCommandManager().addCommand(new DeleteCommand(s, parent.getIndex(s)));
				parent.remove(s);
				MainFrame.getInstance().getTreeModel().nodeStructureChanged(parent);
				//SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());

				

			}
		} else if (o instanceof Proizvod) {
			Proizvod p = (Proizvod) o;
			SoftverskaKompanija parent = (SoftverskaKompanija) p.getParent();
			if (p.getChildCount() != 0) {
				int dialog = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
						"This node has " + p.getChildCount() + " children. You will delete them. Continue?");
				if (dialog == JOptionPane.YES_OPTION) {
					MainFrame.getInstance().getCommandManager().addCommand(new DeleteCommand(p, parent.getIndex(p)));
					parent.remove(p);
					MainFrame.getInstance().getTreeModel().nodeStructureChanged(parent);
				}
			} else {
				// leaf je pa samo remove
				MainFrame.getInstance().getCommandManager().addCommand(new DeleteCommand(p, parent.getIndex(p)));
				parent.remove(p);
				SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
				//MainFrame.getInstance().getTreeModel().nodeStructureChanged(parent);
			}
		} else if (o instanceof Modul) {
			Modul m = (Modul) o;
			Proizvod parent = (Proizvod) m.getParent();
			if (m.getChildCount() != 0) {
				int dialog = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
						"This node has " + m.getChildCount() + " children. You will delete them. Continue?");
				if (dialog == JOptionPane.YES_OPTION) {
					MainFrame.getInstance().getCommandManager().addCommand(new DeleteCommand(m, parent.getIndex(m)));
					parent.remove(m);
					MainFrame.getInstance().getTreeModel().nodeStructureChanged(parent);
				}
			} else {
				// leaf je pa samo remove
				MainFrame.getInstance().getCommandManager().addCommand(new DeleteCommand(m, parent.getIndex(m)));
				parent.remove(m);
				MainFrame.getInstance().getTreeModel().nodeStructureChanged(parent);
			}
		} else if (o instanceof Parametar) {
			// zeza parent jer je TreeNode
			Parametar p = (Parametar) o;
			if (p.getParent() == null)
				System.out.println("GRESKA!!! parent je null");
			
			Cvor parent = p.getParent();
			MainFrame.getInstance().getCommandManager().addCommand(new DeleteCommand(p, parent.getIndex(p)));
			parent.remove(p);
			//p.removeFromParent();
			MainFrame.getInstance().getTreeModel().nodeStructureChanged(parent);
			//SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());

		}

	}
}