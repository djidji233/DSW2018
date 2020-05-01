package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import app.MainFrame;
import tree.model.Project;

public class CloseAction extends AbstractGlavnaAction {

	public CloseAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
		        KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("icons/close.png"));
		putValue(NAME, "Close");
		putValue(SHORT_DESCRIPTION, "Close");
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

}
