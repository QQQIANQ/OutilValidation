package kalux.outilvalidation.filtrage;

import java.io.File;



import java.io.InputStreamReader;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PassableFiltre {
	public void execute() throws Exception {

		JFrame jf = new JFrame("Filtrer données Passable");
		jf.setSize(400, 450);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();

		// TextArea
		JTextArea msgTextArea = new JTextArea(20, 30);
		msgTextArea.setLineWrap(true);
		msgTextArea.setEditable(false);
		panel.add(msgTextArea);

		JScrollPane scrollPane = new JScrollPane(msgTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panel.add(scrollPane);
		// Bouton ouvrir les fichier
		JButton openBtn = new JButton("Ouvrir");
		openBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showTxtReaderDialog(jf, msgTextArea);
			}
		});
		panel.add(openBtn);


		jf.setContentPane(panel);
		jf.setVisible(true);

	}

	private static void showTxtReaderDialog(Component parent, JTextArea msgTextArea) {

		JOptionPane.showMessageDialog(parent, "Choisir le fichier passable.txt",
				"Message important", JOptionPane.INFORMATION_MESSAGE);

		JFileChooser txtChooser = new JFileChooser();

		txtChooser.setCurrentDirectory(new File("."));

		txtChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		// enable selectionne multi fichiers
		// fileChooser.setMultiSelectionEnabled(true);

		// filtre des fichiers
		txtChooser.addChoosableFileFilter(new FileNameExtensionFilter("txt(*.txt)", "txt"));
		txtChooser.setFileFilter(new FileNameExtensionFilter("txt(*.txt)", "txt"));

		int resultTxt = txtChooser.showOpenDialog(parent);

		if (resultTxt == JFileChooser.APPROVE_OPTION) {

			JOptionPane.showMessageDialog(parent, "Choisir les données à filtrer", "Message important",
					JOptionPane.INFORMATION_MESSAGE);

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

				File fileTxt = txtChooser.getSelectedFile();
				ArrayList<File> files = new ArrayList<>();
				File[] tempList = fileChooser.getSelectedFiles();
				for (int i = 0; i < tempList.length; i++) {
					if (tempList[i].isFile()) {
						files.add(tempList[i]);
					}
				}
				JOptionPane.showMessageDialog(parent, "Maintenant, chosir un endroit à sauvegarder les données passable",
						"Message important", JOptionPane.INFORMATION_MESSAGE);
				JFileChooser fileChooserSave = new JFileChooser();

				fileChooserSave.setCurrentDirectory(new File("."));

				fileChooserSave.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				// enable selectionne multi fichiers
				fileChooserSave.setMultiSelectionEnabled(true);

				// filtre des fichiers
				fileChooserSave.addChoosableFileFilter(new FileNameExtensionFilter("inkml(*.inkml)", "inkml"));
				fileChooserSave.setFileFilter(new FileNameExtensionFilter("inkml(*.inkml)", "inkml"));

				int resultSave = fileChooserSave.showOpenDialog(parent);
				if (resultSave == JFileChooser.APPROVE_OPTION) {
					File save = fileChooserSave.getSelectedFile();
					int compt = 0;

					try {
						InputStreamReader reader = new InputStreamReader(new FileInputStream(fileTxt),"UTF-8");
						BufferedReader br = new BufferedReader(reader);
						ArrayList<String> filesName = new ArrayList<>();
						String line = br.readLine();
						while (line != null) {
							filesName.add(line);
							line = br.readLine();
						}
						br.close();
						
					for (int i = 0; i < filesName.size(); i++) {
							for (int j = 0; j < files.size(); j++) {
							
							
								if (files.get(j).getName().equals(filesName.get(i))) {
									
									File newFile = new File(save.getPath(), files.get(j).getName());
									try {
										files.get(j).renameTo(newFile);
										compt++;
									} catch (Exception e) {
										System.out.println(e);
									}
								}
							}
						}

					}

					catch (Exception e) {
						e.printStackTrace();
					}

					msgTextArea.append("Filtrage terminé, " + compt + " fichier ont été trouvé et déplacé \n");
				

				}
			}

		}
	}
}
