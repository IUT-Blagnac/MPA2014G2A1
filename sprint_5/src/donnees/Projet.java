package donnees;

import java.util.ArrayList;

/**Classe Projet, contenant donc les projets créés. Un projet ne peut être créé que si le groupe et le sujet sont déterminés
 * @author groupe2A1
 *
 */
public class Projet {

	//Attributs
	private String numPrj;
	private Groupe groupe;
	private Sujet sujet;
	private static ArrayList<Projet> listeProjet = new ArrayList<Projet>();


	//Constructeurs
	/**Constructeur complet du projet. <b> Attention ! L'ordre des deux derniers paramtres intervenants sont importants : le premier est superviseur, le deuxième support technique. 
	 * Ne pas hésiter à affecter à null pour conserver l'ordre </b>
	 * @param pNumPrj ID String, id du projet, unique
	 * @param pSujet Sujet, sujet du projet
	 * @param pGroupe Groupe, groupe affecté au projet
	 * @param pSuperviseur Intervenant, superviseur du projet
	 * @param pSupportTechnique Intervenant, support technique du projet
	 * @throws Exception Si l'ID du projet existe déjà
	 */
	public Projet(String pNumPrj, Sujet pSujet, Groupe pGroupe) throws Exception{
		for(Projet prjTemp : listeProjet){
			if(prjTemp.numPrj.equals(pNumPrj)){
				Exception idExistant = new Exception("Numero du projet déjà existant");
				throw  idExistant;
			}
		}
		numPrj=pNumPrj;
		sujet=pSujet;
		groupe = pGroupe;
		listeProjet.add(this);

	}


	//Accesseurs

	/**Accesseur du numero du projet
	 * @return numPrj - String
	 */
	public String getNum(){
		return this.numPrj;
	}

	/**Accesseur du groupe du projet
	 * @return groupe - Groupe
	 */
	public Groupe getGroupe(){
		return this.groupe;
	}

	/**Accesseur du sujet du projet
	 * @return sujet - Sujet
	 */
	public Sujet getSujet(){
		return this.sujet;
	}

	/**Accesseur du client du projet
	 * @return client - Intervenant
	 */
	public Intervenant getClient(){
		Intervenant client=null;
		for(Participation cherche : Participation.getListeParticipation()){
			if(cherche.getProjet().equals(this)&&cherche.getRole()==0){
				client=cherche.getIntervenant();
			}
		}
		return client;
	}

	/**Accesseur du superviseur du projet
	 * @return superviseur - Intervenant
	 */
	public Intervenant getSuperviseur(){
		Intervenant client=null;
		for(Participation cherche : Participation.getListeParticipation()){
			if(cherche.getProjet().equals(this)&&cherche.getRole()==1){
				client=cherche.getIntervenant();
			}
		}
		return client;
	}

	/**Accesseur du support technique du projet
	 * @return supportTechnique - Intervenant
	 */
	public Intervenant getSupport(){
		Intervenant client=null;
		for(Participation cherche : Participation.getListeParticipation()){
			if(cherche.getProjet().equals(this)&&cherche.getRole()==2){
				client=cherche.getIntervenant();
			}
		}
		return client;
	}

	/**Accesseur de la liste des projets
	 * @return listeProjet - ArrayList de Projets
	 */
	public static ArrayList<Projet> getListeProjet(){
		return listeProjet;
	}

	//Setters

	/**Setter du numero de Projet. <b> Aussi ID du projet</b>
	 * @param pNumPrj String, ID du projet
	 * @throws Exception levée si l'ID existe déjà
	 */
	public void setNum(String pNumPrj) throws Exception{
		for(Projet prjTemp : listeProjet){
			if(prjTemp.numPrj.equals(pNumPrj)){
				Exception idExistant = new Exception("Numero du projet déjà existant");
				throw  idExistant;
			}
		}
		this.numPrj=pNumPrj;
	}

