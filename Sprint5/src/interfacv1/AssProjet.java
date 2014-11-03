package interfacv1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.server.ExportException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import donnees.Participation;

public class AssProjet extends JDialog
{
	private donnees.Intervenant choixInter;

	public AssProjet(Intervenant fenetreMere, String titre, String pNameFile , donnees.Intervenant pchoixInter) throws IOException 
	{
		choixInter = pchoixInter;
		setLocationRelativeTo(fenetreMere);
		setContentPane(panneauDeContenu(pNameFile));
		setVisible(true);
		setSize(450,250);
	}

	JPanel panneauDeContenu(String fileName) throws IOException 
	{

		// Creation des listes deroulantes

		ArrayList listeProjet = new ArrayList();
		final JComboBox listeDeroulantProjet = new JComboBox();
		final JComboBox listeDeroulantRole = new JComboBox();

		JButton valider = new JButton("Valider");
		JButton annuler = new JButton("annuler");

		final ArrayList roles = new ArrayList();
		final ArrayList indiceProjet = new ArrayList();

		// Creation des JPanel et JLabel

		final JLabel titreProjet = new JLabel("Choisissez un Projet");	
		final JLabel titreRole = new JLabel("Choisissez un Role");

		final JPanel panFormulaire = new JPanel(new GridLayout(4 , 1));
		final JPanel panValidation = new JPanel(new FlowLayout());
		final JPanel panFenetre = new JPanel(new BorderLayout());

		// Import toutes les donnees

		try 
		{
			donnees.IOTool.importDonnees("s2014_2015");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		//On recupere tous les projets pour les afficher

		for(int i = 0 ; i < donnees.Projet.getListeProjet().size() ; i++)
		{
			listeProjet.add( donnees.Projet.getListeProjet().get(i) );
			listeDeroulantProjet.addItem( donnees.Projet.getListeProjet().get(i).getNum() );
		}

		//Action qui permet d'afficher les roles disponiblent pour un projet précis

		listeDeroulantProjet.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				final String choixProjet = (String)listeDeroulantProjet.getSelectedItem();	
				 indiceProjet.add( listeDeroulantProjet.getSelectedIndex() );

				for(int i = 0 ; i < donnees.Participation.getListeParticipation().size() ; i++)
				{
					if( choixProjet == donnees.Participation.getListeParticipation().get(i).getProjet().getNum() )
					{
						roles.add( donnees.Participation.getListeParticipation().get(i).getRoleS() );
					}
				}

				//On nettoie la liste déroulante de toutes les valeurs présentes

				listeDeroulantRole.removeAllItems();

				if( !roles.contains( "client") )
				{
					listeDeroulantRole.addItem("client");
				}

				if( !roles.contains( "superviseur") )
				{
					listeDeroulantRole.addItem("superviseur");
				}

				if( !roles.contains( "support technique") )
				{
					listeDeroulantRole.addItem("support technique");
				}


				panFormulaire.removeAll();
				panFormulaire.revalidate();

				panFormulaire.add(titreProjet);
				panFormulaire.add(listeDeroulantProjet);

				panFormulaire.add(titreRole);
				panFormulaire.add(listeDeroulantRole);
			}
		});

		valider.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				int choixRole = 0;

				if( listeDeroulantRole.getSelectedItem() == "client")
					choixRole = 0;
				if( listeDeroulantRole.getSelectedItem() == "superviseur")
					choixRole = 1;
				if( listeDeroulantRole.getSelectedItem() == "support technique")
					choixRole = 2;



				

				if( listeDeroulantRole.getSelectedIndex() != -1 )
				{
					
					try
					{
						Participation nouvParticipation = new Participation(donnees.Projet.getListeProjet().get((int)indiceProjet.get(0)), choixInter , choixRole);
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					
					try
					{
						donnees.IOTool.exportDonneesParticipation("participation_toast.csv");
					} 
					catch (FileNotFoundException e) 
					{
						e.printStackTrace();
					}
					catch (UnsupportedEncodingException e) 
					{
						e.printStackTrace();
					}
					dispose();
				}
				else
					System.out.println("pas bon");

			}
		});

		annuler.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});

		// ajout des differents elements dans les JPanel

		panFormulaire.removeAll();
		panFormulaire.revalidate();


		panFormulaire.add(titreProjet);
		panFormulaire.add(listeDeroulantProjet);

		panFormulaire.add(titreRole);
		panFormulaire.add(listeDeroulantRole);

		panValidation.add(valider);
		panValidation.add(annuler);

		panFenetre.add(panFormulaire , BorderLayout.NORTH);
		panFenetre.add(panValidation , BorderLayout.SOUTH);

		return panFenetre;
	}
}
