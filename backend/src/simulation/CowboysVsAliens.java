package simulation;

import static util.Continuous2DUtils.randomPositionAtBorder;
import agents.AlienOnFoot;
import agents.CowboyOnFoot;
import coordinator.Coordinator;
import sim.engine.SimState;
import sim.field.continuous.Continuous2D;
import sim.field.network.Network;
import sim.util.Bag;
import sim.util.Double2D;

// This is the MASON model or simulation, the highest level class.
public class CowboysVsAliens extends SimState {
    // TODO: determine required width/height bounds and appropriate discretisation.
    public Continuous2D battlefield = new Continuous2D(1.0, 100, 100);
    public Network cowboyComms = new Network(false);
    public Coordinator coordinator = new Coordinator();

    public int initialCowboys = 4;
    public int initialAliens = 6;
    public double initialCowboyCircleRadius = 20;

    public int getInitialCowboys() {
        return initialCowboys;
    }

    public void setInitialCowboys(int val) {
        if (val > 0) {
            initialCowboys = val;
        }
    }

    public int getInitialAliens() {
        return initialAliens;
    }

    public void setInitialAliens(int val) {
        if (val > 0) {
            initialAliens = val;
        }
    }

    public double getInitialCowboyCircleRadius() {
        return initialCowboyCircleRadius;
    }

    public void setInitialCowboyCircleRadius(double val) {
        if (val > 0 && val < Math.min(battlefield.width, battlefield.height)) {
            initialCowboyCircleRadius = val;
        }
    }

    public Object domInitialCowboyCircleRadius() {
        return new sim.util.Interval(0.0, Math.min(battlefield.width, battlefield.height));
    }

    public CowboysVsAliens(long seed) {
        super(seed);
    }

    public void start() {
        super.start();

        battlefield.clear();
        cowboyComms.clear();

        initCowboys();
        initAliens();

        // Coordinator steps after schedule steps
        schedule.addAfter(coordinator);
    }

    /** Setup for cowboys */
    private void initCowboys() {
        double separationAngle = (2 * Math.PI) / initialCowboys;
        for (int i = 0; i < initialCowboys; i++) {
            CowboyOnFoot cowboy = new CowboyOnFoot(15);
            // For now just put cowboys in a circle around the centre.
            battlefield.setObjectLocation(cowboy,
                    new Double2D(
                            (battlefield.width / 2)
                                    + initialCowboyCircleRadius * Math.cos(i * separationAngle),
                            (battlefield.height / 2)
                                    + initialCowboyCircleRadius * Math.sin(i * separationAngle)));

            cowboyComms.addNode(cowboy);
            // Perhaps in the future, Agents (maybe just cowboys) will be dynamically de/rescheduled
            schedule.scheduleRepeating(cowboy);
        }

        // Setup ring network
        Bag allCowboys = cowboyComms.getAllNodes();
        for (int i = 0; i < allCowboys.size(); i++) {
            Object cowboy = allCowboys.get(i);
            Object nextCowboy = null;
            if (i == allCowboys.size() - 1) {
                // set next cowboy to first one for final cowboy
                nextCowboy = allCowboys.get(0);
            } else {
                nextCowboy = allCowboys.get(i + 1);
            }
            // Null because edge has no weighting, could potentially make the edge weighting a
            // measure of communication strength
            cowboyComms.addEdge(cowboy, nextCowboy, null);
        }

    }

    /** Setup for aliens */
    private void initAliens() {
        for (int i = 0; i < initialAliens; i++) {
            AlienOnFoot alien = new AlienOnFoot(25,
                    new Double2D(battlefield.getHeight() / 2, battlefield.getWidth() / 2), 0.5);
            // For now just put aliens randomly at the border of the battlefield.
            battlefield.setObjectLocation(alien, randomPositionAtBorder(battlefield, random));
            schedule.scheduleRepeating(alien);
        }
    }

    public static void main(String[] args) {
        doLoop(CowboysVsAliens.class, args);
        System.exit(0);
    }
}
