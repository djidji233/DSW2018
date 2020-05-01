package tree.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

import app.MainFrame;
import events.Lisnr;
import events.Obs;

public class Project extends Cvor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Project parent=null;
	private ArrayList<SoftverskaKompanija> kids = new ArrayList<>();
	private String sadrzaj;

	public Project() {
		super();
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public Cvor getChildAt(int a) {
		return kids.get(a);
	}

	@Override
	public int getChildCount() {
		return kids.size();
	}

	public int getChildLeafCount() {
		int count = 0;
		for (SoftverskaKompanija n : kids) {
			if (n.getChildCount() == 0) {
				count += 1;
			}
		}
		return count;
	}

	@Override
	public DefaultMutableTreeNode getParent() {
		return parent;
	}

	public int getIndex(DefaultMutableTreeNode arg0) {
		int index;

		if (arg0 instanceof Project)
			index = kids.indexOf((Project) arg0);
		else
			index = -100;

		if (index == -100)
			System.out.println("Greska pri getIndex(TreeNode) metodi u Projectu");

		return index;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public boolean isLeaf() {
		if (kids.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Enumeration<SoftverskaKompanija> children() {
		return Collections.enumeration(kids);
	}

	public ArrayList<SoftverskaKompanija> getChildren() {
		return kids;
	}

	public void add(SoftverskaKompanija novi) {
		this.kids.add(novi);
		novi.setParent(this);
	}
	
	public void addAt(SoftverskaKompanija child, int n) {
		this.kids.add(n, child);
	}

	@Override
	public void remove(int index) {
		kids.remove(index);
	}

	public void removeAllChildren() {
		for (int i = 0; i < kids.size(); i++) {
			kids.remove(i);
		}
	}

//	public void remove(DefaultMutableTreeNode t) {
//		if (t instanceof SoftverskaKompanija) {
//			kids.remove((SoftverskaKompanija) t);
//		} else
//			System.out.println("Greska pri remove TreeNodea u Projectu");
//	}

	@Override
	public void removeFromParent() {
		parent.children.remove(this);
	}

	public void setName(String s) {
		name = s;
	}

	public String getName() {
		return name;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	@Override
	public void remove(Cvor c) {
		if (c instanceof SoftverskaKompanija) {
			kids.remove((SoftverskaKompanija) c);
		} else
			System.out.println("Greska pri remove TreeNodea u Projectu");
	}

}
