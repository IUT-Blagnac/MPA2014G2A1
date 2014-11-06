package web;


public class Projet {

	/**
	 * @param args
	 */
	public static String creationProjet(){

		String part1 =  "<!-- DEBUT page projets -->" +
				"\n<div data-role=\"page\" id=\"projets\" data-title=\"OPTIweb - V0.1\">"+
				"\n<div data-role=\"header\" data-add-back-btn=\"true\">"+
				"\n<h1>Projets 2014-2015</h1>" +
				"\n</div> " +
				"\n<div data-role=\"content\">" +
				"\n<form class=\"ui-filterable\">" +
				"\n<input id=\"autocomplete-input-projet\" name=\"projet\" data-type=\"search\" placeholder=\"Vous cherchez ?...\"> " +
				"\n</form>" +
				"\n<ol id=\"listeprojets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-projet\">" ;


		
		String autoGenerProj = "";
		//On balaye tous les projets
		for(int i = 0; i < donnees.Projet.getListeProjet().size(); i++){
			
			
			String client ="";
			String superviseur ="";
			String etudiants = "";
			
			//On cherche les intervenants du projet
			for(int j=0 ; j <  donnees.Participation.getListeParticipation().size() ; j++){


				if(donnees.Participation.getListeParticipation().get(j).getProjet().equals(donnees.Projet.getListeProjet().get(i))){

					//On regarde les roles de chaque intervenant
					if(donnees.Participation.getListeParticipation().get(j).getRole() == 0){
						//On récupére le nom et le prénom du client
						client = donnees.Participation.getListeParticipation().get(j).getIntervenant().getPrenom() +" "+donnees.Participation.getListeParticipation().get(j).getIntervenant().getNom();
					}

					if(donnees.Participation.getListeParticipation().get(j).getRole() == 1){
						//On récupére le nom et le prénom du Superviseur
						superviseur = donnees.Participation.getListeParticipation().get(j).getIntervenant().getPrenom() +" "+ donnees.Participation.getListeParticipation().get(j).getIntervenant().getNom();
					}
				}
			}


			donnees.Projet leProjet = donnees.Projet.getListeProjet().get(i);

			//récupération des étudiants
			for(int j = 0; j < leProjet.getGroupe().getMembres().size(); j++){
				if(leProjet.getGroupe().getMembres().get(j)!=null){
					etudiants = etudiants + 
							leProjet.getGroupe().getMembres().get(j).getPrenom() +" "+
							leProjet.getGroupe().getMembres().get(j).getNom() +
							" - ";
				}
			}

			autoGenerProj = autoGenerProj +
					"\n<li>" +
					"\n<p>" +
					"\n<b>["+leProjet.getSujet().getTitre()+"]</b> "+leProjet.getSujet().getContexte() +
					"\n</p>" +
					"\n<p>" +
					"\n<b>Client : </b>"+client +
					"\n</p>" +
					"\n<p>" +
					"\n<b>Superviseur : </b>"+ superviseur +
					"\n</p>" +
					"\n<p>" +
					"\n<b>Groupe "+leProjet.getGroupe().getNom()+" : </b>" + etudiants +
					"\n</p>" +
					"\n</li>";

		}

		String part2 = "\n</ol>" +
				"\n</div>" +
				"\n<div data-role=\"footer\">" +
				"\n<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-tasks fa-2x\"></i></h4>" +
				"\n</div>" +
				"\n</div>" +
				"\n<!-- FIN page projets -->";

		return part1 + autoGenerProj + part2;

	}

}
