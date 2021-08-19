package kalux.outilvalidation.classeurparmodel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class ClasseurParLots {


	public void execute() throws Exception {
		FlatIntelliJLaf.setup();
		JFrame jf = new JFrame("Classeur les données par lots");
		jf.setSize(400, 450);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		JTextField jtextField = new JTextField(10);
		panel.add(jtextField, BorderLayout.CENTER);
		// TextArea
		JTextArea msgTextArea = new JTextArea(20, 30);
		msgTextArea.setLineWrap(true);
		msgTextArea.setEditable(false);
		panel.add(msgTextArea);

		JScrollPane scrollPane = new JScrollPane(msgTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane);
		JButton openBtn = new JButton("Commencer");
		openBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				showClasseurLotsDialog(jf, msgTextArea, jtextField);
			}
		});
		panel.add(openBtn);

		jf.setContentPane(panel);
		jf.setVisible(true);

	}

	private static void showClasseurLotsDialog(Component parent, JTextArea msgTextArea, JTextField jtextField) {

		if (isNumeric(jtextField.getText())) {
			int nbParLots = Integer.valueOf(jtextField.getText());
			JOptionPane.showMessageDialog(parent, "Choisir les fichiers à traiter, ctrl + a pour tout selectionné",
					"Message important", JOptionPane.INFORMATION_MESSAGE);

			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setCurrentDirectory(new File("."));

			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			// enable selectionne multi fichiers
			fileChooser.setMultiSelectionEnabled(true);

			// filtre des fichiers
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("inkml(*.inkml)", "inkml"));
			fileChooser.setFileFilter(new FileNameExtensionFilter("inkml(*.inkml)", "inkml"));

			int result = fileChooser.showOpenDialog(parent);
			if (result == JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(parent, "Maintenant, Choisir un endroit à sauvegarder les fichier classé",
						"Message important", JOptionPane.INFORMATION_MESSAGE);

				JFileChooser fileChooserSave = new JFileChooser();

				fileChooserSave.setCurrentDirectory(new File("."));

				fileChooserSave.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				// enable selectionne multi fichiers
				// fileChooserSave.setMultiSelectionEnabled(true);

				// filtre des fichiers
				fileChooserSave.addChoosableFileFilter(new FileNameExtensionFilter("inkml(*.inkml)", "inkml"));
				fileChooserSave.setFileFilter(new FileNameExtensionFilter("inkml(*.inkml)", "inkml"));

				int resultSave = fileChooserSave.showOpenDialog(parent);
				if (resultSave == JFileChooser.APPROVE_OPTION) {
					ArrayList<File> files = new ArrayList<>();
					File[] tempList = fileChooser.getSelectedFiles();
					File save = fileChooserSave.getSelectedFile();

					for (int i = 0; i < tempList.length; i++) {
						if (tempList[i].isFile()) {
							files.add(tempList[i]);
						}
					}
					int lot = 0;
					int compt =0;

					if (result < files.size()) {
						for (int i = 0; i < files.size(); i++) {
							if (i%nbParLots == 0) {
								lot++;
							}
							File newFile = new File(save.getPath(), "Lot"+String.valueOf(lot));
							if(!newFile.exists()) {
								newFile.mkdir();
							}
							File newTrace= new File(newFile.getPath(), files.get(i).getName());
							try {
								files.get(i).renameTo(newTrace);
								compt++;
							} catch (Exception e) {
								System.out.println(e);
							}
							
							
						}
					}
					msgTextArea.append("Classment terminé, " + compt + " fichier ont été classé dans " +lot+" lots \n");

				}

			}
		}

	}

	private static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
