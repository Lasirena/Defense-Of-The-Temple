package actor.player;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Pan the view to follow the player
 */
public class PlayerTracker implements StepListener {
    /** The view */
    private final WorldView view;

    /** The body */
    private final Player player;

    public PlayerTracker(WorldView view, Player player) {
        this.view = view;
        this.player = player;
    }

    @Override
    public void preStep(StepEvent e) {
        
    }

    @Override
    public void postStep(StepEvent e) {
        view.setCentre(new Vec2(player.getPosition().x, player.getPosition().y + 10));
    }
    
}
