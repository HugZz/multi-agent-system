import java.awt.Point;
import java.util.Random;
import java.util.LinkedList;

public class CellulesIm extends Cellules {

	public CellulesIm(int n, int m, int ne){
		super(n,m,ne);
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



}
