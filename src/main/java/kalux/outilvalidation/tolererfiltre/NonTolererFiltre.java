package kalux.outilvalidation.tolererfiltre;

import kalux.outilvalidation.traceparser.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.util.ArrayList;

public class NonTolererFiltre {

	public void execute() throws Exception {

		JFrame jf = new JFrame("Filtrer données non Tolérées");
		jf.setSize(400, 400);
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
				showFileOpenDialog(jf, msgTextArea);
			}
		});
		panel.add(openBtn);

		jf.setContentPane(panel);
		jf.setVisible(true);
	}

	/*
	 * ouvrir fichier
	 */
	private static void showFileOpenDialog(Component parent, JTextArea msgTextArea) {

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
			// un seul fichier
			// File file = fileChooser.getSelectedFile();

			// selectionné tout les fichiers

			JOptionPane.showMessageDialog(parent,
					"Maintenant, Choisir un endroit à sauvegarder les fichies non tolérées", "Message important",
					JOptionPane.INFORMATION_MESSAGE);

			JFileChooser fileChooserSave = new JFileChooser();

			// fileChooser.setCurrentDirectory(new
			// File("C:\\Users\\qma\\Desktop\\KALUX\\Données validées"));

			fileChooserSave.setCurrentDirectory(new File("."));

			fileChooserSave.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			// enable selectionne multi fichiers
			fileChooserSave.setMultiSelectionEnabled(true);

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
				InkmlParser parserDemo = new InkmlParser();
				ArrayList<Trace> traces = new ArrayList<>();

				int compt = 0;
				for (int i = 0; i < files.size(); i++) {
					Trace trace = new Trace();
					traces.add(parserDemo.xmlParser(files.get(i), trace));

					for (int j = i; j < traces.size(); j++) {
						if (!(traces.get(j).getExpectedLetterDetails()
								.equals(traces.get(j).getGroundTruthLetterDetails()))) {
							File newFile = new File(save.getPath(), files.get(i).getName());

							try {

								files.get(i).renameTo(newFile);
								compt++;

							} catch (Exception e) {
								System.out.println(e);
							}

						}
					}

				}

				msgTextArea.append("Déplacement terminé " + compt + " fichier ont été déplacé \n");

			}
		}

	}

}
