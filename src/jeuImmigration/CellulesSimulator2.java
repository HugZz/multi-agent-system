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
    /**
     *Constructeur par défaut sans paramètre 
     */
    public CellulesSimulator2(){
        this.setPlateau(new Cellules2(50,50,50));
        this.setGUIPlateau(new GUISimulator(500, 500, Color.BLUE));
        this.setTailleCellule(10);
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
    }
    /**
     * Adder de plateau à partir de Cellules
     * @param c
     *      Cellules à créer dans le plateau
     */
    public void setPlateau(Cellules2 c){
        this.plateau = c;
    }
    /**
     * Getter de plateau
     * @return Cellules du plateau
     */
    public Cellules2 getPlateau(){
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
     * Méthodes privé pour donner la taille des cellules 
     */
    private void setTailleCellule(int t){
        this.tailleCellule = t;
    }
    /**
     * @return Done la taille graphique des cellules
     */
    public int getTailleCellule(){
        return this.tailleCellule;
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
        this.getPlateau().reInit();
        this.getGUIPlateau().reset();
        this.affiche();
    }
    /**
     * Méthode qui donne une couleur en fonction de l'entrée
     * @param e
     *      Etat de la Cellule
     * @return c
     *      Couleur correspondante à cet état
     */
    public Color getColorEtat(int e){
        if( e == 0 ) { return Color.BLACK;}
        if( e == 1 ) { return Color.BLUE;}
        if( e == 2 ) { return Color.RED;}
        if( e == 3 ) { return Color.YELLOW;}
        if( e == 4 ) { return Color.GREEN;}
        if( e == 5 ) { return Color.PINK;}
        if( e == 6 ) { return Color.GRAY;}
        if( e == 7 ) { return Color.ORANGE;}
        if( e == 8 ) { return Color.MAGENTA;}
        if( e == 9 ) { return Color.DARK_GRAY;}
        if( e == 10) { return Color.LIGHT_GRAY;}
        else {
            throw new RuntimeException("ERREUR(couleur) l'état est forcement dans ]1:10]!!!");
        }
    }
    /**
     * Fonction qui affiche avec la bonne couleur les Cellules dans la fenêtre graphique
     */
    public void affiche() {
        Color c = new Color(0,0,0);
        for(int i=0; i<(this.getPlateau().getNbL()); i++){
            for(int k=0; k<(getPlateau().getNbL()); k++){
                c = this.getColorEtat(this.getPlateau().getCellule(i, k));
                this.getGUIPlateau().addGraphicalElement( new Rectangle(tailleCellule*i+tailleCellule/2,tailleCellule*k+tailleCellule/2, c, c, this.getTailleCellule()) );
            }
        }
    }
}
