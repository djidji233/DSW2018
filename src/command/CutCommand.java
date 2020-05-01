package command;

import java.util.List;

import javax.swing.SwingUtilities;

import app.MainFrame;
import tree.model.Modul;
import tree.model.Parametar;
import tree.model.Proizvod;


public class CutCommand extends AbstractCommand {

	private List<Parametar> promenjeni;
	private List<Integer> pozicija;

	public CutCommand(List<Parametar> promenjeni, List<Integer> pozicija) {
		this.promenjeni = promenjeni;
		this.pozicija = pozicija;
	}

	@Override
	public void doCommand() {
		if (pozicija.get(0) < 0) {
			return;
		}
		for (int i = 0; i < promenjeni.size(); i++) {
			if (promenjeni.get(i) instanceof Parametar) {
				Parametar parametar = (Parametar) promenjeni.get(i);
				if (parametar.getParent() instanceof Proizvod) {
					Proizvod p = (Proizvod) parametar.getParent();
					p.remove(parametar);
				} else if (parametar.getParent() instanceof Modul) {
					Modul m = (Modul) parametar.getParent();
					m.remove(parametar);
				}
			}
		}
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
	}

	@Override
	public void undoCommand() {
		if (pozicija.get(0) < 0) {
			return;
		}
		for (int i = pozicija.size() - 1; i >= 0; i--) {
			if (promenjeni.get(i) instanceof Parametar) {
				Parametar parametar = (Parametar) promenjeni.get(i);
				if (parametar.getParent() instanceof Proizvod) {
					Proizvod p = (Proizvod) parametar.getParent();
					p.addAt(parametar, pozicija.get(i));
				} else if (parametar.getParent() instanceof Modul) {
					Modul m = (Modul) parametar.getParent();
					m.addAt(parametar, pozicija.get(i));
				}
			}
		}
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
	}
}
