package kalux.outilvalidation.mainframe;

import javax.swing.*;



import kalux.outilvalidation.classeurparmodel.ClasseurFrame;
import kalux.outilvalidation.filtrage.FiltrageFrame;
import kalux.outilvalidation.nontolerercompteur.CompteurFrame;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class MainFrame {
	public static void main(String[] args) throws Exception {
		FlatIntelliJLaf.setup();

		JFrame jf = new JFrame("Outil de validation");
		jf.setSize(400,400);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,10,10));

		JButton btnCompteur = new JButton("Calculer proportions données non-tolérées ");
		btnCompteur.setHorizontalTextPosition(JButton.CENTER);
		btnCompteur.setVerticalTextPosition(JButton.CENTER);
		btnCompteur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CompteurFrame compteurFrame = new CompteurFrame();
				try {
					compteurFrame.execute();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnCompteur);
		
		JButton btnFiltre = new JButton("Données filtrage");
		btnFiltre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FiltrageFrame filtrageFrame= new FiltrageFrame();
				try {
					filtrageFrame.execute();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel.add(btnFiltre);
		
		JButton btnClasseur = new JButton("Données classeur");
		btnClasseur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClasseurFrame classeur= new ClasseurFrame();
				try {
					classeur.execute();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel.add(btnClasseur);

		jf.setContentPane(panel);
		jf.setVisible(true);
	}
}
