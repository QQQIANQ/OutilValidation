package kalux.outilvalidation.filtrage;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class FiltrageFrame {

		public void execute() throws Exception {
			
			

			JFrame jf = new JFrame("Outil de validation");
			jf.setSize(400,400);
			jf.setLocationRelativeTo(null);
			jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			JPanel panel = new JPanel();
	        panel.setLayout(new GridLayout(3,2,10,10));


			JButton btnTolererFiltre = new JButton("Filtrer les données tolérées");
			btnTolererFiltre.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					TolererFiltre tolereFiltre= new TolererFiltre();
					try {
						tolereFiltre.execute();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(btnTolererFiltre);

			JButton btnNonTolererFiltre = new JButton("Filtrer les données non tolérées");
			btnNonTolererFiltre.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					NonTolererFiltre nonTolererFiltre= new NonTolererFiltre();
					try {
						nonTolererFiltre.execute();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(btnNonTolererFiltre);


			JButton btnPassableFiltre = new JButton("Filtrer les données passable");
			btnPassableFiltre.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					PassableFiltre passableFilre= new PassableFiltre();
					try {
						passableFilre.execute();
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
