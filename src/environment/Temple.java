package environment;

import city.cs.engine.*;
import collision_listener.LevelCollider;

/**
 * This class stores the shape an image of a Temple level object. This is a static body used as one of the level structure elements.
 */

public class Temple extends StaticBody {
    private static final Shape MAIN_SHAPE = new PolygonShape(-37.8f,-5.0f, 0.2f,-5.0f, 0.2f,-9.9f, -37.3f,-9.8f);
    private static final Shape UG_SHAPE = new PolygonShape(-0.3f,-7.0f, 17.4f,-7.0f, 17.4f,-12.0f, -0.5f,-12.1f);
    private static final Shape LG_SHAPE = new PolygonShape(12.3f,-11.2f, 36.6f,-11.2f, 36.7f,-13.0f, 12.1f,-13.0f);
    private static final Shape BLOCK_SHAPE = new PolygonShape(-1.5f,-9.0f, -1.7f,20.0f, -10.9f,20.0f, -10.7f,-9.0f);
    private static final BodyImage TEMPLE_IMAGE = new BodyImage("data/environment/temple.png", 50.0f);
    private final World world;
    
    public Temple(World world) {
        super(world, MAIN_SHAPE);
        this.world = world;
        this.addImage(TEMPLE_IMAGE);
        Fixture upperGround = new SolidFixture(this, UG_SHAPE);
        Fixture lowerGround = new SolidFixture(this, LG_SHAPE);
        Fixture blockedEntrance = new SolidFixture(this, BLOCK_SHAPE);
        this.addCollisionListener(new LevelCollider(this));
    }
}
