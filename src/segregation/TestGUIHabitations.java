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
        int nbLignes = 50;
        int nbColonnes = 50;
        int largeur = 500;
        int hauteur = 500;
        int ne = 2;
        int k = 3;
        if (args.length == 4) {
            nbLignes = Integer.parseInt(args[0]); 
            nbColonnes = Integer.parseInt(args[1]);
            ne = Integer.parseInt(args[2]);
            k = Integer.parseInt(args[3]);
        }
        else {
            System.out.println("Veuillez donner en entrée de l'exe : le nombre de ligne (int) \n le nombre de colonne (int) \n le nombre d'états (int) \n le paramètre de ségregation (int)\n"); 
        }
        GUISimulator window = new GUISimulator(largeur, hauteur, Color.WHITE);
        //window.addGraphicalElement(new Rectangle(0, 90, Color.BLACK, Color.BLACK, 10));
        
        HabitationsSimulator jvc = new HabitationsSimulator(nbColonnes, nbLignes,largeur/nbLignes, ne, k, window); 

        window.setSimulable(jvc);
    }
}
