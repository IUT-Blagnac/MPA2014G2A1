package Tests;
import donnees.*;
import junit.framework.TestCase;
import java.io.*;

public class ProjetTest extends TestCase {

	Groupe groupe;
	Sujet sujet;
	
	Projet test1;
	Projet test2;


	protected void setUp() throws IOException {  
		Runtime.getRuntime().exec("java.exe -cp .;bin/donnees Projet");
		Projet.getListeProjet().clear();
		Groupe.getListeGroupe().clear();
		Sujet.getListeSujet().clear();
		try {
			groupe = new Groupe("00",null,null,null);
			sujet = new Sujet("","","","","");
			test1 = new Projet("00",sujet,groupe);
			test2 = new Projet("01",sujet,groupe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void test_Accesseurs(){
		assertEquals("00",test1.getNum());
		assertEquals("01",test2.getNum());
		assertEquals(groupe,test1.getGroupe());
		assertEquals(groupe,test2.getGroupe());
		assertEquals(sujet,test1.getSujet());
		assertEquals(sujet,test2.getSujet());
		//assertEquals(null,test1.getSuperviseur());
		//assertEquals(superviseur,test2.getSuperviseur());
		//assertEquals(support,test2.getSupport());
	}
	
	public void test_AccesseursListe(){
		assertEquals("00",Projet.getListeProjet().get(0).getNum());
		assertEquals("01",Projet.getListeProjet().get(1).getNum());
		assertEquals(groupe,Projet.getListeProjet().get(0).getGroupe());
		assertEquals(groupe,Projet.getListeProjet().get(1).getGroupe());
		assertEquals(sujet,Projet.getListeProjet().get(0).getSujet());
		assertEquals(sujet,Projet.getListeProjet().get(1).getSujet());
		//assertEquals(null,Projet.getListeProjet().get(0).getSuperviseur());
		//assertEquals(superviseur,Projet.getListeProjet().get(1).getSuperviseur());
	}
	
	public void test_Setters(){
		Projet.getListeProjet().remove(1);
		assertEquals(1,Projet.getListeProjet().size());
		test1.setGroupe(groupe);
		try {
			test1.setNum("02");
		} catch (Exception e) {
			e.printStackTrace();
		}
		test1.setSujet(sujet);
		//test1.setSuperviseur(superviseur);

		assertEquals("02",test1.getNum());
		//assertEquals(superviseur,test1.getSuperviseur());	
		assertEquals(groupe,test1.getGroupe());
		assertEquals(sujet,test1.getSujet());
		Projet.getListeProjet().add(1,test2);
	}


}
