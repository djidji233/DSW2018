package tree.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import events.Lisnr;

public class Proizvod extends Cvor implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private SoftverskaKompanija parent = null;
	private ArrayList<Cvor> kids = new ArrayList<>();
	private ArrayList<Lisnr> listeners = new ArrayList<>();
	private String sadrzaj;

	public Proizvod() {
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
		for (DefaultMutableTreeNode n : kids) {
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
		if (t instanceof SoftverskaKompanija)
			parent = (SoftverskaKompanija) t;
		else
			System.out.println("Greska pri setParent metodi u Proizvodu");
		// notifySve();
	}

	public int getIndex(DefaultMutableTreeNode arg0) {
		int index;

		if (arg0 instanceof Project)
			index = kids.indexOf((Project) arg0);
		else
			index = -100;

		if (index == -100)
			System.out.println("Greska pri getIndex(TreeNode) metodi u Proizvodu");

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
	public Enumeration<Cvor> children() {
		return Collections.enumeration(kids);
	}

	public ArrayList<Cvor> getChildren() {
		return kids;
	}

	public void add(Modul novi) {
		this.kids.add(novi);
		novi.setParent(this);
		// notifySve();
	}

	public void add(Parametar novi) {
		this.kids.add(novi);
		novi.setParent(this);
		// notifySve();
	}
	public void addAt(Cvor child, int n) {
		this.kids.add(n, child);
	}

//	public void insert(DefaultMutableTreeNode novi, int index) {
//		if (novi instanceof Proizvod) {
//			if (novi.getParent() != null) {
//				parent = (SoftverskaKompanija) novi.getParent();
//				parent.getChildren().remove(novi);
//				this.getChildren().add(index, (Modul) novi);
//			}
//		} else {
//			System.out.println("Greska pri insertovanju TreeNodea na index u Projectu");
//		}
//		//notifySve();
//	}

	@Override
	public void remove(int index) {
		kids.remove(index);
		// notifySve();
	}

	public void removeAllChildren() {
		for (int i = 0; i < kids.size(); i++) {
			kids.remove(i);
		}
		// notifySve();
	}


	public void setName(String s) {
		name = s;
		// notifySve();
	}

	public String getName() {
		return name;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
		// notifySve();
	}

	@Override
	public void remove(Cvor c) {
		if (c instanceof Parametar) {
			kids.remove(c);
		} else if(c instanceof Modul) {
			kids.remove(c);
		}	else 
			System.out.println("Greska pri remove TreeNodea u Proizvodu");
	}


}
