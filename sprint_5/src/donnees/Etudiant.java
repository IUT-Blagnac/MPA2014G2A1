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
	 * @param pNom  String, nom de l'�tudiant
	 * @param pPrenom  String, pr�nom de l'�tudiant
	 * @param pNumEtu ID String, id de l'�tudiant
	 * @throws Exception si l'id de l'�tudiant existe d�j�
	 */
	public Etudiant (String pNumEtu,String pNom, String pPrenom) throws Exception{
		
		for(Etudiant etuTemp : listeEtu){
			if(etuTemp.numEtu.equals(pNumEtu)){
				Exception idExistant = new Exception("Id de l'etudiant d�j� existant");
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
	
	/**Accesseur de l'ID de l'�tudiant
	 * @return numEtu - String
	 */
	public String getNumEtu(){
		return this.numEtu;
	}
	
	/**Accesseur du groupe de l'�tudiant, renvoie null si l'�tudiant n'a pas de groupe
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
	
	/**Accesseur de la liste des �tudiants existant
	 * @return listeEtu - ArrayList d'etudiants
	 */
	public static ArrayList<Etudiant> getListeIndividu(){
		return listeEtu;
	}
	
	//Setters


	/**Setter du nom de l'etudiant
	 * <b> Attention ! Le nom est aussi l'ID de l'�tudiant </b>
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
	
	/**Setter de l'ID de l'�tudiant.
	 * @param pNumEtu  String
	 * @throws Exception si l'id de l'�tudiant existe d�j�
	 */
	public void setNumEtu(String pNumEtu) throws Exception{
		for(Etudiant etuTemp : listeEtu){
			if(etuTemp.numEtu.equals(pNumEtu)){
				Exception idExistant = new Exception("Id de l'etudiant d�j� existant");
				throw  idExistant;
			}
		}
		this.numEtu=pNumEtu;
	}
}
