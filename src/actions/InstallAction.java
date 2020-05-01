package actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import app.MainFrame;
import tree.model.Proizvod;
import view.InstallFrame;

public class InstallAction extends AbstractGlavnaAction {

	public InstallAction() {
		putValue(SMALL_ICON, loadIcon("icons/install.png"));
		putValue(NAME, "Select for install");
		putValue(SHORT_DESCRIPTION, "Select for install");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		//jfc.showOpenDialog(MainFrame.getInstance());
		int rezultat = jfc.showSaveDialog(null);
		if (rezultat == JFileChooser.APPROVE_OPTION) {
			File f = jfc.getSelectedFile();
			try {
				Proizvod p = null;
				ObjectInputStream os = new ObjectInputStream(new FileInputStream(f));
				try {
					p = (Proizvod) os.readObject();
				} catch (ClassNotFoundException e0) {
					
				}

				InstallFrame instalframe = new InstallFrame(p.getChildren(), 0, f);

				os.close();
			} catch (FileNotFoundException e1) {
				
			} catch (IOException e2) {
				
			}
		}
	}
}
