package Tests;
import donnees.*;
import junit.framework.TestCase;

import java.io.*;
import java.util.ArrayList;

public class IOToolTest extends TestCase {
	
	//Déclaration des variables
	ArrayList<String> Toast = new ArrayList<String>();
	String path;
	
	protected void setUp() throws Exception {  
		Runtime.getRuntime().exec("java.exe -cp .;bin/donnees IOTool");
		Etudiant.getListeIndividu().clear();
		Intervenant.getListeIntervenant().clear();
		Groupe.getListeGroupe().clear();
		Projet.getListeProjet().clear();
		Sujet.getListeSujet().clear();
		
		//initialisation des variables
		path=null;
		Toast.add("Salut;je;suis;un"); 
		Toast.add("fichier;de;test;null"); 
		IOTool.importDonnees("_toast");
	}
	
	public void test_read(ArrayList<String> pDonnees) throws IOException{
		if(path==null){
			path="toast";
		}
		ArrayList<String> donnees = IOTool.read(path);
		for(int i=0; i<pDonnees.size();i++){
			assertEquals(pDonnees.get(i),donnees.get(i));
		}
	}
	
	public void test_readwriteTab() throws IOException{
		String[][] donnees = IOTool.readTab("toast");
		assertEquals("Salut",donnees[0][0]);
		assertEquals("je",donnees[0][1]);
		assertEquals("suis",donnees[0][2]);
		assertEquals("un",donnees[0][3]);
		assertEquals("fichier",donnees[1][0]);
		assertEquals("de",donnees[1][1]);
		assertEquals("test",donnees[1][2]);
		assertEquals("null",donnees[1][3]);
		
		path="toastWrite";
		IOTool.writeTab(path,donnees);
		this.test_read(Toast);
		path=null;
	}
	
	public void test_write() throws IOException{
		this.test_read(Toast);
		ArrayList<String> donnees = IOTool.read("toast");
		path="toastWrite";
		IOTool.write(path, donnees);
		this.test_read(donnees);
		path=null;
	}
	
	public void test_exportDonneesEtudiant() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_importDonneesEtudiant();
		
