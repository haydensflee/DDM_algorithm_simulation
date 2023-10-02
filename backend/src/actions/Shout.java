package actions;

import agents.Agent;
import simulation.CowboysVsAliens;

/** An Action that prints something to the terminal */
public class Shout extends Action {
    String message;

    public Shout(Agent agent, String message) {
        super(agent);
        this.message = message;
    }

    public ActionResult performImpl(CowboysVsAliens state) {
        System.out.println(agent.toString() + " shouts: " + message.toUpperCase());
        return ActionResult.SUCCESS;
    }

    public void postStep(CowboysVsAliens state) {}
}
