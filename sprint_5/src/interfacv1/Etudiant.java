package interfacv1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Etudiant extends JDialog{

	private String [][] tabData ;
	
	private DefaultTableModel modele ;	
	private JTable donneeTab ;
		
	public Etudiant(JFrame fenetreMere, String titre, String pNameFile) throws IOException {
		setLocationRelativeTo(fenetreMere);
		setContentPane(panneauDeContenu(pNameFile));
		setResizable(false);
		setVisible(true);
		setSize(1000,550);
		

	}

	JPanel panneauDeContenu(String fileName) throws IOException {


		// importation des données
		
			try {
				donnees.IOTool.importDonnees("s2014_2015");
			} catch (Exception e) {
				//e.printStackTrace();
			}
		
		
		//Créationn des différentes Layout
		
		final JPanel pageEtu = new JPanel(new BorderLayout());
		JPanel grandSud = new JPanel(new FlowLayout());
		JPanel grandOuest = new JPanel(new BorderLayout());
		final JPanel grandEst = new JPanel(new BorderLayout());

		@SuppressWarnings("unused")
		ArrayList<donnees.Etudiant> listeEtu = donnees.Etudiant.getListeIndividu();
		tabData = donnees.IOTool.readTab(fileName);

		String[] entete = {"Nom" , "Prenom" , "NumEtu"} ;
		
		
		modele = new DefaultTableModel(tabData , entete) ;
		
		donneeTab = new JTable( modele ) ;
		
	
		//Création des nouveaux boutons

		JButton valider = new JButton("Enregistrer");
		JButton ajouter = new JButton("Ajouter");
		JButton supprimer = new JButton("Supprimer");

		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					enregistrer();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					System.out.println(e.getMessage());
				}
			}
		});
		
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajouter();
			}
		});

		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				supprimer();
			}
		});
		
		// Ajout de la liste des intervenants, sujets, projets, auxquel un étudiant est rataché
		
		donneeTab.addMouseListener(new MouseAdapter() {
			  @SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent e) {
				  
				    int idEtu = donneeTab.getSelectedRow();
				    donnees.Projet monProjet = null;
				    int sizeInt = 0;
				    
				    JLabel titreTab = new JLabel ("Données Complémentaires");		   
				    String [][]numGroupe = new String[0][3];	    
				    
				    for( int i = 0 ; i < donnees.Projet.getListeProjet().size() ; i++ ){
				    	
				    	for( int j = 0 ; j < donnees.Projet.getListeProjet().get(i).getGroupe().getMembres().size() ; j++ ){
				    		
				    		if( donnees.Projet.getListeProjet().get(i).getGroupe().getMembres().get(j) == donnees.Etudiant.getListeIndividu().get(idEtu) ){
				    			
				    			numGroupe = new String[1][3];
				    			
				    			numGroupe[0][0] = donnees.Projet.getListeProjet().get(i).getGroupe().getNom();
				    			numGroupe[0][1] = donnees.Projet.getListeProjet().get(i).getSujet().getTitre();
				    			numGroupe[0][2] = donnees.Projet.getListeProjet().get(i).getNum();
				    			
				    			monProjet = donnees.Projet.getListeProjet().get(i);
				    			
				    		}
				    		
				    	
				    		
				    	}
				    }
				    
				    @SuppressWarnings("rawtypes")
					ArrayList nombreIntervenant = new ArrayList();
	    			int i = 0;
	    			boolean fini = false ;
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
		        	
		        	for(int j = 0; j < sizeInt ; j++){
		        		tabDataTempInt[j][0] = donnees.Participation.getListeParticipation().get((int) nombreIntervenant.get(j)).getRoleS();
		          		tabDataTempInt[j][1] = donnees.Participation.getListeParticipation().get((int) nombreIntervenant.get(j)).getIntervenant().getNom();
		          		tabDataTempInt[j][2] = donnees.Participation.getListeParticipation().get((int) nombreIntervenant.get(j)).getIntervenant().getPrenom();
		          		tabDataTempInt[j][3] = donnees.Participation.getListeParticipation().get((int) nombreIntervenant.get(j)).getIntervenant().getNumInt();
		        	}
	
				    // on supprime tous les anciens elements
		        	
				    grandEst.removeAll();
				    grandEst.revalidate();
				    JPanel pDonneeComp = new JPanel(new GridLayout(3,1));
				    
				    
				     //Création des entetes des des JTables de donnees complementaires
				    
					 String []entete = { "NomGroupe" , "NomSujet" ,"NumProjet" };
					 DefaultTableModel modeleComp = new DefaultTableModel(numGroupe , entete);
					 JTable donneeC = new JTable( modeleComp );
					
					
					 String []enteteInter = { "Role" , "NomInter" , "PrenomInter" ,"NumInter" };   
					 DefaultTableModel modeleInter = new DefaultTableModel(tabDataTempInt , enteteInter);
					 JTable donneeInter = new JTable(modeleInter);
				   
					JScrollPane scrollTabComp = new JScrollPane(donneeC);
					JScrollPane scrollTabInter = new JScrollPane(donneeInter);
					
					pDonneeComp.add(scrollTabComp);
					pDonneeComp.add(scrollTabInter);
					
					grandEst.add(titreTab , BorderLayout.NORTH);
					grandEst.add(pDonneeComp , BorderLayout.CENTER);
				  }			  
			
				 
		});
			

		//Ajout des différents éléments à la fenetre

		grandSud.add(valider);
		grandSud.add(ajouter);
		grandSud.add(supprimer);

		JScrollPane scrollTab = new JScrollPane(donneeTab);
		grandOuest.add(scrollTab , BorderLayout.CENTER);
		
	
		supprimer.setPreferredSize(valider.getPreferredSize());
		pageEtu.add(grandSud, BorderLayout.SOUTH);
		pageEtu.add(grandOuest, BorderLayout.WEST);
		pageEtu.add(grandEst, BorderLayout.EAST);

		return pageEtu;
	}
	
	public void ajouter(){
		ArrayList<String> tabNomArg = detectionListe("Etudiant");
		
		String [] tabVar = new String[tabNomArg.size()];
		
		Object[] Lismessg = new Object[tabNomArg.size()];
		
		boolean test=true;
		int i=0;
		
		for(int j=0 ; j<tabVar.length; j++){
			tabVar[j]="";
		}

		while(test && i<tabVar.length){
			
			while(tabVar[i]== "" ){
				tabVar[i] = JOptionPane.showInputDialog(null, "Veuillez indiquer "+tabNomArg.get(i), "Saisie "+tabNomArg.get(i), JOptionPane.QUESTION_MESSAGE);
				
				if(tabVar[i]== null){
					test=false;
				}
				
				if(tabVar[i]== "" ){
					JOptionPane.showMessageDialog(null, "Saisie incorrecte", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
				if(i == tabVar.length){Lismessg[i] = tabVar[i];}
				
				else{
					Lismessg[i] = tabVar[i];
				}
			}
			
			i++;
		}
		
		if(test){
			modele.addRow(Lismessg);
			donneeTab.setModel(modele);
			donneeTab.repaint();
		}
	}

	public void supprimer(){
		
		int confirmation = 0;
		
		if(donneeTab.getSelectedRows().length == 0){
			JOptionPane.showMessageDialog(null, "Vous n'avez rien sélectionné", "Attention", 
					JOptionPane.ERROR_MESSAGE); 
			confirmation = 1;
		}
		else{
			System.out.println(donneeTab.getSelectedRows()[donneeTab.getSelectedRows().length-1]);
			confirmation = JOptionPane.showConfirmDialog(this, "Voulez vous vraiment supprimer ?", "Suppression",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			
			if(confirmation == 0){
				
				DefaultTableModel ModeleTemp = (DefaultTableModel)donneeTab.getModel();
				int i = 0;
				while(i < donneeTab.getSelectedRows().length)
				{
					ModeleTemp.removeRow(donneeTab.getSelectedRows()[i]);
				}
			
			}
		}
	}
	
	public ArrayList<String> detectionListe(String pTitre){
		
		ArrayList<String> tabNomArg = new ArrayList<String>();
		switch(pTitre){
			case "Liste Etudiants" : tabNomArg.add("nom"); tabNomArg.add("prénom"); tabNomArg.add("numéro étudiant");
			break;
			case "Liste Sujets" : tabNomArg.add("nom du sujet"); tabNomArg.add("description"); tabNomArg.add("langages");
			break;
			case "Liste Intervenants" : tabNomArg.add("nom"); tabNomArg.add("prénom"); tabNomArg.add("numéro intervenants");
			break;
			case "Liste Projets" : tabNomArg.add("numéro de projet"); tabNomArg.add("nom projet"); tabNomArg.add("numéro intervenants");
			break;
		}
		return tabNomArg;
	}

	public void enregistrer() throws FileNotFoundException, UnsupportedEncodingException{
		String fileName = JOptionPane.showInputDialog("Choisissez le nom du fichier");
		
		DefaultTableModel ModeleTemp = (DefaultTableModel)donneeTab.getModel();
		String[][] donneeTemp = new String[ModeleTemp.getRowCount()][ModeleTemp.getColumnCount()];
		
		for( int i = 0 ; i < ModeleTemp.getRowCount() ; i++)
			for( int j = 0 ; j < ModeleTemp.getColumnCount() ; j++)
				donneeTemp[i][j] = ModeleTemp.getValueAt(i, j).toString();
		
		
		donnees.IOTool.exportDonnees(fileName);
		dispose();
	} 
	
}
