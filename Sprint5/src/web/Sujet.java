package web;


public class Sujet {

	public static String creationSujet(){
		String grandeString="";
		String partieUne =  "<!-- DEBUT page sujets -->\n"+
		"<div data-role=\"page\" id=\"sujets\" data-title=\"OPTIweb - V0.1\">"+
		"<div data-role=\"header\" data-add-back-btn=\"true\">"+
		"<h1>Sujets 2014-2015</h1>"+
		"</div>\n"+
		"<div data-role=\"content\">\n"+
		"<form class=\"ui-filterable\">\n"+
		"<input id=\"autocomplete-input-sujet\" name=\"sujet\" data-type=\"search\" placeholder=\"Vous cherchez ?\">"+
				"</form>\n"+
		"<ol id=\"listesujets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-sujet\" data-divider-theme=\"b\" data-count-theme=\"a\">\n"+
		"<li data-role=\"list-divider\">\n"+
		"Sujet<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span>\n"+
		"</li>\n";
		grandeString+=partieUne;
		
		for(donnees.Sujet pSujet : donnees.Sujet.getListeSujet()){
			String Remplissage="<li data-find=\"["+pSujet.getTitre()+"]\">\n";
			
			Remplissage+="<a href=\"#projets\">["+pSujet.getTitre()+"] <br><div style=\"white-space:normal;\">\n";
			String groupes="";
			for(donnees.Projet pProjet : pSujet.getProjets()){
				//Remplissage+="<a href=\"#projets\">["+pProjet.getNum()+"] <br><div style=\"white-space:normal;\">\n";
				groupes+=pProjet.getGroupe().getNom()+" ";
			}
			
			
			
			Remplissage+="<span><b>"+pSujet.getContexte()+"</b>\n"+
					"</span><span class=\"ui-li-count\">"+groupes+"</span>\n"+
					"</div>\n"+
					"</a>\n"+
					"</li>\n";
			grandeString+=Remplissage;
		}		
		String partieDeux="</ol>\n"+
		"</div>\n"+
		"<div data-role=\"footer\">\n"+
		"<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-copy fa-2x\"></i></h4>\n"+
		"</div>\n"+
		"</div>\n"+
		"<!-- FIN page sujets -->";
		
		
		grandeString+=partieDeux;
		return grandeString;
	}
}
