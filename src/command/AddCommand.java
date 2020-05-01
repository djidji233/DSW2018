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


public class AddCommand extends AbstractCommand {

	private TreeNode promenjeni;

	public AddCommand(TreeNode promenjeni) {
		this.promenjeni = promenjeni;
	}

	@Override
	public void doCommand() {
		if (promenjeni instanceof Project) {
			TreeModel treeModel = new TreeModel((Project) promenjeni);
			MainFrame.getInstance().getTree().setModel(treeModel);
			//treeModel.addObserver(Frame1.getInstance().getItemTree());
		} else if (promenjeni instanceof SoftverskaKompanija) {
			SoftverskaKompanija sk = (SoftverskaKompanija) promenjeni;
			Project parent = (Project) sk.getParent();
			parent.add(sk);
		} else if (promenjeni instanceof Proizvod) {
			Proizvod proizvod = (Proizvod) promenjeni;
			SoftverskaKompanija sk = (SoftverskaKompanija) proizvod.getParent();
			sk.add(proizvod);
		} else if (promenjeni instanceof Modul) {
			Modul m = (Modul) promenjeni;
			Proizvod p = (Proizvod) m.getParent();
			p.add(m);
		} else if (promenjeni instanceof Parametar) {
			Parametar parametar = (Parametar) promenjeni;
			if (parametar.getParent() instanceof Proizvod) {
				Proizvod p = (Proizvod) parametar.getParent();
				p.add(parametar);
			} else if (parametar.getParent() instanceof Modul) {
				Modul m = (Modul) parametar.getParent();
				m.add(parametar);
			}
		}
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
	}

	@Override
	public void undoCommand() {
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
}
