package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.tree.DefaultMutableTreeNode;

import app.MainFrame;
import message.MyExceptionHandler;
import tree.model.Project;

public class SaveAction extends AbstractGlavnaAction {

	public SaveAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("icons/save.png"));
		putValue(NAME, "Save");
		putValue(SHORT_DESCRIPTION, "Save");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Project root = (Project)MainFrame.getInstance().getTree().getModel().getRoot();

		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("save"));
			// cuva u folderu gde je projekat tj workspace
			os.writeObject(root);
			os.close();
		} catch (FileNotFoundException e) {
			MyExceptionHandler.createMessage(e, this);
		} catch (IOException e) {
			MyExceptionHandler.createMessage(e, this);
		}
	}

}
