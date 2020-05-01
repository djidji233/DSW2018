package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;

import app.MainFrame;

public class UndoAction extends AbstractGlavnaAction {
	
	public UndoAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(SMALL_ICON, loadIcon("icons/undo.png"));
		putValue(NAME, "Undo");
		putValue(SHORT_DESCRIPTION, "Undo");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getCommandManager().undoCommand();
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree()); // ?!?!??!?!?!?
	}

}
