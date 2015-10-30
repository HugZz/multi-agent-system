public class TestCellules{
    public static void main(String [] args){
        if (args.length == 3) {
            int nbLignes = Integer.parseInt(args[1]); 
            int nbColonnes =  Integer.parseInt(args[2]);
            int d =  Integer.parseInt(args[3]);
        }
        else {
            System.out.println("Veuillez donner en entrée de l'exe : le nombre de ligne (int) \n le nombre de colonne (int) \n le pourcentage de remplissage (int)"); 
        }
        GUISimulator window = new GUISimulator(500, 500, Color.BLUE);
        Cellules c = new Cellules(nbLignes, nbColonnes, d);
        System.out.println(c.toString());
    }
}
