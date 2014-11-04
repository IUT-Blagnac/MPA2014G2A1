/**
 * 
 */
package Tests;

import java.io.IOException;

import donnees.Etudiant;
import donnees.Groupe;
import donnees.Sujet;
import junit.framework.TestCase;

/**
 * @author Etudiant
 *
 */
public class GroupeTest extends TestCase {

	Etudiant test1;
	Etudiant test2;
	Etudiant test3;
	Etudiant test4;
	Etudiant test5;
	Sujet sujet;
	Sujet sujet2;
	Sujet sujet3;
	Groupe groupe1;
	Groupe groupe2;



	protected void setUp() throws IOException {  
		Runtime.getRuntime().exec("java.exe -cp .;bin/donnees Groupe");
		Groupe.getListeGroupe().clear();
		Sujet.getListeSujet().clear();
		try {
			sujet = new Sujet("A1","A2","A3","A4","A5");
			sujet2 = new Sujet("B1","B2","B3","B4","B5");
			sujet3 = new Sujet("C1","C2","C3","C4","C5");
			groupe1 = new Groupe("A",test1,test2,test3);
			groupe2 = new Groupe("B",test1,test2,test3,test4,test5);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

	public void test_Accesseurs(){
		assertEquals("A",groupe1.getNom());
		assertEquals(test1,groupe1.getMembres().get(0));
		assertEquals(test2,groupe1.getMembres().get(1));
		assertEquals(test2,groupe1.getMembres().get(2));
		assertEquals(null,groupe1.getMembres().get(3));
		assertEquals(null,groupe1.getMembres().get(4));
		assertEquals("A1",groupe1.getVoeux().get(0).getID());
		assertEquals("B1",groupe1.getVoeux().get(1).getID());
		assertEquals("C1",groupe1.getVoeux().get(2).getID());
		assertEquals("B1",groupe1.getVoeuxPos(1).get(0).getID());
		
		assertEquals("B",groupe2.getNom());
		assertEquals(test1,groupe2.getMembres().get(0));
		assertEquals(test2,groupe2.getMembres().get(1));
		assertEquals(test3,groupe2.getMembres().get(2));
		assertEquals(test4,groupe2.getMembres().get(3));
		assertEquals(test5,groupe2.getMembres().get(4));
		assertEquals("A1",groupe2.getVoeux().get(0).getID());
		assertEquals("B1",groupe2.getVoeux().get(1).getID());
		assertEquals("C1",groupe2.getVoeux().get(2).getID());
		
		assertEquals(groupe1,Groupe.getGrpSansSujet().get(0));
		assertEquals(groupe2,Groupe.getGrpSansSujet().get(1));
		
		assertEquals(groupe1,Groupe.getGrpPosSuj(sujet,0).get(0));
		assert(Groupe.getGrpPosSuj(sujet2,0).isEmpty());
		assertEquals(groupe2,Groupe.getGrpPosSuj(sujet,0).get(1));

	}
	
	public void test_AccesseursListe(){
		assertEquals("A",Groupe.getListeGroupe().get(0).getNom());
		assertEquals(test1,Groupe.getListeGroupe().get(0).getMembres().get(0));
		assertEquals(test2,Groupe.getListeGroupe().get(0).getMembres().get(1));
		assertEquals(test3,Groupe.getListeGroupe().get(0).getMembres().get(2));
		assertEquals(null,Groupe.getListeGroupe().get(0).getMembres().get(3));
		assertEquals(null,Groupe.getListeGroupe().get(0).getMembres().get(4));
		assertEquals("A1",Groupe.getListeGroupe().get(0).getVoeux().get(0).getID());
		
		assertEquals("B",Groupe.getListeGroupe().get(1).getNom());
		assertEquals(test1,Groupe.getListeGroupe().get(1).getMembres().get(0));
		assertEquals(test2,Groupe.getListeGroupe().get(1).getMembres().get(1));
		assertEquals(test3,Groupe.getListeGroupe().get(1).getMembres().get(2));
		assertEquals(test4,Groupe.getListeGroupe().get(1).getMembres().get(3));
		assertEquals(test5,Groupe.getListeGroupe().get(1).getMembres().get(4));
		assertEquals("C1",Groupe.getListeGroupe().get(1).getVoeux().get(2).getID());
	}
	
	public void test_Setters(){
		Groupe.getListeGroupe().remove(0);
		assertEquals(1,Groupe.getListeGroupe().size());
		
		groupe2.setNom("trololo");		
		assertEquals(groupe2.getNom(),"trololo");
		
		Groupe.getListeGroupe().add(0,groupe1);
		
		groupe1.setPosVoeux(sujet3, 0);
		assertEquals("C1",groupe1.getVoeux().get(0).getID());
		groupe1.echPosVoeux(sujet2, sujet3);
		assertEquals("B1",groupe1.getVoeux().get(0).getID());
	}


}
