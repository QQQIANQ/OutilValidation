package kalux.outilvalidation.classeurparmodel;

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
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import kalux.outilvalidation.traceparser.InkmlParser;
import kalux.outilvalidation.traceparser.Trace;

public class ClasseurParModel {
	public void execute() throws Exception {

		JFrame jf = new JFrame("Classeur les données traité par model");
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

		JButton openBtn = new JButton("Ouvrir");
		panel.add(scrollPane);
		openBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showClasseurDialog(jf,msgTextArea);
			}
		});
		panel.add(openBtn);

		jf.setContentPane(panel);
		jf.setVisible(true);

	}
	
	/*
	 * ouvrir fichier
	 */
	private static void showClasseurDialog(Component parent, JTextArea msgTextArea) {

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

			// fileChooser.setCurrentDirectory(new
			// File("C:\\Users\\qma\\Desktop\\KALUX\\Données validées"));

			fileChooserSave.setCurrentDirectory(new File("."));

			fileChooserSave.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			// enable selectionne multi fichiers
			//fileChooserSave.setMultiSelectionEnabled(true);

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
					traces.add(parserDemo.inkmlParser(files.get(i), trace));

					for (int j = i; j < traces.size(); j++) {
						if (traces.get(j).getExpectedLetterDetails()!=null) {
							File newFile = new File(save.getPath(), traces.get(j).getExpectedLetterDetails());
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

				}

				msgTextArea.append("Classment terminé " + compt + " fichier ont classé \n");

			}
		}

	}
	
	
	
	
}
