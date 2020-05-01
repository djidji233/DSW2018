package command;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;

import app.MainFrame;
import tree.model.Modul;
import tree.model.Parametar;
import tree.model.Proizvod;
import tree.model.Project;
import tree.model.SoftverskaKompanija;

public class RenameCommand extends AbstractCommand {

	private TreeNode promenjeni;
	private String oldName;
	private String newName;

	public RenameCommand(TreeNode promenjeni, String newName, String oldName) {
		this.promenjeni = promenjeni;
		this.newName = newName;
		this.oldName = oldName;
	}

	@Override
	public void undoCommand() {
		if (promenjeni instanceof Project) {
			((Project) promenjeni).setName(newName);
		} else if (promenjeni instanceof SoftverskaKompanija) {
			((SoftverskaKompanija) promenjeni).setName(newName);
		} else if (promenjeni instanceof Proizvod) {
			((Proizvod) promenjeni).setName(newName);
		} else if (promenjeni instanceof Modul) {
			((Modul) promenjeni).setName(newName);
		} else if (promenjeni instanceof Parametar) {
			((Parametar) promenjeni).setName(newName);
		}
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
	}

	@Override
	public void doCommand() {
		if (promenjeni instanceof Project) {
			((Project) promenjeni).setName(oldName);
		} else if (promenjeni instanceof SoftverskaKompanija) {
			((SoftverskaKompanija) promenjeni).setName(oldName);
		} else if (promenjeni instanceof Proizvod) {
			((Proizvod) promenjeni).setName(oldName);
		} else if (promenjeni instanceof Modul) {
			((Modul) promenjeni).setName(oldName);
		} else if (promenjeni instanceof Parametar) {
			((Parametar) promenjeni).setName(oldName);
		}
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
	}
}