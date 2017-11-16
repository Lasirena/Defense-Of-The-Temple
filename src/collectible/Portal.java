package collectible;

import city.cs.engine.*;

/**
 * A portal which represents an item responsible for transitions between levels in the game and checks collisions like a pickup object.
 */

public class Portal extends DynamicBody {
    private static final Shape PORTAL_SHAPE = new PolygonShape(-0.41f,3.78f, -1.79f,1.17f, -1.73f,-1.5f, -0.39f,-4.0f, 0.99f,-1.3f, 1.08f,1.37f);
    
    //scroll's animated image
    private static final BodyImage PORTAL_IMAGE = new BodyImage("data/collectibles/portal.png", 10.0f);
    
    public Portal(World world) {
        super(world, PORTAL_SHAPE);
        this.setGravityScale(0);
        addImage(PORTAL_IMAGE);
    }
    
    /**
     * Turns the portal into a ghostly fixture making sure that nothing can collide with it.
     */
    public void makeGhostly() {
        this.getFixtureList().get(0).destroy();
        Fixture portalShape = new GhostlyFixture(this, PORTAL_SHAPE);
    }
    
    /**
     * Turns the portal into a solid fixture enabling collision with the player (and other objects).
     */
    public void makeSolid() {
        this.getFixtureList().get(0).destroy();
        Fixture portalShape = new SolidFixture(this, PORTAL_SHAPE);
    }
}
