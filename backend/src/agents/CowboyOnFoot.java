package agents;

import actions.Action;
import actions.NullAction;
import actions.Shout;
import simulation.CowboysVsAliens;

/** A type of cowboy that moves on foot */
public class CowboyOnFoot extends Agent {

    public CowboyOnFoot(double visionRadius) {
        super(visionRadius);
    }

    protected Action decideAction(CowboysVsAliens state) {
        String shoutStr = new String();

        // Do nothing if visible enemies have not changed
        if (newlyVisibleAgents.isEmpty() && noLongerVisibleAgents.isEmpty()) {
            return new NullAction(this);
        }

        if (!newlyVisibleAgents.isEmpty()) {
            // Agents were added by the coordinator
            shoutStr += "I can now see:\n";
            for (int i = 0; i < newlyVisibleAgents.size(); i++) {
                shoutStr += newlyVisibleAgents.get(i).toString();
            }
        }

        if (!noLongerVisibleAgents.isEmpty()) {
            // Agents were removed by the coordinator
            shoutStr += "\nI can no longer see:\n";
            for (int i = 0; i < noLongerVisibleAgents.size(); i++) {
                shoutStr += noLongerVisibleAgents.get(i).toString();
            }
        }

        return new Shout(this, shoutStr);
    }
}
