package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public class ExportExcelAction extends AbstractGlavnaAction{
	
	public ExportExcelAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
		        KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("icons/excel.png"));
		putValue(NAME, "ExpToMsExcel");
		putValue(SHORT_DESCRIPTION, "Export to Excel");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
