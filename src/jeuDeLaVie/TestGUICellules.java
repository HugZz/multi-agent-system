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
            int nbLignes = 0;
            int nbColonnes = 0;
            int d = 0;
        if (args.length > 2) {
            nbLignes = Integer.parseInt(args[0]); 
            nbColonnes = Integer.parseInt(args[1]);
            d = Integer.parseInt(args[2]);
        }
        else {
            System.out.println("Veuillez donner en entrée de l'exe : le nombre de ligne (int) \n le nombre de colonne (int) \n le pourcentage de remplissage (int)"); 
        }
        GUISimulator window = new GUISimulator(500, 500, Color.BLUE);
        
        CellulesSimulator jvc = new CellulesSimulator(nbLignes, nbColonnes , d, window); 

        window.setSimulable(jvc);
    }
}
