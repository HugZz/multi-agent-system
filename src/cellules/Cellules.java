import java.awt.Point;
public class Cellules {
    private boolean [][] grille;
    private boolean [][] tmpGrille;
    private int nbL;
    private int nbC;
/**
 * Constructeur
 * @param n
 *      nbr de lignes
 * @param m
 *      nbr de colonnes
 */
    public Cellules(int n, int m){ 
        boolean[][] g = new boolean [n][];
        for(int i=0; i<n; i++){
            g[i] = new boolean[m];
        }
        this.setNbL(n);
        this.setNbC(m);
        this.setGrille(g);
        this.setTmpGrille(g);
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
     *      Tableau 2D de boolean
     */
    public void setGrille(boolean [][] g){
        this.grille = g;
    }
    /**
     * Adder: de grille
     * @param g
     *      Tableau 2D de boolean
     */
    public void setTmpGrille(boolean[][] g){
        this.tmpGrille = g;
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
    public boolean[][] getGrille(){
        return this.grille;
    }
    /**
     *Accesseur à l'attribut TmGgrille de Cellules
     */
    public boolean[][] getTmpGrille(){
        return this.tmpGrille;
    }

    /**
     * Changer l'état de la cellule (i,k) de la grille principale  à e 
     * @param i
     *      Numero de la ligne dont on desire connaitre l'état
     * @param k
     *      Numero de la colonne dont on desire connaitre l'état
     * @param e
     *      Nouvelle état de la cellule (i,k)
     */
    public void setCellule (int i, int k, boolean e) {
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
    public void setTmpCellule (int i, int k, boolean e) {
        this.getTmpGrille()[i][k] = e;
    }
    /**
     * Donne la valeur de la cellule (i,k) de la grille temporaire 
     * @param i
     *      Numero de la ligne dont on desire connaitre l'état
     * @param k
     *      Numero de la colonne dont on desire connaitre l'état
     */
    public boolean getTmpCellule(int i,int k) {
        return (this.getTmpGrille()[i][k]); 
    }
    /**
     * Donne la valeur de la cellule (i,k) de la grille principale
     * @param i
     *      Numero de la ligne dont on desire connaitre l'état
     * @param k
     *      Numero de la collone dont on desire connaitre l'état
     */
    public boolean getCellule(int i,int k) {
        return (this.getGrille()[i][k]); 
    }
    /**
     * Donne ne nombre de voisin à la cellule (i,k)
     * @param i
     *      ligne de la cellule
     * @param k
     *      colonne de la cellule
     */
    public int nbVoisin(int i, int k){
        int cpt = 0;
        int b = i-1;
        int h = i+1;
        int d = k+1;
        int g = k-1;
        if( i==0 ) { b = (this.getNbL()-1);}
        if( i==this.getNbL()-1 ) { h = 0;}
        if( k==0) { g = (this.getNbC()-1);}
        if( k==this.getNbC()-1 ) { d = 0;}
        if ( this.getCellule(b,g) ) cpt++;
        if ( this.getCellule(i,g) ) cpt++;
        if ( this.getCellule(h,g) ) cpt++;
        if ( this.getCellule(b,k) ) cpt++;
        if ( this.getCellule(h,k) ) cpt++;
        if ( this.getCellule(b,d) ) cpt++;
        if ( this.getCellule(i,d) ) cpt++;
        if ( this.getCellule(h,d) ) cpt++;
        return cpt;
    }

    /**
     */
    public void actualiser (){
        int voisin = 0;
        for(int i=0; i<this.getNbL(); i++){
            for(int k=0;k<this.getNbC(); k++){
                voisin = this.nbVoisin(i,k);
                //Cellule vivant reste en vie SSI elle a deux ou trois voisin, sinon meurt
                //On met ces valeurs dans le tableau tmp pour le moment
                if( getCellule(i,k) ){
                    if(voisin==2 || voisin==3){
                        this.setTmpCellule(i,k,true);
                    }
                    else {
                        this.setTmpCellule(i,k,false);
                    }
                }
                //Si la cellule est morte elle née SSI elle a exactement 3 voisins
                else {
                    if(voisin == 3){
                        this.setTmpCellule(i,k,true);
                    } 
                    else{
                        this.setTmpCellule(i,k,false);
                    }
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
     * Remplie la grille aléatoirement avec des true et des false
     */
    public void reInit(){
        boolean b = true;
        for(int i=0; i<this.getNbL(); i++){
            for(int k=0;k<this.getNbC(); k++){
                b = (boolean)((Math.random())>=0.5);
                this.setCellule(i, k, b);
                this.setTmpCellule(i, k, b);
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
