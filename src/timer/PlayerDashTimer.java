/*
 * (c) Lanaya
 */
package timer;

import city.cs.engine.SolidFixture;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import actor.player.Player;
import org.jbox2d.common.Vec2;

/**
 * This implements the timer initiated when the player uses their dash ability to determine when to stop the dash.
 * @author Lanaya
 */

public class PlayerDashTimer implements StepListener {
    
    private final Player player;
    private final World world;
    private long dodgeTimer;
    
    public PlayerDashTimer (Player player, World world) {
        this.player = player;
        this.world = world;
    }
    
    /**
     * 
     * @param durationms Desired duration of the dash in milliseconds.
     */
    public void setDodgeDuration(long durationms) {
        dodgeTimer = System.currentTimeMillis() + durationms;
    }
    
    @Override
    public void preStep(StepEvent e) {
        if (System.currentTimeMillis() > dodgeTimer) {
            player.setGravityScale(1);
            player.getFixtureList().get(0).destroy();
            SolidFixture playerBody = new SolidFixture(player, player.getPlayerShape());
            playerBody.setFriction(100);
            if (!player.isFlipped()) {
                player.setLinearVelocity(new Vec2(15,0));
            } else {
                player.setLinearVelocity(new Vec2(-15,0));
            }
            world.removeStepListener(this);
        }
    }
    
    @Override
    public void postStep(StepEvent e) {
        
    }
}
