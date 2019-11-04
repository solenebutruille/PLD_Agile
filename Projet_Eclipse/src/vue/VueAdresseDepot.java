package vue;

import java.awt.Color;
import java.awt.Graphics;

class VueAdresseDepot{
    final float x1; 
    final float y1;    
    final int tailleDepot = 20;

    public VueAdresseDepot(float x1, float y1) {
        this.x1 = x1;
        this.y1 = y1; 
    }             
    
    public void dessiner(Graphics g, int width, int height, int modifLatitude, int modifLongitude) {
    	int x = (int) ((x1-VuePlan.latitudeMin)*height/VuePlan.intervalleLatitude);
		int y = (int) ((y1-VuePlan.longitudeMin)*width/VuePlan.intervalleLongitude); 
    	g.fillOval(y-(tailleDepot/2)+modifLongitude, height-x-(tailleDepot/2)+modifLatitude, tailleDepot, tailleDepot);
    }
}