		path="etudiant_export_toast";
		IOTool.exportDonneesEtudiant(path);
		ArrayList<String> donnees = IOTool.read(path);
		path="etudiant_toast";
		this.test_read(donnees);
		path=null;

	}
	
	public void test_exportDonneesIntervenant() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_importDonneesIntervenant();
	
		
		path="intervenant_export_toast";
		IOTool.exportDonneesIntervenant(path);
		ArrayList<String> donnees = IOTool.read(path);
		path="intervenant_toast";
		this.test_read(donnees);
		path=null;

	}
	
	public void test_exportDonneesGroupe() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_importDonneesGroupe();
		
		path="groupe_export_toast";
		IOTool.exportDonneesGroupe(path);
		ArrayList<String> donnees = IOTool.read(path);
		path="groupe_toast";
		this.test_read(donnees);
		path=null;

	}
	
	public void test_exportDonneesSujet() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_importDonneesSujet();
		
		path="sujet_export_toast";
		IOTool.exportDonneesSujet(path);
		ArrayList<String> donnees = IOTool.read(path);
		path="sujet_toast";
		this.test_read(donnees);
		path=null;

	}
	
	public void test_exportDonneesProjet() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_importDonneesProjet();
		
		path="projet_export_toast";
		IOTool.exportDonneesProjet(path);
		ArrayList<String> donnees = IOTool.read(path);
		path="projet_toast";
		this.test_read(donnees);
		path=null;

	}
	
	public void test_exportDonneesParticipation() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_importDonneesProjet();
		
		path="participation_export_toast";
		IOTool.exportDonneesParticipation(path);
		ArrayList<String> donnees = IOTool.read(path);
		path="participation_toast";
		this.test_read(donnees);
		path=null;

	}
	
	public void test_exportDonnees() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_exportDonneesEtudiant();
		this.test_exportDonneesGroupe();
		this.test_exportDonneesIntervenant();
		this.test_exportDonneesProjet();
		this.test_exportDonneesSujet();
		this.test_exportDonneesParticipation();
		
		path="_export_toast";
		IOTool.exportDonnees(path);
		
		path="etudiant_toast";
		ArrayList<String> donnees = IOTool.read(path);
		path="etudiant_export_toast";
		this.test_read(donnees);
		
		path="intervenant_toast";
		donnees = IOTool.read(path);
		path="intervenant_export_toast";
		this.test_read(donnees);
		
		path="groupe_toast";
		donnees = IOTool.read(path);
		path="groupe_export_toast";
		this.test_read(donnees);
		
		path="sujet_toast";
		donnees = IOTool.read(path);
		path="sujet_export_toast";
		this.test_read(donnees);
		
		path="projet_toast";
		donnees = IOTool.read(path);
		path="projet_export_toast";
		this.test_read(donnees);
		
		path="participation_toast";
		donnees = IOTool.read(path);
		path="participation_export_toast";
		this.test_read(donnees);
		
		path=null;
	}
	
	public void test_importDonneesEtudiant() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_readwriteTab();
		
		path="etudiant_toast";
		IOTool.importDonneesEtudiant(path);
		
		String[][] donnees = IOTool.readTab(path);
		for(int i=0 ; i<Etudiant.getListeIndividu().size() ; i++){
			assertEquals(donnees[i][1],Etudiant.getListeIndividu().get(i).getNom());
			assertEquals(donnees[i][2],Etudiant.getListeIndividu().get(i).getPrenom());
			assertEquals(donnees[i][0],Etudiant.getListeIndividu().get(i).getNumEtu());
		}
		path=null;
	}
	
	public void test_importDonneesIntervenant() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_readwriteTab();
		
		path="intervenant_toast";
		IOTool.importDonneesIntervenant(path);
		
		String[][] donnees = IOTool.readTab(path);
		for(int i=0 ; i<Intervenant.getListeIntervenant().size() ; i++){
			assertEquals(donnees[i][1],Intervenant.getListeIntervenant().get(i).getNom());
			assertEquals(donnees[i][2],Intervenant.getListeIntervenant().get(i).getPrenom());
			assertEquals(donnees[i][0],Intervenant.getListeIntervenant().get(i).getNumInt());
		}
		path=null;
	}
	
	public void test_importDonneesGroupe() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_readwriteTab();
		
		path="groupe_toast";
		IOTool.importDonneesGroupe(path);
		
		String[][] donnees = IOTool.readTab(path);
		for(int i=0 ; i<Groupe.getListeGroupe().size() ; i++){
			assertEquals(donnees[i][0],Groupe.getListeGroupe().get(i).getNom());
			assertEquals(donnees[i][1],Groupe.getListeGroupe().get(i).getMembres().get(0).getNumEtu());
			assertEquals(donnees[i][2],Groupe.getListeGroupe().get(i).getMembres().get(1).getNumEtu());
			assertEquals(donnees[i][3],Groupe.getListeGroupe().get(i).getMembres().get(2).getNumEtu());
			if(donnees[i][4].equals("null")){
				assertEquals(null,Groupe.getListeGroupe().get(i).getMembres().get(3));
			}else{
				assertEquals(donnees[i][4],Groupe.getListeGroupe().get(i).getMembres().get(3).getNumEtu());
			}
			if(donnees[i][5].equals("null")){
				assertEquals(null,Groupe.getListeGroupe().get(i).getMembres().get(4));
			}else{
				assertEquals(donnees[i][5],Groupe.getListeGroupe().get(i).getMembres().get(4).getNumEtu());
			}
			for(int j=0; j<Groupe.getListeGroupe().get(i).getVoeux().size();j++){
				assertEquals(donnees[i][j+6],Groupe.getListeGroupe().get(i).getVoeux().get(j).getID());
			}
		}
		path=null;
	}
	
	public void test_importDonneesProjet() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_readwriteTab();
		
		path="projet_toast";
		IOTool.importDonneesProjet(path);
		
		String[][] donnees = IOTool.readTab(path);
		for(int i=0 ; i<Projet.getListeProjet().size() ; i++){
			assertEquals(donnees[i][0],Projet.getListeProjet().get(i).getNum());
			assertEquals(donnees[i][1],Projet.getListeProjet().get(i).getSujet().getID());
			assertEquals(donnees[i][2],Projet.getListeProjet().get(i).getGroupe().getNom());
		}
		path=null;
	}
	
	public void test_importDonneesSujet() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_readwriteTab();
		
		path="sujet_toast";
		IOTool.importDonneesSujet(path);
		
		String[][] donnees = IOTool.readTab(path);
		for(int i=0 ; i<Sujet.getListeSujet().size() ; i++){
			assertEquals(donnees[i][0],Sujet.getListeSujet().get(i).getID());
			assertEquals(donnees[i][1],Sujet.getListeSujet().get(i).getTitre());
			assertEquals(donnees[i][2],Sujet.getListeSujet().get(i).getContexte());
			assertEquals(donnees[i][3],Sujet.getListeSujet().get(i).getDescription());
			assertEquals(donnees[i][4],Sujet.getListeSujet().get(i).getOutils());
		}
		path=null;
	}
	
	public void test_importDonneesParticipation() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_readwriteTab();
		
		path="participation_toast";
		IOTool.importDonneesParticipation(path);
		
		String[][] donnees = IOTool.readTab(path);
		for(int i=0 ; i<Participation.getListeParticipation().size() ; i++){
			assertEquals(donnees[i][0],Participation.getListeParticipation().get(i).getProjet().getNum());
			assertEquals(donnees[i][1],Participation.getListeParticipation().get(i).getIntervenant().getNumInt());
			assertEquals(Integer.parseInt(donnees[i][2]),Participation.getListeParticipation().get(i).getRole());
		}
		path=null;
	}
	
	public void test_importDonnees() throws Exception{
		this.test_read(Toast);
		this.test_write();
		this.test_readwriteTab();
		this.test_importDonneesEtudiant();
		this.test_importDonneesGroupe();
		this.test_importDonneesIntervenant();
		this.test_importDonneesProjet();
		this.test_importDonneesSujet();
		this.test_importDonneesParticipation();
		
		
	}
}
