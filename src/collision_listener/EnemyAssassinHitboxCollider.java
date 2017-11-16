package collision_listener;

import city.cs.engine.*;
import collision_object.EnemyAssassinHitbox;
import actor.player.Player;

/**
 * A collision listener implementing collisions between the player and EnemyAssassin's melee hitbox when they initiate an attack on the player.
 */

public class EnemyAssassinHitboxCollider implements CollisionListener {
    
    private final Player player;
    private final EnemyAssassinHitbox hitbox;
    
    public EnemyAssassinHitboxCollider(EnemyAssassinHitbox hitbox, Player player) {
        this.hitbox = hitbox;
        this.player = player;
    }
    
    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() == hitbox && e.getOtherBody() == player) {
            if (player.getCurrentHealth() <= hitbox.getDamage()) {
                player.destroy();
                System.exit(0);
            } else {
                player.setCurrentHealth(player.getCurrentHealth() - hitbox.getDamage());
            }
            hitbox.destroy();
        }
    }
}
