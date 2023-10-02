package coordinator;

import actions.Action;
import actions.ActionResult;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Bag;
import simulation.CowboysVsAliens;

/**
 * Executes Actions that Agents decide to to perform.
 * <p>
 * TODO: (Low priority) Some descheduling/rescheduling based on whether its actually possible for an
 * Agent to take an Action in the next simulation step
 */
public class Coordinator implements Steppable {
    private Bag actionsThisStep;

    public Coordinator() {
        actionsThisStep = new Bag();
    }

    /**
     * Step the coordinator - it loops over all the Actions performed this step, and calls the
     * Action::postStep() method. TODO: Somehow return, from postStep(), Agents that should be
     * de/rescheduled
     *
     * @param state an instance of the CowboysVsAliens SimState
     */
    public void step(SimState state) {
        CowboysVsAliens sim = (CowboysVsAliens) state;
        for (int i = 0; i < actionsThisStep.size(); i++) {
            Action a = (Action) actionsThisStep.get(i);
            a.postStep(sim);
        }

        actionsThisStep.clear();
    }

    /**
     * Performs the specified action, manipulating the simulation state
     *
     * @param a the action to perform
     * @return ActionResult.SUCCESS if the action is valid and can be performed, ActionResult.FAIL
     *         otherwise
     */
    public ActionResult performAction(Action a, CowboysVsAliens state) {
        ActionResult res = a.perform(state);
        if (res == ActionResult.SUCCESS) {
            actionsThisStep.push(a);
        }
        return res;
    }
}
