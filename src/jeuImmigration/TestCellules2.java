import gui.*;
import java.awt.Color;
/**
 * Cette classe test la classe Cellules,
 * Elle créer une fenêtre de simulation,
 * et à chaque appui sur next, elle affichera dans le TERMINALE 
 * l'état de chaque cellule de la grille correspond à une couleur
 * pour l'executer, lancer : " make testGUICellules ARG1=(int) ARG2=(int) ARG3=(int) " avec : 
 * arg1 = (int) nombre de lignes souhaitées
 * arg1 = (int) nombre de lignes souhaitées
 * arg2 = (int) nombre de colonnes souhaitées
 * arg3 = (int) nombre d'états possibles pour une cellule
 */
public class TestCellules2 {
    public static void main(String [] args){
        int nbLignes = 0;
        int nbColonnes = 0;
        int ne =0;
        if (args.length == 3) {
            nbLignes = Integer.parseInt(args[0]); 
            nbColonnes =  Integer.parseInt(args[1]);
            ne =  Integer.parseInt(args[2]);
        }
        else {
            System.out.println("Veuillez donner en entrée de l'exe : le nombre de ligne (int) \n le nombre de colonne (int) \n le nombre d'états (int)"); 
            return;
        }
        GUISimulator window = new GUISimulator(500, 500, Color.WHITE);
        Cellules2 c = new Cellules2(nbLignes, nbColonnes, ne);
        System.out.println(c.toString());
    }
}
