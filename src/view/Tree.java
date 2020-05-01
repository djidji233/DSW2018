package view;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import app.MainFrame;
import tree.controller.MouseListen;
import tree.editor.TreeEditor;
import tree.model.Project;
import tree.model.TreeModel;




public class Tree extends JTree {
	public Tree() {
		addMouseListener(new MouseListen());
	    setCellEditor(new TreeEditor(this,new DefaultTreeCellRenderer())); // rename radi cudno 
	    setEditable(true);
	}
	
	public void addProject(Project project){
		((TreeModel)getModel()).addProject(project);
		SwingUtilities.updateComponentTreeUI(this);
	}
	public static Object getSelectedObject() {
		TreePath tp = MainFrame.getInstance().getTree().getSelectionPath();
		if (tp != null) {
			Object o = tp.getLastPathComponent();
			return o;
		}
		return null;
	}

}
