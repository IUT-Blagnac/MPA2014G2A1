package interfacv1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
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
public class SaisieVoeux  extends JDialog{
	
	private ArrayList<donnees.Groupe> tabData ;
	
	public SaisieVoeux (JFrame fenetreMere, String titre, String pNameFile) throws IOException {
		setTitle(titre);
		setLocationRelativeTo(fenetreMere);
		setContentPane(panneauDeContenu(pNameFile));
		setResizable(false);
		setVisible(true);
		setSize(900,500);
	}
	
JPanel panneauDeContenu(String fileName) throws IOException {
		
		//Création des différentes Layout

		JPanel pageEtu = new JPanel(new BorderLayout());
		JPanel grandOuest = new JPanel(new GridLayout());
		final JPanel grandCentre = new JPanel(new BorderLayout());
		final JPanel petitNord = new JPanel(new FlowLayout());
		final JPanel petitCentre = new JPanel(new FlowLayout());
		final JPanel petitSud = new JPanel(new FlowLayout());
		
		
		final JLabel sujet = new JLabel ("Aucun groupe sélectionné");

		//Import des donnees
		try {
			donnees.IOTool.importDonnees("s2014_2015");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//On récupére la liste des groupes
		tabData = donnees.Groupe.getListeGroupe();
		
		//On créé la liste des groupes
		final List listGrp = new List(tabData.size(), false);
		
		
		
		ArrayList<String> StringTabData = new ArrayList<String>();
		
		//On créé la liste des groupes
		for(int i=0; i < tabData.size();i++){
			//Récupére le nom du groupe
			StringTabData.add(tabData.get(i).getNom());
			
			listGrp.add((String) StringTabData.get(i));
		}
		 //Action lorsque l'on clique dans la Liste
		listGrp.addItemListener(new ItemListener(){
	        public void itemStateChanged(ItemEvent ie)
	        {
	        	//Affichage du sujet
	        	sujet.setText("Veuillez indiquer le rang des "+donnees.Sujet.getListeSujet().size()+" sujets en commencant par 0.");
	    
	        	donnees.Groupe grpTemp = donnees.Groupe.getListeGroupe().get(listGrp.getSelectedIndex());
	        	ArrayList<donnees.Sujet> arrayTemp = grpTemp.getVoeux();
	        	
	        	final String [][] tabDataTemp = new String[donnees.Sujet.getListeSujet().size()][2];
	        	String[] entete = {"Sujet","Rang"};

	    		//Remplis la JTable avec l'acronyme et la liste des voeux
	    		for(int i = 0 ; i < donnees.Sujet.getListeSujet().size() ; i++){
	    			
	    				try{
									tabDataTemp[i][0] = donnees.Sujet.getListeSujet().get(i).getTitre();
									tabDataTemp[i][1] = ""+arrayTemp.indexOf(donnees.Sujet.getListeSujet().get(i));
	    				}
	    				catch(Exception e){
	    					
	    					System.out.println(e.getMessage());
	    				}
			
	    		}
	    		
	    		
	    		
	    		    		
	    		//Creation de la jTable
	        	DefaultTableModel modele = new DefaultTableModel(tabDataTemp , entete);
	        	final JTable donneeTab = new JTable( modele ) ;
	        	donneeTab.setEnabled(true);
	        	JScrollPane scrollTab = new JScrollPane(donneeTab);
	        	petitCentre.removeAll();
	        	petitCentre.revalidate();
	        	petitCentre.add(scrollTab);
	        		   
	        	JButton valider = new JButton("Valider");
	        	
	        	
	        	
	        	valider.addActionListener(new ActionListener() {	        			        			        		
	    			public void actionPerformed(ActionEvent arg0) {
	    				for(int i = 0 ; i < donneeTab.getRowCount() ; i++){
	    					tabData.get(listGrp.getSelectedIndex()).setPosVoeux(donnees.Sujet.getListeSujet().get(i), Integer.parseInt(donneeTab.getValueAt(i, 1).toString()));
	    				}
	    				JOptionPane.showMessageDialog(new JFrame(), "Voeux saisis");
	    			}
	    		});
	        	
	        	petitSud.removeAll();
	        	petitSud.revalidate();
	        	petitSud.add(valider);
	        	
	        	
	        }
	        
	    });
	    
		petitNord.add(sujet);
		grandOuest.add(listGrp);
		grandCentre.add(petitNord, BorderLayout.NORTH);
		grandCentre.add(petitCentre, BorderLayout.CENTER);
		grandCentre.add(petitSud, BorderLayout.SOUTH);
		pageEtu.add(grandOuest, BorderLayout.WEST);
		pageEtu.add(grandCentre, BorderLayout.CENTER);

		return pageEtu;
	}
	
}
