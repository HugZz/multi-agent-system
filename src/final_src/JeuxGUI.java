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
        int nbLignes = 100;
        int nbColonnes = 250;
        int largeur = 800;
        int hauteur = 500;
		int tailleCellule = 5;
		GUISimulator window = null;
		EventManager eventManager = new EventManager(); 

		String nomJeu = new String("");
		Scanner sc = new Scanner(System.in);
		for (int j=0; j<20 ;j++ ) {
			System.out.println("\n");
		}
		System.out.println("Bienvenue à vous, nous vous proposons plusieurs jeux :\n");
		System.out.println("Pour lancer le Jeu de la Vie de Conway entrer : 'jdv'");
		System.out.println("Pour lancer le Jeu de l'Immigration entrer    : 'jim'");
		System.out.println("Pour lancer le Jeu de la Ségrégation entrer   : 'jseg'");
		System.out.println("Pour lancer le Jeu de Boids entrer            : 'jboids'");
		System.out.println("Pour lancer le Jeu de Balles entrer           : 'jballes'");
		System.out.println("Pour quitter entrer                           : 'exit'");

		nomJeu = sc.nextLine();
		switch (nomJeu){
			case "jdv":
				System.out.println("\nVous avez choisi de lancer le jeu: Jeu de la Vie de Conway");
				System.out.println("Entrer le pourcentage de cases Vivantes à l'initialisation [0..100]");
				int p = sc.nextInt();
				while ( p<0 || p>100) {
					System.out.println("Veuillez entrer un (int) entre [0..100]\n"); 
					p = sc.nextInt();
				}
				window = new GUISimulator(largeur,hauteur, Color.WHITE);
				CellulesVie c = new CellulesVie(nbLignes, nbColonnes , p);
				CellulesEvent ce = new CellulesEvent(c, 1, eventManager );
				CellulesSimulator jdv = new CellulesSimulator(	c,
																tailleCellule,
																window,
																ce,
                                                                eventManager); 
				window.setSimulable(jdv);
				break;
			case "jim":
				System.out.println("\nVous avez choisi de lancer le jeu: Jeu de l'Immigration");
				System.out.println("Entrer le nombre d'état par cellule (int) [1..10]");
				int nei = sc.nextInt();
				while ( nei<1 || nei>10) {
					System.out.println("Veuillez entrer un (int) entre [1..10]"); 
					nei = sc.nextInt();
				}
				window = new GUISimulator(largeur,hauteur, Color.BLUE);
				CellulesIm ci = new CellulesIm(nbLignes, nbColonnes , nei);
				CellulesEvent cei = new CellulesEvent(ci, 1, eventManager );
				CellulesSimulator jim = new CellulesSimulator(	ci,
																tailleCellule,
																window,
																cei,
                                                                eventManager); 
				window.setSimulable(jim);
				break;

			case "jseg":
				System.out.println("\nVous avez choisi de lancer le jeu: Jeu de la Ségrégation");
				System.out.println("Entrer le nombre d'état par cellule (int) [1..10]");
				int neS = sc.nextInt();
				while ( neS<1 || neS>10) {
					System.out.println("Veuillez entrer un (int) entre [1..10]"); 
					neS = sc.nextInt();
				}
				System.out.println("Entrer le facteur de segregation (int) [1..8]"); 
				int k = sc.nextInt();
				while ( k<1 || k>9) {
					System.out.println("Veuillez entrer un (int) entre [1..8]"); 
					k = sc.nextInt();
				}
				window = new GUISimulator(largeur,hauteur, Color.BLUE);
				CellulesSeg cS = new CellulesSeg(nbLignes, nbColonnes , neS, k);
				CellulesEvent ceS = new CellulesEvent(cS, 1, eventManager );
				CellulesSimulator jseg = new CellulesSimulator(	cS,
																tailleCellule,
																window,
																ceS,
                                                                eventManager); 
				window.setSimulable(jseg);
				break;

			case "jboids":
				System.out.println("\nVous avez choisi de lancer le jeu: Jeu de Boids");
				System.out.println("Entrer le nombre de groupes de Boids (int) [1..10]"); 
				int nbGroupe = sc.nextInt();
				while ( nbGroupe<1 || nbGroupe>10) {
					System.out.println("Veuillez entrer un (int) entre [1..10]"); 
					nbGroupe = sc.nextInt();
				}
				Boids[] groupesBoids = new Boids[nbGroupe];
				int[] delai = new int[nbGroupe];
				int nbBoids = 0; 
				for (int i=0;i<nbGroupe ;i++ ) {
					System.out.println("Entrer le nombre de Boids (int) [1..500] pour le  groupe " + i); 
					nbBoids = sc.nextInt();
					while ( nbBoids<1 || nbBoids>500) {
						System.out.println("Veuillez entrer un (int) entre [1..500]"); 
						 nbBoids = sc.nextInt();
					}
					groupesBoids[i] = new Boids(nbBoids);
					System.out.println("Entrer la réactivité des Boids (int) [1..20] pour le  groupe " + i); 
					delai[i] = sc.nextInt();
					while ( delai[i]<1 || delai[i]>20) {
						System.out.println("Veuillez entrer un (int) entre [1..20]"); 
						delai[i] = sc.nextInt();
					}
				}
				window = new GUISimulator(largeur,hauteur, Color.BLUE);
                BBEvent boidsEvents = new BBEvent(groupesBoids, 1, eventManager, delai, -1);
                BBSimulator b = new BBSimulator(window,
												groupesBoids,
												boidsEvents,
												eventManager); 
                //b.getBalls()[0].setParameters(4, 10, 4, 15, 100);
                window.setSimulable(b);

				break;

			case "jballes":
				System.out.println("\nVous avez choisi de lancer le jeu: Jeu de Balles");
				System.out.println("Entrer le nombre de groupes de Balles (int) [1..10]"); 
				int nbGroupesBalles = sc.nextInt();
				while ( nbGroupesBalles<1 || nbGroupesBalles>10) {
					System.out.println("Veuillez entrer un (int) entre [1..10]"); 
					nbGroupesBalles = sc.nextInt();
				}
				Balls[] groupesBalles = new Balls[nbGroupesBalles];
				int[] delaiB = new int[nbGroupesBalles];
				int nbBalles = 0;
				for (int i=0;i<nbGroupesBalles ;i++ ) {
					System.out.println("Entrer le nombre de Balles (int) [1..50] pour le  groupe " + i); 
					nbBalles = sc.nextInt();
					while ( nbBalles<1 || nbBalles>50) {
						System.out.println("Veuillez entrer un (int) entre [1..50]"); 
						nbBalles = sc.nextInt();
					}
					groupesBalles[i] = new Balls(nbBalles);
					System.out.println("Entrer la réactivité des Boids (int) [1..20] pour le  groupe " + i); 
					delaiB[i] = sc.nextInt();
					while ( delaiB[i]<1 || delaiB[i]>20) {
						System.out.println("Veuillez entrer un (int) entre [1..20]"); 
						delaiB[i] = sc.nextInt();
					}
				}
				window = new GUISimulator(largeur,hauteur, Color.BLUE);
                BBEvent ballesEvents = new BBEvent(groupesBalles, 1, eventManager, delaiB, -1);
                BBSimulator bb = new BBSimulator(window,
												groupesBalles,
												ballesEvents,
												eventManager); 
                //b.getBalls()[0].setParameters(4, 10, 4, 15, 100);
                window.setSimulable(bb);
				break;

			case "exit":
				System.out.println("\nVous avez demander de quitter, Bye Bye" ); 
				return;
			default :
				System.out.println("\nVous n'avez pas entré le nom d'un jeu valide... Relancer le jeu"); 
				return;
		}
    }
}
