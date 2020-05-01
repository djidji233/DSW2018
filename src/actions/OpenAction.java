package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.TreePath;

import app.MainFrame;
import message.MyExceptionHandler;
import tree.model.Project;
import tree.model.TreeModel;

public class OpenAction extends AbstractGlavnaAction {

	public OpenAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("icons/open.png"));
		putValue(NAME, "Open");
		putValue(SHORT_DESCRIPTION, "Open");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		final JFileChooser fc = new JFileChooser();
		
		int returnVal = fc.showOpenDialog(MainFrame.getInstance());
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			if(fc.getSelectedFile() == null) {
				System.out.println("Nije izabrao fajl");
				return;
			}
			File f = fc.getSelectedFile();
			ObjectInputStream oi=null;
			
			
			((TreeModel) MainFrame.getInstance().getTree().getModel()).delete();
			try {
				try {
					oi = new ObjectInputStream(new FileInputStream(f));
				} catch (FileNotFoundException e) {
					MyExceptionHandler.createMessage(e, this);
					return;
				} catch (IOException e) {
					MyExceptionHandler.createMessage(e, this);
					return;
				}
				Project p = null;
				p = (Project) oi.readObject();
				
				TreeModel.setProject(p);
				MainFrame.getInstance().getTreeModel().root();
				//MainFrame.getInstance().getTreeModel().setRoot(p);
				MainFrame.getInstance().getTreeModel().nodeStructureChanged(p);
				oi.close();
			} catch (Exception e) {
				MyExceptionHandler.createMessage(e, this);
			}
		}
	}
}


