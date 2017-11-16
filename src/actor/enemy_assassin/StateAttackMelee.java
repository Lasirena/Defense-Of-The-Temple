package actor.enemy_assassin;

import collision_object.EnemyAssassinHitbox;
import fsm.FSMState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.jbox2d.common.Vec2;

/**
 * One of the EnemyAssassin FSM states which specifies what the enemy should do while it's attacking the player.
 */
public class StateAttackMelee extends FSMState<EnemyAssassin> implements ActionListener {

    private Timer durationTimer;
    private boolean isAttacking;
    private EnemyAssassinHitbox hitbox;
    
    @Override
    protected void update() {
        //if the attack animation duration has ended, return to the follow player state.
        if (!isAttacking) {
            gotoState(new StateFollow());
        }
    }

    @Override
    protected void enter() {
        EnemyAssassin enemy = getContext();
        //stop the enemy and set a timer for the duration of which the attack will be animated
        enemy.setLinearVelocity(new Vec2(0, 0));
        durationTimer = new Timer(1000, this);
        durationTimer.setRepeats(false);
        durationTimer.start();
        
        //create a new separate shape which will act as the enemy's hitbox and will check for collision with the player
        hitbox = new EnemyAssassinHitbox(enemy, enemy.getWorld());
        
        isAttacking = true;
        
        //setting enemy animations and hitbox location based on whether the player is to the right or left of the enemy
        enemy.removeAllImages();
        if (enemy.playerInRange(EnemyAssassin.MELEE_RANGE) == 1) {
            enemy.addImage(enemy.getAnimation("melee")).flipHorizontal();
            hitbox.addRight();
        } else if (enemy.playerInRange(EnemyAssassin.MELEE_RANGE) == -1) {
            enemy.addImage(enemy.getAnimation("melee"));
            hitbox.addLeft();
        }
    }

    @Override
    protected void exit() {
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        isAttacking = false;
    }
    
}