	/**Setter du groupe du projet
	 * @param pGroupe Groupe
	 */
	public void setGroupe(Groupe pGroupe){
		this.groupe=pGroupe;
	}

	/**Setter du Sujet du Projet
	 * @param pSujet Sujet
	 */
	public void setSujet(Sujet pSujet){
		this.sujet=pSujet;
	}


	/**Setter du client du Projet
	 * @param pClient Intervenant
	 */
	public void setClient(Intervenant pClient){
		for(Participation cherche : Participation.getListeParticipation()){
			if(cherche.getProjet().equals(this)&&cherche.getRole()==0){
				cherche.setIntervenant(pClient);
			}
		}
	}

	/**Setter du superviseur du Projet
	 * @param pSuperviseur - Intervenant
	 */
	public void setSuperviseur(Intervenant pSuperviseur){

	}

	/**Setter du support technique du Projet
	 * @param pSupport - Intervenant
	 */
	public void setSupport(Intervenant pSupport){

	}

	//Méthode


	/**<b> En cours d'implémentation </b>
	 * Méthode permettant d'affecter de façon automatique les sujets aux groupes.
	 * Si deux groupes ont exactement les mêmes voeux ==> math random.
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void AutoAffectation() throws Exception{

		//Déclaration 
		ArrayList<Groupe> groupeSProjet = new ArrayList<Groupe>();
		ArrayList<Groupe> groupeTempList = new ArrayList<Groupe>();
		ArrayList<Groupe> groupeAProjet = new ArrayList<Groupe>();
		ArrayList<Groupe> groupeConflit = new ArrayList<Groupe>();
		groupeSProjet.addAll(Groupe.getGrpSansSujet());
		groupeAProjet.addAll(Groupe.getGrpAvecSujet());

		ArrayList<Sujet> sujetAAffecter = new ArrayList<Sujet>();

		//
		for(int i=0;i<Sujet.getListeSujet().size();i++){
			
			sujetAAffecter.clear();
			groupeConflit.clear();
			
			for(Sujet cherche : Sujet.getListeSujet()){
				if(sujet.getProjets().size()==i){
					sujetAAffecter.add(cherche);	
				}
			}
			
			for(Sujet cherche : sujetAAffecter){
				groupeTempList.clear();
				groupeTempList.addAll(Groupe.getGrpPosSuj(cherche, i));
				groupeTempList.removeAll(groupeAProjet);
				if(groupeTempList.size()==1){
					Projet prjTemp = new Projet(String.valueOf(Projet.getListeProjet().size()),cherche,groupeTempList.get(0));
					sujetAAffecter.remove(cherche);
				}else if (groupeTempList.size()!=0){
					groupeConflit.addAll(groupeTempList);
				}
			}
			
			for(Groupe cherche : groupeConflit){
				for(int j=1;j<cherche.getVoeux().size();j++){
					Sujet nextVoeux = cherche.getVoeux().get(j);
					groupeTempList.clear();
					groupeTempList.addAll(Groupe.getGrpPosSuj(nextVoeux, i));
					groupeTempList.removeAll(groupeAProjet);
					if(groupeTempList.size()==1){
						Projet prjTemp = new Projet(String.valueOf(Projet.getListeProjet().size()),nextVoeux,groupeTempList.get(0));
						sujetAAffecter.remove(nextVoeux);
						groupeConflit.remove(cherche);
						break;
					}else if (groupeTempList.size()!=0){
						groupeTempList.clear();
						groupeTempList.addAll(Groupe.getGrpPosSuj(nextVoeux, i+1));
						groupeTempList.removeAll(groupeAProjet);
						if(groupeTempList.size()==1){
							Projet prjTemp = new Projet(String.valueOf(Projet.getListeProjet().size()),nextVoeux,groupeTempList.get(0));
							sujetAAffecter.remove(nextVoeux);
							groupeConflit.remove(cherche);
							break;
						}
					}
				}
			}
			
			
			
		}




	}


}
