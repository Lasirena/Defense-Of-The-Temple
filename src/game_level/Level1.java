package game_level;

import timer.PlayerManaRegenTimer;
import collision_listener.PickupCollider;
import environment.*;
import collectible.Portal;
import game.*;
import org.jbox2d.common.Vec2;

/**
 * Level 1 of the game.
 * @author Lanaya
 */
public class Level1 extends GameLevel{
    
    private Portal portal;

    public Level1() {
        
    }
    
    @Override
    public void populate (Game game) {
        super.populate(game);
        PlayerManaRegenTimer playerRegen = new PlayerManaRegenTimer(this.getPlayer());
        this.addStepListener(playerRegen);
        playerRegen.setRegenRate(500);
        
        portal = new Portal(this);
        portal.setPosition(new Vec2(151, 8));
        portal.addCollisionListener(new PickupCollider(game));
        
        //create environment objects and set their positions in the world
        RockPlatformLarge platform1 = new RockPlatformLarge(this);
        platform1.setPosition(new Vec2(50, -15));
        
        RockPlatform platform2 = new RockPlatform(this);
        platform2.setPosition(new Vec2(70, -3));
        
        RockPlatform platform3 = new RockPlatform(this);
        platform3.setPosition(new Vec2(130, -3));
        
        RockPlatformLarge platform4 = new RockPlatformLarge(this);
        platform4.setPosition(new Vec2(150, -5.5f));

        Temple temple = new Temple(this);
        temple.setPosition(new Vec2(0,0));
        //end of creating environment objects
    }
    
    @Override
    public Vec2 getStartPosition() {
        return new Vec2(0,-2);
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
    
    @Override
    public String getAssetsFolder() {
        return "level1";
    }

    @Override
    public String getObjectives() {
        return "Find a portal to the Veiled Sisters' Temple.";
    }
    
    @Override
    public Portal getPortal() {
        return portal;
    }
}
