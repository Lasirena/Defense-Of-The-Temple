package environment;

import city.cs.engine.*;
import collision_listener.LevelCollider;

/**
 * This class stores the shape an image of a Statue level object. This is a static body used as one of the level structure elements.
 */

public class Statue extends StaticBody {
    
    private static final Shape LEFT_ARM_SHAPE = new PolygonShape(-22.7f,-1.8f, -6.1f,-1.8f, -6.7f,0.4f, -23.0f,0.4f);
    private static final Shape RIGHT_ARM_SHAPE = new PolygonShape(12.0f,6.7f, 12.0f,5.6f, 26.1f,5.4f, 26.0f,6.7f);
    private static final Shape BLADE_SHAPE = new PolygonShape(-8.2f,3.9f, 11.6f,3.9f, 11.6f,5.3f, -9.4f,5.3f);
    private static final BodyImage STATUE_IMAGE = new BodyImage("data/environment/pa_statue.png", 70.0f);
    
    public Statue(World world) {
        super(world, LEFT_ARM_SHAPE);
        this.addImage(STATUE_IMAGE);
        Fixture rightArm = new SolidFixture(this, RIGHT_ARM_SHAPE);
        Fixture blade = new SolidFixture(this, BLADE_SHAPE);
        this.addCollisionListener(new LevelCollider(this));
    }
}
