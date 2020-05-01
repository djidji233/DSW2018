package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import app.MainFrame;

public class ExitAction extends AbstractGlavnaAction {
	
	public ExitAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("icons/exit.png"));
		putValue(NAME, "Exit");
		putValue(SHORT_DESCRIPTION, "Exit");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int res = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Do you want to save before closing?");
		if(res == JOptionPane.YES_OPTION) {
			MainFrame.getInstance().getActionManager().getSaveAction().actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
			System.exit(1);
		} else if (res == JOptionPane.NO_OPTION) {
			System.exit(1);
		} else {
			
		}
		
	}
	
}
