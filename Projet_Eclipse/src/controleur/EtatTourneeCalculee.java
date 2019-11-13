package controleur;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.util.Pair;
import modele.DemandeLivraison;
import modele.GraphePCC;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import vue.Fenetre;
import xml.ExceptionXml;
import xml.LectureXml;

public class EtatTourneeCalculee implements Etat {
	// Etat initial 
	
	
	@Override
	public void chargerPlan(Controleur controleur, Fenetre fenetre, ListeDeCdes listeDeCdes, Plan plan, DemandeLivraison demandeLivraison, Tournee tournee) {
		
		fenetre.afficheMessage("Chargement d'un plan : Veuillez saisir le fichier XML représentant le plan.");
		
		try {
			LectureXml.creerPlan(plan);
			
			demandeLivraison.reset();
			fenetre.effacerVuePlan();
			fenetre.effacerVueDemandeLivraison();
			
			tournee.reset();
			fenetre.effacerVueTournee();
			
			listeDeCdes.reset();
			
			fenetre.initialiserVuePlan();
			controleur.setEtatCourant(controleur.etatPlanCharge);
		
		} catch (IOException | ParserConfigurationException | SAXException | NumberFormatException | ExceptionXml e) {
			fenetre.afficheMessage(e.getMessage());
		}
	}
	
	
	@Override
	public void chargerDemandeLivraison(Controleur controleur, Fenetre fenetre, ListeDeCdes listeDeCdes, Plan plan, DemandeLivraison demandeLivraison, Tournee tournee) {
		
		fenetre.afficheMessage("Chargement d'une demande de livraison : Veuillez saisir le fichier XML représentant la demande de livraison.");
		
		try {
			LectureXml.creerDemandeDeLivraison(plan, demandeLivraison);
			
			fenetre.effacerVueDemandeLivraison();
			
			tournee.reset();
			fenetre.effacerVueTournee();
			
			listeDeCdes.reset();
			
			fenetre.initialiserVueDemandeLivraison();
			controleur.setEtatCourant(controleur.etatDemandeLivraisonCharge);
		
		} catch (IOException | ParserConfigurationException | SAXException | NumberFormatException | ExceptionXml e) {
			fenetre.afficheMessage(e.getMessage());
		}
	}
	
	
	@Override
	public void calculerTournee(Controleur controleur, Fenetre fenetre, ListeDeCdes listeDeCdes, Plan plan, DemandeLivraison demandeLivraison, GraphePCC graphePCC, Tournee tournee) {
		fenetre.afficheMessage("Calcul d'une tournee.");
		
		tournee.reset();
		fenetre.effacerVueTournee();
		
		listeDeCdes.reset();
		
		graphePCC.initialiserGraphePCC();
		tournee.initialiserGraphePCC(graphePCC);
		tournee.calculerUneTournee(1000000, demandeLivraison);
		
		fenetre.initialiserVueTournee();
		
	}
	
	
	@Override
	public void supprimerLivraison(Controleur controleur, Fenetre fenetre, ListeDeCdes listeDeCdes, Plan plan, DemandeLivraison demandeLivraison, Livraison livraison, Tournee tournee) {
		
		fenetre.afficheMessage("Suppression d'une livraison et calcul d'une nouvelle tournee.");
		
		listeDeCdes.ajoute(new CdeSuppressionLivraison(demandeLivraison, livraison, tournee, null, null));
		
		fenetre.effacerVueDemandeLivraison();
		fenetre.initialiserVueDemandeLivraison();
		fenetre.effacerVueTournee();
		fenetre.initialiserVueTournee();
		
		
	}
	
	@Override
	public void ajouterLivraison(Controleur controleur, Fenetre fenetre, ListeDeCdes listeDeCdes, Plan plan, DemandeLivraison demandeLivraison, Livraison livraison, Tournee tournee, Pair<Integer, Intersection> interAvantEnlevement, Pair<Integer, Intersection> interAvantDepot) {
		
		fenetre.afficheMessage("Ajout d'une livraison et calcul d'une nouvelle tournee.");
		
		listeDeCdes.ajoute(new CdeInverse(new CdeSuppressionLivraison(demandeLivraison, livraison, tournee, interAvantEnlevement, interAvantDepot)));
		
		fenetre.effacerVueDemandeLivraison();
		fenetre.initialiserVueDemandeLivraison();
		fenetre.effacerVueTournee();
		fenetre.initialiserVueTournee();
		
		
	}
	
	
	@Override
	public void undo(Fenetre fenetre, ListeDeCdes listeDeCdes) {
		listeDeCdes.undo();
		
		fenetre.effacerVueDemandeLivraison();
		fenetre.initialiserVueDemandeLivraison();
		
		fenetre.effacerVueTournee();
		fenetre.initialiserVueTournee();
	}
	
	
	@Override
	public void redo(Fenetre fenetre, ListeDeCdes listeDeCdes) {
		listeDeCdes.redo();
		
		fenetre.effacerVueDemandeLivraison();
		fenetre.initialiserVueDemandeLivraison();
		
		fenetre.effacerVueTournee();
		fenetre.initialiserVueTournee();
		
		
	}
	
	
}