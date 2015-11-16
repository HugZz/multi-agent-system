import java.awt.Point;

import java.util.Random;
import java.util.ArrayList;

/**
 * Classe fille de Cellules qui va implementer les deux méthodes actualiser() et reInit().
 */
public class CellulesSeg extends Cellules {
    /**
     * Paramètre de ségrégation.
     */
	private int k;
    /**
     * Liste chainée des logements vacants.
     */
    private ArrayList<Point> vacants;

    /**
     * Constructeur : Initialise les paramètres du jeu et la liste chainée.
     * @param n Nombre de lignes
     * @param m Nombre de colonnes
     * @param ne Nombre d'état par cellules
     * @param k Facteur de ségrégation
     */
	public CellulesSeg(int n, int m, int ne, int k) {
		super(n,m,ne);
		this.setK(k);
        this.setVac(new ArrayList<Point>());
		this.reInit();
	}
	
    /**
     * Modifieur du paramètre de ségrégation.
     * @param k Nouvelle ségrégation [1..8]
     */
	private void setK (int k) {
		if (k<0 || k>8) {
			throw new RuntimeException("Attention le parametre de segregation doit être entre [1..8] !!!");
		}
		this.k = k;
	}
    /**
     * Accesseur du paramètre de ségrégation.
     * @return Facteur de ségrégation
     */
	private int  getK () {
		return this.k;
	}
	/**
     * Getter
     * @param vacants
     *      on obtient la liste des vacants
     */
    public ArrayList<Point> getVac(){
        return this.vacants;
    }
	/**
     * Adder
     * @param vacants
     *      on fixe le paramètre vacants
     */
    public void setVac(ArrayList<Point> vacants){
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
		if (i<0 || i>super.getNbL() ) {
			throw new RuntimeException("Attention cette ligne existe pas !");
		}
		if (k<0 || k>super.getNbC()) {
			throw new RuntimeException("Attention cette colonne n'existe pas !");
		}
		else{
			int cpt = 0;
			int b = i-1;
			int h = i+1;
			int d = k+1;
			int g = k-1;
			int etat = 0;
			etat = this.getCellule(i,k);
			// Le plateau est circulaire.
			if( i==0 ) { b = (this.getNbL()-1);}
			if( i==this.getNbL()-1 ) { h = 0;}
			if( k==0) { g = (this.getNbC()-1);}
			if( k==this.getNbC()-1 ) { d = 0;}
			// L'état vacant n'est pas compté comme un nouvel état.
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
    }

	/**
	 * Méthode qui permet de modifier la valeur de chaque cellule en fonction de la règle du jeu de ségrégation
	 * c'est ce qui est fait entre chaque itération
	 * Si l'habitation de couleur c ( != -1 ) a plus de K voisins elle démanage et l'habitation devient vacante
	 */
	public void actualiser (){
		int voisinDiff = 0;
		int c = 0;
		Random r = new Random();
		for(int i=0; i<this.getNbL(); i++){
			for(int k=0;k<this.getNbC(); k++){
				voisinDiff = this.nbVoisin(i,k);

				if(voisinDiff > this.getK() && this.getCellule(i, k) != -1) {
					c = r.nextInt(this.getVac().size());
					// L'habitation devient vacante sur la grille.
					this.setTmpCellule(i, k, -1);
					// On prend le premier élément de la liste des habitations vacantes,
					// et on déplace l'habitation.
					Point nlleMaison = new Point(this.getVac().remove(c));
					this.setTmpCellule((int)nlleMaison.getX(), (int)nlleMaison.getY(), this.getCellule(i,k));
					// On ajoute l'habitation à la liste chainée.
					this.getVac().add(new Point(i, k));
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
	 * Affecte à chaque cellule un état parmi nbEtat de façon aléatoire et équiprobable
	 */
	public void reInit(){
		int c = 0;
		int nbVac = 0;
		this.getVac().clear();
		Random r = new Random();
		for(int i=0; i<this.getNbL(); i++){
			for(int k=0;k<this.getNbC(); k++){
				// On met des cases vacantes par défaut, le nombre de ces cases est
                // un nombre dépendant de n, m et k.
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
	}
}
