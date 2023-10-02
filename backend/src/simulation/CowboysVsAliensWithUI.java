package simulation;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import agents.Agent;
import agents.AlienOnFoot;
import agents.CowboyOnFoot;
import sim.display.Console;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.continuous.ContinuousPortrayal2D;
import sim.portrayal.network.NetworkPortrayal2D;
import sim.portrayal.network.SimpleEdgePortrayal2D;
import sim.portrayal.network.SpatialNetwork2D;
import sim.portrayal.simple.FacetedPortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;

public class CowboysVsAliensWithUI extends GUIState {
    public Display2D display;
    public JFrame displayFrame;
    ContinuousPortrayal2D battlefieldPortrayal = new ContinuousPortrayal2D();
    NetworkPortrayal2D cowboyCommsPortrayal = new NetworkPortrayal2D();

    public static void main(String[] args) {
        CowboysVsAliensWithUI ui = new CowboysVsAliensWithUI();
        Console c = new Console(ui);
        c.setVisible(true);
    }

    public CowboysVsAliensWithUI() {
        super(new CowboysVsAliens(System.currentTimeMillis()));
    }

    public CowboysVsAliensWithUI(SimState state) {
        super(state);
    }

    public static String getName() {
        return "Cowboys vs Aliens";
    }

    public Object getSimulationInspectedObject() {
        return state;
    }

    public void start() {
        super.start();
        setupPortrayals();
    }

    public void load() {
        super.load(state);
        setupPortrayals();
    }

    public void setupPortrayals() {
        CowboysVsAliens sim = (CowboysVsAliens) state;

        // Portrayal of the continuous 2D space
        battlefieldPortrayal.setField(sim.battlefield);
        OvalPortrayal2D[] cowboyOvals = new OvalPortrayal2D[2];
        // Cowboy as blue dot
        cowboyOvals[0] = new OvalPortrayal2D(Color.blue);
        // visionRadius as red/green circle
        cowboyOvals[1] = new OvalPortrayal2D() {
            public void draw(Object object, Graphics2D graphics, DrawInfo2D info) {
                Agent agent = (Agent) object;
                scale = 2.0 * agent.getVisionRadius();
                if (agent.canSeeAgents()) {
                    paint = new Color(0, 255, 0, 20);
                } else {
                    paint = new Color(255, 0, 0, 20);
                }
                super.draw(object, graphics, info);
            }
        };
        battlefieldPortrayal.setPortrayalForClass(CowboyOnFoot.class,
                new FacetedPortrayal2D(cowboyOvals, true));
        battlefieldPortrayal.setPortrayalForClass(AlienOnFoot.class,
                new OvalPortrayal2D(Color.red));

        // Portrayal of the cowboy network in the 2D space
        cowboyCommsPortrayal.setField(new SpatialNetwork2D(sim.battlefield, sim.cowboyComms));
        cowboyCommsPortrayal.setPortrayalForAll(new SimpleEdgePortrayal2D());

        display.reset();
        display.setBackdrop(Color.white);
        display.repaint();
    }

    public void init(Controller c) {
        super.init(c);
        display = new Display2D(600, 600, this);
        display.setClipping(false);

        displayFrame = display.createFrame();
        displayFrame.setTitle("Battlefield Display");
        c.registerFrame(displayFrame);
        displayFrame.setVisible(true);
        display.attach(battlefieldPortrayal, "Battlefield");
        display.attach(cowboyCommsPortrayal, "Cowboy Communication Network");
    }

    public void quit() {
        super.quit();
        if (displayFrame != null) {
            displayFrame.dispose();
        }
        displayFrame = null;
        display = null;
    }
}
