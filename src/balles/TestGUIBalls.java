import java.awt.Color;
import gui.*;

public class TestGUIBalls {
    public static void main (String [] args) {
        int nb_balls = 4;

        GUISimulator window = new GUISimulator(500, 500, Color.WHITE);
        
        BallsSimulator bs = new BallsSimulator(nb_balls,window);

        window.setSimulable(bs);

    }
}
