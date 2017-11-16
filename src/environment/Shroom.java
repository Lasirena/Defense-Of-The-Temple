package environment;

import city.cs.engine.*;
import collision_listener.LevelCollider;

/**
 * This class stores the shape an image of a Mushroom level object. This is a static body used as one of the level structure elements.
 */

public class Shroom extends StaticBody{
    
    private static final Shape SHROOM_SHAPE = new PolygonShape(-18.9f,18.4f, 21.2f,18.4f, 21.2f,9.3f, -18.9f,9.5f);
    private static final BodyImage SHROOM_IMAGE = new BodyImage("data/environment/shroom.png", 50.0f);
    
    public Shroom(World world) {
        super(world, SHROOM_SHAPE);
        this.addImage(SHROOM_IMAGE);
        this.addCollisionListener(new LevelCollider(this));
    }
}
