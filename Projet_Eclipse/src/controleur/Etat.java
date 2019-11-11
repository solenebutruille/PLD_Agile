package controleur;

import vue.Fenetre;
import modele.Plan;
import modele.DemandeLivraison;
import modele.Livraison;

public interface Etat {
	
	/**
	 * Methode appelee par le controleur apres un clic sur le bouton "Charger un Plan"
	 * @param c le controleur
	 * @param f la fenetre
	 */
	public default void chargerPlan(Controleur c, Fenetre f, Plan plan, DemandeLivraison demandeLivraison){};
	
	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Charger une demande de livraison"
	 * @param c le controleur
	 * @param f la fenetre
	 */
	public default void chargerDemandeLivraison(Controleur c, Fenetre f, Plan plan, DemandeLivraison demandeLivraison){};
	
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Calculer une tournee"
	 * @param c le controleur
	 * @param f la fenetre
	 */
	public default void calculerTournee(Controleur c, Fenetre f, Plan plan, DemandeLivraison demandeLivraison) {};
	
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Supprimer une livraison" puis le chois d'une livraison par l'utilisateur
	 * @param c le controleur
	 * @param f la fenetre
	 */
	public default void supprimerLivraison(Controleur c, Fenetre f, Plan plan, DemandeLivraison demandeLivraison, Livraison livraison) {};
	
}