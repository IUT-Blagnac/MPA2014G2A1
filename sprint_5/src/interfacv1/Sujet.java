package interfacv1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class Sujet extends JDialog{

	private String [][] tabData ;
	
	private DefaultTableModel modele ;	
	private JTable donneeTab ;
	private String  myTitre;
		
	public Sujet(JFrame fenetreMere, String titre, String pNameFile) throws IOException {
		getTitre(titre);
		setTitle(titre);
		setLocationRelativeTo(fenetreMere);
		setContentPane(panneauDeContenu(pNameFile));
		setResizable(false);
		setVisible(true);
		pack();
		

	}

	JPanel panneauDeContenu(String fileName) throws IOException {


		//Création des différentes Layout

		JPanel pageEtu = new JPanel(new BorderLayout());
		JPanel grandSud = new JPanel(new FlowLayout());
		JPanel grandCentre = new JPanel();


		tabData = donnees.IOTool.readTab(fileName);
		
		String []entete =  {"ID","Titre","Contexte","Description","Outils"};
		
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

		//Ajout des différents éléments à la fenetre

		grandSud.add(valider);
		grandSud.add(ajouter);
		grandSud.add(supprimer);
		


		//donneeTab.setPreferredSize(new Dimension( 240 , 250 ));
		JScrollPane scrollTab = new JScrollPane(donneeTab);
		grandCentre.add(scrollTab);
	
		supprimer.setPreferredSize(valider.getPreferredSize());
		pageEtu.add(grandSud, BorderLayout.SOUTH);
		pageEtu.add(grandCentre, BorderLayout.CENTER);

		return pageEtu;
	}
	
	public void ajouter(){
		ArrayList<String> tabNomArg = detectionListe(myTitre);
		
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
			case "Liste Etudiants" : tabNomArg.add("nom"); tabNomArg.add("pr�nom"); tabNomArg.add("num�ro �tudiant");
			break;
			case "Liste Sujets" : tabNomArg.add("nom du sujet"); tabNomArg.add("description"); tabNomArg.add("langages");
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
		
		
		donnees.IOTool.writeTab(fileName,donneeTemp);
		donnees.IOTool.exportDonneesSujet(fileName);
		dispose();
	} 
	
	// Permet de r�cup�rer le titre
			public void getTitre(String pTitre){
				myTitre = pTitre;
			}
}
