package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public class ExportPdfAction extends AbstractGlavnaAction{
	
	public ExportPdfAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
		        KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("icons/pdf.png"));
		putValue(NAME, "ExpToPdf");
		putValue(SHORT_DESCRIPTION, "Export to PDF");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
