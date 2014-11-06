package donnees;

import java.util.ArrayList;

/**Classe d'association "Participation" joignant intervenant et projet.
 * @author groupe2A1
 *
 */

public class Participation {

	private Projet projet;
	private Intervenant intervenant;
	private int role;
	private String [] roles = {"client","superviseur","support technique"};
	static ArrayList<Participation> listeParticipation = new ArrayList<Participation>();

	/**Constructeur complet de participation
	 * @param pProjet Projet sélectionné
	 * @param pIntervenant Intervenant concerné
	 * @param pRole Int, 0 pour client, 1 pour support et 2 pour superviseur
	 * @throws Exception - Si la participation existe déjà ou l'erreur superviseur/client
	 */
	public Participation(Projet pProjet, Intervenant pIntervenant, int pRole) throws Exception{


		for(Participation test : listeParticipation){
			if(pProjet==test.projet){
				if(pIntervenant==test.intervenant){
					if( (test.role==0&&pRole==1)||(test.role==1&&pRole==0) ){
						Exception participationImpossible = new Exception("Cette participation n'est pas possible, l'intervenant ne peut être client et superviseur");
						throw  participationImpossible;
					}
					if(pRole==test.role){
						Exception participationExistante = new Exception("Cette participation existe déjà");
						throw  participationExistante;
					}
				}
			}

		}

		projet=pProjet;
		intervenant=pIntervenant;
		role=pRole;
		listeParticipation.add(this);
	}
	//Accesseurs

	/**Acesseur du Projet de la participation
	 * @return projet - Projet
	 */
	public Projet getProjet() {
		return projet;
	}

	
	/**Acesseur du Role de la participation
	 * @return role - int
	 */
	public int getRole() {
		return this.role;
	}
	
	
	/**Acesseur du Role de la participation version String
	 * @return role - String
	 */
	public String getRoleS() {
		return roles[this.getRole()];
	}

	/**Acesseur de l'intervenant de la participation
	 * @return intervenant - Intervenant
	 */
	public Intervenant getIntervenant() {
		return intervenant;
	}

	/**Acesseur de la liste des Participations
	 * @return listeParticipation - ArrayList<Participation>
	 */
	public static ArrayList<Participation> getListeParticipation() {
		return listeParticipation;
	}

	//Setters
	/**Setter pour changer le projet
	 * @param projet Projet
	 */
	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	/**Setter de l'intervenant
	 * @param intervenant Intervenant
	 */
	public void setIntervenant(Intervenant intervenant) {
		this.intervenant = intervenant;
	}


	/**Setter du role
	 * @param role Int
	 */
	public void setRole(int pRole) {
		this.role=pRole;
	}

	//Méthodes



}
