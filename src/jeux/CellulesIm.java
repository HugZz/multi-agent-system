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

}
