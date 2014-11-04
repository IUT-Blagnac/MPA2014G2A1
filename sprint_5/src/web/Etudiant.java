package web;

public class Etudiant {

	public static String creationEtudiant(){
		String grandeString="";
		String partieUne ="<!-- DEBUT page etudiants -->\n"+
				"<div data-role=\"page\" id=\"etudiants\" data-title=\"OPTIweb - V0.1\">\n"+
				"<div data-role=\"header\" data-add-back-btn=\"true\">\n"+
				"<h1>Etudiants 2014-2015</h1>\n"+
				"</div>\n"+
				"<div data-role=\"content\">\n"+
				"<form class=\"ui-filterable\">\n"+
				"<input id=\"autocomplete-input-etudiant\" name=\"etudiant\" data-type=\"search\" placeholder=\"Etudiant ou Groupe X\">\n"+    
				"</form>\n"+
				"<ol id=\"listeetudiants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-etudiant\" data-divider-theme=\"b\">\n"+
				"<li data-role=\"list-divider\">\n"+  
				"Etudiant<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span>\n"+
				"</li>\n";
		grandeString+=partieUne;

		

		
		for(donnees.Etudiant pEtudiant : donnees.Etudiant.getListeIndividu()){
			String Remplissage="<li data-find=\""+pEtudiant.getPrenom()+" "+pEtudiant.getNom()+"\">\n";

			Remplissage+="<a href=\"#projets\">"+pEtudiant.getNom()+" "+pEtudiant.getPrenom()+" <span class=\"ui-li-count\" title=\"Groupe\">\n";
			String groupes="";
			if(pEtudiant.getGroupe()!=null){
				groupes=pEtudiant.getGroupe().getNom();
			}
			
			
			Remplissage+="Groupe "+groupes+"</span>\n"+
					"</a>\n"+
					"</li>\n";
			grandeString+=Remplissage;
		}		
		String partieDeux="</ol>\n"+
				"</div>\n"+
				"<div data-role=\"footer\">\n"+
				"<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>\n"+
				"</div>\n"+
				"</div>\n"+
				"<!-- FIN page etudiants -->\n";


		grandeString+=partieDeux;
		return grandeString;
	}
}
