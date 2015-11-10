import gui.*;
import java.awt.Color;
/**
 * Classe test du " jeu de la vie de conway"
 * pour l'executer, lancer : " make exeTestGUICellules ARG1=(int) ARG2=(int) ARG3=(int) " avec : 
 * arg1 = (int) nombre de lignes souhaitées
 * arg2 = (int) nombre de colonnes souhaitées
 * arg3 = (int) pourcentage de case vivante à la création de la grille
 */
public class TestGUIHabitations {
    public static void main (String [] args ){
        int nbLignes = 150;
        int nbColonnes = 250;
	int tailleCellule = 5;
        int largeur = 1000;
        int hauteur = 750;
        int ne = 2;
        int k = 3;
        //if (args.length == 4) {
        if (args.length == 2) {
            ne = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
            //nbLignes = Integer.parseInt(args[2]); 
            //nbColonnes = Integer.parseInt(args[3]);
        }
        else {
            //System.out.println("Veuillez donner en entrée de l'exe : le nombre de ligne (int) \n le nombre de colonne (int) \n le nombre d'états (int) \n le paramètre de ségregation (int)\n"); 
            System.out.println("Veuillez donner en entrée de l'exe :\n ARG1= le nombre d'états (int)[0..10]\n ARG2= le paramètre de ségregation (int)[0..8]\n"); 
            return;
        }
        GUISimulator window = new GUISimulator(largeur, hauteur, Color.WHITE);
        
        HabitationsSimulator jvc = new HabitationsSimulator(nbLignes, nbColonnes,tailleCellule, ne, k, window); 

        window.setSimulable(jvc);
    }
}
