package actions;

import agents.Agent;
import simulation.CowboysVsAliens;

/** The Action base class that all Actions extend */
abstract public class Action {
    protected Agent agent;
    private boolean performed;

    public Action(Agent agent) {
        this.agent = agent;
    }

    /**
     * @return the Agent performing this action
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Performs the Action, manipulating the simulation state. Sets internal flag performed = true.
     * This should only be called by the coordinator, the Agent superclass calls
     * state.coordinator.performAction(action) to perform actions.
     *
     * @param state a CowboysVsAliens simulation state
     * @return ActionResult.SUCCESS if the action is valid and was performed, ActionResult.FAIL if
     *         the action failed and could not be performed, ActionResult.ALREADY_PERFORMED if the
     *         action was already performed in the past
     */
    public ActionResult perform(CowboysVsAliens state) {
        if (!performed) {
            ActionResult res = performImpl(state);
            if (res == ActionResult.SUCCESS) {
                performed = true;
            } else if (res == ActionResult.FAIL) {
                System.out.println("Error: Could not perform action: " + this.toString());
            }
            return res;
        } else {
            return ActionResult.PERFORMED_ALREADY;
        }
    }

    /**
     * The implementation of performing this action, actually manipulates the state. Implement this
     * in your specific Action subclass
     *
     * @param state a CowboysVsAliens simulation state
     * @return ActionResult.SUCCESS if the action is valid and was performed, ActionResult.FAIL if
     *         the action failed and could not be performed
     */
    abstract ActionResult performImpl(CowboysVsAliens state);


    /**
     * Called by the coordinator after each simulation step has completed. This function should
     * handle updating information known by each Agent possibly affected by this Action.
     * <p>
     * E.g. The Move action checks all other Agents and informs them if the moving Agent moved into
     * their visionRadius
     *
     * @param state a CowboysVsAliens simulation state
     */
    public abstract void postStep(CowboysVsAliens state);

    /**
     * @return whether the action has been successfully performed
     */
    public boolean isPerformed() {
        return performed;
    }
}
