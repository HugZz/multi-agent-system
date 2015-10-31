import gui.*;
import java.awt.Color;
/**
 * Classe test du " jeu de la vie de conway"
 * pour l'executer, lancer : " make exeTestGUICellules ARG1=(int) ARG2=(int) ARG3=(int) " avec : 
 * arg1 = (int) nombre de lignes souhaitées
 * arg2 = (int) nombre de colonnes souhaitées
 * arg3 = (int) pourcentage de case vivante à la création de la grille
 */
public class TestGUICellules2 {
    public static void main (String [] args ){
        int nbLignes = 0;
        int nbColonnes = 0;
        int largeur = 500;
        int hauteur = 500;
        int ne = 0;
        if (args.length == 3) {
            nbLignes = Integer.parseInt(args[0]); 
            nbColonnes = Integer.parseInt(args[1]);
            ne = Integer.parseInt(args[2]);
        }
        else {
            System.out.println("Veuillez donner en entrée de l'exe : le nombre de ligne (int) \n le nombre de colonne (int) \n le nombre d'états (int)"); 
        }
        GUISimulator window = new GUISimulator(largeur, hauteur, Color.WHITE);
        
        CellulesSimulator2 jvc = new CellulesSimulator2(nbLignes, nbColonnes ,largeur/nbLignes, ne, window); 

        window.setSimulable(jvc);
    }
}
