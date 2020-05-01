package command;

import java.util.ArrayList;

public class CommandManager {

	private ArrayList<AbstractCommand> commands = new ArrayList<AbstractCommand>();

	private int currentCommand = 0;

	public void addCommand(AbstractCommand command) {
		while (currentCommand < commands.size() && !commands.isEmpty()) {
			int top = commands.size() - 1;
			commands.remove(top);
		}
		commands.add(command);
		currentCommand++;
	}

	public void doCommand() {
		if (currentCommand < commands.size()) {
			if (commands.get(currentCommand) instanceof AddCommand) {
				AddCommand adc = (AddCommand) commands.get(currentCommand);
				adc.doCommand();
			}
			if (commands.get(currentCommand) instanceof DeleteCommand) {
				DeleteCommand dc = (DeleteCommand) commands.get(currentCommand);
				dc.doCommand();
			}
			if (commands.get(currentCommand) instanceof RenameCommand) {
				RenameCommand rc = (RenameCommand) commands.get(currentCommand);
				rc.doCommand();
			}
			if (commands.get(currentCommand) instanceof CutCommand) {
				CutCommand cc = (CutCommand) commands.get(currentCommand);
				cc.doCommand();
			}
			if (commands.get(currentCommand) instanceof PasteCommand) {
				PasteCommand pc = (PasteCommand) commands.get(currentCommand);
				pc.doCommand();
			}
			currentCommand++;
		}
	}

	public void undoCommand() {
		if (currentCommand > 0) {
			if (commands.get(currentCommand - 1) instanceof AddCommand) {
				AddCommand ac = (AddCommand) commands.get(currentCommand - 1);
				ac.undoCommand();
			}
			if (commands.get(currentCommand - 1) instanceof DeleteCommand) {
				DeleteCommand dc = (DeleteCommand) commands.get(currentCommand - 1);
				dc.undoCommand();
			}
			if (commands.get(currentCommand - 1) instanceof RenameCommand) {
				RenameCommand rc = (RenameCommand) commands.get(currentCommand - 1);
				rc.undoCommand();
			}
			if (commands.get(currentCommand - 1) instanceof CutCommand) {
				CutCommand cc = (CutCommand) commands.get(currentCommand - 1);
				cc.undoCommand();
			}
			if (commands.get(currentCommand - 1) instanceof PasteCommand) {
				PasteCommand pc = (PasteCommand) commands.get(currentCommand - 1);
				pc.undoCommand();
			}
			currentCommand--;
		}
	}
}
