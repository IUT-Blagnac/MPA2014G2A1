package donnees;

import java.util.ArrayList;

/**Cette classe Groupe, d�fini les groupes compos�s de 3 � 5 �tudiants, avec un nom de groupe lui servant d'ID.
 * @author groupe2A1
 *
 */
public class Groupe {

	//Attributs
	private ArrayList<Etudiant> membres = new ArrayList<Etudiant> ();
	private String nom;
	private static ArrayList<Groupe> listeGroupe = new ArrayList<Groupe> ();
	private ArrayList<Sujet> voeux = new ArrayList<Sujet>();

	//Constructeurs
	/**Constructeur de Groupe complet
	 * @param pNom ID String, nom du groupe aussi son ID
	 * @param pEtu0 Etudiant, etudiant numero 1
	 * @param pEtu1 Etudiant, etudiant numero 2
	 * @param pEtu2 Etudiant, etudiant numero 3
	 * @param pEtu3 Etudiant, etudiant numero 4
	 * @param pEtu4 Etudiant, etudiant numero 5
	 * @throws Exception si l'ID du groupe existe d�j�
	 */
	public Groupe(String pNom, Etudiant pEtu0, Etudiant pEtu1, Etudiant pEtu2, Etudiant pEtu3, Etudiant pEtu4) throws Exception{

		for(Groupe grpTemp : listeGroupe){
			if(grpTemp.nom.equals(pNom)){
				System.out.println(pNom);
				Exception idExistant = new Exception("Nom du groupe d�j� existant");
				throw  idExistant;
			}
		}
		this.nom=pNom;
		this.membres.add(pEtu0);
		this.membres.add(pEtu1);
		this.membres.add(pEtu2);
		this.membres.add(pEtu3);
		this.membres.add(pEtu4);
		voeux.addAll(Sujet.getListeSujet());
		listeGroupe.add(this);

	}
	
	/**Constructeur de Groupe avec  4 etudiants
	 * @param pNom ID String, nom du groupe aussi son ID
	 * @param pEtu0 Etudiant, etudiant numero 1
	 * @param pEtu1 Etudiant, etudiant numero 2
	 * @param pEtu2 Etudiant, etudiant numero 3
	 * @param pEtu3 Etudiant, etudiant numero 4
	 * @throws Exception si l'ID du groupe existe d�j�
	 */
	public Groupe(String pNom, Etudiant pEtu0, Etudiant pEtu1, Etudiant pEtu2, Etudiant pEtu3) throws Exception{
		this(pNom, pEtu0, pEtu1, pEtu2, pEtu3, null);

	}
	
	/**Constructeur de Groupe avec  3 etudiants
	 * @param pNom ID String, nom du groupe aussi son ID
	 * @param pEtu0 Etudiant, etudiant numero 1
	 * @param pEtu1 Etudiant, etudiant numero 2
	 * @param pEtu2 Etudiant, etudiant numero 3
	 * @throws Exception si l'ID du groupe existe d�j�
	 */
	public Groupe(String pNom, Etudiant pEtu0, Etudiant pEtu1, Etudiant pEtu2) throws Exception{
		this(pNom, pEtu0, pEtu1, pEtu2, null, null);
	}

	//Accesseurs
	
	
	/**
	 * Accesseur permettant l'obtention des �tudiants pr�sents dans le groupe, compris entre 3 et 5
	 * @return membres - ArrayList d'�tudiants, les membres du groupe
	 */
	public ArrayList<Etudiant> getMembres(){
		return this.membres;
	}

	/**
	 * Accesseur retournant le nom du groupe
	 * @return nom - String
	 */
	public String getNom(){
		return this.nom;
	}

	/**Accesseur retournant une ArrayList de Groupe, contenant tous les groupes cr��s
	 * @return listeGroupe - ArrayList de Groupe
	 */
	public static ArrayList<Groupe> getListeGroupe(){
		return listeGroupe;
	}
	
	/**Accesseur du Projet attribu� au Groupe, renvoie null si le groupe ne s'est pas encore vu attribu� de projet
	 * @return aTrouve - Projet, ou null
	 */
	public Projet getProjet(){
		Projet aTrouve=null;
		for(Projet recherche : Projet.getListeProjet()){
			if(recherche.getGroupe()==this){
				aTrouve = recherche;
			}
		}
		return aTrouve;
	}
	
