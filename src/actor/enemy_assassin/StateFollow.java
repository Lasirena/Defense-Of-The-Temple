package actor.enemy_assassin;

import fsm.FSMState;
import org.jbox2d.common.Vec2;

/**
 * One of the EnemyAssassin FSM states which specifies what the enemy should do while it's following the player.
 */

public class StateFollow extends FSMState<EnemyAssassin> {
    
    @Override
    protected void update() {
        EnemyAssassin enemy = getContext();
        //if the enemy is in melee attack range, go to the melee attack state
        if (enemy.playerInRange(EnemyAssassin.MELEE_RANGE) != 0) {
            gotoState(new StateAttackMelee());
        }
        //if the enemy is in dash range, go to the dash state
        else if (enemy.playerInRange(EnemyAssassin.DASH_RANGE) != 0 && enemy.hasDash()) {
            gotoState(new StateDash());
        //if the enemy is in vision range, start running towards them
        } else if (enemy.playerInRange(EnemyAssassin.VISION_RANGE) == 1) {
            enemy.setLinearVelocity(new Vec2 (enemy.getMoveSpeed(), 0));
            if (!enemy.isFlipped()) {
                enemy.removeAllImages();
                enemy.addImage(enemy.getAnimation("move")).flipHorizontal();
            }
        } else if (enemy.playerInRange(EnemyAssassin.VISION_RANGE) == -1) {
            enemy.setLinearVelocity(new Vec2 (-enemy.getMoveSpeed(), 0));
            if (enemy.isFlipped()) {
                enemy.removeAllImages();
                enemy.addImage(enemy.getAnimation("move"));
            }
        //if the enemy is out of vision range, return to standing still
        } else if (enemy.playerInRange(EnemyAssassin.VISION_RANGE) == 0){
            gotoState(new StateStandStill());
        }
    }
    
    @Override
    protected void enter() {
        EnemyAssassin enemy = getContext();
        enemy.resetToAnimation("move");
    }

    @Override
    protected void exit() {
    }
}
