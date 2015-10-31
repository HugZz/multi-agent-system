import java.awt.Point;
import java.util.Random;
/**
 * Cette classe permet de créer une grille contenant une valeur (int) correspondant à sont etat
 * Ce que contient chaque cellule peut être afficher textuelement
 * Mais peut aussi bien faire l'objet d'une simulation graphique grace à la classe CellulesSimulator
 * @see CellulesSimulator
 */
public class Cellules2 {
    private int [][] grille;
    private int [][] tmpGrille;
    private int nbL;
    private int nbC;
    private int nbEtats;
/**
 * Constructeur de Cellules : créer une grille de n*m cellules, instancie les attribues nbL à n et nbC à m, et affecte à chaque cellule 1 état parmi ne 
 * @param n
 *      nbr de lignes
 * @param m
 *      nbr de colonnes
 * @param ne
 *      nbr de d'état que peut prendre une cellule    (1 < ne <=10)
 * @trows Attention 1 < ne <= 10
 */
    public Cellules2(int n, int m, int ne){ 
        if (n<=0) {
            throw new RuntimeException("Attention le nombre de ligne doit être positif !!!");
        }
        if (m<=0) {
            throw new RuntimeException("Attention le nombre de colonnes doit être positif");
        }
        if (ne<=1 || ne>10 ) {
            throw new RuntimeException("Attention le nombre d'état peut être uniquement dans ]1:10] !!!");
        }
        int[][] g = new int [n][];
        for(int i=0; i<n; i++){
            g[i] = new int[m];
        }
        int[][] tmpG = new int [n][];
        for(int i=0; i<n; i++){
            tmpG[i] = new int[m];
        }
        this.setNbL(n);
        this.setNbC(m);
        this.setNbEtats(ne);
        this.setGrille(g);
        this.setTmpGrille(tmpG);
        this.reInit();
    }
    /**
     * Adder
     * @param n
     *      nbr de lignes à créer
     */
    public void setNbL(int n){
        this.nbL = n;
    }
    /**
     * Adder
     * @param m
     *      nbr de col à créer
     */
    public void setNbC(int m){
        this.nbC = m;
    }
    /**
     * Adder : de TmpGrille
     * @param g
     *      Tableau 2D de int
     */
    public void setGrille(int [][] g){
        this.grille = g;
    }
    /**
     * Adder: de grille
     * @param g
     *      Tableau 2D de boolean
     */
    public void setTmpGrille(int[][] tmpG){
        this.tmpGrille = tmpG;
    }
    /**
     *Getter à l'attribut nbL de Cellules
     */
    public int getNbL(){
        return this.nbL;
    }
    /**
     *Getter à l'attribut nbC de Cellules
     */
    public int getNbC(){
        return this.nbC;
    }
    /**
     *Accesseur à l'attribut grille de Cellules
     */
    public int[][] getGrille(){
        return this.grille;
    }
    /**
     *Accesseur à l'attribut TmGgrille de Cellules
     */
    public int[][] getTmpGrille(){
        return this.tmpGrille;
    }
    /**
     * Adder du nombre d'état possible par case
     * @param ne
     *      nombre d'état par possible par cellule 
     */
    public void setNbEtats(int ne){
        this.nbEtats = ne;
    }
    /**
     *Getter à l'attribut nbEtat de Cellules
     */
    public int getNbEtats(){
        return this.nbEtats;
    }
         /**
     * Changer l'état de la cellule (i,k) de la grille principale à e 
     * @param i
     *      Numero de la ligne dont on desire connaitre l'état
     * @param k
     *      Numero de la colonne dont on desire connaitre l'état
     * @param e
     *      Nouvelle état de la cellule (i,k)
     */
    public void setCellule (int i, int k, int e) {
        this.getGrille()[i][k] = e;
    }
    /**
     * Changer l'état de la cellule (i,k) de la grille temporaire à e
     * @param i
     *      Numero de la ligne dont on desire connaitre l'état
     * @param k
     *      Numero de la colonne dont on desire connaitre l'état
     * @param e
     *      Nouvelle état de la cellule (i,k)
     */
    public void setTmpCellule (int i, int k, int e) {
        this.getTmpGrille()[i][k] = e;
    }
    /**
     * Donne l'état de la cellule (i,k) de la grille temporaire 
     * @param i
     *      Numero de la ligne dont on desire connaitre l'état
     * @param k
     *      Numero de la colonne dont on desire connaitre l'état
     */
    public int getTmpCellule(int i,int k) {
        return (this.getTmpGrille()[i][k]); 
    }
    /**
     * Donne l'état de la cellule (i,k) de la grille principale
     * @param i
     *      Numero de la ligne dont on desire connaitre l'état
     * @param k
     *      Numero de la collone dont on desire connaitre l'état
     */
    public int getCellule(int i,int k) {
        return (this.getGrille()[i][k]); 
    }
    /**
     * Donne ne nombre de voisin à l'état '(e+1)%nbEtat' de la cellule (i,k) dans l'état 'e'
     * @param i
     *      ligne de la cellule
     * @param k
     *      colonne de la cellule
     * @return nombre de voisin dans l'état '(e+1)%nbEtat'
     */
    public int nbVoisin(int i, int k){
        int cpt = 0;
        int b = i-1;
        int h = i+1;
        int d = k+1;
        int g = k-1;
        int etat = 0;
        int n = 0;
        etat = this.getCellule(i,k);
        n = this.getNbEtats();
        if( i==0 ) { b = (this.getNbL()-1);}
        if( i==this.getNbL()-1 ) { h = 0;}
        if( k==0) { g = (this.getNbC()-1);}
        if( k==this.getNbC()-1 ) { d = 0;}
        if ( this.getCellule(b,g) == (etat+1)%n ) cpt++;
        if ( this.getCellule(i,g) == (etat+1)%n ) cpt++;
        if ( this.getCellule(h,g) == (etat+1)%n ) cpt++;
        if ( this.getCellule(b,k) == (etat+1)%n ) cpt++;
        if ( this.getCellule(h,k) == (etat+1)%n ) cpt++;
        if ( this.getCellule(b,d) == (etat+1)%n ) cpt++;
        if ( this.getCellule(i,d) == (etat+1)%n ) cpt++;
        if ( this.getCellule(h,d) == (etat+1)%n ) cpt++;
        return cpt;
    }

