package actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import app.MainFrame;
import tree.model.Modul;
import tree.model.Parametar;
import tree.model.Proizvod;
import view.Tree;

public class SaveForInstallationAction extends AbstractGlavnaAction {
	public SaveForInstallationAction() {
		putValue(SMALL_ICON, loadIcon("icons/saveforinst.png"));
		putValue(NAME, "Save for installation");
		putValue(SHORT_DESCRIPTION, "Save for installation");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainFrame.getInstance().getTree();
		Object o = Tree.getSelectedObject();
		if (o instanceof Proizvod) {
			Proizvod p = (Proizvod) o;
			JFileChooser jfs = new JFileChooser();
			jfs.setDialogTitle("Save for installation as:");
			int rezultat = jfs.showSaveDialog(null);
			if (rezultat == JFileChooser.APPROVE_OPTION) {
				if (jfs.getSelectedFile() == null) {
					return;
				}
				File f = jfs.getSelectedFile();
				String string = new String(jfs.getSelectedFile().toString());
				int broj = string.lastIndexOf("/");
				int k = string.length() - 1;
				while (broj < k) {
					string = string.substring(0, string.length() - 1);
					k--;
				}

				try {
					ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f));
					os.writeObject(p);
					os.close();
				} catch (FileNotFoundException e1) {
					
				} catch (IOException e1) {
					
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have to select a product to save.", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
