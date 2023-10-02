package actions;

import agents.Agent;
import sim.util.Bag;
import sim.util.Double2D;
import simulation.CowboysVsAliens;

/** An Action that moves an agent to a new position */
public class Move extends Action {
    private Double2D vector;
    private boolean absolute;
    private Double2D prevLocation;
    private Double2D newLocation;

    /**
     *
     * @param agent The agent to move
     * @param vector A 2D position vector (absolute or relative)
     * @param absolute Whether vector is absolute or relative to the agent's current position
     */
    public Move(Agent agent, Double2D vector, boolean absolute) {
        super(agent);
        this.vector = vector;
        this.absolute = absolute;
    }

    public ActionResult performImpl(CowboysVsAliens state) {
        boolean outOfBounds = false;
        if (vector.x > state.battlefield.getWidth() || vector.y > state.battlefield.getHeight()) {
            outOfBounds = true;
        } else {
            prevLocation = state.battlefield.getObjectLocation(agent);
            if (absolute) {
                if (vector.x < 0 || vector.y < 0) {
                    outOfBounds = true;
                } else {
                    state.battlefield.setObjectLocation(agent, vector);
                    newLocation = vector;
                }
            } else {
                // relative position
                double nextX = prevLocation.x + vector.x;
                double nextY = prevLocation.y + vector.y;
                if (nextX > state.battlefield.getWidth() || nextX > state.battlefield.getHeight()
                        || nextX < 0 || nextY < 0) {
                    outOfBounds = true;
                } else {
                    state.battlefield.setObjectLocation(agent, new Double2D(nextX, nextY));
                    newLocation = new Double2D(nextX, nextY);
                }
            }
        }

        if (outOfBounds) {
            System.out.println("Error: Move action would result in out of bounds movement");
            return ActionResult.FAIL;
        } else {
            return ActionResult.SUCCESS;
        }

    }

    public void postStep(CowboysVsAliens state) {
        double myVisionRadius = agent.getVisionRadius();
        // FIXME: There is probably a smarter way than getting every single object
        // and this breaks down if there's anything other than Agents in the battlefield
        // (see fixme just below)
        Bag allAgents = state.battlefield.getAllObjects();
        for (int i = 0; i < allAgents.size(); i++) {
            // FIXME: THIS CAST IS BAD - this breaks down if there's anything other than Agents in
            // the battlefield, maybe need a bunch of separate Continuous2Ds for different classes?
            Agent otherAgent = (Agent) allAgents.get(i);
            if (otherAgent == agent) {
                continue;
            }
            Double2D otherAgentLoc = state.battlefield.getObjectLocation(otherAgent);
            double otherVisionRadius = otherAgent.getVisionRadius();

            if (newLocation.distance(otherAgentLoc) <= agent.getVisionRadius()) {
                if (prevLocation.distance(otherAgentLoc) > myVisionRadius) {
                    // I can now see otherAgent
                    System.out.println(agent.toString() + " can now see " + otherAgent.toString());
                    agent.newVisibleAgent(otherAgent);
                }
            }

            if (prevLocation.distance(otherAgentLoc) <= myVisionRadius) {
                if (newLocation.distance(otherAgentLoc) > myVisionRadius) {
                    // I can no longer see otherAgent
                    System.out.println(
                            agent.toString() + " can no longer see " + otherAgent.toString());
                    agent.removeVisibleAgent(otherAgent);
                }
            }

            if (otherAgentLoc.distance(newLocation) <= otherVisionRadius) {
                if (otherAgentLoc.distance(prevLocation) > otherVisionRadius) {
                    // otherAgent can now see me
                    System.out.println(otherAgent.toString() + " can now see " + agent.toString());
                    otherAgent.newVisibleAgent(agent);
                }
            }

            if (otherAgentLoc.distance(prevLocation) <= otherVisionRadius) {
                if (otherAgentLoc.distance(newLocation) > otherVisionRadius) {
                    // otherAgent can no longer see me
                    System.out.println(
                            otherAgent.toString() + " can no longer see " + agent.toString());
                    otherAgent.removeVisibleAgent(agent);
                }
            }

        }
    }

    /**
     * @return The new location of the Agent after it has moved, null if the move hasn't been
     *         performed yet.
     */
    public Double2D getNewLocation() {
        if (this.isPerformed()) {
            return newLocation;
        } else {
            return null;
        }
    }

    /**
     * @return The previous location of the Agent before it moved, null if the move hasn't been
     *         performed yet.
     */
    public Double2D getPrevLocation() {
        if (this.isPerformed()) {
            return prevLocation;
        } else {
            return null;
        }
    }
}
