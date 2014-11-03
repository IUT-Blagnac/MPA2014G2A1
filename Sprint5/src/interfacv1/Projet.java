package interfacv1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Projet extends JDialog{
	
	private String [][] tabData ;
	private DefaultTableModel modele;	
	private JTable donneeTab ;
	
	public Projet(JFrame fenetreMere, String titre, String pNameFile) throws IOException {
		setTitle(titre);
		setLocationRelativeTo(fenetreMere);
		setContentPane(panneauDeContenu(pNameFile));
		setResizable(false);
		setVisible(true);
		pack();
		

	}

	JPanel panneauDeContenu(String fileName) throws IOException {


		//Créationn des différentes Layout

		JPanel pageEtu = new JPanel(new BorderLayout());
		JPanel grandSud = new JPanel(new FlowLayout());
		JPanel grandCentre = new JPanel();


		tabData = donnees.IOTool.readTab(fileName);

		
		String[] entete ={"Numéro", "Groupe", "ID Sujet"};
		
		modele = new DefaultTableModel(tabData , entete) ;
		
		donneeTab = new JTable( modele ) ;

	
		//Création des nouveaux boutons

		JButton valider = new JButton("Enregistrer");
		JButton ajouter = new JButton("Ajouter");
		JButton cloner = new JButton("Cloner");
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
		
		cloner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cloner();
			}
		});

		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				supprimer();
			}
		});

		//Ajout des différents éléments Ã  la fenetre

		grandSud.add(valider);
		grandSud.add(ajouter);
		grandSud.add(cloner);
		grandSud.add(supprimer);

		//donneeTab.setPreferredSize(new Dimension( 240 , 250 ))
		JScrollPane scrollTabGrp = new JScrollPane(donneeTab);
		grandCentre.add(scrollTabGrp);
	
		supprimer.setPreferredSize(valider.getPreferredSize());
		pageEtu.add(grandSud, BorderLayout.SOUTH);
		pageEtu.add(grandCentre, BorderLayout.CENTER);

		return pageEtu;
	}
	
	public void ajouter(){
		String[] tabNomArg = { "numéro projet", "acronyme","numéro groupe", "numéro sujet"};
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
	
public void cloner(){
		
	int confirmation = 0;
	
	if(donneeTab.getSelectedRows().length == 0){
		JOptionPane.showMessageDialog(null, "Vous n'avez rien sélectionné", "Attention", 
				JOptionPane.ERROR_MESSAGE); 
		confirmation = 1;
	}
	else{
		if(donneeTab.getSelectedRows().length > 1){
			JOptionPane.showMessageDialog(null, "Vous avez trop sélectionné de ligne", "Attention", 
					JOptionPane.ERROR_MESSAGE); 
			confirmation = 1;
		}
		else{
			confirmation = JOptionPane.showConfirmDialog(this, "Voulez vous vraiment cloner ?", "Clonage",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				if(confirmation == 0){
					Object[] Lismessg = new Object[3];
					
					Lismessg[0] = Integer.toString(modele.getRowCount());
					Lismessg[1] = modele.getValueAt(donneeTab.getSelectedRow(),1);
					Lismessg[2] = modele.getValueAt(donneeTab.getSelectedRow(),2);
					
					modele.addRow(Lismessg);
					donneeTab.setModel(modele);
					donneeTab.repaint();
				}
		}
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
			System.out.println(donneeTab.getSelectedRows()[0]);
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

public void enregistrer() throws FileNotFoundException, UnsupportedEncodingException{
	String fileName = JOptionPane.showInputDialog("Choisissez le nom du fichier");
	
	DefaultTableModel ModeleTemp = (DefaultTableModel)donneeTab.getModel();
	String[][] donneeTemp = new String[ModeleTemp.getRowCount()][ModeleTemp.getColumnCount()];
	
	for( int i = 0 ; i < ModeleTemp.getRowCount() ; i++)
		for( int j = 0 ; j < ModeleTemp.getColumnCount() ; j++)
			donneeTemp[i][j] = ModeleTemp.getValueAt(i, j).toString();
	
	
	donnees.IOTool.writeTab(fileName,donneeTemp);
	donnees.IOTool.exportDonneesProjet(fileName);
	dispose();
} 

}
