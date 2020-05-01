package view;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import tree.model.Cvor;
import tree.model.Parametar;

public class InstallFrameModul extends JFrame {
	public InstallFrameModul(List<Cvor> list, int hisLevel, List<Parametar> parametri, int broj, File f) {
		if (parametri.size() <= broj) {
			InstallFrame iframe = new InstallFrame(list, hisLevel, f);
			return;
		}
		Parametar p = parametri.get(broj);
		setTitle(p.getName());
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jpanel = new JPanel();
		JPanel jpanel2 = new JPanel();
		JButton jbuttonNext = new JButton("Next");
		JButton jbuttonExit = new JButton("Exit");
		JCheckBox jcheckBox = new JCheckBox();

		if (p.getType().equals("autor")) {
			jpanel.add(new JLabel(p.getAutor()));
		}
		if (p.getType().equals("desktop shortcut")) {
			jcheckBox.setText("Desktop shortcut");
			jpanel.add(jcheckBox);
		}
		if (p.getType().equals("logo")) {
			jpanel.add(new JLabel(new ImageIcon(p.getLogoPath())));
		}
		if (p.getType().equals("look and feel")) {
			jcheckBox.setText("Look and Feel" + p.getLookAndFeel());
			jpanel.add(jcheckBox);
		}
		if (p.getType().equals("naziv")) {
			jpanel.add(new JLabel(p.getNaziv()));
		}
		if (p.getType().equals("pokretanje nakon instalacije")) {
			jcheckBox.setText("Run after installation");
			jpanel.add(jcheckBox);
		}
		if (p.getType().equals("uslovi koriscenja")) {
			jpanel.add(new JLabel(p.getTermsOfUse()));
		}
		if (p.isCustom()) {
			if (p.getGui().equals("Combo box")) {
				jpanel.add(new JLabel("Terms of use: " + p.getAutor()));
				JComboBox<String> jcomboBox = new JComboBox<>();
				jcomboBox.addItem(p.getNaziv());
				jcomboBox.addItem(p.getTermsOfUse());
				jpanel.add(jcomboBox);
			}
			if (p.getGui().equals("Check box")) {
				JCheckBox jcb = new JCheckBox(p.getAutor());
				jpanel.add(jcb);
				jpanel.add(new JLabel(p.getTermsOfUse()));
				jpanel.add(new JLabel(p.getNaziv()));
			}
			if (p.getGui().equals("Text field")) {
				jpanel.add(new JLabel(p.getAutor()));
				jpanel.add(new JTextField());
				jpanel.add(new JLabel(p.getTermsOfUse()));
				jpanel.add(new JLabel(p.getNaziv()));
			}
			if (p.getGui().equals("Radio button")) {
				jpanel.add(new JLabel(p.getAutor()));
				jpanel.add(new JRadioButton());
				jpanel.add(new JLabel(p.getTermsOfUse()));
				jpanel.add(new JLabel(p.getNaziv()));
			}
		}
		jbuttonNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (p.getType().equals("pokretanje nakon instalacije") && jcheckBox.isSelected()) {
					Desktop desktop = Desktop.getDesktop();
					try {
						desktop.open(f);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (p.getType().equals("desktop shortcut") && jcheckBox.isSelected()) {
					String path = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory()
							.toString() + File.separator + f.getName();
					File file = new File(path);
					Path sourcePath = f.toPath();
					Path destinationPath = file.toPath();
					try {
						Files.copy(sourcePath, destinationPath);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (p.getType().equals("look and feel") && jcheckBox.isSelected()) {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if (p.getLookAndFeel().equals(info.getName()))
							try {
								UIManager.setLookAndFeel(info.getClassName());
							} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
									| UnsupportedLookAndFeelException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				}
				if (parametri.size() == broj) {
					InstallFrame installationFrame = new InstallFrame(list, hisLevel, f);
					dispose();
				}
				InstallFrameModul installationFramModule = new InstallFrameModul(list, hisLevel, parametri, broj + 1,
						f);
				dispose();
			}
		});

		jbuttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jpanel2.add(jbuttonNext);
		jpanel2.add(jbuttonExit);
		this.setLayout(new BorderLayout());
		this.add(jpanel, BorderLayout.CENTER);
		this.add(jpanel2, BorderLayout.PAGE_END);
		setVisible(true);
	}
}
