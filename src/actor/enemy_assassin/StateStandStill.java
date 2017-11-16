package actor.enemy_assassin;

import fsm.FSMState;
import org.jbox2d.common.Vec2;

/**
 * One of the EnemyAssassin FSM states which specifies what the enemy should do while it's standing still.
 */

public class StateStandStill extends FSMState<EnemyAssassin> {
    
    @Override
    protected void update() {
        EnemyAssassin enemy = getContext();
        //if enemy detects the player, start following them
        if (enemy.playerInRange(EnemyAssassin.VISION_RANGE) != 0) {
            gotoState(new StateFollow());
        }
    }
    
    @Override
    protected void enter() {
        EnemyAssassin enemy = getContext();
        enemy.setLinearVelocity(new Vec2(0, 0));
        enemy.resetToAnimation("idle");
    }

    @Override
    protected void exit() {
    }
}
