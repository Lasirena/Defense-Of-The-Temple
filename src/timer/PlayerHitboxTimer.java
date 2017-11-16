package timer;

import actor.player.Player;
import city.cs.engine.*;

/**
* This is a steplistener that counts when the hitbox from the melee attack should be removed. 
* It is removed before the animation itself finishes hence why it is a separate class.
*/

public class PlayerHitboxTimer implements StepListener {
    private final WorldView view;
    private final Player player;
    
    private int counter;

    public PlayerHitboxTimer(WorldView view, Player player) {
        this.view = view;
        this.player = player;
        counter = 0;
    }

    @Override
    public void preStep(StepEvent e) {
        
    }

    @Override
    public void postStep(StepEvent e) {
        counter++;
        if (counter > 15) {
            player.getFixtureList().get(0).destroy();
            player.getWorld().removeStepListener(this);
        }
    }
}
