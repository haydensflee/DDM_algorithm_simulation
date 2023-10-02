package actions;

import agents.Agent;
import simulation.CowboysVsAliens;

/** An action that does nothing */
public class NullAction extends Action {
    public NullAction(Agent agent) {
        super(agent);
    }

    public ActionResult performImpl(CowboysVsAliens state) {
        return ActionResult.SUCCESS;
    }

    public void postStep(CowboysVsAliens state) {}
}
