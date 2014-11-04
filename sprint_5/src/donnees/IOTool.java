package donnees;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class IOTool {

	/**Fonction de lecture d'un fichier CSV
	 * @param path String, ne pas mettre .csv ou autre juste le nom du fichier
	 * @return donnees - ArrayList de String
	 * @throws IOException
	 */
	public static ArrayList<String> read(String path) throws IOException{

		ArrayList<String> donnees = new ArrayList<String> () ;

		BufferedReader in = new BufferedReader(new FileReader("data/"+path+".csv"));
		String line = in.readLine(); 
		while(line!=null){
			donnees.add(line);
			line = in.readLine(); 
		}
		in.close();
		return donnees; 
	}

	/**Comme read() mais retourne un tableau à deux dimensions au lieu d'un array
	 * @param path String, ne pas mettre .csv ou autre juste le nom du fichier
	 * @return tabDeString - String[][]
	 * @throws IOException
	 */
	public static String [][] readTab(String path) throws IOException{
		ArrayList<String> donnees = read(path);
		String [] ligne = donnees.get(0).split(";");
		String [][] tabDeString = new String [donnees.size()][ligne.length];
		for(int i=0; i<donnees.size() ; i++){
			ligne = donnees.get(i).split(";");
			for(int j = 0 ; j<ligne.length;j++)
				tabDeString[i][j]=ligne[j];
		}
		return tabDeString;

	}

	/**
	 * @param path
	 * @param pDonnees
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void write(String path, ArrayList<String> pDonnees) throws FileNotFoundException, UnsupportedEncodingException{

		ArrayList<String> donnees = pDonnees;
		PrintWriter writer;
		writer = new PrintWriter("data/"+path+".csv", "UTF-8");
		for(int i=0; i<donnees.size();i++){
			writer.println(donnees.get(i));
		}
		writer.close();	
	}


	/**
	 * @param path
	 * @param pDonnees
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void writeTab(String path, String [][] pDonnees) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer;
		writer = new PrintWriter("data/"+path+".csv", "UTF-8");
		for(String[] ligne : pDonnees){
			for(String mot : ligne){
				if(mot.equals(ligne[ligne.length-1])){
					writer.print(mot);
				}else{
					writer.print(mot+";");
				}
			}
			writer.print("\n");
		}
		writer.close();	
	}

	/**
	 * @param path
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void exportDonneesEtudiant(String path) throws FileNotFoundException, UnsupportedEncodingException{

		PrintWriter writer;
		writer = new PrintWriter("data/"+path+".csv");

		//Inscription des etudiants
		
		for(int i=0;i<Etudiant.getListeIndividu().size();i++){
			writer.print(Etudiant.getListeIndividu().get(i).getNumEtu()+";");
			writer.print(Etudiant.getListeIndividu().get(i).getNom()+";");
			writer.print(Etudiant.getListeIndividu().get(i).getPrenom()+"\n");
		}

		writer.close();

	}

	/**
	 * @param path
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void exportDonneesGroupe(String path) throws FileNotFoundException, UnsupportedEncodingException{

		PrintWriter writer;
		writer = new PrintWriter("data/"+path+".csv");

		//Inscription des groupes 
		
		for(int i=0; i<Groupe.getListeGroupe().size() ; i++){
			writer.print(Groupe.getListeGroupe().get(i).getNom()+";");
			writer.print(Groupe.getListeGroupe().get(i).getMembres().get(0).getNumEtu()+";");
			writer.print(Groupe.getListeGroupe().get(i).getMembres().get(1).getNumEtu()+";");
			writer.print(Groupe.getListeGroupe().get(i).getMembres().get(2).getNumEtu()+";");
			try{
				writer.print(Groupe.getListeGroupe().get(i).getMembres().get(3).getNumEtu()+";");
			}catch(NullPointerException e){
				writer.print("null;");
			}
			try{
				writer.print(Groupe.getListeGroupe().get(i).getMembres().get(4).getNumEtu()+";");
			}catch(NullPointerException e){
				writer.print("null;");
			}
			for(int j=0;j<Groupe.getListeGroupe().get(i).getVoeux().size();j++){
				if(j==Groupe.getListeGroupe().get(i).getVoeux().size()-1){
					writer.print(Groupe.getListeGroupe().get(i).getVoeux().get(j).getID()+"\n");
				}else{
					writer.print(Groupe.getListeGroupe().get(i).getVoeux().get(j).getID()+";");
				}
			}
		}
		writer.close();

	}

	/**
	 * @param path
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void exportDonneesProjet(String path) throws FileNotFoundException, UnsupportedEncodingException{

		PrintWriter writer;
		writer = new PrintWriter("data/"+path+".csv");

		//Inscription des projets
		
		for(int i=0;i<Projet.getListeProjet().size();i++){
			writer.print(Projet.getListeProjet().get(i).getNum()+";");
			writer.print(Projet.getListeProjet().get(i).getSujet().getID()+";");
			writer.print(Projet.getListeProjet().get(i).getGroupe().getNom()+"\n");
					
		}
		writer.close();
	}

	/**
	 * @param path
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void exportDonneesSujet(String path) throws FileNotFoundException, UnsupportedEncodingException{

		PrintWriter writer;
		writer = new PrintWriter("data/"+path+".csv");

		//Inscription des sujets
		
		for(int i=0;i<Sujet.getListeSujet().size();i++){
			writer.print(Sujet.getListeSujet().get(i).getID()+";");
			writer.print(Sujet.getListeSujet().get(i).getTitre()+";");
			writer.print(Sujet.getListeSujet().get(i).getContexte()+";");
			writer.print(Sujet.getListeSujet().get(i).getDescription()+";");
			writer.print(Sujet.getListeSujet().get(i).getOutils()+"\n");
		}

		writer.close();		
	}

	/**
	 * @param path
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void exportDonneesIntervenant(String path) throws FileNotFoundException, UnsupportedEncodingException{

		PrintWriter writer;
		writer = new PrintWriter("data/"+path+".csv");

		//Inscription des intervenants
		
		for(int i=0;i<Intervenant.getListeIntervenant().size();i++){
			writer.print(Intervenant.getListeIntervenant().get(i).getNumInt()+";");
			writer.print(Intervenant.getListeIntervenant().get(i).getNom()+";");
			writer.print(Intervenant.getListeIntervenant().get(i).getPrenom()+"\n");			
		}

		writer.close();		
	}
	
	/**
	 * @param path
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void exportDonneesParticipation(String path) throws FileNotFoundException, UnsupportedEncodingException{

		PrintWriter writer;
		writer = new PrintWriter("data/"+path+".csv");

		//Inscription des Participation
		
		for(int i=0;i<Participation.getListeParticipation().size();i++){
			writer.print(Participation.getListeParticipation().get(i).getProjet().getNum()+";");
			writer.print(Participation.getListeParticipation().get(i).getIntervenant().getNumInt()+";");
			writer.print(Participation.getListeParticipation().get(i).getRole()+"\n");
		}

		writer.close();		
	}

	/**
	 * @param path
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void exportDonnees(String path) throws FileNotFoundException, UnsupportedEncodingException{
		exportDonneesEtudiant("etudiant"+path);
		exportDonneesIntervenant("intervenant"+path);
		exportDonneesGroupe("groupe"+path);
		exportDonneesSujet("sujet"+path);
		exportDonneesProjet("projet"+path);
		exportDonneesParticipation("participation"+path);
	}


	/**
	 * @param path
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static void importDonneesEtudiant(String path) throws Exception{

		//Initialisation des variables
		Groupe grpTemp =null;
		Etudiant.getListeIndividu().clear();

		String line="";

		//Initialisation du lecteur
		BufferedReader in = new BufferedReader(new FileReader("data/"+path+".csv"));

		//Lecture ligne à ligne et séparation des données
		line = in.readLine();
		while(line!=null){
			String [] ligne = line.split(";");

			//Import des données

			Etudiant tempEtu = new Etudiant(ligne[0],ligne[1],ligne[2]);
			
			line = in.readLine();
		}
		in.close();
	}

	/**
	 * @param path
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static void importDonneesIntervenant(String path) throws Exception{

		//Initialisation des variables
		String line="";
		Intervenant.getListeIntervenant().clear();

		//Initialisation du lecteur
		BufferedReader in = new BufferedReader(new FileReader("data/"+path+".csv"));

		//Lecture ligne à ligne et séparation des données

		line = in.readLine();
		while(line!=null){
			String [] ligne = line.split(";");

			//Import des données
			Intervenant tempInt = new Intervenant(ligne[0],ligne[1],ligne[2]);
			line = in.readLine();
		}
		in.close();
	}


	/**
	 * @param path
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static void importDonneesGroupe(String path) throws Exception{

		//Initialisation des variables
		String line="";
		Groupe.getListeGroupe().clear();

		//Initialisation du lecteur
		BufferedReader in = new BufferedReader(new FileReader("data/"+path+".csv"));

		//Lecture ligne à ligne et séparation des données
		line = in.readLine();
		while(line!=null){
			String [] ligne = line.split(";");

			//Import des données
			
			//Etudiants à insérer dans le groupe
			Etudiant etuTemp1 = null;
			Etudiant etuTemp2 = null;
			Etudiant etuTemp3 = null;
			Etudiant etuTemp4 = null;
			Etudiant etuTemp5 = null;
			Sujet sujTemp = null;
			Groupe tempGrp;
			
			for(int i =0; i<Etudiant.getListeIndividu().size();i++){					
				if(Etudiant.getListeIndividu().get(i).getNumEtu().equals(ligne[1])){
					etuTemp1 = Etudiant.getListeIndividu().get(i);
				}
				if(Etudiant.getListeIndividu().get(i).getNumEtu().equals(ligne[2])){
					etuTemp2 = Etudiant.getListeIndividu().get(i);
				}
				if(Etudiant.getListeIndividu().get(i).getNumEtu().equals(ligne[3])){
					etuTemp3 = Etudiant.getListeIndividu().get(i);
				}
				if(Etudiant.getListeIndividu().get(i).getNumEtu().equals(ligne[4])){
					etuTemp4 = Etudiant.getListeIndividu().get(i);
				}
				if(Etudiant.getListeIndividu().get(i).getNumEtu().equals(ligne[5])){
					etuTemp5 = Etudiant.getListeIndividu().get(i);
				}

			}
			
			if(etuTemp1==null){
				Exception etudiantIntrouvable = new Exception("Premier etudiant non trouvé, la liste contenant les étudiants n'est probablement pas importé");
				throw etudiantIntrouvable;
			}

			if(etuTemp5!=null){
				tempGrp = new Groupe(ligne[0],etuTemp1,etuTemp2,etuTemp3,etuTemp4,etuTemp5);
			}else if(etuTemp4!=null){
				tempGrp = new Groupe(ligne[0],etuTemp1,etuTemp2,etuTemp3,etuTemp4);
			}else{
				tempGrp = new Groupe(ligne[0],etuTemp1,etuTemp2,etuTemp3);
			}
			
			for(int j=6;j<ligne.length;j++){
				for(int k=0 ;k<Sujet.getListeSujet().size() ;k++){
					if(Sujet.getListeSujet().get(k).getID().equals(ligne[j])){
						sujTemp=Sujet.getListeSujet().get(k);
					}
				}
				tempGrp.setPosVoeux(sujTemp,j-6);
			}
			line = in.readLine();
		}
		in.close(); 
	}
 
	/**
	 * @param path
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static void importDonneesParticipation(String path) throws Exception{

		//Initialisation des variables
		Intervenant intTemp = null;
		Projet prjTemp = null;
		Participation.getListeParticipation().clear();
		String line="";

		//Initialisation du lecteur
		BufferedReader in = new BufferedReader(new FileReader("data/"+path+".csv"));

		//Lecture ligne à ligne et séparation des données
		line = in.readLine();
		while(line!=null){
			String [] ligne = line.split(";");
			
			for(int i=0;i<Projet.getListeProjet().size();i++){
				if(Projet.getListeProjet().get(i).getNum().equals(ligne[0])){
					prjTemp = Projet.getListeProjet().get(i);
				}
			}
			for(int i=0;i<Intervenant.getListeIntervenant().size();i++){
				if(Intervenant.getListeIntervenant().get(i).getNumInt().equals(ligne[1])){
					intTemp = Intervenant.getListeIntervenant().get(i);
				}
			}
			//Import des données
			Participation partTemp = new Participation(prjTemp,intTemp,Integer.parseInt(ligne[2]));
			line = in.readLine();
		}
		in.close();
	}
	
	/**
	 * @param path
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static void importDonneesSujet(String path) throws Exception{

		//Initialisation des variables
		Intervenant intTemp = null;
		Sujet.getListeSujet().clear();
		String line="";

		//Initialisation du lecteur
		BufferedReader in = new BufferedReader(new FileReader("data/"+path+".csv"));

		//Lecture ligne à ligne et séparation des données
		line = in.readLine();
		while(line!=null){
			String [] ligne = line.split(";");
			
			//Import des données
			Sujet tempSuj = new Sujet(ligne[0],ligne[1],ligne[2],ligne[3],ligne[4]);
			line = in.readLine();
		}
		in.close();
	}

	/**
	 * @param path
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static void importDonneesProjet(String path) throws Exception{

		//Initialisation des variables
		Intervenant intTemp = null;
		Intervenant supportTemp=null;
		Sujet sujTemp = null;
		Groupe grpTemp =null;
		Projet.getListeProjet().clear();

		String line="";

		//Initialisation du lecteur
		BufferedReader in = new BufferedReader(new FileReader("data/"+path+".csv"));

		//Lecture ligne à ligne et séparation des données

		line = in.readLine();
		while(line!=null){
			String [] ligne = line.split(";");

			//Import des données

			for(int i =0; i<Sujet.getListeSujet().size();i++){
				if(Sujet.getListeSujet().get(i).getID().equals(ligne[1])){
					sujTemp = Sujet.getListeSujet().get(i);
				}
			}

			for(int i =0; i<Groupe.getListeGroupe().size();i++){
				if(Groupe.getListeGroupe().get(i).getNom().equals(ligne[2])){
					grpTemp = Groupe.getListeGroupe().get(i);
				}
			}
			Projet projet= new Projet(ligne[0],sujTemp,grpTemp);
				
			line = in.readLine();
		}
		in.close();
	}

	/**
	 * @param path
	 * @throws Exception
	 */
	public static void importDonnees(String path) throws Exception{
		importDonneesEtudiant("etudiant"+path);
		importDonneesIntervenant("intervenant"+path);
		importDonneesSujet("sujet"+path);
		importDonneesGroupe("groupe"+path);
		importDonneesProjet("projet"+path);
		importDonneesParticipation("participation"+path);
	}

}