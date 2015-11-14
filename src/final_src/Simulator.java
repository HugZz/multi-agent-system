import gui.*;
import java.awt.Color;

public abstract class Simulator implements Simulable {
    private GUISimulator simulator;
    private EventManager manager;
    private Event event;
	private ColorTable colorTable;

    public Simulator(GUISimulator simulator, Event event, EventManager manager) {
        this.simulator = simulator;
        this.manager = manager;
        this.event = event;
        this.manager.addEvent(event);
		this.setColorTable();
    }

    public GUISimulator getGUI() {
        return this.simulator;
    }

    public EventManager getManager() {
        return this.manager;
    }

    abstract public  void affiche();
    // Inutile si classe père entre Balles et Cellules
    abstract public void reInit();

    @Override
    public void next() {
        this.manager.next();
        this.simulator.reset();
        this.affiche();
    }

    @Override
    public void restart(){
        this.manager.restart();
        this.reInit();
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
		System.out.println(this.colorTable.toString());
	}
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
