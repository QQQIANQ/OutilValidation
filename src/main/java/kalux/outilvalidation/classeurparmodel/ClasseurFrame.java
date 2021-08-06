package kalux.outilvalidation.classeurparmodel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



public class ClasseurFrame {
	public void execute() throws Exception {
		
		

		JFrame jf = new JFrame("Outil de validation");
		jf.setSize(400,400);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,10,10));


		JButton btnClasseur = new JButton("Classer les données par model");
		btnClasseur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClasseurParModel classeur= new ClasseurParModel();
				try {
					classeur.execute();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnClasseur);

		JButton btnClasseurMots = new JButton("Classer les données par mots");
		btnClasseurMots.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClasseurParMots classeurMots= new ClasseurParMots();
				try {
					classeurMots.execute();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});


		panel.add(btnClasseurMots);

		jf.setContentPane(panel);
		jf.setVisible(true);
	}
}
