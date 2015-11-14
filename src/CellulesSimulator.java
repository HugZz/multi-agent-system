import gui.*;
import java.awt.Color;

/**
 * Cette classe permet de gérer la partie "affichage" des trois jeux.
 * @author Lucas Mahieu
 * @author Hugues de Valon
 */
public class CellulesSimulator extends Simulator {
    /**
     * Plateau de cellules.
     */
    private Cellules plateau;
    /**
     * Taille d'une cellule.
     */
    private int tailleCellule;

    /**
     * Constructeur : initialise le plateau de cellules, le GUI, l'évènement principal et le manager d'évènements.
     * @param plateau
     * @param tailleCellule
     * @param simulator
     * @param event
     * @param manager
     */
    public CellulesSimulator(Cellules plateau, int tailleCellule, GUISimulator simulator, Event event, EventManager manager) {
        super(simulator, event, manager);
        this.setPlateau(plateau);
        this.setTailleCellule(tailleCellule);
    }

    /**
     * Modifieur sur le plateau
     * @param plateau Nouveau plateau
     */
    public void setPlateau(Cellules plateau) {
        this.plateau = plateau;
    }

    /**
     * Modifieur sur la taille des cellules.
     * @param t Nouvelle taile
     */
    private void setTailleCellule(int t){
        this.tailleCellule = t;
    }

    /**
     * Cette méthode ré-initialise le plateau de cellules.
     */
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
