package actions;

public class ActionManager {
	private AboutAction aboutAction;
	private CloseAction closeAction;
	private DeleteAction deleteAction;
	private ExitAction exitAction;
	private ExportExcelAction exportExcelAction;
	private ExportPdfAction exportPdfAction;
	private ExportWordAction exportWordAction;
	private NewAction newAction;
	private OpenAction openAction;
	private SaveAction saveAction;
	private SaveAsAction saveAsAction;
	private SwitchAction switchAction;
	private CopyAction copyAction;
	private CutAction cutAction;
	private PasteAction pasteAction;
	private UndoAction undoAction;
	private RedoAction redoAction;
	private SaveForInstallationAction saveForInstallAction;
	private InstallAction installAction;
	
	public ActionManager(String user) {
		initialise(user);
	}
	private void initialise(String user) {
		aboutAction = new AboutAction();
		exitAction = new ExitAction();
		closeAction = new CloseAction();
		deleteAction = new DeleteAction();
		newAction = new NewAction();
		saveAction = new SaveAction();
		saveAsAction = new SaveAsAction();
		openAction = new OpenAction();
		exportExcelAction = new ExportExcelAction();
		exportPdfAction = new ExportPdfAction();
		exportWordAction = new ExportWordAction();
		switchAction = new SwitchAction();
		copyAction = new CopyAction();
		cutAction = new CutAction();
		pasteAction = new PasteAction();
		undoAction = new UndoAction();
		redoAction = new RedoAction();
		saveForInstallAction = new SaveForInstallationAction();
		installAction = new InstallAction();
		
		if(user.equals("user")) {
			exitAction.setEnabled(false);
			closeAction.setEnabled(false);
			deleteAction.setEnabled(false);
			newAction.setEnabled(false);
			saveAction.setEnabled(false);
			saveAsAction.setEnabled(false);
			copyAction.setEnabled(false);
			cutAction.setEnabled(false);
			pasteAction.setEnabled(false);
			switchAction.setEnabled(false);
			exportExcelAction.setEnabled(false);
			exportPdfAction.setEnabled(false);
			exportWordAction.setEnabled(false);
			undoAction.setEnabled(false);
			redoAction.setEnabled(false);
			saveForInstallAction.setEnabled(false);
			installAction.setEnabled(false);
		}
	}
	public ExportExcelAction getExportExcelAction() {
		return exportExcelAction;
	}
	public ExportPdfAction getExportPdfAction() {
		return exportPdfAction;
	}
	public ExportWordAction getExportWordAction() {
		return exportWordAction;
	}
	public AboutAction getAboutAction() {
		return aboutAction;
	}
	public CloseAction getCloseAction() {
		return closeAction;
	}
	public ExitAction getExitAction() {
		return exitAction;
	}
	public DeleteAction getDeleteAction() {
		return deleteAction;
	}
	public NewAction getNewAction() {
		return newAction;
	}
	public SaveAction getSaveAction() {
		return saveAction;
	}
	public SaveAsAction getSaveAsAction() {
		return saveAsAction;
	}
	public OpenAction getOpenAction() {
		return openAction;
	}
	public SwitchAction getSwitchAction() {
		return switchAction;
	}
	public CopyAction getCopyAction() {
		return copyAction;
	}
	public CutAction getCutAction() {
		return cutAction;
	}
	public PasteAction getPasteAction() {
		return pasteAction;
	}
	public UndoAction getUndoAction() {
		return undoAction;
	}
	public RedoAction getRedoAction() {
		return redoAction;
	}
	public SaveForInstallationAction getSaveForInstallAction() {
		return saveForInstallAction;
	}
	public InstallAction getInstallAction() {
		return installAction;
	}
}
