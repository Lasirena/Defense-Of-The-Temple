package collision_listener;

import city.cs.engine.*;
import collectible.Portal;
import collectible.Scroll;
import game.Game;

/**
 * Collision listener that allows the player to pick up items and enter portals.
 */
public class PickupCollider implements CollisionListener {
    private final Game game;
    
    public PickupCollider(Game game) {
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        // describes collision with a scroll (boosts player damage)
        if (e.getOtherBody() == game.getPlayer() && e.getReportingBody() instanceof Scroll) {
            game.getPlayer().setDamage(game.getPlayer().getDamage() + 15);
            e.getReportingBody().destroy();
            System.out.println("You picked up ancient Psi-Blade schematics! Player damage increased by 15. Current damage: " + game.getPlayer().getDamage());
            game.getActiveWorld().getPortal().makeSolid();
            game.getActiveWorld().getPortal().addCollisionListener(this);
        } else if (e.getOtherBody() == game.getPlayer() && e.getReportingBody() instanceof Portal && game.getPlayer().getWorld().isCompleted()) {
            game.advanceLevel();
        }
    }
    
}
