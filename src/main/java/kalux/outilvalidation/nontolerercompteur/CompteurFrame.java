package kalux.outilvalidation.nontolerercompteur;

import kalux.outilvalidation.traceparser.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CompteurFrame {

	public void execute() throws Exception {
		JFrame jf = new JFrame("CompteurProportionNonTolere");
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
			ArrayList<File> files = new ArrayList<>();
			File[] tempList = fileChooser.getSelectedFiles();

			for (int i = 0; i < tempList.length; i++) {
				if (tempList[i].isFile()) {
					files.add(tempList[i]);
				}
			}
			InkmlParser parserDemo = new InkmlParser();
			ArrayList<Trace> traces = new ArrayList<>();
			for (int i = 0; i < files.size(); i++) {
				Trace trace = new Trace();
				traces.add(parserDemo.xmlParser(files.get(i), trace));
			}

			double ratio = 1.00 - (double) parserDemo.compterNonTolerer(traces) / files.size();
			NumberFormat format = NumberFormat.getPercentInstance();
			format.setMaximumFractionDigits(2);

			msgTextArea.append("Donnees validees non Tolere: " + parserDemo.compterNonTolerer(traces) + ".\n"
					+ "Parmi total de :" + files.size() + " donnees validees" + ".\n" + "La proportion tolere est : "
					+ format.format(ratio) + ".\n\n");

		}

	}

}