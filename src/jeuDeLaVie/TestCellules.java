import gui.*;
import java.awt.Color;
/**
 * Cette classe test la classe Cellules,
 * Elle créer une fenêtre de simulation,
 * et à chaque appuie sur next, elle affichera dans le TERMINALE 
 * l'état de chaque cellule de la grille ''true'' or ''false''
 * pour l'executer, lancer : " make testGUICellules ARG1=(int) ARG2=(int) ARG3=(int) " avec : 
 * arg1 = (int) nombre de lignes souhaitées
 * arg1 = (int) nombre de lignes souhaitées
 * arg2 = (int) nombre de colonnes souhaitées
 * arg3 = (int) pourcentage de case vivante à la création de la grille
 */
public class TestCellules{
    public static void main(String [] args){
        if (args.length == 3) {
            int nbLignes = Integer.parseInt(args[0]); 
            int nbColonnes =  Integer.parseInt(args[1]);
            int d =  Integer.parseInt(args[2]);
        }
        else {
            System.out.println("Veuillez donner en entrée de l'exe : le nombre de ligne (int) \n le nombre de colonne (int) \n le pourcentage de remplissage (int)"); 
        }
        GUISimulator window = new GUISimulator(500, 500, Color.BLUE);
        Cellules c = new Cellules(nbLignes, nbColonnes, d);
        System.out.println(c.toString());
    }
}