    /**
     * Méthode qui permet de modifier la valeur de chaque cellule en fonction de la règle de jeu donnée.
     * C'est ce qui est fait entre chaque itération
     */
    public void actualiser (){
        int voisin = 0;
        for(int i=0; i<this.getNbL(); i++){
            for(int k=0;k<this.getNbC(); k++){
                voisin = this.nbVoisin(i,k);
                //Cellule dans l'état e passe à l'état e+1 SSI elle à au moins 3 voisins dans cette état
                //On met ces valeurs dans le tableau tmp pour le moment
                if( voisin >= 3){
                    this.setTmpCellule(i, k, (this.getCellule(i,k)+1)%this.getNbEtats());
                }
                else{
                    this.setTmpCellule(i, k, this.getCellule(i,k));
                }
            }
        }
        //Mtn que la grille tmp contient toutes les mise à jours de grille, on va pouvoir faire la copie de tmp dans grille.
        for(int i=0; i<this.getNbL(); i++){
            for(int k=0;k<this.getNbC(); k++){
                this.setCellule(i, k, this.getTmpCellule(i, k));
            }
        }
    }

    /**
     * Affecte à chaque cellule un état parmi nbEtat de façon aléatoire et équiprobable pour chaque cellule
     */
    public void reInit(){
        int c = 0;
        Random r = new Random();
        for(int i=0; i<this.getNbL(); i++){
            for(int k=0;k<this.getNbC(); k++){
                c = r.nextInt(this.getNbEtats());
                this.setCellule(i, k, c);
                this.setTmpCellule(i, k, c);
            }
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("");
        for (int i=0; i<this.getNbL(); i++){
            for (int k=0; k<this.getNbC(); k++){
                sb.append(this.getCellule(i, k) + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
   }
}
