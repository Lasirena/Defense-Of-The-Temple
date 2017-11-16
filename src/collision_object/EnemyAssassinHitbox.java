package collision_object;

import collision_listener.EnemyAssassinHitboxCollider;
import city.cs.engine.*;
import actor.enemy_assassin.EnemyAssassin;
import game_level.GameLevel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * The hitbox body for EnemyAssassin's melee attacks with its own separate shapes.
 */

public class EnemyAssassinHitbox extends DynamicBody implements ActionListener {
    private static final Shape HITBOX_SHAPE_LEFT = new PolygonShape(-5.42f,-3.96f, -1.96f,-3.94f, -2.05f,1.81f, -5.4f,1.81f);
    private static final Shape HITBOX_SHAPE_RIGHT = new PolygonShape(1.9f,-3.96f, 5.64f,-3.94f, 5.61f,1.87f, 1.84f,1.87f);
    
    private final EnemyAssassin enemy;
    private SolidFixture hitbox;
    private final Timer hitboxDuration;
    
    public EnemyAssassinHitbox(EnemyAssassin enemy, GameLevel world) {
        super(world);
        this.setGravityScale(0);
        this.enemy = enemy;
        this.setPosition(enemy.getPosition());
        this.addCollisionListener(new EnemyAssassinHitboxCollider(this, enemy.getWorld().getPlayer()));
        hitboxDuration = new Timer(1000, this);
        hitboxDuration.setRepeats(false);
        hitboxDuration.start();
    }
    
    /**
     * Adds the hitbox shape to the left of the enemy.
     */
    public void addLeft() {
        hitbox = new SolidFixture(this, HITBOX_SHAPE_LEFT);
    }
    
    /**
     * Adds the hitbox shape to the right of the enemy.
     */
    public void addRight() {
        hitbox = new SolidFixture(this, HITBOX_SHAPE_RIGHT);
    }
    
    /**
     * 
     * @return The damage of the enemy this hitbox belongs to.
     */
    public int getDamage() {
        return enemy.getDamage();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.destroy();
    }
}
