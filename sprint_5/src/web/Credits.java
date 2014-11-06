package web;

public class Credits {
	public static String creationCredits(){


		//Ecriture des crédits
		String grandeString = "";

		String partie3="<!-- DEBUT page credits -->\n"+
				"<div data-role=\"page\" id=\"credits\" data-title=\"OPTIweb - V0.1 - Crédits\" data-url=\"credits\">"+
				"<div data-role=\"header\" data-add-back-btn=\"true\">"+
				"<h1>Crédits</h1>"+
//				"<div data-role=\"page\" id=\"credits\" data-title=\"OPTIweb - V0.1 - Crédits\" data-url=\"credits\" tabindex=\"0\" class=\"ui-page ui-page-theme-a\" style=\"min-height: 626px;\">\n"+
//				"<div data-role=\"header\" data-add-back-btn=\"true\" role=\"banner\" class=\"ui-header ui-bar-inherit\"><a role=\"button\" href=\"javascript:void(0);\" class=\"ui-btn ui-corner-all ui-shadow ui-btn-left ui-toolbar-back-btn ui-icon-carat-l ui-btn-icon-left\" data-rel=\"back\">Back</a>\n"+
//				"<h1 class=\"ui-title\" role=\"heading\" aria-level=\"1\">Crédits</h1>\n"+
				"</div>\n"+
				"<div data-role=\"content\" class=\"ui-content\" role=\"main\">\n"+
				"    <p>Cette application a été réalisée dans le cadre du module M3301/MPA du DUT Informatique à l'IUT de Blagnac.</p>\n"+
				"<ul data-role=\"listview\" data-inset=\"true\" id=\"contacts\" data-theme=\"a\" data-divider-theme=\"b\" class=\"ui-listview ui-listview-inset ui-corner-all ui-shadow ui-group-theme-a\">\n"+
				"    <li data-role=\"list-divider\" role=\"heading\" class=\"ui-li-divider ui-bar-b ui-first-child\">Product Owner</li>\n"+
				"    <li class=\"ui-li-static ui-body-inherit\">André PÉNINOU</li>\n"+
				"    <li class=\"ui-li-static ui-body-inherit ui-last-child\">Université Toulouse 2 - IUT de Blagnac\n"+
				"    <br>Département INFORMATIQUE</li>\n"+
				"</ul>\n"+
				"<ul data-role=\"listview\" data-inset=\"true\" id=\"listecredits\" data-theme=\"a\" data-divider-theme=\"b\" class=\"ui-listview ui-listview-inset ui-corner-all ui-shadow ui-group-theme-a\">\n"+
				"    <li data-role=\"list-divider\" role=\"heading\" class=\"ui-li-divider ui-bar-b ui-first-child\">Membres de l'équipe enseignante</li>\n"+
				"<li class=\"ui-li-static ui-body-inherit\">Jean-Michel BRUEL</li><li class=\"ui-li-static ui-body-inherit\">Jean-Michel INGLEBERT</li><li class=\"ui-li-static ui-body-inherit\">André PÉNINOU</li><li class=\"ui-li-static ui-body-inherit ui-last-child\">Olivier ROQUES</li>\n"+
				"</ul>\n"+
				"<ul data-role=\"listview\" data-inset=\"true\" id=\"listepowered\" data-theme=\"a\" data-divider-theme=\"b\" class=\"ui-listview ui-listview-inset ui-corner-all ui-shadow ui-group-theme-a\">\n"+
				"    <li data-role=\"list-divider\" role=\"heading\" class=\"ui-li-divider ui-bar-b ui-first-child\">Propulsé par</li>\n"+
				"    <li><a href=\"http://jquerymobile.com/\" target=\"autrePage\" class=\"ui-btn ui-btn-icon-right ui-icon-carat-r\">http://jquerymobile.com/</a></li>\n"+
				"    <li class=\"ui-last-child\"><a href=\"http://fortawesome.github.io/Font-Awesome/\" target=\"autrePage\" class=\"ui-btn ui-btn-icon-right ui-icon-carat-r\">http://fortawesome.github.io/Font-Awesome/</a></li>\n"+
				"</ul>\n"+
				"</div>\n"+
				"<div data-role=\"footer\" role=\"contentinfo\" class=\"ui-footer ui-bar-inherit\"> \n"+
				" <h4 class=\"ui-title\" role=\"heading\" aria-level=\"1\">OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4> \n"+
				"</div>\n"+
				"</div>\n"+
				"<!-- FIN page credits -->";

		grandeString+=partie3;

		return grandeString;
	}
}
