package Tests;
import donnees.*;
import junit.framework.TestCase;
import java.io.*;

public class SujetTest extends TestCase {

	Sujet test1;
	Sujet test2;


	protected void setUp() throws IOException {  
		Runtime.getRuntime().exec("java.exe -cp .;bin/donnees Sujet");
		Sujet.getListeSujet().clear();
		try {
			test1 = new Sujet("ToastA1","ToastA2","ToastA3","ToastA4","ToastA5");
			test2 = new Sujet("ToastB1","ToastB2","ToastB3","ToastB4","ToastB5");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void test_Accesseurs(){
		assertEquals("ToastA1",test1.getID());
		assertEquals("ToastB1",test2.getID());
		assertEquals("ToastA2",test1.getTitre());
		assertEquals("ToastB2",test2.getTitre());
		assertEquals("ToastA3",test1.getContexte());
		assertEquals("ToastB3",test2.getContexte());
		assertEquals("ToastA4",test1.getDescription());
		assertEquals("ToastB4",test2.getDescription());
		assertEquals("ToastA5",test1.getOutils());
		assertEquals("ToastB5",test2.getOutils());
		//assertEquals(client,test1.getClient());
		//assertEquals(client2,test2.getClient());
	}
	
	public void test_AccesseursListe(){
		assertEquals("ToastA1",Sujet.getListeSujet().get(0).getID());
		assertEquals("ToastB1",Sujet.getListeSujet().get(1).getID());
		assertEquals("ToastA2",Sujet.getListeSujet().get(0).getTitre());
		assertEquals("ToastB2",Sujet.getListeSujet().get(1).getTitre());
		assertEquals("ToastA3",Sujet.getListeSujet().get(0).getContexte());
		assertEquals("ToastB3",Sujet.getListeSujet().get(1).getContexte());
		assertEquals("ToastA4",Sujet.getListeSujet().get(0).getDescription());
		assertEquals("ToastB4",Sujet.getListeSujet().get(1).getDescription());
		assertEquals("ToastA5",Sujet.getListeSujet().get(0).getOutils());
		assertEquals("ToastB5",Sujet.getListeSujet().get(1).getOutils());
		//assertEquals(client,Sujet.getListeSujet().get(0).getClient());
		//assertEquals(client2,Sujet.getListeSujet().get(1).getClient());
	}
	
	public void test_Setters(){
		Sujet.getListeSujet().remove(1);
		assertEquals(1,Sujet.getListeSujet().size());
		
		try {
			test1.setID("ToastC1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		test1.setTitre("ToastC2");
		test1.setContexte("ToastC3");
		test1.setDescription("ToastC4");
		test1.setOutils("ToastC5");
		//test1.setClient(client2);

		
		assertEquals("ToastC1",test1.getID());
		assertEquals("ToastC2",test1.getTitre());
		assertEquals("ToastC3",test1.getContexte());
		assertEquals("ToastC4",test1.getDescription());
		assertEquals("ToastC5",test1.getOutils());
		//assertEquals(client2,test1.getClient());
		Sujet.getListeSujet().add(1,test2);
	}


}
