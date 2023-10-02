package agents;

import actions.Action;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Bag;
import simulation.CowboysVsAliens;

/**
 * The Agent base class that all Agents (cowboys and aliens) extend
 */
public abstract class Agent implements Steppable {
    protected double visionRadius;
    protected Bag visibleAgents;
    protected Bag newlyVisibleAgents;
    protected Bag noLongerVisibleAgents;

    public Agent(double visionRadius) {
        this.visionRadius = visionRadius;
        visibleAgents = new Bag();
        newlyVisibleAgents = new Bag();
        noLongerVisibleAgents = new Bag();
    }

    /**
     * Step the agent - it will decide an action and perform it, if possible
     *
     * @param state an instance of the CowboysVsAliens SimState
     */
    public void step(SimState state) {
        CowboysVsAliens sim = (CowboysVsAliens) state;

        // TODO: don't give Agents the full, master state to decide their action
        Action action = decideAction(sim);
        updateKnownInformation();
        sim.coordinator.performAction(action, sim);
    }

    public double getVisionRadius() {
        return visionRadius;
    }

    /**
     * Decide an action to perform. Implement this in your specific Agent subclass.
     *
     * @return the Action to perform
     */
    protected abstract Action decideAction(CowboysVsAliens state);

    public void newVisibleAgent(Agent enemy) {
        newlyVisibleAgents.push(enemy);
    }

    public void removeVisibleAgent(Agent enemy) {
        noLongerVisibleAgents.push(enemy);
    }

    public boolean canSeeAgents() {
        return !visibleAgents.isEmpty();
    }

    /** Updates any 'new' information to be 'already known' */
    protected void updateKnownInformation() {
        visibleAgents.addAll(newlyVisibleAgents);
        newlyVisibleAgents.clear();

        visibleAgents.removeAll(noLongerVisibleAgents);
        noLongerVisibleAgents.clear();
    }
}
