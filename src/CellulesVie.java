	/**
	 * Cette classe implement la classe abstraite Cellules
	 * Pour le jeu de la vie de Conway
	 */
 public class CellulesVie extends Cellules {
	/**
	 * Pourcentage de cases vivantes à la création
	 */
	private static int pourcentage;
	/**
	 * Constructeur de la grille du jeu de la vie
	 */
	public CellulesVie (int n, int m, int p) {
		super(n,m,2);
		this.setPourcentage(p);
		this.reInit();
	}
	
	/**
	 * Adder de pourcentage 
	 * @param p Pourcentage entre [0..100]
	 */
	private void setPourcentage(int p) {
		if (p<0 || p>100) {
			throw new RuntimeException("Attention Pourcentage doit être entre  [1..100]");
		}
		else{
			this.pourcentage = p;
		}
	}
	private int getPourcentage() {
		return this.pourcentage;
	}
	/**
     * Donne le nombre de voisin à la cellule (i,k)
     * @param i
     *      ligne de la cellule
     * @param k
     *      colonne de la cellule
     */
    public int nbVoisin(int i, int k){
		if (i<0 || i>this.getNbL() ) {
			throw new RuntimeException("Attention la ligne n'existe pas !");
		}
		else if (k<0 || k>this.getNbC() ) {
			throw new RuntimeException("Attention la colonne n'existe pas !");
		}
		else{
			int cpt = 0;
			int b = i-1;
			int h = i+1;
			int d = k+1;
			int g = k-1;
			if( i==0 ) { b = (this.getNbL()-1);}
			if( i==this.getNbL()-1 ) { h = 0;}
			if( k==0) { g = (this.getNbC()-1);}
			if( k==this.getNbC()-1 ) { d = 0;}
			if ( this.getCellule(b,g) == 1 ) cpt++;
			if ( this.getCellule(i,g) == 1 ) cpt++;
			if ( this.getCellule(h,g) == 1 ) cpt++;
			if ( this.getCellule(b,k) == 1 ) cpt++;
			if ( this.getCellule(h,k) == 1 ) cpt++;
			if ( this.getCellule(b,d) == 1 ) cpt++;
			if ( this.getCellule(i,d) == 1 ) cpt++;
			if ( this.getCellule(h,d) == 1 ) cpt++;
			return cpt;
		}
	}
	/**
	 * Méthode qui permet de modifier la valeur de chaque cellule en fonction de la règle du jeu de la vie
	 * c'est ce qui est fait entre chaque itération
	 * Cellule vivant reste en vie SSI elle a deux ou trois voisin, sinon meurt
	 * Si la cellule est morte elle née SSI elle a exactement 3 voisins
	 */
	public void actualiser (){
		int voisin = 0;
		for(int i=0; i<this.getNbL(); i++){
			for(int k=0;k<this.getNbC(); k++){
				voisin = this.nbVoisin(i,k);
				//On met ces valeurs dans le tableau tmp pour le moment
				if( getCellule(i,k) == 1 ){
					if(voisin==2 || voisin==3){
						this.setTmpCellule(i,k,1);
					}
					else {
						this.setTmpCellule(i,k,0);
					}
				}
				else {
					if(voisin == 3){
						this.setTmpCellule(i,k,1);
					} 
					else{
						this.setTmpCellule(i,k,0);
					}
				}
			}
		}
		//Mtn que la grille tmp contient toutes les mises à jours de grille, on va pouvoir faire la copie de tmp dans grille.
		for(int i=0; i<this.getNbL(); i++){
			for(int k=0;k<this.getNbC(); k++){
				this.setCellule(i, k, this.getTmpCellule(i, k));
			}
		}
	}

	/**
	 * Remplie la grille aléatoirement avec des 0 et des 1
	 */
	public void reInit(){
		int b = 1;
		double d = this.getPourcentage();
		d = d/100;
		for(int i=0; i<this.getNbL(); i++){
			for(int k=0;k<this.getNbC(); k++){
				b = ((Math.random()) <= d)?1:0;
				this.setCellule(i, k, b);
				this.setTmpCellule(i, k, b);
			}
		}
	}
 } 
