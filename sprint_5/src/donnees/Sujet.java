package donnees;

import java.util.ArrayList;

/**Classe Sujet, contenant donc les sujets disponibles pour le PTUT.
 * @author groupe2A1
 *
 */
public class Sujet {
	
	//Attributs
	private String ID;
	private String titre;
	private String contexte;
	private String description; 
	private String outils;
	
	private static ArrayList<Sujet> listeSujet = new ArrayList<Sujet>();
	
	//Constructeur
	
	/**Constructeur d'un sujet
	 * @param pID ID String, acronyme du sujet sert aussi d'ID
	 * @param pTitre String, titre du sujet
	 * @param pContexte String, contexte du sujet
	 * @param pProjet String, description du sujet
	 * @param pOutils Stting, outils pour le sujet
	 * @throws Exception idExistant, si l'acronyme du sujet est déjà pris, lève une exception
	 */
	public Sujet(String pID, String pTitre, String pContexte, String pDescription, String pOutils) throws Exception{
		for(Sujet sujetTemp : listeSujet){
			if(sujetTemp.ID.equals(pID)){
				Exception idExistant = new Exception("Numero du sujet déjà existant");
				throw  idExistant;
			}
		}
		ID=pID;
		titre=pTitre;
		contexte=pContexte;
		description=pDescription;
		outils=pOutils;
		
		listeSujet.add(this);
	}
	
	//Accesseurs
		
	/** Accesseur de l'acronyme, ID du sujet 
	 * @return acronyme - String
	 */
	public String getID(){
		
		return this.ID;
	}
	
	/** Accesseur du titre du sujet
	 * @return titre - String
	 */
	public String getTitre(){
		return this.titre;
	}
	
	/** Accesseur du contexte du sujet
	 * @return contexte - String
	 */
	public String getContexte(){
		return this.contexte;
	}
	
	/**
	 * Accesseur de la description du sujet du projet. 
	 * @return String description
	 */
	public String getDescription(){
		return this.description;
	}
	
	/** Accesseur des outils du sujet
	 * @return outils - String
	 */
	public String getOutils(){
		return this.outils;
	}
	
	/**Accesseur des projets utilisant ce sujet
	 * @return
	 */
	public ArrayList<Projet> getProjets(){
		ArrayList<Projet> listeProjet = new ArrayList<Projet>();
		for(Projet cherche : Projet.getListeProjet()){
			if(cherche.getSujet().equals(this)){
				listeProjet.add(cherche);
			}
		}
		return listeProjet;
	}
	
	/** Accesseur de la liste des sujets
	 * @return listeSujet - ArrayList de Sujets
	 */
	public static ArrayList<Sujet> getListeSujet(){
		return listeSujet;
	}
	
	//Setters
	
	/** Setter de l'acronyme du sujet
	 * @param pAcronyme String
	 * @throws Exception acronymeExistant, si l'acronyme existe déjà, l'exception est levée
	 */
	public void setID(String pID) throws Exception{
		for(Sujet sujetTemp : listeSujet){
			if(sujetTemp.ID.equals(pID)){
				Exception idExistant = new Exception("Numero du projet déjà existant");
				throw  idExistant;
			}
		}
		this.ID = pID;
	}
	
	/** Setter du titre du sujet
	 * @param pTitre String
	 */
	public void setTitre(String pTitre){
		this.titre=pTitre;
	}
	
	/** Setter du contexte du sujet
	 * @param pContexte String
	 */
	public void setContexte(String pContexte){
		this.contexte=pContexte;
	}
	
	/** Setter de la description du sujet
	 * @param pProjet String
	 */
	public void setDescription(String pDescription){
		this.description=pDescription;
	}
	
	/** Setter des outils du sujet
	 * @param pOutils String
	 */
	public void setOutils(String pOutils){
		this.outils=pOutils;
	}
	
	/** Setter du client du sujet
	 * @param pClient Intervenant
	 */
	public void setClient(Intervenant pClient){
		
	}
	
	
}
