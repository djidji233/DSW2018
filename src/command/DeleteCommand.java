package command;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;

import app.MainFrame;
import tree.model.Modul;
import tree.model.Parametar;
import tree.model.Proizvod;
import tree.model.Project;
import tree.model.SoftverskaKompanija;
import tree.model.TreeModel;

public class DeleteCommand extends AbstractCommand {

	private TreeNode promenjeni;
	private int pozicija;

	public DeleteCommand(TreeNode promenjeni, int pozicija) {
		this.promenjeni = promenjeni;
		this.pozicija = pozicija;
	}

	@Override
	public void doCommand() {
		if (pozicija < 0) {
			System.out.println("do izlaz");
			return;
		}
		if (promenjeni instanceof Project) {
			((TreeModel) MainFrame.getInstance().getTree().getModel()).delete();
		} else if (promenjeni instanceof SoftverskaKompanija) {
			SoftverskaKompanija sk = (SoftverskaKompanija) promenjeni;
			Project parent = (Project) sk.getParent();
			parent.remove(sk);
		} else if (promenjeni instanceof Proizvod) {
			Proizvod proizvod = (Proizvod) promenjeni;
			SoftverskaKompanija sk = (SoftverskaKompanija) proizvod.getParent();
			sk.remove(proizvod);
		} else if (promenjeni instanceof Modul) {
			Modul m = (Modul) promenjeni;
			Proizvod p = (Proizvod) m.getParent();
			p.remove(m);
		} else if (promenjeni instanceof Parametar) {
			Parametar parametar = (Parametar) promenjeni;
			if (parametar.getParent() instanceof Proizvod) {
				Proizvod p = (Proizvod) parametar.getParent();
				p.remove(parametar);
			} else if (parametar.getParent() instanceof Modul) {
				Modul m = (Modul) parametar.getParent();
				m.remove(parametar);
			}
		}
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
	}

	@Override
	public void undoCommand() {
		if (pozicija < 0) {
			System.out.println("do izlaz");
			return;
		}
		if (promenjeni instanceof Project) {
			TreeModel itemModel = new TreeModel((Project) promenjeni);
			MainFrame.getInstance().getTree().setModel(itemModel);
			//itemModel.addObserver(Frame1.getInstance().getItemTree());
		} else if (promenjeni instanceof SoftverskaKompanija) {
			SoftverskaKompanija sk = (SoftverskaKompanija) promenjeni;
			Project parent = (Project) sk.getParent();
			parent.addAt(sk, pozicija);
		} else if (promenjeni instanceof Proizvod) {
			Proizvod proizvod = (Proizvod) promenjeni;
			SoftverskaKompanija sk = (SoftverskaKompanija) proizvod.getParent();
			sk.addAt(proizvod, pozicija);
		} else if (promenjeni instanceof Modul) {
			Modul m = (Modul) promenjeni;
			Proizvod p = (Proizvod) m.getParent();
			p.addAt(m, pozicija);
		} else if (promenjeni instanceof Parametar) {
			Parametar parametar = (Parametar) promenjeni;
			if (parametar.getParent() instanceof Proizvod) {
				Proizvod p = (Proizvod) parametar.getParent();
				p.addAt(parametar, pozicija);
			} else if (parametar.getParent() instanceof Modul) {
				Modul m = (Modul) parametar.getParent();
				m.addAt(parametar, pozicija);
			}
		}
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
	}
}
