package modele;

import java.util.LinkedList;


public class GraphePCC {
	private int nbSommets;
	private LinkedList<Trajet>[] listeAdjacence;
	
	
	public GraphePCC(int nbSommets){
		this.nbSommets = nbSommets;
		listeAdjacence= new LinkedList[nbSommets];
	}
	
	public void ajouterGraphIntermediaire(LinkedList<Trajet> graphIntermediaire, int position) {
		
		listeAdjacence[position] = graphIntermediaire;
		
	}
	
	public int getNbSommets(){
		return nbSommets;
	}
	
	public LinkedList<Trajet>[] getListeAdjacence(){
		return listeAdjacence;
	}
}
