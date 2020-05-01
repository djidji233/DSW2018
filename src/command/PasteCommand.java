package command;

import java.util.List;

import javax.swing.SwingUtilities;

import app.MainFrame;
import tree.model.Modul;
import tree.model.Parametar;
import tree.model.Proizvod;

public class PasteCommand extends AbstractCommand {

	private List<Parametar> promenjeni;

	public PasteCommand(List<Parametar> promenjeni) {
		this.promenjeni = promenjeni;
	}

	@Override
	public void doCommand() {
		for (int i = 0; i < promenjeni.size(); i++) {
			if (promenjeni.get(i) instanceof Parametar) {
				Parametar parametar = (Parametar) promenjeni.get(i);
				if (parametar.getParent() instanceof Proizvod) {
					Proizvod p = (Proizvod) parametar.getParent();
					p.add(parametar);
				} else if (parametar.getParent() instanceof Modul) {
					Modul m = (Modul) parametar.getParent();
					m.add(parametar);
				}
			}
		}
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
	}

	@Override
	public void undoCommand() {
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
}