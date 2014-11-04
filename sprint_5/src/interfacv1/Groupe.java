package interfacv1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



@SuppressWarnings("serial")
public class Groupe extends JDialog{
	
	private ArrayList<donnees.Groupe> tabData ;
	

		
	public Groupe(JFrame fenetreMere, String titre, String pNameFile) throws IOException {
		setTitle(titre);
		setLocationRelativeTo(fenetreMere);
		setContentPane(panneauDeContenu());
		setResizable(false);
		setVisible(true);
		setSize(new Dimension(450,450));
		}
	
	
	JPanel panneauDeContenu() throws IOException {
		
		//Création des différentes Layout

		JPanel pageEtu = new JPanel(new BorderLayout());
		JPanel grandOuest = new JPanel(new GridLayout());
		final JPanel grandCentre = new JPanel(new GridLayout(2,1));
		final JPanel petitNord = new JPanel(new BorderLayout());
		final JPanel petitSud = new JPanel(new BorderLayout());
		
		
		final JLabel sujet = new JLabel ("Aucun groupe sélectionné"); 


		//Import des donnees
		try {
			donnees.IOTool.importDonnees("s2014_2015");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//On récupère la liste des groupes
		tabData = donnees.Groupe.getListeGroupe();
		
		//On créé la liste des groupes
		final List listGrp = new List(tabData.size(), false);
		
		
		
		ArrayList<String> StringTabData = new ArrayList<String>();
		
		//On créé la liste des groupes
		for(int i=0; i < tabData.size();i++){
			//Récupère le nom du groupe
			StringTabData.add(tabData.get(i).getNom());
			
			listGrp.add((String) StringTabData.get(i));
		}
		 //Action lorsque l'on clique dans la Liste
		listGrp.addItemListener(new ItemListener(){
	        @SuppressWarnings("unchecked")
			public void itemStateChanged(ItemEvent ie)
	        {
	        	
	        	
	        	
	        	int size = tabData.get(listGrp.getSelectedIndex()).getMembres().size();
	        	
	        	//Vérification du nombre d'étudiants dans le groupe
	        	if(tabData.get(listGrp.getSelectedIndex()).getMembres().get(3)==null){
	        		size --;
	        	}
	        	if(tabData.get(listGrp.getSelectedIndex()).getMembres().get(4)==null){
	        		size --;
	        	}
	        	String [][] tabDataTemp = new String[size][3];
	        	String[] enteteGrp = {"Nom","Prenom", "Numéro Etudiant"};

	    		//Remplis la JTable avec le nom, le prenom ou le numEtu
	    		for(int i = 0 ; i < tabDataTemp.length ; i++){
	    			
	    				try{
									tabDataTemp[i][0] = tabData.get(listGrp.getSelectedIndex()).getMembres().get(i).getNom();
									tabDataTemp[i][1] = tabData.get(listGrp.getSelectedIndex()).getMembres().get(i).getPrenom();
									tabDataTemp[i][2] = tabData.get(listGrp.getSelectedIndex()).getMembres().get(i).getNumEtu();
	    				}
	    				catch(Exception e){
	    					
	    					System.out.println(e.getMessage());
	    				}
			
	    		}
	    		
	    		int i=0;
	    		boolean fini = false;
	    		boolean projet = false;
	    		//Récupération du projet en comparant les groupes des projets
	    		while(!fini){
	    			
	    			if(i >= donnees.Projet.getListeProjet().size()){
	    				sujet.setText("Ce groupe n'a pas encore été affecté a un projet");
	    				fini = true;
	    				
	    			}
	    			
	    			else if(donnees.Projet.getListeProjet().get(i).getGroupe() == tabData.get(listGrp.getSelectedIndex())){
		
	    				//Affichage du sujet grâce au porojet du groupe
	    	        	sujet.setText("Sujet : "+donnees.Projet.getListeProjet().get(i).getSujet().getTitre()+" Voeux n° "+(tabData.get(listGrp.getSelectedIndex()).getVoeux().indexOf(donnees.Projet.getListeProjet().get(i).getSujet())+1));
	    				fini = true;
	    				projet = true;
	    			}
	    	
	    			
	    			i++;
	    		}
	    		
	    		

	    		
	    		
	    		
	    		//Ajout des groupes
	        	DefaultTableModel modeleGrp = new DefaultTableModel(tabDataTemp , enteteGrp);
	        	JTable donneeTabGrp = new JTable( modeleGrp ) ;
	        	donneeTabGrp.setEnabled(false);
	        	JScrollPane scrollTabGrp = new JScrollPane(donneeTabGrp);
	        	
	        	
	        	
	        	
	        	//Ajout des intervenants
	    		JLabel inter = new JLabel ("");
	        	String []enteteInt = {"Qualité","Nom","Prenom","Numéro Intervenant"};
	        	
	        	
	        	//Cherche le nombre d'intervenants
	        	int sizeInt = 0;
	        	
	    		//vérifie que le groupe a un projet pûis cherche les intervenants si il a un groupe
	    		if(projet){
	    			inter.setText("Liste des intervenants pour ce groupe");
	    			donnees.Projet monProjet = donnees.Projet.getListeProjet().get(i-1);
	    			@SuppressWarnings("rawtypes")
					ArrayList nombreIntervenant = new ArrayList();
	    			i=0;
	    			fini = false ;
	    			//Calcul le nombre d'intervenant
	    			while(!fini){
	    

		    			if(i >= donnees.Participation.getListeParticipation().size()){
		    				fini = true;
		    				
		    			}
		    			else if(donnees.Participation.getListeParticipation().get(i).getProjet() == monProjet){
		    				nombreIntervenant.add(i);
		    				//Affichage du sujet grâce au porojet du groupe
		    	        	sizeInt++;
		    			}
		    	
		    			
		    			i++;
		    		}
	    			
	    			
		        	String [][] tabDataTempInt = new String[sizeInt][4];
		        	
		        	//On remplit le tableau des intervenants
		        	for(int j =0; j < sizeInt ; j++){
		        		tabDataTempInt[j][0] = donnees.Participation.getListeParticipation().get((int) nombreIntervenant.get(j)).getRoleS();
		          		tabDataTempInt[j][1] = donnees.Participation.getListeParticipation().get((int) nombreIntervenant.get(j)).getIntervenant().getNom();
		          		tabDataTempInt[j][2] = donnees.Participation.getListeParticipation().get((int) nombreIntervenant.get(j)).getIntervenant().getPrenom();
		          		tabDataTempInt[j][3] = donnees.Participation.getListeParticipation().get((int) nombreIntervenant.get(j)).getIntervenant().getNumInt();
		        	}
		        	
		        	
		        	
		        	//Ajout des intervenants au panel
		          	petitSud.removeAll();        	
		        	petitSud.revalidate();
		        	DefaultTableModel modeleInt = new DefaultTableModel(tabDataTempInt, enteteInt);
		        	JTable donneeTabInt = new JTable( modeleInt ) ;
		        	donneeTabInt.setEnabled(false);
		        	JScrollPane scrollTabInt = new JScrollPane(donneeTabInt);
		        	petitSud.add(scrollTabInt, BorderLayout.CENTER);
		        	
	    		}
	    		else{
	    			
	    			//On ajoute un table vide au Panel
	    			petitSud.removeAll();        	
	    			petitSud.revalidate();
	    			
	    			String [][] tabDataTempInt = new String[0][4];
		        	
	    			DefaultTableModel modeleInt = new DefaultTableModel(tabDataTempInt, enteteInt);
		        	JTable donneeTabInt = new JTable( modeleInt ) ;
		        	donneeTabInt.setEnabled(false);
		        	JScrollPane scrollTabInt = new JScrollPane(donneeTabInt);
	    			
	    			inter.setText("Il n'y a pas d'intervenant pour ce groupe");
	    			petitSud.add(scrollTabInt, BorderLayout.CENTER);
	    		}
    	
	        	//On supprime ce qu'il y a dans le panel puis on mets ce que l'on veut
	        	petitNord.removeAll();	
	        	petitNord.revalidate();
	        	petitNord.add(sujet, BorderLayout.NORTH);
	        	petitNord.add(scrollTabGrp,  BorderLayout.CENTER);
	        	
	        	petitSud.add(inter,  BorderLayout.NORTH);
	        	
	        	

	        }
	    });
		
		
		petitNord.add(sujet,  BorderLayout.NORTH);
		grandOuest.add(listGrp);
		grandCentre.removeAll();        	
		grandCentre.revalidate();
		grandCentre.add(petitNord);
		grandCentre.add(petitSud);
		pageEtu.add(grandOuest, BorderLayout.WEST);
		pageEtu.add(grandCentre, BorderLayout.CENTER);

		

		return pageEtu;
	}
	
}
