package donnees;

import java.util.ArrayList;

/**
 * @author groupe2A1
 *
 */
public class Etudiant {
	
	//Attributs
	
	private String nom;
	private String prenom;
	private String numEtu;
	private static ArrayList<Etudiant> listeEtu = new ArrayList<Etudiant>();

	//Constructeur
	

	
	/**Constructeur etudiant complet.
	 * @param pNom  String, nom de l'étudiant
	 * @param pPrenom  String, prénom de l'étudiant
	 * @param pNumEtu ID String, id de l'étudiant
	 * @throws Exception si l'id de l'étudiant existe déjà
	 */
	public Etudiant (String pNumEtu,String pNom, String pPrenom) throws Exception{
		
		for(Etudiant etuTemp : listeEtu){
			if(etuTemp.numEtu.equals(pNumEtu)){
				Exception idExistant = new Exception("Id de l'etudiant déjà existant");
				throw  idExistant;
			}
		}
		nom=pNom;
		prenom=pPrenom;
		numEtu=pNumEtu;
		listeEtu.add(this);
	}
	
	//Accesseurs

	
	/**
	 * Accesseur de l'attribut nom
	 * @return nom - String
	 */
	public  String getNom(){
		return this.nom;
	}
	
	/**Accesseur de l'attribut prenom
	 * @return prenom - String
	 */
	public  String getPrenom(){
		return this.prenom;
	}
	
	/**Accesseur de l'ID de l'étudiant
	 * @return numEtu - String
	 */
	public String getNumEtu(){
		return this.numEtu;
	}
	
	/**Accesseur du groupe de l'étudiant, renvoie null si l'étudiant n'a pas de groupe
	 * @return groupe - Groupe
	 */
	public Groupe getGroupe(){
		Groupe aTrouve=null;
		for(Groupe recherche : Groupe.getListeGroupe()){
			if(recherche.getMembres().contains(this)){
				aTrouve=recherche;
			}
		}
		return aTrouve;
	}
	
	/**Accesseur de la liste des étudiants existant
	 * @return listeEtu - ArrayList d'etudiants
	 */
	public static ArrayList<Etudiant> getListeIndividu(){
		return listeEtu;
	}
	
	//Setters


	/**Setter du nom de l'etudiant
	 * <b> Attention ! Le nom est aussi l'ID de l'étudiant </b>
	 * @param pNom  String
	 */
	public void setNom(String pNom){
		this.nom=pNom;
	}
	
	/**Setter du prenom de l'etudiant
	 * @param pPrenom  String
	 */
	public void setPrenom(String pPrenom){
		this.prenom=pPrenom;
	}
	
	/**Setter du groupe de l'etudiant
	 * @param pGroupe  Groupe
	 */
	public void setGroupe(Groupe pGroupe){
		
	}
	
	/**Setter de l'ID de l'étudiant.
	 * @param pNumEtu  String
	 * @throws Exception si l'id de l'étudiant existe déjà
	 */
	public void setNumEtu(String pNumEtu) throws Exception{
		for(Etudiant etuTemp : listeEtu){
			if(etuTemp.numEtu.equals(pNumEtu)){
				Exception idExistant = new Exception("Id de l'etudiant déjà existant");
				throw  idExistant;
			}
		}
		this.numEtu=pNumEtu;
	}
}
