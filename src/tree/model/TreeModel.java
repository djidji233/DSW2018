package tree.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import app.MainFrame;
import tree.model.Project;
import tree.model.TreeModel;

public class TreeModel extends DefaultTreeModel {
	
	private static Project project = new Project();
	
	public TreeModel() {
		super(new Project());
		this.setRoot(null);
	}
	public TreeModel(TreeNode n) {
		super(n);
	}
	public void addProject(Project p) {
		if(this.getRoot()==null) {
			this.setRoot(p);
		} 
	}

	public void delete() {
		this.setRoot(null);
	}
	

	public Project search(String s) {
		Project root = (Project) this.getRoot();
		Enumeration<DefaultMutableTreeNode> e = root.breadthFirstEnumeration();
		
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode node = e.nextElement();
			if(node.toString().equalsIgnoreCase(s)) {
				return (Project) node;
			}
		}
		return null;
	}
	
	public boolean test(Enumeration<DefaultMutableTreeNode> elements) {
		// testira da li postoje razliciti objekti sa istim imenom
		Project root = (Project)MainFrame.getInstance().getTree().getModel().getRoot();
		Enumeration<DefaultMutableTreeNode> e = root.breadthFirstEnumeration();
	    
		while(elements.hasMoreElements()) {
	    	
			DefaultMutableTreeNode node1 = elements.nextElement();
			
			while(e.hasMoreElements()) {
				DefaultMutableTreeNode node2 = e.nextElement();
				
				if(node2.toString().equals(node1.toString()) && node2!=node1) {
					return false;
				}
					
			}

	    }

		return true;
	}
	public static Project getProject() {
		return project;
	}
	public static void setProject(Project project) {
		TreeModel.project = project;
	}
	public void root() {
		setRoot(project);
	}
	
}
