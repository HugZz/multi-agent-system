import gui.*;
import java.awt.Color;
/**
 * Classe test du " jeu de la vie de conway"
 * pour l'executer, lancer : " make exeTestGUICellules ARG1=(int) ARG2=(int) ARG3=(int) " avec : 
 * arg1 = (int) nombre de lignes souhaitées
 * arg2 = (int) nombre de colonnes souhaitées
 * arg3 = (int) pourcentage de case vivante à la création de la grille
 */
public class TestGUICellules {
    public static void main (String [] args ){
        int nbLignes = 150;
        int nbColonnes = 250;
	int hauteur = 750 ;
	int largeur = 1000;
        int d = 0;
            
        GUISimulator window = new GUISimulator(largeur,hauteur, Color.BLUE);
        CellulesSimulator jvc = null;

        //if (args.length > 2) {
        if (args.length == 1) {
            d = Integer.parseInt(args[0]);
            //nbLignes = Integer.parseInt(args[1]); 
            //nbColonnes = Integer.parseInt(args[2]);
            jvc = new CellulesSimulator(nbLignes, nbColonnes , d, window); 
        }
        else {
            //System.out.println("Veuillez donner en entrée de l'exe : le nombre de ligne (int) \n le nombre de colonne (int) \n le pourcentage de remplissage (int)"); 
            System.out.println("Veuillez donner en entrée de l'exe :le pourcentage de remplissage de cases vivantes (int) [0..100]\n"); 
	    return;
        }

        window.setSimulable(jvc);
    }
}
