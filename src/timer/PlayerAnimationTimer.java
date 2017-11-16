package timer;

import city.cs.engine.*;
import actor.player.Player;

/**
* The purpose of this counter is to reset the player's animation back to idle once it plays through once. 
* This is useful for melee and cast animations which are not intended to loop constantly.
*/

public class PlayerAnimationTimer implements StepListener {
    private final Player player;
    private final int counterLimit;
    private int counter;
    private final BodyImage anim;

    public PlayerAnimationTimer(Player player, BodyImage anim, int counterLimit) {
        this.player = player;
        this.counterLimit = counterLimit;
        this.counter = 0;
        this.anim = anim;
    }

    @Override
    public void preStep(StepEvent e) {
        
    }

    @Override
    public void postStep(StepEvent e) {
        // The counter variable counts frames in the simulation and serves as the indicator of whether the animation has finished playing. 
        // This seems like a sensible choice since we know exactly how many frames there are in each animation, and we can pass that in as our counterLimit value at constructor
        counter++;
        if (counter >= counterLimit) {
            // we also do a check for whether the animation has changed since calling the step listener, in which case we don't want to reset it
            if (player.getCurrentAnimation() == anim) {
                player.resetToAnimation("idle");
            }
            player.getWorld().removeStepListener(this);
        }
    }
}