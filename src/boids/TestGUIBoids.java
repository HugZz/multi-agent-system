import gui.*;
import java.awt.Color;
/**
 * Classe test du " jeu de la vie de conway"
 * pour l'executer, lancer : " make exeTestGUICellules ARG1=(int) ARG2=(int) ARG3=(int) " avec : 
 * arg1 = (int) nombre de lignes souhaitées
 * arg2 = (int) nombre de colonnes souhaitées
 * arg3 = (int) pourcentage de case vivante à la création de la grille
 */
public class TestGUIBoids {
    public static void main (String [] args ){
        int largeur = 1000;
        int hauteur = 750;
        int ne = 0;
        if (args.length == 1) {
            ne = Integer.parseInt(args[0]);
        }
        else {
            System.out.println("Veuillez donner en entrée de l'exe :\n ARG1= le nombre d'états (int) [0..10]"); 
        }
        GUISimulator window = new GUISimulator(largeur, hauteur, Color.WHITE);
        
        BoidsSimulator b = new BoidsSimulator(ne,  window); 

        window.setSimulable(b);
    }
}
