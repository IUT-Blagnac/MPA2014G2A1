package Tests;
import donnees.*;
import junit.framework.TestCase;

import java.io.*;

public class EtudiantTest extends TestCase {

	Etudiant test1;
	Etudiant test2;
	Groupe groupe;

	protected void setUp() throws IOException {  
		Runtime.getRuntime().exec("java.exe -cp .;bin/donnees Etudiant");
		Etudiant.getListeIndividu().clear();
		Groupe.getListeGroupe().clear();
		try {
			test1 = new Etudiant("00","Riva","Toast");
			test2 = new Etudiant("01","Olivier","Toast");
			groupe=new Groupe("00",test1,test2,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void test_Accesseurs(){
		assertEquals(test1.getNom(),"Riva");
		assertEquals(test2.getNom(),"Olivier");
		assertEquals(test1.getNumEtu(),"00");
		assertEquals(test2.getNumEtu(),"01");
		assertEquals(test1.getPrenom(),"Toast");		
		assertEquals(test2.getPrenom(),"Toast");
		assertEquals(test1.getGroupe(),groupe);
	}
	
	public void test_AccesseursListe(){
		assertEquals(Etudiant.getListeIndividu().get(0).getNom(),"Riva");
		assertEquals(Etudiant.getListeIndividu().get(1).getNom(),"Olivier");
		assertEquals(Etudiant.getListeIndividu().get(0).getNumEtu(),"00");
		assertEquals(Etudiant.getListeIndividu().get(1).getNumEtu(),"01");
		assertEquals(Etudiant.getListeIndividu().get(0).getPrenom(),"Toast");		
		assertEquals(Etudiant.getListeIndividu().get(1).getPrenom(),"Toast");
		assertEquals(Etudiant.getListeIndividu().get(0).getGroupe(),groupe);
	}
	 
	public void test_Setters(){
		Etudiant.getListeIndividu().remove(1);
		assertEquals(1,Etudiant.getListeIndividu().size());

		test1.setNom("carto");
		test1.setPrenom("viclefor");
		try {
			test1.setNumEtu("01");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(test1.getNom(),"carto");
		assertEquals(test1.getNumEtu(),"01");
		assertEquals(test1.getPrenom(),"viclefor");	
		Etudiant.getListeIndividu().add(1,test2);
	}


}
