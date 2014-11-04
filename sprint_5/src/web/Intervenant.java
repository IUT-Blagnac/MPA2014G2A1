package web;

public class Intervenant {
	public static String creationIntervenant(){
	
		String part1 = "<!-- DEBUT page intervenants -->" +
			"<div data-role=\"page\" id=\"intervenants\" data-title=\"OPTIweb - V0.1\">"+
		"\n<div data-role=\"header\" data-add-back-btn=\"true\">    <!-- 1 -->"+
		"\n<h1>Intervenants 2014-2015</h1>"+
		"\n</div>"+
		"\n<div data-role=\"content\">"+
		"\n<form class=\"ui-filterable\">"+
		"\n<input id=\"autocomplete-input-intervenant\" name=\"intervenant\" data-type=\"search\" placeholder=\"Intervenant\">    <!-- 2 -->"+
		"\n</form>"+
		"\n<ul id=\"listeintervenants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-intervenant\" data-divider-theme=\"b\">"+
		"\n<li data-role=\"list-divider\">    <!-- 3 -->"+
		"\nIntervenant<span class=\"ui-li-count\" style=\"right: 110px !important;\" title=\"Client\">Client</span><span class=\"ui-li-count\" title=\"Superviseur\">Superviseur</span>"+
		"\n</li>";
		
		
		String autoGenerInter = "";
		String nommage = "";
		
	
		//On balaye tous les intervenants
		for(int i = 0; i < donnees.Intervenant.getListeIntervenant().size(); i++){
			int nbrClient = 0;
			int nbrSuper = 0 ;
			
			//On les compare aux intervenants des particiaptions
			for(int j=0 ; j <  donnees.Participation.getListeParticipation().size() ; j++){
				
				//On regarde leur role pour chaque
				if(donnees.Participation.getListeParticipation().get(j).getIntervenant().equals(donnees.Intervenant.getListeIntervenant().get(i))){
					
					
			    	if(donnees.Participation.getListeParticipation().get(j).getRole() == 0){
			    		nbrClient ++;
			    	}
			    	if(donnees.Participation.getListeParticipation().get(j).getRole() == 1){
			    		nbrSuper ++;
			    	}
				}
	    	
			}
			
			nommage = donnees.Intervenant.getListeIntervenant().get(i).getPrenom() + " " + donnees.Intervenant.getListeIntervenant().get(i).getNom();
			autoGenerInter = autoGenerInter + "\n<li data-find=\""+ nommage  +"\">" + 
			"\n<a href=\"#projets\">" +
			"\n" +nommage+
			"\n<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\">"+nbrClient+"</span>" +
			"\n<span class=\"ui-li-count\" title=\"Superviseur\">"+nbrSuper+"</span>" +
			"\n</a>" +
			"\n</li>";
		}


		String part2 = "\n</ul>"+
		"\n</div>"+
		"\n<div data-role=\"footer\">"+
		"\n<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>"+
		"\n</div>"+
		"\n</div>"+
		"\n<!-- FIN page intervenants -->";

		return part1 + autoGenerInter + part2;
	}
}