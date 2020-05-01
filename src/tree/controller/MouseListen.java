package tree.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import app.MainFrame;
import view.Tab;
import tree.model.Parametar;
import tree.model.Project;

public class MouseListen implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		//TODO
		if(e.getClickCount()==2) {
			Object o = MainFrame.getInstance().getTree().getLastSelectedPathComponent();
			if(o instanceof Parametar) {
			Parametar p =(Parametar) o;
			if(p==null) return; // ako se pritisne ispod stabla negde na belinu, bez ovoga dodajTab baca null pointer
			MainFrame.getInstance().getDesnoGore().dodajTab(p);
			int a = MainFrame.getInstance().getDesnoGore().nadjiIndex(p);
			MainFrame.getInstance().getDesnoGore().getTabPane().setSelectedIndex(a);
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
