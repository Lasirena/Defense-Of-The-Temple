package collectible;

import city.cs.engine.*;

/*
* One of the collectibles in the game which boosts the player's damage.
*/

public class Scroll extends DynamicBody {
    // using a simple box shape for the scroll to avoid unnecessary load in collision detection
    private static final Shape scrollShape = new BoxShape(0.5f, 0.5f);
    
    //scroll's animated image
    private static final BodyImage scrollImage = new BodyImage("data/collectibles/anim_collectible_scroll.gif", 2.0f);
    
    public Scroll(World world) {
        super(world, scrollShape);
        this.setGravityScale(0);
        addImage(scrollImage);
    }
}
