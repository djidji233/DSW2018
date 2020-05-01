package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import actions.ActionManager;
import command.CommandManager;
import view.PopUpMenu;
import tree.model.Project;
import tree.model.TreeModel;
import view.LogIn;
import view.MenuBar;
import view.PanelDesnoGore;
import view.StatusBar;
import view.ToolBar;
import view.Tree;

public class MainFrame extends JFrame implements ClipboardOwner {

	private static MainFrame instance = null;
	private ActionManager actionManager;
	private String user;

	private Tree tree;
	private TreeModel treeModel;

	private PanelDesnoGore desnoGore;
	
	 private Clipboard clipboard=new Clipboard("InstaFram clipboard");
	 
	 private CommandManager commandManager = new CommandManager();

	private MainFrame() {

	}

	public static MainFrame getInstance() {

		if (instance == null) {
			instance = new MainFrame();
			instance.initialise();
		}
		return instance;
	}

	private void initialise() {
		user = LogIn.getInstance().getUser();
		actionManager = new ActionManager(user);
		initialiseTree();
		initialiseGUI();
	}

	private void initialiseGUI() {
		setTitle("InstaFram");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		if (user.equals("user")) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		if (user.equals("admin")) {
			addWindowListener(new WindowListener() {
				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowClosing(WindowEvent e) {
					actionManager.getExitAction()
							.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}

		MenuBar menuBar = new MenuBar();
		setJMenuBar(menuBar);

		ToolBar toolBar = new ToolBar();
		add(toolBar, BorderLayout.NORTH);

		// levi deo
		JScrollPane levo = new JScrollPane(tree);
		levo.setMinimumSize(new Dimension(200, 200));
		// desni deo
//		desnoDole = new PanelDesnoDole();
		desnoGore = new PanelDesnoGore();

//		JSplitPane desno = new JSplitPane(JSplitPane.VERTICAL_SPLIT, desnoGore, desnoDole);
//		desno.setDividerLocation(150);
		// ceo splitpane
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, levo, desnoGore);
		split.setDividerLocation(250);
		add(split, BorderLayout.CENTER);

		StatusBar statusBar = new StatusBar();
		add(statusBar, BorderLayout.SOUTH);
	}

	private void initialiseTree() {
		tree = new Tree();
		treeModel = new TreeModel();
		tree.setModel(treeModel);

		PopUpMenu popup = new PopUpMenu();
		tree.setComponentPopupMenu(popup);

		((TreeModel) tree.getModel()).setRoot(null);
		// reminder :
		// ima 2 txt fajla u folderu proba na desktopu
		// ova 3 txt fajla u projektu su na
		// C:\Users\djole\eclipse\workspace\Installer900
	}

	public ActionManager getActionManager() {
		return actionManager;
	}

	public Tree getTree() {
		return tree;
	}

	public TreeModel getTreeModel() {
		return treeModel;
	}


	public PanelDesnoGore getDesnoGore() {
		return desnoGore;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}
	public Clipboard getClipboard() {
		return clipboard;
	}
	public void setClipboard(Clipboard clipboard) {
		this.clipboard = clipboard;
	}

	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {
		// TODO Auto-generated method stub
		
	}
	public CommandManager getCommandManager() {
		return commandManager;
	}
	public void setCommandManager(CommandManager commandManager) {
		this.commandManager = commandManager;
	}

}
