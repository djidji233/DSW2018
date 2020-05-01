package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import app.MainFrame;
import message.MyExceptionHandler;
import tree.model.Project;

public class SaveAsAction extends AbstractGlavnaAction {

	public SaveAsAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("icons/save_as.png"));
		putValue(NAME, "Save as");
		putValue(SHORT_DESCRIPTION, "Save as");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
			//Cuvanjee trenutnog stabla u novi fajl koji biramo
			//Ukoliko je selektovan cvor podrazumeva se da se on cuva kao root i da se odatle cuva
			//Dakle cuva se podstablo samo
			//Ako nista nije selektovano cuva se sve
			
			Project root = (Project)MainFrame.getInstance().getTree().getModel().getRoot();
			save(root);
		}
		
		private void save (Project root) {

			JFileChooser fileChooser = new JFileChooser();
			File file=null;
			if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
			  file = fileChooser.getSelectedFile();
			}
			if(file==null)
				return;
			try {
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
				os.writeObject(root);
				os.close();
			} catch (FileNotFoundException e) {
				MyExceptionHandler.createMessage(e, this);
			} catch (IOException e) {
				MyExceptionHandler.createMessage(e, this);
			}
		}

	}


