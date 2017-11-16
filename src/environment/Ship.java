package environment;

import city.cs.engine.*;
import collision_listener.LevelCollider;

/**
 * This class stores the shape an image of a Ship level object. This is a static body used as one of the level structure elements.
 */

public class Ship extends StaticBody {
    
    private static final Shape mainShape = new PolygonShape(-54.3f,-27.4f, 54.6f,-27.4f, 58.7f,-31.0f, 55.4f,-39.6f, -57.8f,-39.1f, -60.6f,-29.4f);
    private static final Shape leftWallShape = new PolygonShape(-56.5f,-9.6f, -51.1f,-27.1f, -58.7f,-33.8f, -64.5f,-16.1f, -65.1f,-6.4f, -60.2f,-2.5f);
    private static final Shape rightWallShape = new PolygonShape(53.9f,-22.7f, 56.5f,-9.4f, 61.2f,-2.5f, 65.1f,-7.7f, 64.3f,-15.9f, 60.0f,-29.0f);
    private static final BodyImage shipImage = new BodyImage("data/environment/longship.png", 80.0f);
    private static final BodyImage sailsImage = new BodyImage("data/environment/longship_sails.png", 80.0f);
    private final World world;
    
    public Ship(World world) {
        super(world, mainShape);
        this.world = world;
        this.addImage(shipImage);
        Fixture leftWall = new SolidFixture(this, leftWallShape);
        Fixture rightWall = new SolidFixture(this, rightWallShape);
        this.addCollisionListener(new LevelCollider(this));
    }
    
    /**
     * Attach the sails of the ship as a separate image. This is done in order to allow other objects to pass in front of the sails.
     */
    public void addSails() {
        Body sails = new StaticBody(world);
        sails.addImage(sailsImage);
        sails.setPosition(this.getPosition());
    }
}
