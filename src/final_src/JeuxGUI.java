import gui.*;
import java.awt.Color;
import java.util.Scanner;
/**
 * Classe test des jeux
 * pour l'executer, lancer : " make exeJeuxGUI" 
 * Il vous sera ensuite demandé d'entrer le nom du jeu voulu et la valeur des paramètres pour ce jeu.
 * Jeux disponible : 
 *	-jeu de la vie de Conway : entrer "jdv"
 *	-jeu de l'immigration : entrer "jim"
 *	-jeu de la ségrégation : entrer "jseg"
 *	-jeu de boids : entrer "jb"
 */
public class JeuxGUI {
    public static void main (String [] args ){
        int nbLignes = 150;
        int nbColonnes = 250;
        int largeur = 1000;
        int hauteur = 750;
		int tailleCellule = 5;

		String nomJeu = new String("");
		Scanner sc = new Scanner(System.in);

		System.out.println("Bienvenue à vous, nous vous proposons plusieurs jeux :");
		System.out.println("Pour lancer le Jeu de la Vie de Conway entrer 'jdv'");
		System.out.println("Pour lancer le Jeu de l'Immigration entrer 'jim'");
		System.out.println("Pour lancer le Jeu de la Ségrégation entrer 'jseg'");
		System.out.println("Pour lancer le Jeu de Boids entrer 'jboids'");
		System.out.println("Pour lancer le Jeu de Balles entrer 'jballes'");
		System.out.println("Pour quitter entrer 'exit'");

		nomJeu = sc.nextLine();
		switch (nomJeu){
			case "jdv":
				System.out.println("\nVous avez choisi de lancer le jeu: Jeu de la Vie de Conway");
				System.out.println("Entrer le pourcentage de cases Vivantes à l'initialisation [0..100]");
				int p = sc.nextInt();
				while ( d<0 && d>100) {
					System.out.println("Veuillez entrer un (int) entre [0..100]\n"); 
					p = sc.nextInt();
				}
				GUISimulator window = new GUISimulator(largeur,hauteur, Color.BLUE);
				Cellules c = new CellulesVie(nbLignes, nbColonnes , p);
				CellulesEvent ce = new CellulesEvent(c, 1, new EventManager() );
				CellulesSimulator jdv = new CellulesSimulator(	c,
																tailleCellule,
																window,
																ce ); 
				window.setSimulable(jdv);
				break;
			case "jim":
				System.out.println("\nVous avez choisi de lancer le jeu: Jeu de l'Immigration");
				System.out.println("Entrer le nombre d'état par cellule (int) [1..10]");
				int ne = sc.nextInt();
				while ( ne<1 && ne>10) {
					System.out.println("Veuillez entrer un (int) entre [1..10]"); 
					ne = sc.nextInt();
				}
				GUISimulator window = new GUISimulator(largeur, hauteur, Color.WHITE);
				Cellules c = new CellulesIm(nbLignes, nbColonnes , ne);
				CellulesEvent ce = new CellulesEvent(c, 1, new EventManager() );
				CellulesSimulator jdv = new CellulesSimulator(	c,
																tailleCellule,
																window,
																ce ); 
				CellulesSimulator jim = new CellulesSimulator2(nbLignes, nbColonnes ,tailleCellule, ne, window); 
				window.setSimulable(jim);
				break;

			case "jseg":
				System.out.println("\nVous avez choisi de lancer le jeu: Jeu de la Ségrégation");
				System.out.println("Entrer le nombre d'état par cellule (int) [1..10]");
				int ne = sc.nextInt();
				while ( ne<1 && ne>10) {
					System.out.println("Veuillez entrer un (int) entre [1..10]"); 
					ne = sc.nextInt();
				}
				System.out.println("Entrer le facteur de segregation (int) [1..8]"); 
				int k = sc.nextInt();
				while ( k<1 && k>9) {
					System.out.println("Veuillez entrer un (int) entre [1..8]"); 
					k = sc.nextInt();
				}
				GUISimulator window = new GUISimulator(largeur, hauteur, Color.WHITE);
				Cellules c = new CellulesVie(nbLignes, nbColonnes , ne, k);
				CellulesEvent ce = new CellulesEvent(c, 1, new EventManager() );
				CellulesSimulator jdv = new CellulesSimulator(	c,
																tailleCellule,
																window,
																ce ); 
				HabitationsSimulator jseg = new HabitationsSimulator(nbLignes, nbColonnes,tailleCellule, ne, k, window); 
				window.setSimulable(jseg);
				break;

			case "jboids":
				System.out.println("\nVous avez choisi de lancer le jeu: Jeu de Boids");
				System.out.println("Entrer le nombre de Groupe de Boids (int) [1..10]"); 
				int nbGroupe = sc.nextInt();
				while ( nbGroupe<1 && nbGroupe>10) {
					System.out.println("Veuillez entrer un (int) entre [1..10]"); 
					ne = sc.nextInt();
				}
				GUISimulator window = new GUISimulator(largeur, hauteur, Color.WHITE);
				Boids[] groupesBoids = new Boids[ne];
				int[] delai = new int[ne];
				BBSimulator[] b = new BBSimulator[ne];
				for (int i=0;i<nbGroupe ;i++ ) {
					System.out.println("Entrer le nombre de Boids (int) [1..500] pour le  groupe " + i); 
					int ne = sc.nextInt();
					while ( ne<1 && ne>500) {
						System.out.println("Veuillez entrer un (int) entre [1..500]"); 
						ne = sc.nextInt();
					}
					System.out.println("Entrer la réactivité des Boids (int) [1..20] pour le  groupe " + i); 
					int delai[i] = sc.nextInt();
					while ( delai[i]<1 && delai[i]>20) {
						System.out.println("Veuillez entrer un (int) entre [1..20]"); 
						delai[i] = sc.nextInt();
					}
					Boids groupesBoids[i] = new Boids(ne);
					BBEvent boidsEvents = new BBEvent(groupesBoids[i], 1, new EventManager(), delai[i]);

					BBSimulator b[i] = new BBSimulator(	window,
														groupesBoids[i],
														boidsEvents ); 
					//b.getBalls()[0].setParameters(4, 10, 4, 15, 100);
					window.setSimulable(b[i]);
				}

				break;

			case "jballes":
				System.out.println("\nVous avez choisi de lancer le jeu: Jeu de Balles");
				System.out.println("Entrer le nombre de Groupe de Balles (int) [1..10]"); 
				int nbGroupe = sc.nextInt();
				while ( nbGroupe<1 && nbGroupe>10) {
					System.out.println("Veuillez entrer un (int) entre [1..10]"); 
					ne = sc.nextInt();
				}
				GUISimulator window = new GUISimulator(largeur, hauteur, Color.WHITE);
				Boids[] groupesBalles = new Balls[ne];
				int[] delai = new int[ne];
				BBSimulator[] b = new BBSimulator[ne];
				for (int i=0;i<nbGroupe ;i++ ) {
					System.out.println("Entrer le nombre de Balles (int) [1..50] pour le  groupe " + i); 
					int ne = sc.nextInt();
					while ( ne<1 && ne>50) {
						System.out.println("Veuillez entrer un (int) entre [1..50]"); 
						ne = sc.nextInt();
					}
					System.out.println("Entrer la réactivité des Boids (int) [1..20] pour le  groupe " + i); 
					int delai[i] = sc.nextInt();
					while ( delai[i]<1 && delai[i]>20) {
						System.out.println("Veuillez entrer un (int) entre [1..20]"); 
						delai[i] = sc.nextInt();
					}
					Balls groupesBalles[i] = new Balls(ne);
					BBEvent boidsEvents = new BBEvent(groupesBalles[i], 1, new EventManager(), delai[i]);

					BBSimulator b[i] = new BBSimulator(	window,
														groupesBalles[i],
														boidsEvents ); 
					//b.getBalls()[0].setParameters(4, 10, 4, 15, 100);
					window.setSimulable(b[i]);
				}
	
				break;
			case "exit":
				System.out.println("\nVous avez demander de quitter, Bye Bye" + i); 
				return;
				break;
			default :
				System.out.println("\nVous n'avez pas entré le nom d'un jeu valide... Relancer le jeu" + i); 
				return;
				break;
		}
    }
}
