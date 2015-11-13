import gui.*;
import java.awt.Color;

public abstract class Simulator implements gui.Simulable {
    private GUISimulator simulator;
    private EventManager manager;

    public Simulator(GUISimulator simulator) {
        this.simulator = simulator;
        this.manager = new EventManager();
    }

    public GUISimulator getGUI() {
        return this.simulator;
    }

    public EventManager getManager() {
        return this.manager;
    }

    public abstract void affiche();

    @Override
    public void next() {
        this.manager.next();
        this.simulator.reset();
        this.affiche();
    }

    @Override
    public void restart(){
        this.manager.restart();
        this.simulator.reset();
    }
}
