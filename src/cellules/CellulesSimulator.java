import gui.*;
import java.awt.Color;
/**
 * Cette classe est le Simulateur de Cellules
 * Grave à ces méthode vous pourrez jouer au jeu de la vie de Conway
 * @author Lucas Mahieu
 * @version 2.0
 */
public class CellulesSimulator implements Simulable {
    private Cellules plateau;
    private GUISimulator GUIPlateau;
    private static int tailleCellule = 10;
    private static int pourcent;
    /**
     *Constructeur par défaut sans paramètre 
     */
    public CellulesSimulator(){
        this.setPlateau(new Cellules(50,50,50));
        this.setGUIPlateau(new GUISimulator(500, 500, Color.BLUE));
    }
    /**
     *Constructeur de la classe avec paramètre 
     * @param n
     *      nombre de ligne du plateau de simulation
     * @param m
     *      nombre de colonnes du plateau de simulation
     * @param d
     *      Pourcentage de case vivante lors de la creation du simulateur de cellule
     * @param g
     *      Fenêtre graphique qui contiendra graphiquement le plateau
     */ 
    public CellulesSimulator(int n, int m, int d, GUISimulator g){
        this.setPlateau(new Cellules(n,m,d));
        this.setGUIPlateau(g);
        this.setPourcent(d);
    }
    /**
     * Constructeur de la classe par copie
     * @param celSim
     *      Créer un CellulesSimulator à partir de celSim
     * @param g
     *      Fenêtre graphique qui contiendra graphiquement le plateau
     */ 
    public CellulesSimulator(CellulesSimulator celSim,GUISimulator g){
        this.setPlateau(celSim.getPlateau());
        this.setGUIPlateau(g);
    }
    /**
     * Adder de plateau à partir de Cellules
     * @param c
     *      Cellules à créer dans le plateau
     */
    public void setPlateau(Cellules c){
        this.plateau = c;
    }
    /**
     * Getter de plateau
     * @return Cellules du plateau
     */
    public Cellules getPlateau(){
        return this.plateau;
    }
    /**
     * Adder de GUIPlateau
     * @param g
     *      Fenêtre à créer pour contenir le plateau
     */
    public void setGUIPlateau(GUISimulator g){
        this.GUIPlateau = g;
    }
    /**
     * Adder de GUIPlateau
     * @return Attribut GUIPlateau de CellulesSimulator
     */
    public GUISimulator getGUIPlateau(){
       return this.GUIPlateau;
    }
    /**
     * Adder privé du pourcentage
     * @return Valeur du Pourcentage de vivant lors de l'initialisation du plateau
     */
    private int getPourcent(){
       return this.pourcent;
    }
    /**
     * Getter du pourcentage
     * @param d
     *      Pourcentage à transmettre à la classe Cellules pour initialiser d% de cellules vivantes
     */
    private void setPourcent(int d){
       this.pourcent = d;
    }
    /**
     * Implementation de la méthode next() de Simulable
     * Effectue l'itération suivante du jeu
     */
    public void next(){
        this.getPlateau().actualiser();
        this.getGUIPlateau().reset();
        this.affiche();
    }
    /**
     * Implementation de la méthode Restart() de Simulable
     * Re disposer de façon aléatoire toute les cases du plateau de jeux 
     */
    public void restart(){
        this.getPlateau().reInit(this.getPourcent());
        this.getGUIPlateau().reset();
        this.affiche();
    }
    /**
     * Fonction qui affiche avec la bonne couleur les Cellules dans la fenêtre graphique
     * Noir = Mort
     * Blanc = Vivant
     */
    public void affiche(){
        Color c = new Color(0,0,0);
        for(int i=0; i<(this.getPlateau().getNbL()); i++){
            for(int k=0; k<(getPlateau().getNbL()); k++){
                if(this.getPlateau().getCellule(i,k)){
                    c = Color.WHITE;
                }
                else {
                    c = Color.BLACK;
                }
                this.getGUIPlateau().addGraphicalElement( new Rectangle(tailleCellule*i+tailleCellule/2,tailleCellule*k+tailleCellule/2, c, c, tailleCellule) );
            }
        }
    }
}