	/**Accesseur du sujet choisi pour le groupe, renvoie null si pas de sujet attribu�
	 * @return aTrouve - Sujet, ou null
	 */
	public Sujet getSujet(){
		Sujet aTrouve = null;
		if(this.getProjet()!=null){
				aTrouve=this.getProjet().getSujet();
		}
		return aTrouve;
	}

	/**Accesseur des voeux du groupe, ArrayList de sujet
	 * @return voeux - ArrayList de sujets
	 */
	public  ArrayList<Sujet> getVoeux(){
		return this.voeux;
	}

	/**Accesseur de tous les voeux en position pass�e en param�tre
	 * @param pos
	 * @return listeVoeuxPos - ArrayList de sujets
	 */
	public ArrayList<Sujet> getVoeuxPos(int pos){
		ArrayList<Sujet> listeVoeuxPos = new ArrayList<Sujet>();
		for(Groupe pGroupe : listeGroupe){
			listeVoeuxPos.add(pGroupe.getVoeux().get(pos));
		}
		return listeVoeuxPos;
	}

	/** Accesseur retournant les groupes non affect�s � un projet
	 * @return pListe - ArrayList de Groupe
	 */
	public static ArrayList<Groupe> getGrpSansSujet(){
		ArrayList<Groupe> pListe = new ArrayList<Groupe>();
		boolean affecte = false;

		for(Groupe pGroupe : listeGroupe){
			for(Projet pProjet : Projet.getListeProjet()){
				if(pProjet.getGroupe()==pGroupe){
					affecte=true;
					break;
				}
			}
			if(!affecte){
				pListe.add(pGroupe);
			}			
		}
		return pListe;
	}
	
	/** Accesseur retournant les groupes affect�s � un projet
	 * @return pListe - ArrayList de Groupe
	 */
	public static ArrayList<Groupe> getGrpAvecSujet(){
		ArrayList<Groupe> pListe = new ArrayList<Groupe>();
		boolean affecte = false;

		for(Groupe pGroupe : listeGroupe){
			for(Projet pProjet : Projet.getListeProjet()){
				if(pProjet.getGroupe()==pGroupe){
					affecte=true;
					break;
				}
			}
			if(affecte){
				pListe.add(pGroupe);
			}			
		}
		return pListe;
	}

	/** Fonction retournant les groupes ayant le sujet pass� en param�tre dans leur liste de voeux � la position donn�e en param�tre
	 * @param pSujet Sujet, sujet � trouver
	 * @param pos Int, position du sujet dans la liste de voeux
	 * @return pListe - ArrayList de Groupe
	 */
	public static ArrayList<Groupe> getGrpPosSuj(Sujet pSujet, int pos){
		ArrayList<Groupe> pListeSSSujet = Groupe.getGrpSansSujet();
		ArrayList<Groupe> pListe = new ArrayList<Groupe>();
		for(Groupe pGroupe : pListeSSSujet){
			for(Sujet sujTemp : pGroupe.getVoeux()){
				if(sujTemp==pSujet&&pGroupe.getVoeux().indexOf(sujTemp)==pos){
					pListe.add(pGroupe);
				}
			}
		}
		return pListe;
	}

	//Setters
	/**Setter permettant de changer le nom du groupe.
	 * <b> Attention ! Le nom est aussi l'ID du groupe </b>
	 * @param pNom String, ID et nom du groupe
	 */
	public void setNom(String pNom){
		this.nom = pNom;
	}

	/**Methode permettant De mettre le voeux pass� en param�tre � la position pass�e en param�tre
	 * @param pVoeux Sujet, voeux � bouger
	 * @param pos Int, position � laquelle le bouger
	 */
	public void setPosVoeux(Sujet pVoeux, int pos){
		voeux.remove(pVoeux);
		voeux.add(pos, pVoeux);
	}

	/**M�thode permettant d'�changer les positions des deux voeux chang�s en param�tres
	 * @param pVoeux Sujet, premier voeux
	 * @param pVoeux2 Sujet, deuxi�me voeux
	 */
	public void echPosVoeux(Sujet pVoeux, Sujet pVoeux2){
		int posIni = voeux.indexOf(pVoeux);
		int pos = voeux.indexOf(pVoeux2);
		voeux.remove(pVoeux);
		voeux.add(posIni, pVoeux2);
		voeux.remove(pVoeux2);
		voeux.add(pos, pVoeux);
	}


}
