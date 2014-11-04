package Tests;
import donnees.*;
import junit.framework.TestCase;
import java.io.*;

public class IntervenantTest extends TestCase {

	Intervenant test1;
	Intervenant test2;


	protected void setUp() throws IOException {  
		Runtime.getRuntime().exec("java.exe -cp .;bin/donnees Intervenant");
		Intervenant.getListeIntervenant().clear();
		try {
			test1 = new Intervenant("00","Riva","Toast");
			test2 = new Intervenant("01","Olivier","Toast");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void test_Accesseurs(){
		assertEquals(test1.getNom(),"Riva");
		assertEquals(test2.getNom(),"Olivier");
		assertEquals(test1.getNumInt(),"00");
		assertEquals(test2.getNumInt(),"01");
		assertEquals(test1.getPrenom(),"Toast");		
		assertEquals(test2.getPrenom(),"Toast");

	}
	
	public void test_AccesseursListe(){
		assertEquals(Intervenant.getListeIntervenant().get(0).getNom(),"Riva");
		assertEquals(Intervenant.getListeIntervenant().get(1).getNom(),"Olivier");
		assertEquals(Intervenant.getListeIntervenant().get(0).getNumInt(),"00");
		assertEquals(Intervenant.getListeIntervenant().get(1).getNumInt(),"01");
		assertEquals(Intervenant.getListeIntervenant().get(0).getPrenom(),"Toast");		
		assertEquals(Intervenant.getListeIntervenant().get(1).getPrenom(),"Toast");

	}
	
	public void test_Setters(){
		Intervenant.getListeIntervenant().remove(1);
		assertEquals(1,Intervenant.getListeIntervenant().size());
		test1.setNom("carto");
		test1.setPrenom("viclefor");
		try {
			test1.setnumInt("01");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(test1.getNom(),"carto");
		assertEquals(test1.getNumInt(),"01");
		assertEquals(test1.getPrenom(),"viclefor");		

		Intervenant.getListeIntervenant().add(1,test2);
	}
}
