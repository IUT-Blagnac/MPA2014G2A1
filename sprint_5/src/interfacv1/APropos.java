package interfacv1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;



@SuppressWarnings("serial")
public class APropos extends JDialog{
	public APropos(JFrame fenetreMere, String titre) {
		setTitle(titre);
		setLocationRelativeTo(fenetreMere);
		setContentPane(panneauDeContenu());
		setResizable(false);
		setVisible(true);
		setSize(300,400);
	
	}
	
	JPanel panneauDeContenu() {
		JPanel pan = new JPanel(new BorderLayout());
		JPanel grandNord = new JPanel(new BorderLayout());
		JPanel grandSud = new JPanel(new GridLayout(2,1));
		
		JLabel projet = new JLabel("<html><h1><center>OPTI" +
				"<br>Sprint 4</center></h1></html>");
		projet.setEnabled(false);
		
		JLabel infos = new JLabel("<html>Université Toulouse II" +
				"<br>IUT Blagnac 2014-2015" +
				"<br>DUT INFO S3/Module MPA</html>");
		
		projet.setBorder(new EtchedBorder(Color.GRAY, Color.black));

		
		JLabel membres = new JLabel("<html><u><center>Liste des membres :</u>" +
				"<br>Victor PINQUIER" +
				"<br>Antoine RIVALIER" +
				"<br>Jean-Sebastien TRILLE" +
				"<br>Tristan BAVEREZ" +
				"<br>Etienne LARROUY</center></html>");
		
		grandNord.add(projet);
		grandSud.add(infos);
		grandSud.add(membres);
		
		// Alignements
		
		projet.setVerticalAlignment(JLabel.CENTER);
		projet.setHorizontalAlignment(JLabel.CENTER);
		
		infos.setVerticalAlignment(JLabel.CENTER);
		infos.setHorizontalAlignment(JLabel.CENTER);
		
		membres.setVerticalAlignment(JLabel.CENTER);
		membres.setHorizontalAlignment(JLabel.CENTER);
		
		pan.add(grandNord, BorderLayout.NORTH);
		pan.add(grandSud, BorderLayout.CENTER);
		
		return pan;	
	}

}
