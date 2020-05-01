package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import tree.model.Parametar;
import tree.model.Project;

public class PanelDesnoGore extends JPanel{
	
	private JTabbedPane tabPane;
	private ToolBar toolBar;
	
	public PanelDesnoGore() {
		setLayout(new BorderLayout());
		tabPane = new JTabbedPane();
		toolBar = new ToolBar();
		add(toolBar, BorderLayout.NORTH);
		add(tabPane, BorderLayout.CENTER);
		
	}
	
	public void dodajTab(Parametar p) {
		boolean ok = true;
		for(int i=0; i<tabPane.getTabCount(); i++) {
			Parametar tmp = ((Tab) tabPane.getComponentAt(i)).getParametar();
			if(p==tmp) {
				ok = false;
				break;
			}
		}
		if(ok) {
			Tab t = new Tab(p);
			tabPane.addTab(p.getName(), t);
		}
		//p.notifySve();
	}
	public JTabbedPane getTabPane() {
		return tabPane;
	}
	public int nadjiIndex(Parametar p) {
		int a=-1;
		for(int i=0; i<tabPane.getTabCount(); i++) {
			Parametar tmp = ((Tab) tabPane.getComponentAt(i)).getParametar();
			if(p==tmp) {
				a = i;
				break;
			}
		}
		if(a==-1)
			System.out.println("a je -1");
		return a;
	}
	
}
