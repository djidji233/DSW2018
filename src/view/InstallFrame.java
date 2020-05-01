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
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.TreeNode;

import message.MyExceptionHandler;
import tree.model.Cvor;
import tree.model.Modul;
import tree.model.Parametar;

public class InstallFrame extends JFrame {

	public InstallFrame(List<Cvor> list, int broj, File f) {
		if (list.size() <= broj) {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		if (list.get(broj) instanceof Parametar) {
			Parametar p = (Parametar) list.get(broj);
			setTitle(p.getName());
			setSize(400, 400);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel jpanel = new JPanel();

			JPanel jpanel2 = new JPanel();
			JButton jbuttonNext = new JButton("Next");
			JButton jbuttonExit = new JButton("Exit");

			if (p.getType().equals("autor")) {
				jpanel.add(new JLabel("Author " + p.getAutor()));
			}
			if (p.getType().equals("desktop shortcut")) {
				jpanel.add(new JLabel("Desktop shortcut " + String.valueOf(p.isDesktopShortcut())));
			}
			if (p.getType().equals("logo")) {
				jpanel.add(new JLabel(new ImageIcon(p.getLogoPath())));
			}
			if (p.getType().equals("look and feel")) {
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					try {
						if (p.getLookAndFeel().equals(info.getName()))
							UIManager.setLookAndFeel(info.getClassName());
					} catch (ClassNotFoundException e1) {
						MyExceptionHandler.createMessage(e1, this);
					} catch (InstantiationException e1) {
						MyExceptionHandler.createMessage(e1, this);
					} catch (IllegalAccessException e1) {
						MyExceptionHandler.createMessage(e1, this);
					} catch (UnsupportedLookAndFeelException e1) {
						MyExceptionHandler.createMessage(e1, this);
					}
					jpanel.add(new JLabel("Look and feel " + p.getLookAndFeel()));
				}
			}
				if (p.getType().equals("naziv")) {
					jpanel.add(new JLabel("Name " + p.getNaziv()));
				}
				if (p.getType().equals("pokretanje nakon instalacije")) {
					jpanel.add(new JLabel("Run after installation " + String.valueOf(p.isRunAfterInstalation())));
				}
				if (p.getType().equals("uslovi koriscenja")) {
					jpanel.add(new JLabel("Terms of use: " + p.getTermsOfUse()));
				}
				if (p.isCustom()) {
					if (p.getGui().equals("Combo box")) {
						jpanel.add(new JLabel(p.getAutor()));
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
						jpanel.add(new JTextField(20));
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
				if (list.size() == broj + 1) {
					jbuttonNext.setText("Finish");
				}
				jbuttonNext.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (p.getType().equals("pokretanje nakon instalacije")) {
							Desktop desktop = Desktop.getDesktop();
							try {
								desktop.open(f);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if (p.getType().equals("desktop shortcut")) {
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
						if (p.getType().equals("look and feel")) {
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
						if (list.size() == broj) {
							try {
								UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
							} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
									| UnsupportedLookAndFeelException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							dispose();
						}
						InstallFrame instalationFrame = new InstallFrame(list, broj + 1, f);
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
			if (list.get(broj) instanceof Modul) {
				Modul m = (Modul) list.get(broj);
				InstallFrameModul instalationFrameModule = new InstallFrameModul(list, broj + 1, m.getChildren(), 0, f);
				dispose();
			}
		}
}