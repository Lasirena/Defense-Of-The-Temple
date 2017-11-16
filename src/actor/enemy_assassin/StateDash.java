package actor.enemy_assassin;

import city.cs.engine.*;
import fsm.FSMState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.jbox2d.common.Vec2;

/**
 * One of the EnemyAssassin FSM states which specifies what the enemy should do while it's dashing towards the player.
 */
public class StateDash extends FSMState<EnemyAssassin> implements ActionListener {
    
    private Timer durationTimer;
    private boolean isDashing;
    private int direction;
    
    @Override
    protected void update() {
        EnemyAssassin enemy = getContext();
        //for the duration of the dash, set the linear velocity at which the enemy is dashing (direction-based).
        if (direction == 1 && isDashing) {
            enemy.setLinearVelocity(new Vec2(40, 0));
        } else if (direction == -1 && isDashing) {
            enemy.setLinearVelocity(new Vec2(-40, 0));
        //once dash has ended, go back to following the player.
        } else {
            gotoState(new StateFollow());
        }
    }
    
    @Override
    protected void enter() {
        EnemyAssassin enemy = getContext();
        //add directional animation
        enemy.resetToAnimation("dash");
        //put dash on cooldown
        enemy.resetDash();
        enemy.stopWalking();
        //remove the primary shape of the enemy and replace it with an identical ghostly fixture
        enemy.getFixtureList().get(0).destroy();
        GhostlyFixture enemyDodge = new GhostlyFixture(enemy, enemy.getShape());
        enemy.setGravityScale(0);
        //set timer equal to duration of the dash
        durationTimer = new Timer(400, this);
        durationTimer.setRepeats(false);
        durationTimer.start();
        
        isDashing = true;
        if (enemy.playerInRange(EnemyAssassin.DASH_RANGE) == 1) {
            direction = 1;
        } else if (enemy.playerInRange(EnemyAssassin.DASH_RANGE) == -1) {
            direction = -1;
        }
    }

    @Override
    protected void exit() {
        EnemyAssassin enemy = getContext();
        //set the gravity scale back to 1 and make the enemy respond to collisions again
        enemy.setGravityScale(1);
        enemy.getFixtureList().get(0).destroy();
        SolidFixture enemyShape = new SolidFixture(enemy, enemy.getShape());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isDashing = false;
    }
}
