package message;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

public class MyExceptionHandler {

	public static void createMessage(Exception meh, Object o) {
		if (meh instanceof FileNotFoundException) {
			JOptionPane.showMessageDialog(null, "File can't be found.\n" + o, "Error", JOptionPane.INFORMATION_MESSAGE);
		}
		if (meh instanceof IOException) {
			JOptionPane.showMessageDialog(null, "I/O operation has failed.\n" + o, "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (meh instanceof ClassNotFoundException) {
			JOptionPane.showMessageDialog(null, "Class could not be found.\n" + o, "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (meh instanceof UnsupportedFlavorException) {
			JOptionPane.showMessageDialog(null, "Unsupported flavor exception.\n" + o, "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (meh instanceof PropertyVetoException) {
			JOptionPane.showMessageDialog(null, "Property veto exception occurred.\n" + o, "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (meh instanceof InstantiationException) {
			JOptionPane.showMessageDialog(null, "Instantiation exception occurred.\n" + o, "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (meh instanceof IllegalAccessException) {
			JOptionPane.showMessageDialog(null, "Illegal access exception occurred.\n" + o, "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (meh instanceof UnsupportedLookAndFeelException) {
			JOptionPane.showMessageDialog(null, "Unsupported look and feel exception exception occurred.\n" + o,
					"Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
