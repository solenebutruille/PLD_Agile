package controleur;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modele.DemandeLivraison;
import modele.Plan;
import vue.Fenetre;
import xml.ExceptionXml;
import xml.LectureXml;

public class EtatPlanCharge implements Etat {
	// Etat initial 
	
	
	@Override
	public void chargerPlan(Controleur controleur, Fenetre fenetre, Plan plan, DemandeLivraison demandeLivraison) {
		
		fenetre.afficheMessage("Chargement d'un plan : Veuillez saisir le fichier XML repr�sentant le plan.");
		
		try {
			LectureXml.creerPlan(plan);
			
			fenetre.effacerVuePlan();
			fenetre.initialiserVuePlan();
		
		} catch (IOException | ParserConfigurationException | SAXException | NumberFormatException | ExceptionXml e) {
			fenetre.afficheMessage(e.getMessage());
		}
	}
	
	
	@Override
	public void chargerDemandeLivraison(Controleur controleur, Fenetre fenetre, Plan plan, DemandeLivraison demandeLivraison) {
		
		fenetre.afficheMessage("Chargement d'une demande de livraison : Veuillez saisir le fichier XML repr�sentant la demande de livraison.");
		
		try {
			LectureXml.creerDemandeDeLivraison(plan, demandeLivraison);
			
			fenetre.initialiserVueDemandeLivraison();
			
			controleur.setEtatCourant(controleur.etatDemandeLivraisonCharge);
		
		} catch (IOException | ParserConfigurationException | SAXException | NumberFormatException | ExceptionXml e) {
			fenetre.afficheMessage(e.getMessage());
		}
	}
	
}