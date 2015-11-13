import gui.*;
import java.awt.Color;

public abstract class Simulator implements gui.Simulable {
    private GUISimulator simulator;
    private EventManager manager;
    private Event event;

    public Simulator(GUISimulator simulator, Event event) {
        this.simulator = simulator;
        this.manager = new EventManager();
        this.event = event;
        this.manager.addEvent(event);
    }

    public GUISimulator getGUI() {
        return this.simulator;
    }

    public EventManager getManager() {
        return this.manager;
    }

    public abstract void affiche();
    // Inutile si classe père entre Balles et Cellules
    public abstract void reInit();

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
        this.affiche;
    }

    /**
     * Méthode qui donne une couleur en fonction de l'entrée
     * @param e
     *      Etat de la Cellule
     * @return c
     *      Couleur correspondante à cet état
     */
    public Color getColorEtat(int e) {
        if( e == -1 ) { return Color.BLUE;}
        if( e == 0 ) { return Color.WHITE;}
        if( e == 1 ) { return Color.BLACK;}
        if( e == 2 ) { return Color.RED;}
        if( e == 3 ) { return Color.YELLOW;}
        if( e == 4 ) { return Color.GREEN;}
        if( e == 5 ) { return Color.PINK;}
        if( e == 6 ) { return Color.GRAY;}
        if( e == 7 ) { return Color.ORANGE;}
        if( e == 8 ) { return Color.MAGENTA;}
        if( e == 9 ) { return Color.DARK_GRAY;}
        if( e == 10) { return Color.LIGHT_GRAY;}
        else {
            throw new RuntimeException("ERREUR(couleur) l'état est forcement dans ]1:10]!!!");
        }
    }
}
