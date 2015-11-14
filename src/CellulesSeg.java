import java.awt.Point;
import java.util.Random;
import java.util.LinkedList;

public class CellulesSeg extends Cellules {
	private int k;
    private LinkedList<Point> vacants;

	public CellulesSeg(int n, int m, int ne, int k) {
		super(n,m,ne);
		this.setK(k);
        this.setVac(new LinkedList<Point>());
		this.reInit();
	}
	
	private void setK (int k) {
		this.k = k;
	}
	private int  getK () {
		return this.k;
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
		int c = 0;
		Random r = new Random();
		for(int i=0; i<this.getNbL(); i++){
			for(int k=0;k<this.getNbC(); k++){
				voisinDiff = this.nbVoisin(i,k);

				// Si l'habitation de couleur considérée ( != -1 ) a plus de K voisins
				// différents, elle déménage.
				if(voisinDiff > this.getK() && this.getCellule(i, k) != -1) {
					c = r.nextInt(this.getVac().size());
					//System.out.printf("Ségrégation !\n");
					//System.out.printf("à déménager : i = %d, k = %d\n", i, k);
					// L'habitation devient vacante sur la grille.
					this.setTmpCellule(i, k, -1);
					// On prend le premier élément de la liste des habitations vacantes,
					// et on déplace l'habitation.
					//System.out.println("Etat des vacants avant le pop : \n" + this.getVac());
					Point nlleMaison = new Point(this.getVac().remove(c));
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
}
