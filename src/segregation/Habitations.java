import java.awt.Point;
import java.util.Random;
import java.util.LinkedList;
/**
 * Cette classe permet de créer une grille dont chaque habitation contient une valeur (int) correspondant à sa couleur
 * Une habitation de couleur = -1 signifie qu'elle est vacante
 * Ce que contient chaque cellule peut être affiché textuellement
 * Mais peut aussi bien faire l'objet d'une simulation graphique grace à la classe HabitationSimulator
 * @see HabitationsSimulator
 */
public class Habitations {
    private int [][] grille;
    private int [][] tmpGrille;
    private int nbL;
    private int nbC;
    private int nbEtats;
    private int k;
    private LinkedList<Point> vacants;
/**
 * Constructeur de Habitations : créer une grille de n*m cellules, instancie les attribues nbL à n et nbC à m, et affecte à chaque cellule 1 état parmi ne 
 * @param n
 *      nbr de lignes
 * @param m
 *      nbr de colonnes
 * @param ne
 *      nbr de d'état que peut prendre une cellule    (1 < ne <=10)
 * @trows Attention 1 < ne <= 10
 */
    public Habitations(int n, int m, int ne, int k){ 
        if (n<=0) {
            throw new RuntimeException("Attention le nombre de ligne doit être positif !!!");
        }
        if (m<=0) {
            throw new RuntimeException("Attention le nombre de colonnes doit être positif");
        }
        if (ne<=1 || ne>10 ) {
            throw new RuntimeException("Attention le nombre d'état peut être uniquement dans ]1:10] !!!");
        }
        if (k<0 || k>7 ) {
            throw new RuntimeException("Attention : ségrégation si on a plus de K voisins différents !");
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
        this.setK(k);
        this.setGrille(g);
        this.setTmpGrille(tmpG);
        this.setVac(new LinkedList<Point>());
        this.reInit();
    }
    /**
     * Getter
     * @param vacants
     *      on obtient la liste des vacants
     */
    public LinkedList<Point> getVac(){
        return this.vacants;
    }
    /**
     * Adder
     * @param vacants
     *      on fixe le paramètre vacants
     */
    public void setVac(LinkedList<Point> vacants){
        this.vacants = vacants;
    }
    /**
     * Adder
     * @param k
     *      on fixe le paramètre k
     */
    public void setK(int k){
        this.k = k;
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
     *      Tableau 2D temporaire de int
     */
    public void setTmpGrille(int[][] tmpG){
        this.tmpGrille = tmpG;
    }
    /**
     *Getter à l'attribut k de Cellules
     */
    public int getK(){
        return this.k;
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
     *      nombre d'état possible par cellule 
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
     * Donne le nombre de voisin de couleur différente que la cellule i,k dans l'état e
     * @param i
     *      ligne de la cellule
     * @param k
     *      colonne de la cellule
     * @return nombre de voisin de couleur différente
     */
    public int nbVoisin(int i, int k){
        int cpt = 0;
        int b = i-1;
        int h = i+1;
        int d = k+1;
        int g = k-1;
        int etat = 0;
        etat = this.getCellule(i,k);
        if( i==0 ) { b = (this.getNbL()-1);}
        if( i==this.getNbL()-1 ) { h = 0;}
        if( k==0) { g = (this.getNbC()-1);}
        if( k==this.getNbC()-1 ) { d = 0;}
        if ( this.getCellule(b,g) != etat && this.getCellule(b,g) != -1) cpt++;
        if ( this.getCellule(i,g) != etat && this.getCellule(i,g) != -1) cpt++;
        if ( this.getCellule(h,g) != etat && this.getCellule(h,g) != -1) cpt++;
        if ( this.getCellule(b,k) != etat && this.getCellule(b,k) != -1) cpt++;
        if ( this.getCellule(h,k) != etat && this.getCellule(h,k) != -1) cpt++;
        if ( this.getCellule(b,d) != etat && this.getCellule(b,d) != -1) cpt++;
        if ( this.getCellule(i,d) != etat && this.getCellule(i,d) != -1) cpt++;
        if ( this.getCellule(h,d) != etat && this.getCellule(h,d) != -1) cpt++;
        return cpt;
    }

    /**
     * Méthode qui permet de modifier la valeur de chaque cellule en fonction de la règle de jeu donnée.
     * C'est ce qui est fait entre chaque itération
     */
    public void actualiser (){
        int voisinDiff = 0;
        for(int i=0; i<this.getNbL(); i++){
            for(int k=0;k<this.getNbC(); k++){
                voisinDiff = this.nbVoisin(i,k);

                // Si l'habitation de couleur considérée ( != -1 ) a plus de K voisins
                // différents, elle déménage.
                if(voisinDiff > this.getK() && this.getCellule(i, k) != -1) {
                    //System.out.printf("Ségrégation !\n");
                    //System.out.printf("à déménager : i = %d, k = %d\n", i, k);
                    // L'habitation devient vacante sur la grille.
                    this.setTmpCellule(i, k, -1);
                    // On prend le premier élément de la liste des habitations vacantes,
                    // et on déplace l'habitation.
                    //System.out.println("Etat des vacants avant le pop : \n" + this.getVac());
                    Point nlleMaison = new Point(this.getVac().pop());
                    //System.out.printf("à emménager : i = %d, k = %d\n", (int)nlleMaison.getX(), (int)nlleMaison.getY());
                    this.setTmpCellule((int)nlleMaison.getX(), (int)nlleMaison.getY(), this.getCellule(i,k));
                    // On ajoute l'habitation à la liste chainée.
                    //System.out.println("Etat des vacants après le pop : \n" + this.getVac());
                    this.getVac().add(new Point(i, k));
                    //System.out.println("Etat des vacants après le add : \n" + this.getVac());
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
        int nbVac = 0;
        this.getVac().clear();
        Random r = new Random();
        for(int i=0; i<this.getNbL(); i++){
            for(int k=0;k<this.getNbC(); k++){
                // On met des cases vacantes par défaut.
                // On met 1/(10*k) d'habitations vacantes (empirique).
                if (nbVac < (this.getNbL() * this.getNbC()) / (5 * (this.getK() + 1))) {
                    this.setCellule(i, k, -1);
                    this.setTmpCellule(i, k, -1);
                    this.getVac().add(new Point(i, k));
                    nbVac++;
                }
                else {
                    c = r.nextInt(this.getNbEtats());
                    this.setCellule(i, k, c);
                    this.setTmpCellule(i, k, c);
                }
            }
        }
        //System.out.println("Etat des vacants après l'init : \n" + this.getVac());
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
