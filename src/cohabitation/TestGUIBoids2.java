import gui.*;
import java.awt.Color;
/**
 * Classe test du " jeu de la vie de conway"
 * pour l'executer, lancer : " make exeTestGUICellules ARG1=(int) ARG2=(int) ARG3=(int) " avec : 
 * arg1 = (int) nombre de lignes souhaitées
 * arg2 = (int) nombre de colonnes souhaitées
 * arg3 = (int) pourcentage de case vivante à la création de la grille
 */
public class TestGUIBoids2 {
    public static void main (String [] args ){
        int nbLignes = 50;
        int nbColonnes = 50;
        int largeur = 1000;
        int hauteur = 750;
        int ne = 20;
        int nombreGroupes = 2;
        int[] delai = {1, 2};
        if (args.length == 3) {
            nbLignes = Integer.parseInt(args[0]); 
            nbColonnes = Integer.parseInt(args[1]);
            ne = Integer.parseInt(args[2]);
        }
        else {
            System.out.println("Veuillez donner en entrée de l'exe : le nombre de ligne (int) \n le nombre de colonne (int) \n le nombre d'états (int) \n le nombre de groupes de boids (int)"); 
        }
        GUISimulator window = new GUISimulator(largeur, hauteur, Color.WHITE);
        
        BoidsSimulator b = new BoidsSimulator(ne, nombreGroupes, delai, window); 
        b.getBoids()[1].setParameters(4, 10, 4, 15, 100);

        window.setSimulable(b);
    }
}
