package collision_listener;

import city.cs.engine.*;
import projectile.PlayerSpellProjectile;

/*
* Collision listener placed on level objects (walls, ground) to destroy player's projectiles when they hit such objects.
*/

public class LevelCollider implements CollisionListener {
    private final Body object;
    
    public LevelCollider(Body object) {
        this.object = object;
    }
    
    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() == object && e.getOtherBody() instanceof PlayerSpellProjectile) {
            e.getOtherBody().destroy();
        }
    }
}
