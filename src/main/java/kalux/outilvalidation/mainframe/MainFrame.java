package kalux.outilvalidation.mainframe;

import javax.swing.*;

import kalux.outilvalidation.nontolerercompteur.*;
import kalux.outilvalidation.passablefiltre.*;
import kalux.outilvalidation.tolererfiltre.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.formdev.flatlaf.IntelliJTheme;
public class MainFrame {
	public static void main(String[] args) throws Exception {
		
		IntelliJTheme.setup( MainFrame.class.getResourceAsStream("/arc-theme.theme.json") );

		JFrame jf = new JFrame("Outil de validation");
		jf.setSize(400,400);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,2,10,10));

		//panel.setLayout(new FlowLayout(FlowLayout.CENTER,30,40));

		JButton btnCompteur = new JButton("<html>"+ "Compteur données <br> non tolérées </html>");
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

		JButton btnTolererFiltre = new JButton("Filtre données tolérées");
		btnTolererFiltre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TolererFiltre tolererFiltre = new TolererFiltre();
				try {
					tolererFiltre.execute();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnTolererFiltre);

		JButton btnNonTolererFiltre = new JButton("Filtre données non tolérées");
		btnNonTolererFiltre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NonTolererFiltre nontolererFiltre = new NonTolererFiltre();
				try {
					nontolererFiltre.execute();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel.add(btnNonTolererFiltre);

		JButton btnPassableFiltre = new JButton("Filtre données passable");
		btnPassableFiltre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TxtReader txtReader = new TxtReader();
				try {
					txtReader.execute();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel.add(btnPassableFiltre);

		jf.setContentPane(panel);
		jf.setVisible(true);
	}
}
