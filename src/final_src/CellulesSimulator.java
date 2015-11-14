import gui.*;
import java.awt.Color;

public class CellulesSimulator extends Simulator {
    private Cellules plateau;
    private int tailleCellule;

    public CellulesSimulator(Cellules plateau, int tailleCellule, GUISimulator simulator, Event event) {
        super(simulator, event);
        this.setPlateau(plateau);
        this.setTailleCellule(tailleCellule);
    }

    public void setPlateau(Cellules plateau) {
        this.plateau = plateau;
    }

    private void setTailleCellule(int t){
        this.tailleCellule = t;
    }
  

	public void reInit() {
        this.plateau.reInit();
    }

    /**
     * Fonction qui affiche avec la bonne couleur les Cellules dans la fenêtre graphique
     */
    public void affiche() {
        Color c = new Color(0,0,0);
        for(int i = 0; i < this.plateau.getNbL(); i++) {
            for(int k = 0; k < plateau.getNbC(); k++) {
                c = super.getColorEtat(this.plateau.getCellule(i, k));
                super.getGUI().addGraphicalElement( new Rectangle(tailleCellule*k+tailleCellule/2,tailleCellule*i+tailleCellule/2, c, c, tailleCellule));
            }
        }
    }
}
