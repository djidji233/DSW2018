package tree.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

import events.Lisnr;
import events.Obs;

public class Parametar extends Cvor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean custom;
	private ArrayList<Lisnr> listeners = new ArrayList<>();
	
	private String type;
	private String name;
	private Cvor parent = null;
	private String naziv;
	private String autor;
	private String gui;
	private String termsOfUse;
	private String lookAndFeel;
	private String logoPath;
	private boolean desktopShortcut;
	private boolean runAfterInstalation;
	
	
	
	public Parametar(Parametar p) {
		super();
		this.custom = p.custom;
		this.listeners = p.listeners;
		this.type = p.type;
		this.name = p.name;
		this.parent = p.parent;
		this.naziv = p.naziv;
		this.autor = p.autor;
		this.gui = p.gui;
		this.termsOfUse = p.termsOfUse;
		this.lookAndFeel = p.lookAndFeel;
		this.logoPath = p.logoPath;
		this.desktopShortcut = p.desktopShortcut;
		this.runAfterInstalation = p.runAfterInstalation;
	}

	public Parametar() {
		super();
	}

	@Override
	public String toString() {
		return name;
	}



	@Override
	public Cvor getParent() {
		return parent;
	}

	public void setParent(Cvor t) {
		if (t instanceof Modul)
			parent = (Modul) t;
		else if(t instanceof Proizvod) {
			parent = (Proizvod) t;
		}
		else
			System.out.println("Greska pri setParent metodi u Parametru");
		//notifySve();
	}


	@Override
	public boolean getAllowsChildren() {
		return false;
	}
	
	public void removeFromParent() {
		int br = parent.getChildCount();
		for(int i=0; i<br; i++) {
			if(this.equals(parent.getChildAt(i))) {
				parent.remove(i);
			}
		}
	}


	public void setName(String s) {
		name = s;
		//notifySve();
	}

	public String getName() {
		return name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}


	public String getGui() {
		return gui;
	}

	public void setGui(String gui) {
		this.gui = gui;
	}



	public ArrayList<Lisnr> getListeners() {
		return listeners;
	}

	public void setListeners(ArrayList<Lisnr> listeners) {
		this.listeners = listeners;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTermsOfUse() {
		return termsOfUse;
	}

	public void setTermsOfUse(String termsOfUse) {
		this.termsOfUse = termsOfUse;
	}

	public String getLookAndFeel() {
		return lookAndFeel;
	}

	public void setLookAndFeel(String lookAndFeel) {
		this.lookAndFeel = lookAndFeel;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public boolean isDesktopShortcut() {
		return desktopShortcut;
	}

	public void setDesktopShortcut(boolean desktopShortcut) {
		this.desktopShortcut = desktopShortcut;
	}

	public boolean isRunAfterInstalation() {
		return runAfterInstalation;
	}

	public void setRunAfterInstalation(boolean runAfterInstalation) {
		this.runAfterInstalation = runAfterInstalation;
	}

	@Override
	public void remove(Cvor c) {
		// TODO Auto-generated method stub
		
	}
	public void setCustom(boolean custom) {
		this.custom = custom;
	}
	public boolean isCustom() {
		if (custom==true)
			return true;
		else
			return false;
	}
}
