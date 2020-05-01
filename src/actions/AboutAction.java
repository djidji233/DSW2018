package actions;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;


public class AboutAction extends AbstractGlavnaAction {
	
	public AboutAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		//putValue(SMALL_ICON, loadIcon("ja.jpeg"));
		putValue(NAME, "About Me");
		putValue(SHORT_DESCRIPTION, "About");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog dijalog = new JDialog();
		dijalog.setName("About me");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(100, 100));
		
		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File("images/ja.jpeg"));
		} catch (IOException e1) {
			System.out.println("nisam pronasao fajl odakle uzimam sliku");
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		panel.add(picLabel);
		
		
		
		JLabel info = new JLabel(
				"<html>Djordje Zivanovic<br/>"
				+ "RN 98 / 2018</br>"
				+ "<br/>Grupa 201</html>",
				SwingConstants.CENTER
				);
		panel.add(info, BorderLayout.NORTH);
		
		dijalog.add(panel);
		
		dijalog.setSize(500,800);
		dijalog.setLocationRelativeTo(null);		
		dijalog.setVisible(true);		
	}
	
}
