import gui.*;
import java.awt.Color;

/**
 * Classe abstraite qui gère la partie affichage des systèmes.
 * @author Lucas Mahieu
 * @author Hugues de Valon
 */
public abstract class Simulator implements Simulable {
    /**
     * GUI du système.
     */
    private GUISimulator simulator;
    /**
     * Manager des événements.
     */
    private EventManager manager;
    /**
     * Evènement à executer.
     */
    private Event event;
    /**
     * Table d'association des couleurs.
     */
	private ColorTable colorTable;

    /**
     * Constructeur : Initialise les attributs et ajoute le premier événement.
     * @param simulator GUI du sytème.
     * @param event Premier événement du système.
     * @param manager Manager d'événements.
     */
    public Simulator(GUISimulator simulator, Event event, EventManager manager) {
        this.simulator = simulator;
        this.manager = manager;
        this.event = event;
        // On ajoute l'événement originel, celui présent à la création du monde.
        this.manager.addEvent(event);
		this.setColorTable();
    }

    /**
     * Accesseur du GUI.
     * @return GUI du système
     */
    public GUISimulator getGUI() {
        return this.simulator;
    }

    /**
     * Accesseur du manager d'événements.
     * @return Manager des événements.
     */
    public EventManager getManager() {
        return this.manager;
    }

    /**
     * Méthode qui définit la méthode d'affichage du système.
     */
    abstract public void affiche();

    /**
     * Méthode qui définit la méthode de réinitialisation du système.
     */
    abstract public void reInit();

    /**
     * Cette classe s'éxecute lorsque l'utilisateur clique sur le bouton
     * "Suivant", elle actualise le système et l'affiche.
     */
    @Override
    public void next() {
        this.manager.next();
        this.simulator.reset();
        this.affiche();
    }

    /**
     * Cette classe s'execute lorsque l'utilisateur clique sur le bouton
     * "Début" du GUI, elle redémarre le système avec ses valeurs par défaut.
     */
    @Override
    public void restart(){
        this.manager.restart();
        this.reInit();
        // Premier événement : celui qui créera tous les autres.
        this.manager.addEvent(this.event);
        this.simulator.reset();
        this.affiche();
    }
	/**
	 * Creer la table des corespondance : Entier -> Couleurs
	 * @param ne
	 * 		Créera une table de correspondance pour les états
	 */
	private void setColorTable() {
		ColorTable ct = new ColorTable();
		ct.associer(-1, Color.WHITE);
		ct.associer(0, Color.BLUE);
		ct.associer(1, Color.RED);
		ct.associer(2, Color.YELLOW);
		ct.associer(3, Color.GREEN);
		ct.associer(4, Color.ORANGE);
		ct.associer(5, Color.MAGENTA);
		ct.associer(6, Color.PINK);
		ct.associer(7, Color.BLACK);
		ct.associer(8, Color.GRAY);
		ct.associer(9, Color.DARK_GRAY);
		ct.associer(10,Color.LIGHT_GRAY);
		this.colorTable = ct;
		//System.out.println(this.colorTable.toString());
	}
    /**
     * Accesseur sur la table des couleurs.
     * @return Table des couleurs.
     */
	private ColorTable getColorTable(){
		return this.colorTable;
	}

    /**
     * Méthode qui donne une couleur en fonction de l'entrée
     * @param e
     *      Etat de la Cellule
     * @return c
     *      Couleur correspondante à cet état
     */
    public Color getColorEtat(int e) {
		return this.getColorTable().obtenirColor(e);
    }
}
