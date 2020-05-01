package view;

import javax.swing.JPopupMenu;

import app.MainFrame;

public class PopUpMenu extends JPopupMenu{
	
	public PopUpMenu() {
		add(MainFrame.getInstance().getActionManager().getNewAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getDeleteAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getSaveAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getSaveAsAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getCopyAction());
		add(MainFrame.getInstance().getActionManager().getCutAction());
		add(MainFrame.getInstance().getActionManager().getPasteAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getUndoAction());
		add(MainFrame.getInstance().getActionManager().getRedoAction());
	}
}
