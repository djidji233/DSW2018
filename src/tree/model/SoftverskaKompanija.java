package tree.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

import events.Lisnr;

public class SoftverskaKompanija extends Cvor implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private Project parent;
	private ArrayList<Proizvod> kids = new ArrayList<>();
	//private ArrayList<Lisnr> listeners = new ArrayList<>();
	private String sadrzaj;

	public SoftverskaKompanija() {
		super();
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public DefaultMutableTreeNode getChildAt(int a) {
		return kids.get(a);
	}

	@Override
	public int getChildCount() {
		return kids.size();
	}

	public int getChildLeafCount() {
		int count = 0;
		for (Proizvod n : kids) {
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

	public void setParent(DefaultMutableTreeNode t) {
		if (t instanceof Project)
			parent = (Project) t;
		else
			System.out.println("Greska pri setParent metodi u SoftKomp");
		//notifySve();
	}

	public int getIndex(DefaultMutableTreeNode arg0) {
		int index;

		if (arg0 instanceof Project)
			index = kids.indexOf((Project) arg0);
		else
			index = -100;

		if (index == -100)
			System.out.println("Greska pri getIndex(TreeNode) metodi u SoftKomp");

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
	public Enumeration<Proizvod> children() {
		return Collections.enumeration(kids);
	}

	public ArrayList<Proizvod> getChildren() {
		return kids;
	}

	public void add(Proizvod novi) {
		this.kids.add(novi);
		novi.setParent(this);
		//notifySve();
	}
	public void addAt(Proizvod child, int n) {
		this.kids.add(n, child);
	}

	@Override
	public void remove(int index) {
		kids.remove(index);
		//notifySve();
	}

	public void removeAllChildren() {
		for (int i = 0; i < kids.size(); i++) {
			kids.remove(i);
		}
		//notifySve();
	}

//	public void remove(DefaultMutableTreeNode t) {
//		if (t instanceof Proizvod) {
//			kids.remove((Proizvod) t);
//		} else
//			System.out.println("Greska pri remove TreeNodea u SoftKomp");
//		//notifySve();
//	}

//	@Override
//	public void removeFromParent() {
//		parent.children.remove(this);
//		//notifySve();
//	}

	public void setName(String s) {
		name = s;
		//notifySve();
	}

	public String getName() {
		return name;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
		//notifySve();
	}

	@Override
	public void remove(Cvor c) {
		if (c instanceof Proizvod) {
			kids.remove((Proizvod) c);
		} else
			System.out.println("Greska pri remove TreeNodea u SoftKomp");
	}

//	@Override
//	public void addListener(Lisnr l) {
//		listeners.add(l);
//	}
//
//	@Override
//	public void removeListener(Lisnr l) {
//		listeners.remove(l);
//	}
//
//	@Override
//	public void notifySve() {
//		for (Lisnr l : this.listeners) {
//			l.update(this);
//		}


}
