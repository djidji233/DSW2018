package view;

import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import app.MainFrame;


public class MenuBar extends JMenuBar{
		private JMenu fileMenu;
		private JMenu viewMenu;
		private JMenu toolsMenu;
		private JMenu parametrizacijaMenu;
		private JMenu helpMenu;
		private JMenu aboutMenu;
	public MenuBar() {
		// FILE MENU
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		fileMenu.add(MainFrame.getInstance().getActionManager().getNewAction());
		fileMenu.addSeparator();
		fileMenu.add(MainFrame.getInstance().getActionManager().getOpenAction());
		fileMenu.addSeparator();
		fileMenu.add(MainFrame.getInstance().getActionManager().getCloseAction());
		fileMenu.addSeparator();
		fileMenu.add(MainFrame.getInstance().getActionManager().getSwitchAction());
		fileMenu.addSeparator();
		fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
		fileMenu.addSeparator();
		fileMenu.add(MainFrame.getInstance().getActionManager().getCopyAction());
		fileMenu.addSeparator();
		fileMenu.add(MainFrame.getInstance().getActionManager().getCutAction());
		fileMenu.addSeparator();
		fileMenu.add(MainFrame.getInstance().getActionManager().getPasteAction());
		fileMenu.addSeparator();
		fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAction());
		fileMenu.addSeparator();
		fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAsAction());
		fileMenu.addSeparator();
		
		JMenu exportMenu= new JMenu("Export");
		exportMenu.add(MainFrame.getInstance().getActionManager().getExportPdfAction());
		exportMenu.addSeparator();
		exportMenu.add(MainFrame.getInstance().getActionManager().getExportExcelAction());
		exportMenu.addSeparator();
		exportMenu.add(MainFrame.getInstance().getActionManager().getExportWordAction());
		
		JMenu importMenu = new JMenu("Import");
		
		fileMenu.add(exportMenu);
		fileMenu.add(importMenu);
		
		fileMenu.addSeparator();
		fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
		add(fileMenu);
		
		// VIEW MENU
		viewMenu = new JMenu("View");
		viewMenu.setMnemonic(KeyEvent.VK_V);
		
		add(viewMenu);
		
		// TOOLS MENU
		toolsMenu = new JMenu("Tools");
		toolsMenu.setMnemonic(KeyEvent.VK_T);
		
		add(toolsMenu);
		
		// PARAMETRIZACIJA MENU
		parametrizacijaMenu = new JMenu("Parametrizacija");
		parametrizacijaMenu.setMnemonic(KeyEvent.VK_P);
		// HELP MENU
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		// ABOUT MENU
		aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		aboutMenu.add(MainFrame.getInstance().getActionManager().getAboutAction());
		
		add(Box.createHorizontalGlue());
		add(parametrizacijaMenu);
		add(helpMenu);
		add(aboutMenu);
	}
}
