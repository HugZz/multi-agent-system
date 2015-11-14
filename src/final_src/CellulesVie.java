 public class CellulesVie extends Cellules {
	private static int pourcentage; 

	public CellulesVie (int n, int m, int p) {
		super(n,m,2);
		this.setPourcentage(p);
		this.reInit();
	}

	private void setPourcentage(int p) {
		this.pourcentage = p;
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
	/**
	 *
	 */
	public void actualiser (){
		int voisin = 0;
		for(int i=0; i<this.getNbL(); i++){
			for(int k=0;k<this.getNbC(); k++){
				voisin = this.nbVoisin(i,k);
				//Cellule vivant reste en vie SSI elle a deux ou trois voisin, sinon meurt
				//On met ces valeurs dans le tableau tmp pour le moment
				if( getCellule(i,k) == 1 ){
					if(voisin==2 || voisin==3){
						this.setTmpCellule(i,k,1);
					}
					else {
						this.setTmpCellule(i,k,0);
					}
				}
				//Si la cellule est morte elle née SSI elle a exactement 3 voisins
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
		//Mtn que la grille tmp contient toutes les mise à jours de grille, on va pouvoir faire la copie de tmp dans grille.
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
