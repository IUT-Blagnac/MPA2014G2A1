package web;



public class Accueil {

	public static String creationAccueil(){


		//Ecriture jusqu'à <body>
		String grandeString = "";

		String partie1 = "<!DOCTYPE html>\n"+
				"<html>\n"+
				"<head>\n"+
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"+
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"+
				"<meta name=\"generator\" content=\"OPTIweb VOPTIweb\" />\n"+
				"<title>OPTIweb - V0.1</title>\n"+
				"<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\" />\n"+
				"<link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css\" />\n"+
				"<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>\n"+
				"<script src=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js\"></script>\n"+
				"<style type=\'text/css\'>\n"+
				"@media all and (orientation:portrait) { .landscape {display: none;} }\n"+
				"@media all and (orientation:landscape) { .landscape {display: inline;} }\n"+
				"</style>\n"+
				"<style type=\"text/css\"></style></head><body class=\"ui-mobile-viewport ui-overlay-a\">\n";

		//Ecriture d'accueil
		String partie2="<!-- DEBUT page accueil -->\n"+
				"<div data-role=\"page\" id=\"accueil\" data-title=\"OPTIweb - V0.1\" data-url=\"accueil\" tabindex=\"0\" class=\"ui-page ui-page-theme-a ui-page-active\" style=\"min-height: 654px;\">\n"+
				"<div data-role=\"header\" data-add-back-btn=\"false\" role=\"banner\" class=\"ui-header ui-bar-inherit\" >\n"+
				"<h1 class=\"ui-title\" role=\"heading\" aria-level=\"1\">P<span class=\"landscape\">rojets </span>tut<span class=\"landscape\">orés</span> 2014-2015<br/>Département INFO<span class=\"landscape\">RMATIQUE</span><br/>IUT de Blagnac</h1>\n"+
				"<a href=\"#credits\" data-theme=\"b\" class=\"ui-btn-right\">Crédits</a>\n"+
				"</div>\n"+
				"<div data-role=\"content\">\n"+
				"<ul data-role=\"listview\" data-inset=\"true\" id=\"listeSources\">\n"+
				"  <li><a href=\"#projets\"><i class=\"fa fa-tasks\"></i> Projets</a></li>\n"+
				"  <li><a href=\"#sujets\"><i class=\"fa fa-copy\"></i> Sujets</a></li>\n"+
				"  <li><a href=\"#etudiants\"><i class=\"fa fa-group\"></i> Etudiants</a></li>\n"+
				"  <li><a href=\"#intervenants\"><i class=\"fa fa-group\"></i> Intervenants</a></li>\n"+
				"</ul>\n"+
				"</div>\n"+
				"<div data-role=\"footer\">\n"+
				"<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>\n"+
				"</div>\n"+
				"</div>\n"+
				"<!-- FIN page accueil -->\n";


		grandeString+=partie1;
		grandeString+=partie2;

		return grandeString;
	}
}
