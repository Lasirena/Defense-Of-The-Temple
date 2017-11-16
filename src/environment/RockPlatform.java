package environment;

import city.cs.engine.*;
import collision_listener.LevelCollider;

/**
 * This class stores the shape and image of a Small Rock Platform level object. This is a static body used as one of the level structure elements.
 */
public class RockPlatform extends StaticBody {
    
    private static final Shape rockPlatformShape = new PolygonShape(-4.67f,1.26f, 4.41f,1.26f, 3.1f,-0.74f, -2.57f,-1.16f);
    private static final BodyImage rockPlatformImage = new BodyImage("data/environment/rockplatform.png", 5.0f);
    
    public RockPlatform(World world) {
        super(world, rockPlatformShape);
        this.addImage(rockPlatformImage);
        this.addCollisionListener(new LevelCollider(this));
    }
}
