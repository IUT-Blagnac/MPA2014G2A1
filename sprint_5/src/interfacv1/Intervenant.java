package interfacv1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
public class Intervenant extends JDialog{

	private ArrayList<donnees.Intervenant> tabData;
	private donnees.Intervenant choixInter;
	
	private DefaultTableModel modele ;	
	private JTable donneeTab ;
		
	public Intervenant(JFrame fenetreMere, String titre, String pNameFile) throws Exception {
		choixInter = new donnees.Intervenant("", "", "");
		setTitle(titre);
		setLocationRelativeTo(fenetreMere);
		setContentPane(panneauDeContenu(pNameFile));
		setResizable(false);
		setVisible(true);
		setSize(1000,500);
		
	}

	JPanel panneauDeContenu(String fileName) throws IOException {


		//Crï¿½ationn des diffï¿½rentes Layout
		JPanel pageEtu = new JPanel(new BorderLayout());
		
		JPanel pageEtuW = new JPanel(new BorderLayout());
		JPanel pageEtuE = new JPanel(new BorderLayout());

		JPanel grandSud = new JPanel(new FlowLayout());
		JPanel grandCentre = new JPanel();
		
		JPanel petitNord = new JPanel(new FlowLayout());
		final JPanel petitCentre = new JPanel(new BorderLayout());
		
		final JLabel sujet = new JLabel ("Aucun groupe sï¿½lectionnï¿½");


		// Import des donnï¿½es
		
		try {
			donnees.IOTool.importDonnees("s2014_2015");
		} catch (Exception e) {
			e.printStackTrace();
		}
		tabData = donnees.Intervenant.getListeIntervenant();
		
		String[] entete = {"Nom","Prenom", "numInt"};
		
    	String [][] tabDataTemp = new String[donnees.Intervenant.getListeIntervenant().size()][3];
    	
    	// Rï¿½cupï¿½ration des donnï¿½es des Intervenants
    	
    	for(int i = 0 ; i < tabDataTemp.length ; i++){
			for(int j = 0; j < 3 ;j++){
			
					if(j == 0)
						tabDataTemp[i][j] = donnees.Intervenant.getListeIntervenant().get(i).getNom();
					if(j == 1)
						tabDataTemp[i][j] = donnees.Intervenant.getListeIntervenant().get(i).getPrenom();
					if(j == 2)
						tabDataTemp[i][j] = donnees.Intervenant.getListeIntervenant().get(i).getNumInt();

			}
    	}
    	
		modele = new DefaultTableModel(tabDataTemp , entete) ;
		donneeTab = new JTable( modele );
		
		JScrollPane scrollTab = new JScrollPane(donneeTab);
	
		//Crï¿½ation des nouveaux boutons

		JButton valider = new JButton("Enregistrer");
		JButton ajouter = new JButton("Ajouter");
		JButton assProjet = new JButton("Assigner Projet");
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
		
		assProjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					assProjet();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// Affiche les projets auxquelles l'intervenant est associï¿½
		donneeTab.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    
				  //Affichage du sujet
				    sujet.setText(""+tabData.get(donneeTab.getSelectedRows()[0]).getNom());
		        	
				    ArrayList<donnees.Participation> listPar = donnees.Participation.getListeParticipation();
				    ArrayList<donnees.Participation> listParRes = new ArrayList<donnees.Participation>();
				    
				    int count = 0;

				    for(int i=0 ; i < listPar.size() ; i++){

					    	if(listPar.get(i).getIntervenant().getNumInt().equals(donneeTab.getValueAt(donneeTab.getSelectedRow(),2))){
					    		listParRes.add(listPar.get(i));
					    		choixInter = listPar.get(i).getIntervenant();
					    		count ++;
					    	}
				    	
				    }

				    String [][] tabDataTemp2 = new String[count][4];
				    
				  //Remplis la JTable avec le numProjet, titre de sujet, rï¿½le et nom de groupe
				    for(int i=0 ; i < listParRes.size() ; i++){
	
				    		tabDataTemp2[i][0] = listParRes.get(i).getProjet().getNum();
				    		tabDataTemp2[i][1] = listParRes.get(i).getProjet().getSujet().getTitre();
				    		tabDataTemp2[i][2] = listParRes.get(i).getRoleS();
				    		tabDataTemp2[i][3] = listParRes.get(i).getProjet().getGroupe().getNom();
				    	

				    }
		        	
		        	//Affichage de la JTable des ï¿½tudiants du groupe
		        	
		    		String[] entete2 = {"Projet","Sujet","Rôle", "Groupe"};

		    		DefaultTableModel modele = new DefaultTableModel(tabDataTemp2 , entete2);
		        	JTable donneeTab2 = new JTable( modele ) ;
		        	donneeTab2.setEnabled(false);
		        	JScrollPane scrollTab2 = new JScrollPane(donneeTab2);
		        	petitCentre.removeAll();
		        	petitCentre.revalidate();
		        	petitCentre.add(scrollTab2);

				   
			  }
		});
		

		//Ajout des diffï¿½rents ï¿½lï¿½ments ï¿½ la fenetre

		grandSud.add(valider);
		grandSud.add(ajouter);
		grandSud.add(assProjet);
		grandSud.add(supprimer);

		//donneeTab.setPreferredSize(new Dimension( 240 , 250 ));
		grandCentre.add(scrollTab);
	
		supprimer.setPreferredSize(valider.getPreferredSize());
		
		petitNord.add(sujet);
		
		pageEtuW.add(grandSud, BorderLayout.SOUTH);
		pageEtuW.add(grandCentre, BorderLayout.CENTER);
		
		pageEtuE.add(petitNord, BorderLayout.NORTH);
		pageEtuE.add(petitCentre, BorderLayout.CENTER);
		
		pageEtu.add(pageEtuW, BorderLayout.WEST);
		pageEtu.add(pageEtuE, BorderLayout.EAST);
		

		return pageEtu;
	}
	
	// Ajoute un nouvel Intervenant
	
	public void ajouter(){
		String[] tabNomArg = { "nom", "prenom","numInt"};		
		Object[] Lismessg = new Object[donneeTab.getColumnCount()];
		
		boolean test=true;
		int i=0;
		
		for(int j=0 ; j<Lismessg.length; j++){
			Lismessg[j]="";
		}

		while(test && i< Lismessg.length){
			
			while(Lismessg[i]== "" ){
				Lismessg[i] = JOptionPane.showInputDialog(null, "Veuillez indiquer "+tabNomArg[i], "Saisie "+tabNomArg[i], JOptionPane.QUESTION_MESSAGE);
				
				if(Lismessg[i]== null){
					test=false;
				}
				
				if(Lismessg[i]== "" ){
					JOptionPane.showMessageDialog(null, "Saisie incorrecte", "Erreur", JOptionPane.ERROR_MESSAGE);
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

	
	//Supprime un Intervenant de la liste
	
	public void supprimer(){
		
		int confirmation = 0;
		
		if(donneeTab.getSelectedRows().length == 0){
			JOptionPane.showMessageDialog(null, "Vous n'avez rien sï¿½lectionnï¿½", "Attention", 
					JOptionPane.ERROR_MESSAGE); 
			confirmation = 1;
		}
		else{
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
	
public void assProjet() throws IOException {
		
	@SuppressWarnings("unused")
	AssProjet associationProjet;
	
	if( donneeTab.getSelectedRow() != -1 )
		associationProjet = new AssProjet(this , "Association Projet/Role" , "participations2014_2015.csv"  , choixInter );
	else
		JOptionPane.showMessageDialog(this , "Aucun Intervenant saisis" , "Erreur", JOptionPane.WARNING_MESSAGE );
	}


	// Enregistre le fichier contenant les intervenants
	
	public void enregistrer() throws FileNotFoundException, UnsupportedEncodingException{
		
		String fileName = JOptionPane.showInputDialog("Choisissez le nom du fichier");
		
		DefaultTableModel ModeleTemp = (DefaultTableModel)donneeTab.getModel();
		String[][] donneeTemp = new String[ModeleTemp.getRowCount()][ModeleTemp.getColumnCount()];
		
		for( int i = 0 ; i < ModeleTemp.getRowCount() ; i++)
			for( int j = 0 ; j < ModeleTemp.getColumnCount() ; j++)
				donneeTemp[i][j] = ModeleTemp.getValueAt(i, j).toString();
		
		
		donnees.IOTool.exportDonneesEtudiant(fileName);
		dispose();

	} 

}

