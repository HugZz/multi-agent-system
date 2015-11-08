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
public class TestHabitations {
    public static void main(String [] args){
        int nbLignes = 50;
        int nbColonnes = 50;
        int ne =2;
        int k = 7;
        if (args.length == 4) {
            nbLignes = Integer.parseInt(args[0]); 
            nbColonnes = Integer.parseInt(args[1]);
            ne = Integer.parseInt(args[2]);
            k = Integer.parseInt(args[3]);
        }
        else {
            System.out.println("Veuillez donner en entrée de l'exe : le nombre de ligne (int) \n le nombre de colonne (int) \n le nombre d'états (int) \n le paramètre de ségregation (int)\n"); 
        }
        //GUISimulator window = new GUISimulator(500, 500, Color.WHITE);
        Habitations h = new Habitations(nbLignes, nbColonnes, ne, k);
        h.reInit();
        System.out.println(h.toString());
        h.actualiser();
        System.out.println(h.toString());
        h.actualiser();
        System.out.println(h.toString());
    }
}
