package interfacv1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;

import donnees.IOTool;

@SuppressWarnings("serial")
public class InterfaceLog extends JFrame{

	public static void main(String[] args) {

		try {
			IOTool.importDonnees("s2014_2015");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();                                  
		} catch (Exception e) {
			e.printStackTrace();
		}

		JFrame cadre = new InterfaceLog("OPTI");
		cadre.setLocationRelativeTo(null);
		cadre.setVisible(true);

	}

	public InterfaceLog(String titre) {
		super(titre);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		//Action de la fermeture de l'applicationn
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				quitter();
			}
		});

		//setIconImage(new ImageIcon(this.getClass().getResource("img/ico.png")).getImage());

		setContentPane(panneauDeContenu());
		setPreferredSize(new Dimension(500,500));
		setResizable(true);
		pack();
	}

	JPanel panneauDeContenu() {

		JPanel app = new JPanel(new BorderLayout());


		//Création des onglets
		JMenuBar onglets = new JMenuBar();

		JMenu affichages = new JMenu("Affichages");
		JMenu aide = new JMenu("Aide");

		//Création des différents Item
		JMenuItem aPropos = new JMenuItem("A Propos");


		JMenuItem listeEtu = new JMenuItem("Etudiants");
		JMenuItem listeInter = new JMenuItem("Intervenants");
		JMenuItem listeSujets = new JMenuItem("Sujets");
		JMenuItem listeProjets = new JMenuItem("Projets");
		JMenuItem listeGroupes = new JMenuItem("Groupes");
		JMenuItem listeSaisieVoeux = new JMenuItem("Saisir Voeux");

		//Ajout des items dans les menu

		affichages.add(listeEtu);
		affichages.add(listeInter);
		affichages.add(listeSujets);
		affichages.add(listeProjets);
		affichages.add(listeGroupes);
		affichages.add(listeSaisieVoeux);
		onglets.add(affichages);

		aide.add(aPropos);
		onglets.add(aide);	

		//Contenu de la page d'acceuil
		JPanel grandCentre = new JPanel(new GridLayout(3,1));


		JLabel projet = new JLabel("<html><h1><center>OPTI" +
				"<br>Sprint 4</center></h1></html>");
		projet.setBorder(new EtchedBorder(Color.GRAY, Color.black));
		
		projet.setVerticalAlignment(JLabel.CENTER);
		projet.setHorizontalAlignment(JLabel.CENTER);
		
		
		JLabel infos = new JLabel("<html><h2><center>Bienvenue !</center></h2> <p>Ce logiciel " +
				"va vous permettre de gérer les etudiant du PTUT.</p>" +
				"Vous pourrez gérer les étudiants leur groupes ainsi que leur projet !</html>");
		
		infos.setVerticalAlignment(JLabel.CENTER);
		infos.setHorizontalAlignment(JLabel.CENTER);
	
		
		
		projet.setEnabled(false);
		grandCentre.add(projet);
		grandCentre.add(infos);
		grandCentre.add(new JLabel(new ImageIcon("img/pied_page.png","pied de page")));
		
		
		
		JPanel grandNord = new JPanel(new BorderLayout());

		//Action des items dans les menus

		aPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				aProposDial();

			}
		});

		listeEtu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					affEtudiant("Liste Etudiants","etudiants2014_2015");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}

			}
		});


		listeInter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					affIntervenant("Liste Intervenants","intervenants2014_2015");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});


		listeSujets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					affList("Liste Sujets","sujets2014_2015");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}


			}
		});


		listeProjets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					affProjet("Liste Projets","projets2014_2015");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}


			}
		});


		listeGroupes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					affGroupe("Liste Groupes","groupes2014_2015");
				}
				catch(IOException e) {
					System.out.println(e.getMessage());
				}

			}
		});
		
		listeSaisieVoeux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					affSaisieVoeux("Saisie des Voeux","groupes2014_2015");
				}
				catch(IOException e) {
					System.out.println(e.getMessage());
				}

			}
		});





		//Ajouts des onglets

		grandNord.add(onglets);

		app.add(grandNord, BorderLayout.NORTH);
		app.add(grandCentre, BorderLayout.CENTER);

		return app;
	}

	//Création du dial aPropos
	private void aProposDial() {
		APropos dial = new APropos(this, "A Propos");
		dial.setLocationRelativeTo(this);
		dial.setVisible(true);

	
	}

	//Création du dial liste
	private void affList(String name, String fichier) throws IOException {
		Sujet dial = new Sujet(this, name,fichier);
		dial.setLocationRelativeTo(this);
		dial.setVisible(true);
	}
	
	//Création du dial Intervenant
	private void affIntervenant(String name, String fichier) throws Exception {
		Intervenant dial = new Intervenant(this, name,fichier);
		dial.setLocationRelativeTo(this);
		dial.setVisible(true);
	}
	
	//Création du dial liste projets
		private void affProjet(String name, String fichier) throws IOException {
			Projet dial = new Projet(this, name, fichier);
			dial.setLocationRelativeTo(this);
			dial.setVisible(true);
		}
		
		//Création du dial liste groupes
		private void affGroupe(String name, String fichier) throws IOException {
			Groupe dial = new Groupe(this, name, fichier);
			dial.setLocationRelativeTo(this);
			dial.setVisible(true);
		}
		
		//Création du dial étudiant
		private void affEtudiant(String name, String fichier) throws IOException {
			Etudiant dial = new Etudiant(this, name, fichier);
			dial.setLocationRelativeTo(this);
			dial.setVisible(true);
		}
		
		//Création du dial Saisie Voeux
		private void affSaisieVoeux(String name, String fichier) throws IOException {
			SaisieVoeux dial = new SaisieVoeux(this, name, fichier);
			dial.setLocationRelativeTo(this);
			dial.setVisible(true);
		}



	//Action lors de la fermeture de l'application
	private void quitter() {
		int confirmation;
		confirmation = JOptionPane.showConfirmDialog(
				this, "Voulez-vous réellement quitter ?", "Quitter ?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if ( confirmation==JOptionPane.YES_OPTION ) {
			System.exit(0);
		}
	}


}
