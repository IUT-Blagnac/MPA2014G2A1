package donnees;

import java.util.ArrayList;

/**Classe Intervenant, contenant donc les intervenants créés. 
 * @author groupe2A1
 *
 */
public class Intervenant {
	
	//Attributs
	private String nom;
	private String prenom;
	private String numInt;
	private static ArrayList<Intervenant> listeIntervenant = new ArrayList<Intervenant>();

	//Constructeur
	/**Constructeur d'un intervenant
	 * @param pNom String, nom de l'intervenant
	 * @param pPrenom String, prenom de l'intervenant
	 * @param pNumInt ID String, id de l'intervenant
	 * @throws Exception Si l'ID de l'intervenant existe déjà
	 */
	public Intervenant ( String pNumInt, String pNom, String pPrenom) throws Exception{
		
		for(Intervenant interTemp : listeIntervenant){
			if(interTemp.numInt.equals(pNumInt)){
				Exception idExistant = new Exception("Id de l'intervenant déjà existant");
				throw  idExistant;
			}
		}
		
		this.nom=pNom;
		this.prenom=pPrenom;
		this.numInt=pNumInt;
		listeIntervenant.add(this);
	}
	
	//Accesseurs

	
	/**Accesseur du nom de l'intervenant
	 * @return nom - String
	 */
	public  String getNom(){
		return this.nom;
	}
	
	/** Accesseur du prenom de l'intervenant
	 * @return prénom - String
	 */
	public  String getPrenom(){
		return this.prenom;
	}
	
	/** Accesseur de la liste des intervenants
	 * @return listeIntervenant - ArrayList d'intervenant
	 */
	public static ArrayList<Intervenant> getListeIntervenant(){
		return listeIntervenant;
	}
	
	/** Accesseur de l'ID de l'intervenant
	 * @return numInt - String
	 */
	public String getNumInt(){
		return this.numInt;
	}
	
	//Setters
	
	
	/** Setter du nom de l'intervenant
	 * @param pNom String
	 */
	public void setNom(String pNom){
		this.nom=pNom;
	}
	
	/** Setter du prenom de l'intervenant
	 * @param pPrenom String
	 */
	public void setPrenom(String pPrenom){
		this.prenom=pPrenom;
	}
	

	/** Setter de l'ID de l'intervenant
	 * @param pNumInt String
	 * @throws Exception
	 */
	public void setnumInt(String pNumInt) throws Exception{
		for(Intervenant interTemp : listeIntervenant){
			if(interTemp.numInt.equals(pNumInt)){
				Exception idExistant = new Exception("Id de l'intervenant déjà existant");
				throw  idExistant;
			}
		}
		this.numInt=pNumInt;
	}
}
