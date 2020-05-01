package view;

import javax.swing.JToolBar;

import app.MainFrame;

public class ToolBar extends JToolBar{

	public ToolBar() {
		setFloatable(false);
		
		add(MainFrame.getInstance().getActionManager().getNewAction());
		addSeparator();
		
		add(MainFrame.getInstance().getActionManager().getOpenAction());
		addSeparator();
		
		add(MainFrame.getInstance().getActionManager().getCloseAction());
		addSeparator();

		add(MainFrame.getInstance().getActionManager().getSaveAction());
		addSeparator();
		
		add(MainFrame.getInstance().getActionManager().getSaveAsAction());
		addSeparator();

		add(MainFrame.getInstance().getActionManager().getDeleteAction());
		addSeparator();
		
		add(MainFrame.getInstance().getActionManager().getUndoAction());
		add(MainFrame.getInstance().getActionManager().getRedoAction());
		
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getSaveForInstallAction());
		add(MainFrame.getInstance().getActionManager().getInstallAction());
	}
	
}
