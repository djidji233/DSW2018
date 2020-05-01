package tree.editor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

import app.MainFrame;
import command.RenameCommand;
import tree.model.Modul;
import tree.model.Parametar;
import tree.model.Proizvod;
import tree.model.Project;
import tree.model.SoftverskaKompanija;

public class TreeEditor extends DefaultTreeCellEditor implements ActionListener {

	private Object stavka = null;
	private JTextField edit = null;

	public TreeEditor(JTree tree, DefaultTreeCellRenderer renderer) {
		super(tree, renderer);
	}

	public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4,
			int arg5) {
		stavka = arg1;

		edit = new JTextField(arg1.toString());
		edit.addActionListener(this);
		return edit;
	}

	public boolean isCellEditable(EventObject arg0) {
		if (arg0 instanceof MouseEvent) {
			if (((MouseEvent) arg0).getClickCount() == 3) {
				return true;
			}
		}
		return false;
	}

	public void actionPerformed(ActionEvent e) {

		// rename malo cudnije radi, kao na Aninom, triple click -> ukucas ime -> enter
		// -> klik sa strane da unselectujes
		String name = e.getActionCommand();
		if (MainFrame.getInstance().getTreeModel().search(name) != null) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "That name already exists", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (stavka instanceof Project) {
			MainFrame.getInstance().getCommandManager().addCommand(new RenameCommand((TreeNode) stavka,((Project)stavka).getName(),name));
			((Project) stavka).setName(name);
		} else if (stavka instanceof SoftverskaKompanija) {
			MainFrame.getInstance().getCommandManager().addCommand(new RenameCommand((TreeNode) stavka,((SoftverskaKompanija)stavka).getName(),name));
			((SoftverskaKompanija) stavka).setName(name);
		} else if (stavka instanceof Proizvod) {
			MainFrame.getInstance().getCommandManager().addCommand(new RenameCommand((TreeNode) stavka,((Proizvod)stavka).getName(),name));
			((Proizvod) stavka).setName(name);
		} else if (stavka instanceof Modul) {
			MainFrame.getInstance().getCommandManager().addCommand(new RenameCommand((TreeNode) stavka,((Modul)stavka).getName(),name));
			((Modul) stavka).setName(name);
		} else if (stavka instanceof Parametar) {
			MainFrame.getInstance().getCommandManager().addCommand(new RenameCommand((TreeNode) stavka,((Parametar)stavka).getName(),name));
			((Parametar) stavka).setName(name);
			// get odgovarajuci tab i setnametf na novo name
			MainFrame.getInstance().getDesnoGore().getTabPane()
					.remove((MainFrame.getInstance().getDesnoGore().nadjiIndex((Parametar) stavka)));
		}
	}
}
