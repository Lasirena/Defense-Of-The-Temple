package environment;

import city.cs.engine.*;
import collision_listener.LevelCollider;

/**
 * This class stores the shape and image of a Large Rock Platform level object. This is a static body used as one of the level structure elements.
 */

public class RockPlatformLarge extends StaticBody {
    private static final Shape LARGE_PLATFORM_SHAPE = new PolygonShape(-4.45f,8.7f, 5.72f,8.7f, 3.22f,-5.96f, -2.09f,-6.1f);
    private static final BodyImage LARGE_PLATFORM_IMAGE = new BodyImage("data/environment/rockplatformlg.png", 20.0f);
    
    public RockPlatformLarge(World world) {
        super(world, LARGE_PLATFORM_SHAPE);
        this.addImage(LARGE_PLATFORM_IMAGE);
        this.addCollisionListener(new LevelCollider(this));
    }
}
