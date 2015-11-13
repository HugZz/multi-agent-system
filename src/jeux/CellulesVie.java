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
