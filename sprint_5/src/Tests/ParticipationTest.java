package Tests;
import donnees.*;
import junit.framework.TestCase;

import java.io.*;

public class ParticipationTest extends TestCase{

	Sujet toastage1;
	Sujet toastage2;
	
	Groupe toasteurs1;
	Groupe toasteurs2;
	
	Intervenant toasteur1;
	Intervenant toasteur2;
	Intervenant toasteur3;
	Intervenant toasteur4;
	
	Projet toasting1;
	Projet toasting2;
	
	Participation test1;
	Participation test2;
	Participation test3;
	Participation test4;
	Participation test5;


	protected void setUp() throws IOException {  
		Runtime.getRuntime().exec("java.exe -cp .;bin/donnees Participation");
		Participation.getListeParticipation().clear();
		Intervenant.getListeIntervenant().clear();
		Projet.getListeProjet().clear();
		Groupe.getListeGroupe().clear();
		Sujet.getListeSujet().clear();
		try {
			toasting1 = new Projet("00",toastage1,toasteurs1);
			toasting2 = new Projet("01",toastage2,toasteurs2);
			
			toasteur1 = new Intervenant("00","test","olivier");
			toasteur2 = new Intervenant("01","test","olivier");
			toasteur3 = new Intervenant("02","test","olivier");
			toasteur4 = new Intervenant("03","test","olivier");
			
			test1 = new Participation(toasting1,toasteur1,0);
			test2 = new Participation(toasting1, toasteur2,2);
			test3 = new Participation(toasting1, toasteur3,1);
			test4 = new Participation(toasting2, toasteur3,0);
			test5 = new Participation(toasting2, toasteur4,2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void test_Accesseurs(){
		assertEquals(toasting1,test1.getProjet());
		assertEquals(toasting1,test2.getProjet());
		assertEquals(toasting1,test3.getProjet());
		assertEquals(toasting2,test4.getProjet());
		assertEquals(toasting2,test5.getProjet());
		
		assertEquals(toasteur1,test1.getIntervenant());
		assertEquals(toasteur2,test2.getIntervenant());
		assertEquals(toasteur3,test3.getIntervenant());
		assertEquals(toasteur3,test4.getIntervenant());
		assertEquals(toasteur4,test5.getIntervenant());
		
		assertEquals("client",test1.getRoleS());
		assertEquals("support technique",test2.getRoleS());
		assertEquals("superviseur",test3.getRoleS());
		assertEquals("client",test4.getRoleS());
		assertEquals("support technique",test5.getRoleS());
	}
	


	public void test_AccesseursListe(){
		assertEquals(toasting1,Participation.getListeParticipation().get(0).getProjet());
		assertEquals(toasting1,Participation.getListeParticipation().get(1).getProjet());
		assertEquals(toasting1,Participation.getListeParticipation().get(2).getProjet());
		assertEquals(toasting2,Participation.getListeParticipation().get(3).getProjet());
		assertEquals(toasting2,Participation.getListeParticipation().get(4).getProjet());
		
		assertEquals(toasteur1,Participation.getListeParticipation().get(0).getIntervenant());
		assertEquals(toasteur2,Participation.getListeParticipation().get(1).getIntervenant());
		assertEquals(toasteur3,Participation.getListeParticipation().get(2).getIntervenant());
		assertEquals(toasteur3,Participation.getListeParticipation().get(3).getIntervenant());
		assertEquals(toasteur4,Participation.getListeParticipation().get(4).getIntervenant());
		
		assertEquals("client",Participation.getListeParticipation().get(0).getRoleS());
		assertEquals("support technique",Participation.getListeParticipation().get(1).getRoleS());
		assertEquals("superviseur",Participation.getListeParticipation().get(2).getRoleS());
		assertEquals("client",Participation.getListeParticipation().get(3).getRoleS());
		assertEquals("support technique",Participation.getListeParticipation().get(4).getRoleS());
	}
	
	public void test_Setters(){
		test1.setProjet(toasting2);
		assertEquals(toasting2,test1.getProjet());
		test1.setProjet(toasting1);
		
		test1.setIntervenant(toasteur2);
		assertEquals(toasteur2,test1.getIntervenant());
		test1.setIntervenant(toasteur1);
		
		test5.setRole(1);
		assertEquals("superviseur",test5.getRoleS());
		test5.setRole(2);
	}


}
