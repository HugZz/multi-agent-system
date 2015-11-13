import gui.*;
import java.awt.Color;
/**
 * Cette classe est le Simulateur de Cellules
 * Grace à ces méthodes vous pourrez jouer au jeu de l'immigration
 * @author Lucas Mahieu
 * @version 1.0
 */
public class CellulesSimulator2 implements gui.Simulable {
    private Cellules2 plateau;
    private GUISimulator GUIPlateau;
    private int tailleCellule;
    private EventManager manager;
    private ColorTable colorTable;
    /**
     *Constructeur par défaut sans paramètre 
     */
    public CellulesSimulator2(){
        this.setPlateau(new Cellules2(50,50,50));
        this.setGUIPlateau(new GUISimulator(500, 500, Color.BLUE));
        this.setTailleCellule(10);
        this.manager = new EventManager();
        this.manager.addEvent(new Cellules2Event(this.plateau, 1, manager));
        this.setColorTable();
    }
    /**
     *Constructeur de la classe avec paramètre 
     * @param n
     *      nombre de ligne du plateau de simulation
     * @param m
     *      nombre de colonnes du plateau de simulation
     * @param ne
     *      nombre d'états possibles pour chaque cellule
     * @param t
     *      taille des cellules
     * @param g
     *      Fenêtre graphique qui contiendra graphiquement le plateau
     */ 
    public CellulesSimulator2(int n, int m, int t, int ne, GUISimulator g){
        this.setPlateau(new Cellules2(n,m,ne));
        this.setGUIPlateau(g);
        this.setTailleCellule(t);
        this.manager = new EventManager();
        this.manager.addEvent(new Cellules2Event(this.plateau, 1, manager));
        this.setColorTable();
    }
    /**
     * Adder de plateau à partir de Cellules
     * @param c
     *      Cellules à créer dans le plateau
     */
    private void setPlateau(Cellules2 c){
        this.plateau = c;
    }
    /**
     * Getter de plateau
     * @return Cellules du plateau
     */
    private Cellules2 getPlateau(){
        return this.plateau;
    }
    /**
     * Adder de GUIPlateau
     * @param g
     *      Fenêtre à créer pour contenir le plateau
     */
    private void setGUIPlateau(GUISimulator g){
        this.GUIPlateau = g;
    }
    /**
     * Adder de GUIPlateau
     * @return Attribut GUIPlateau de CellulesSimulator
     */
    private GUISimulator getGUIPlateau(){
       return this.GUIPlateau;
    }
    /**
     * Méthodes privé pour donner la taille des cellules 
     */
    private void setTailleCellule(int t){
        this.tailleCellule = t;
    }
    /**
     * @return Done la taille graphique des cellules
     */
    private int getTailleCellule(){
        return this.tailleCellule;
    }
    /**
     * Implementation de la méthode next() de Simulable
     * Effectue l'itération suivante du jeu
     */
    public void next(){
        //this.getPlateau().actualiser();
        this.manager.next();
        this.getGUIPlateau().reset();
        this.affiche();
    }
    /**
     * Implementation de la méthode Restart() de Simulable
     * Re disposer de façon aléatoire toute les cases du plateau de jeux 
     */
    public void restart(){
        this.manager.restart();
        this.getPlateau().reInit();
        this.manager.addEvent(new Cellules2Event(this.plateau, 1, this.manager));
        this.getGUIPlateau().reset();
        this.affiche();
    }
    /**
     * Creer la table des corespondance : Entierw -> Couleurs
     * @param ne
     * 		créra une table de correspondance pour les ne premier entiers
     */
    private void setColorTable() {
    	ColorTable ct = new ColorTable();
	ct.associer(0, Color.BLUE);
	ct.associer(1, Color.RED);
	ct.associer(2, Color.YELLOW);
	ct.associer(3, Color.GREEN);
	ct.associer(4, Color.ORANGE);
	ct.associer(5, Color.MAGENTA);
	ct.associer(6, Color.PINK);
	ct.associer(7, Color.BLACK);
	ct.associer(8, Color.GRAY);
	ct.associer(9, Color.DARK_GRAY);
	ct.associer(10, Color.LIGHT_GRAY);
	this.colorTable = ct;
	System.out.println(this.colorTable.toString());
    }
    private ColorTable getColorTable(){
    	return this.colorTable;
    }

    /**
     * Fonction qui affiche avec la bonne couleur les Cellules dans la fenêtre graphique
     */
    public void affiche() {
        Color c = new Color(0,0,0);
        for(int i=0; i<(this.getPlateau().getNbL()); i++){
            for(int k=0; k<(getPlateau().getNbC()); k++){
                c = this.getColorTable().obtenirColor(this.getPlateau().getCellule(i, k));
                this.getGUIPlateau().addGraphicalElement( new Rectangle(tailleCellule*k+tailleCellule/2,tailleCellule*i+tailleCellule/2, c, c, this.getTailleCellule()) );
            }
        }
    }
}
