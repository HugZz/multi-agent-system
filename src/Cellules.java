import java.awt.Point;
import java.util.Random;
/**
 * Cette classe permet de créer une grille de Cellules contenant une valeur (int) correspondant à son état
 * L'état d'une Cellules peut être afficher textuellement
 * Mais peut aussi bien faire l'objet d'une simulation graphique grâce à la classe CellulesSimulator
 * Cette classe est abstraite pour que l'on puisse uniquement créer certain type de Cellule en fonction du jeu voulu
 * @see CellulesVie
 * @see CellulesIm
 * @see CellulesSeg
 * @see CellulesSimulator
 */
abstract public class Cellules {
	private int [][] grille;
	private int [][] tmpGrille;
	private int nbL;
	private int nbC;
	private int nbEtats;
	/**
	 * Constructeur: créer une grille de n*m Cellules, instancie les attribues nbL à n et nbC à m et définit le nombre d'état possible pour chaque cellule 
	 * @param n nbr de lignes [1..500]
	 * @param m nbr de colonnes [0..500]
	 * @param ne  nbr de d'état que peut prendre une cellule    [2..10]
	 */
	public Cellules(int n, int m, int ne){ 
		this.setNbL(n);
		this.setNbC(m);
		this.setNbEtats(ne);
		int[][] g = new int [getNbL()][];
		int[][] tmpG = new int [n][];
		for(int i=0; i<getNbL(); i++){
			g[i] = new int[getNbC()];
			tmpG[i] = new int[getNbC()];
		}
		this.setGrille(g);
		this.setTmpGrille(tmpG);
	}
	/**
	 * Adder
	 * @param n nbr de lignes à créer [1..500]
	 */
	public void setNbL(int n){
		if (n<=0 || n>500) {
			throw new RuntimeException("Attention le nombre de ligne doit être entre [1..500] !!!");
		}
		this.nbL = n;
	}
	/**
	 * Adder de nbC
	 * @param m nbr de colonnes à créer [1..500]
	 */
	public void setNbC(int m){
		if (m<=0 || m>500) {
			throw new RuntimeException("Attention le nombre de colonnes doit être entre [1..500]");
		}
		this.nbC = m;
	}
	/**
	 * Adder de TmpGrille
	 * @param g
	 *      Tableau 2D de int
	 */
	public void setGrille(int [][] g){
		this.grille = g;
	}
	/**
	 * Adder de grille
	 * @param g
	 *      Tableau 2D de boolean
	 */
	public void setTmpGrille(int[][] tmpG){
		this.tmpGrille = tmpG;
	}
	/**
	 *	Getter à l'attribut nbL de Cellules
	 */
	public int getNbL(){
		return this.nbL;
	}
	/**
	 *	Getter à l'attribut nbC de Cellules
	 */
	public int getNbC(){
		return this.nbC;
	}
	/**
	 *	Accesseur à l'attribut grille de Cellules
	 */
	public int[][] getGrille(){
		return this.grille;
	}
	/**
	 *	Accesseur à l'attribut TmGgrille de Cellules
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
		if (ne<=1 || ne>10 ) {
			throw new RuntimeException("Attention le nombre d'état peut être uniquement dans [2..10] !!!");
		}
		else{
			this.nbEtats = ne;
		}
	}
	/**
	 *	Getter à l'attribut nbEtat de Cellules
	 */
	public int getNbEtats(){
		return this.nbEtats;
	}
	/**
	 * Changer l'état de la cellule (i,k) de la grille principale à l'état e 
	 * @param i
	 *      Numéro de la ligne dont on désire connaitre l'état
	 * @param k
	 *      Numero de la colonne dont on désire connaitre l'état
	 * @param e
	 *      Nouvelle état de la cellule (i,k) e>1 et e<ne
	 */
	public void setCellule (int i, int k, int e) {
		if (i<1 || i>this.getNbL() ) {
			throw new RuntimeException("Attention la ligne doit être entre [1..nbL] !!!");
		}
		else if (k<1 || k>this.getNbL() ) {
			throw new RuntimeException("Attention la colonne doit être entre [1..nbC] !!!");
		}
		else if (e<2 || e>this.getNbEtats() ) {
			throw new RuntimeException("Attention l'état doit être uniquement dans [2..nbEtats] !!!");
		}
		else{
			this.getGrille()[i][k] = e;
		}
	}
	/**
	 * Donne l'état de la cellule (i,k) de la grille principale
	 * @param i
	 *      Numéro de la ligne dont on désire connaitre l'état [1..nbL]
	 * @param k
	 *      Numéro de la colonne dont on désire connaitre l'état [1..nbC]
	 */
	public int getCellule(int i,int k) {
		if (i<1 || i>this.getNbL() ) {
			throw new RuntimeException("Attention la ligne doit être entre [1..nbL] !!!");
		}
		else if (k<1 || k>this.getNbL() ) {
			throw new RuntimeException("Attention la colonne doit être entre [1..nbC] !!!");
		}
		else{
			return (this.getGrille()[i][k]); 
		}
	}
	/**
	 * Changer l'état de la cellule (i,k) de la grille temporaire à e
	 * @param i
	 *      Numéro de la ligne dont on désire connaitre l'état [1..nbL]
	 * @param k
	 *      Numéro de la colonne dont on désire connaitre l'état [1..nbC]
	 * @param e
	 *      Nouvelle état de la cellule (i,k)
	 */
	public void setTmpCellule (int i, int k, int e) {
		if (i<1 || i>this.getNbL() ) {
			throw new RuntimeException("Attention la ligne doit être entre [1..nbL] !!!");
		}
		else if (k<1 || k>this.getNbL() ) {
			throw new RuntimeException("Attention la colonne doit être entre [1..nbC] !!!");
		}
		else if (e<2 || e>this.getNbEtats() ) {
			throw new RuntimeException("Attention l'état doit être uniquement dans [2..nbEtats] !!!");
		}
		else{
			this.getTmpGrille()[i][k] = e;
		}
	}
	/**
	 * Donne l'état de la cellule (i,k) de la grille temporaire 
	 * @param i
	 *      Numero de la ligne dont on desire connaitre l'état
	 * @param k
	 *      Numero de la colonne dont on desire connaitre l'état
	 */
	public int getTmpCellule(int i,int k) {
		if (i<1 || i>this.getNbL() ) {
			throw new RuntimeException("Attention la ligne doit être entre [1..nbL] !!!");
		}
		else if (k<1 || k>this.getNbL() ) {
			throw new RuntimeException("Attention la colonne doit être entre [1..nbC] !!!");
		}
		else{
			return (this.getTmpGrille()[i][k]); 
		}
	}
	abstract public void actualiser();

	abstract public void reInit();

	@Override
	public String toString(){
		String sb = "";
		for (int i=0; i<this.getNbL(); i++){
			for (int k=0; k<this.getNbC(); k++){
				sb += this.getCellule(i, k) + " ";
			}
			sb += "\n";
		}
		return sb;
	}
}
