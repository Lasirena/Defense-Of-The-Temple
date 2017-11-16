package projectile;

import city.cs.engine.*;

/**
 * A class for the visible projectiles spawned by the player.
 */
public class PlayerSpellProjectile extends DynamicBody {

    private static final Shape spellShape = new PolygonShape(-0.734f,-0.008f, -0.012f,0.248f, 0.736f,-0.008f, -0.003f,-0.246f);
    private static final BodyImage spellImage =
        new BodyImage("data/animations/projectile.gif", 0.5f);

    /**
     * Construct the projectile.
     * @param world the world in which this body exists.
     */
    public PlayerSpellProjectile(World world) {
        super(world, spellShape);
        addImage(spellImage);
        this.setGravityScale(0);
    }
}
