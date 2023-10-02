package agents;

import actions.Action;
import actions.Move;
import sim.util.Double2D;
import simulation.CowboysVsAliens;

/** A type of Alien agent that moves on foot */
public class AlienOnFoot extends Agent {
    private Double2D targetLocation;
    private double distancePerStep;

    public AlienOnFoot(double visionRadius, Double2D targetLocation, double distancePerStep) {
        super(visionRadius);
        this.targetLocation = targetLocation;
        this.distancePerStep = distancePerStep;
    }

    protected Action decideAction(CowboysVsAliens state) {
        Double2D currentLocation = state.battlefield.getObjectLocation(this);
        // Move toward target location at specified velocity
        return new Move(this, targetLocation.subtract(currentLocation).resize(distancePerStep),
                false);
    }
}
