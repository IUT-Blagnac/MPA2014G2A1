package web;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class MakeOPTIweb {
	
	public static void main(String [] args){

		try {
			donnees.IOTool.importDonnees("s2014_2015");
			creationPage();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void creationPage() throws FileNotFoundException, UnsupportedEncodingException{
		
		//Initialisation 
		PrintWriter writer;
		writer = new PrintWriter("OPTIweb/test/OPTIweb.html", "UTF-8");
		
		//Ecriture d'accueil
		writer.println(Accueil.creationAccueil());
		
		//Ecriture Projet
		writer.println(Projet.creationProjet());
		
		//Ecriture Sujet
		writer.println(Sujet.creationSujet());
		
		//Ecriture intervenant
		writer.println(Intervenant.creationIntervenant());
		
		//Ecriture etudiant
		writer.println(Etudiant.creationEtudiant());
		
		//Ecriture script et bas de page
		writer.println("<script> ");
		writer.println(" // li click handler which fills the projects search bar ");
		writer.println("// with the value of the current data-find attribute");
		writer.println("$( 'li[data-find]' ).on( 'click',function(event){");
		writer.println("$(\"#autocomplete-input-projet\").val($(this).attr('data-find')).trigger('change');");
		writer.println("});");
		writer.println("</script>");
		writer.println("<div class=\"ui-loader ui-corner-all ui-body-a ui-loader-default\"><span class=\"ui-icon-loading\"></span><h1>loading</h1></div></body></html>");
		
		
		writer.close();
	}
}
